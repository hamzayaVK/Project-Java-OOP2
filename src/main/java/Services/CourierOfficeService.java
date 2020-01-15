package Services;

import POJO.Address;
import POJO.CourierCompany;
import POJO.CourierOffice;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import java.util.ArrayList;
import java.util.List;

public class CourierOfficeService {

    private static Logger logger = Logger.getLogger(CourierOfficeService.class);

    public static void createCourierOffice(Address address, CourierCompany courierCompany)
    {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        Transaction tx = session.beginTransaction();
        CourierOffice courierOffice = new CourierOffice();
        courierOffice.setAddress(address);
        courierOffice.setCourierCompany(courierCompany);
        session.save(courierOffice);
        tx.commit();
        session.close();
    }
    public static List displayRecords() {
        List<CourierOffice> courierOfficeList = new ArrayList<>();
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            courierOfficeList = session.createQuery("FROM CourierOffice",CourierOffice.class).getResultList();
        } catch(HibernateException hibernateException ) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return courierOfficeList;
    }
    public static void updateRecord(int id,Address address,int companyId) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            // Creating Transaction Entity
            CourierOffice courierOffice = session.get(CourierOffice.class, id);
            courierOffice.setAddress(address);
            courierOffice.setCourierCompany(session.get(CourierCompany.class, companyId));
            // Committing The Transactions To The Database
            session.getTransaction().commit();
            logger.info("\nStudent With Id?= " + courierOffice.getId() + " Is Successfully Updated In The Database!\n");
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

    public static CourierOffice findRecordById(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        CourierOffice courierOffice = null;
        try {
            session.beginTransaction();
            courierOffice = session.load(CourierOffice.class, id);
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        }
        session.close();
        return courierOffice;
    }

    public static void deleteRecord(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            CourierOffice courierOffice = findRecordById(id);
            session.delete(courierOffice);
            session.getTransaction().commit();
            logger.info("\nCourier Office With Id?= " + courierOffice.getId() + " Is Successfully Deleted From The Database!\n");
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
