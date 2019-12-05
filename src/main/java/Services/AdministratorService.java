package Services;

import couriercompany.CourierCompany;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import users.Administrator;
import java.util.ArrayList;
import java.util.List;

public class AdministratorService {

    private static Logger logger = Logger.getLogger(AdministratorService.class);

    public static void createAdministrator(String username, String password, CourierCompany courierCompany)
    {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        Transaction tx = session.beginTransaction();
        Administrator administrator = new Administrator();
        administrator.setUsername(username);
        administrator.setPassword(password);
        administrator.setCourierCompany(courierCompany);
        session.save(administrator);
        tx.commit();
        session.close();
    }
    public static List displayRecords() {
        List<Administrator> administratorList = new ArrayList<>();
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            administratorList = session.createQuery("FROM Administrator",Administrator.class).getResultList();
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
        return administratorList;
    }
    public static void updateRecord(int id, String username, String password, CourierCompany courierCompany) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            // Creating Transaction Entity
            Administrator administrator = session.get(Administrator.class, id);
            administrator.setUsername(username);
            administrator.setPassword(password);
            administrator.setCourierCompany(courierCompany);
            // Committing The Transactions To The Database
            session.getTransaction().commit();
            logger.info("\nAdministrator With Id?= " + administrator.getId() + " Is Successfully Updated In The Database!\n");
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

    public static Administrator findRecordById(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        Administrator administrator = null;
        try {
            session.beginTransaction();
            administrator = session.load(Administrator.class, id);
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        }
        session.close();
        return administrator;
    }

    public static void deleteRecord(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            Administrator administrator = findRecordById(id);
            session.delete(administrator);
            session.getTransaction().commit();
            logger.info("\nAdministrator With Id?= " + administrator.getId() + " Is Successfully Deleted From The Database!\n");
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
