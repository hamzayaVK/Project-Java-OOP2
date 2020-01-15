package Services;

import POJO.CourierOffice;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import POJO.Courier;
import java.util.ArrayList;
import java.util.List;

public class CourierService {

    private static Logger logger = Logger.getLogger(CourierService.class);

    public static void createCourier(Courier courier)
    {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(courier);
        tx.commit();
        session.close();
    }
    public static List<Courier> displayRecords() {
        List<Courier> courierList = new ArrayList<>();
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            courierList = session.createQuery("FROM Courier",Courier.class).getResultList();
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return courierList;
    }
    public static void updateRecord(int id, String firstName, String familyName,String username, String password, CourierOffice courierOffice) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            // Creating Transaction Entity
            Courier courier = session.get(Courier.class, id);
            courier.setUsername(username);
            courier.setPassword(password);
            courier.setFirstname(firstName);
            courier.setFamilyname(familyName);
            courier.setCourierOffice(courierOffice);
            // Committing The Transactions To The Database
            session.getTransaction().commit();
            logger.info("\nCourier With Id?= " + courier.getId() + " Is Successfully Updated In The Database!\n");
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public static Courier findRecordById(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        Courier courier = null;
        try {
            session.beginTransaction();
            courier = session.load(Courier.class, id);
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        }
        session.close();
        return courier;
    }

    public static void deleteRecord(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            Courier courier = findRecordById(id);
            session.delete(courier);
            session.getTransaction().commit();
            logger.info("\nCourier With Id?= " + courier.getId() + " Is Successfully Deleted From The Database!\n");
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
