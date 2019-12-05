package Services;

import address.Address;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import users.Client;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static Logger logger = Logger.getLogger(ClientService.class);

    public void createAccount(Client client, Address address)
    {
        client.setAddress(address);
        Session session = SingletonConfig.getInstance().sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(client);
        tx.commit();
        session.close();
    }

    public static List displayRecords() {
        List<Client> clientList = new ArrayList<>();
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            clientList = session.createQuery("FROM Client",Client.class).getResultList();
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
        return clientList;
    }

    public static void updateRecord(int id,String firstName, String middleName, String familyName, String username, String password, Address address) {
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try {
            session.beginTransaction();
            // Creating Transaction Entity
            Client client = session.get(Client.class, id);
            client.setUsername(username);
            client.setPassword(password);
            client.setFirstname(firstName);
            client.setFamilyname(familyName);
            client.setMiddlename(middleName);
            client.setAddress(address);
            // Committing The Transactions To The Database
            session.getTransaction().commit();
            logger.info("\nClient With Id?= " + client.getId() + " Is Successfully Updated In The Database!\n");
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

    public static Client findRecordById(int id) {
        SingletonConfig singletonConfig = SingletonConfig.getInstance();
        org.hibernate.Session session = singletonConfig.sf.openSession();
        Client client = null;
        try {
            session.beginTransaction();
            client = session.load(Client.class, id);
        } catch(HibernateException hibernateException) {
            if(null != session.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n",hibernateException);
                session.getTransaction().rollback();
            }
        }
        session.close();
        return client;
    }

    public static void deleteRecord(int id) {
        SingletonConfig singletonConfig = SingletonConfig.getInstance();
        org.hibernate.Session session = singletonConfig.sf.openSession();
        try {
            session.beginTransaction();
            Client client = findRecordById(id);
            session.delete(client);
            session.getTransaction().commit();
            logger.info("\nClient With Id?= " + client.getId() + " Is Successfully Deleted From The Database!\n");
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
