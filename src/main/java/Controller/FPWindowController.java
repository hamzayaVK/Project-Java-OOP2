package Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.hibernate.Transaction;
import singletonconfig.SingletonConfig;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import POJO.Administrator;
import POJO.Client;
import POJO.Courier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FPWindowController implements Initializable {

    @FXML
    private TextField loginUsernameText;

    @FXML
    private TextField fpPasswordText;

    @FXML
    private ChoiceBox accountType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountType.getItems().addAll(FXCollections.observableArrayList("client","courier","admin"));
    }

    @FXML
    void RetrievePassword(ActionEvent event) {
        String username = loginUsernameText.getText();
        org.hibernate.Session session = SingletonConfig.getInstance().sf.openSession();
        Transaction tx = session.beginTransaction();
        List<Administrator> administratorList = new ArrayList<>();
        List<Courier> courierList = new ArrayList<>();
        List<Client> clientList = new ArrayList<>();
        if (accountType.getValue() == "admin") {
            administratorList = session.createQuery("FROM Administrator WHERE username = :username", Administrator.class).setParameter("username", username).getResultList();
        }
        else if (accountType.getValue() == "courier") {
            courierList = session.createQuery("FROM Courier WHERE username = :username", Courier.class).setParameter("username",username).getResultList();
        }
        else if (accountType.getValue() == "client") {
            clientList = session.createQuery("FROM Client WHERE username = :username", Client.class).setParameter("username",username).getResultList();
        }
        try {
            if(!administratorList.isEmpty()) {
                fpPasswordText.setText(administratorList.get(0).getPassword());
            }
            else if(!courierList.isEmpty()) {
                fpPasswordText.setText(courierList.get(0).getPassword());
            }
            else {
                fpPasswordText.setText(clientList.get(0).getPassword());
            }
        }
        catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Account with that name isn't present in the Database!");
            alert.show();
        }
        session.close();
    }
    @FXML
    void ReturnToLoginScreen(ActionEvent event) throws Exception{
        Parent login = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(login));
    }

}

