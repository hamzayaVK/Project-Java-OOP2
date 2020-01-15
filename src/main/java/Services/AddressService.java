package Services;

import POJO.Address;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import java.util.ArrayList;
import java.util.List;

public class AddressService {

    private static Logger logger = Logger.getLogger(AddressService.class);

    public static void createAddress(Address address)
    {
        Session session = SingletonConfig.getInstance().sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
        session.close();
    }

    public static List displayRecords() {
        List<Address> accountList = new ArrayList<>();
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            accountList = session.createQuery("FROM Address",Address.class).getResultList();
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
        return accountList;
    }

    public static void updateRecord(int id,String city, String district) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            // Creating Transaction Entity
            Address address = session.get(Address.class, id);
            address.setCity(city);
            address.setDistrict(district);
            // Committing The Transactions To The Database
            session.getTransaction().commit();
            logger.info("\nAddress With Id?= " + address.getId() + " Is Successfully Updated In The Database!\n");
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

    public static Address findRecordById(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        Address address = null;
        try {
            session.beginTransaction();
            address = session.load(Address.class, id);
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        }
        session.close();
        return address;
    }

    public static void deleteRecord(int id) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            Address address = findRecordById(id);
            session.delete(address);
            session.getTransaction().commit();
            logger.info("\nAddress With Id?= " + address.getId() + " Is Successfully Deleted From The Database!\n");
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
