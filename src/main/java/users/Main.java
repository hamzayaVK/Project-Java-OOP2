package users;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String []args)
    {
        Courier courier = new Courier();
        courier.setFirstname("Danidsads");
        courier.setFamilyname("Todorsdfs");

        Configuration configuration = new org.hibernate.cfg.Configuration().addAnnotatedClass(Courier.class);
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(courier);
        tx.commit();
        session.close();
    }
}
