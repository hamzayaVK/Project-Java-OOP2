package Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import POJO.Administrator;
import POJO.Client;
import POJO.Courier;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class LoginWindowController implements Initializable {

    private static Logger logger = Logger.getLogger(LoginWindowController.class);

    @FXML
    private TextField loginUsernameText;

    @FXML
    private TextField loginPasswordText;

    @FXML
    private ChoiceBox choiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(FXCollections.observableArrayList("client","courier","admin"));
    }

    @FXML
    void fpOpenNewFPScene(ActionEvent event) throws Exception{
        Parent fp = FXMLLoader.load(getClass().getResource("/forgotten_password.fxml"));
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(fp));
    }

    @FXML
    void loginCheckInfoDB(ActionEvent event) {
        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        try (session) {
            Transaction tx = session.beginTransaction();
            if (choiceBox.getValue() == "admin") {
                List<Administrator> administratorAccountList = session.createQuery("FROM Administrator WHERE username = :username AND password = :password", Administrator.class).setParameter("username", username).setParameter("password", password).getResultList();
                Administrator administrator = administratorAccountList.get(0);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login success " + administrator.getUsername() + "!");
                alert.show();
                Parent fp = FXMLLoader.load(getClass().getResource("/admin_panel.fxml"));
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(new Scene(fp));

            } else if (choiceBox.getValue() == "courier") {
                List<Courier> courierAccountList = session.createQuery("FROM Courier WHERE username = :username AND password = :password", Courier.class).setParameter("username", username).setParameter("password", password).getResultList();
                Courier courier = courierAccountList.get(0);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login success " + courier.getUsername() + "!");
                alert.show();
            } else if (choiceBox.getValue() == "client") {
                List<Client> clientAccountList = session.createQuery("FROM Client WHERE username = :username AND password = :password", Client.class).setParameter("username", username).setParameter("password", password).getResultList();
                Client client = clientAccountList.get(0);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login success " + client.getUsername() + "!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Choose the correct user type from the choice box!");
                alert.show();
            }
        } catch (Throwable throwable) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login failed, Invalid credentials/non-existing account!");
            alert.show();
            logger.info("Login failed, Invalid credentials/non-existing account!", throwable);
        }
    }

}
