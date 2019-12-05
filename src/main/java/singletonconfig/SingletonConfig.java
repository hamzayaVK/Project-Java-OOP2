package singletonconfig;

import address.Address;
import couriercompany.CourierCompany;
import couriercompany.CourierOffice;
import order_package.Orders;
import order_package.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import users.Administrator;
import users.Client;
import users.Courier;

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
