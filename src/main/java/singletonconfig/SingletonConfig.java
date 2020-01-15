package singletonconfig;

import POJO.Address;
import POJO.CourierCompany;
import POJO.CourierOffice;
import POJO.Orders;
import POJO.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import POJO.Administrator;
import POJO.Client;
import POJO.Courier;

public class SingletonConfig {
    private static SingletonConfig single_instance = null;
    private Configuration configuration;
    public SessionFactory sf;
    private SingletonConfig()
    {
        configuration = new org.hibernate.cfg.Configuration().addAnnotatedClass(Courier.class).addAnnotatedClass(CourierOffice.class).addAnnotatedClass(Address.class).addAnnotatedClass(CourierCompany.class).addAnnotatedClass(Orders.class).addAnnotatedClass(Product.class).addAnnotatedClass(Administrator.class).addAnnotatedClass(Client.class);
        sf = configuration.buildSessionFactory();
    }
    public static SingletonConfig getInstance()
    {
        if(single_instance == null)
            single_instance = new SingletonConfig();

        return single_instance;
    }
}
