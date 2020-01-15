package Services;

import POJO.Address;
import POJO.CourierCompany;
import POJO.CourierOffice;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import java.util.ArrayList;
import java.util.List;

public class CourierCompanyService {

    private static Logger logger = Logger.getLogger(ClientService.class);

    public static void createCourierCompany(Address address) {
        CourierCompany courierCompany = new CourierCompany();
        courierCompany.setAddress(address);
        Session session = SingletonConfig.getInstance().sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(courierCompany);
        tx.commit();
        session.close();
    }

    public static List<CourierCompany> displayRecords() {
        List<CourierCompany> courierCompanyList = new ArrayList<>();
        Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            courierCompanyList = session.createQuery("FROM CourierCompany",CourierCompany.class).getResultList();
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return courierCompanyList;
    }

    public static void updateRecord(int id,Address address, CourierOffice courierOffice) {
        Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            // Creating Transaction Entity
            CourierCompany courierCompany = session.get(CourierCompany.class, id);
            courierCompany.setAddress(address);
            courierCompany.getCourierOfficeList().add(CourierOfficeService.findRecordById(courierOffice.getId()));
            // Committing The Transactions To The Database
            session.getTransaction().commit();
            logger.info("\nCourier Company With Id?= " + courierCompany.getId() + " Is Successfully Updated In The Database!\n");
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public static CourierCompany findRecordById(int id) {
        Session session = SingletonConfig.getInstance().sf.openSession();
        CourierCompany courierCompany = null;
        try {
            session.beginTransaction();
            courierCompany = session.load(CourierCompany.class, id);
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
        }
        session.close();
        return courierCompany;
    }

    public static void deleteRecord(int id) {
        Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            CourierCompany courierCompany = findRecordById(id);
            session.delete(courierCompany);
            session.getTransaction().commit();
            logger.info("\nCourier Company With Id?= " + courierCompany.getId() + " Is Successfully Deleted From The Database!\n");
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
