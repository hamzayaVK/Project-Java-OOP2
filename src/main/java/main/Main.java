package main;

import Services.AddressService;
import Services.AdministratorService;
import Services.CourierCompanyService;
import Services.CourierOfficeService;
import address.Address;
import couriercompany.CourierCompany;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import users.Administrator;

import java.util.List;


public class Main extends Application {
    public static void main(String []args)
    {
//        AdministratorService administratorService = new AdministratorService();
//        administratorService.createAdministrator("admin","admin",0,0);
//        List<Administrator> administratorList = administratorService.displayRecords();
//        for (Administrator administrator :
//                administratorList) {
//            System.out.println(administrator.toString());
//        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}