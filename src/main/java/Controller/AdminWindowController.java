package Controller;

import Services.*;
import address.Address;
import couriercompany.CourierCompany;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import users.Courier;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminWindowController implements Initializable {

    @FXML
    private ChoiceBox<Object> existingOfficeCB;

    @FXML
    private ChoiceBox<Object> existingCompanyCB;

    @FXML
    private TextField officeCity;

    @FXML
    private TextField officeDistrict;

    @FXML
    private TextField companyCity;

    @FXML
    private TextField companyDistrict;

    @FXML
    private ChoiceBox<Object> officeExistingCompanyCB;

    @FXML
    private TextField administratorUsername;

    @FXML
    private TextField administratorPassword;

    @FXML
    private ChoiceBox<Object> administratorExistingCompanyCB;

    @FXML
    private TextField courierUsername;

    @FXML
    private TextField courierPassword;

    @FXML
    private TextField courierFirstName;

    @FXML
    private TextField courierLastName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        existingOfficeCB.getItems().add(CourierCompanyService.displayRecords());
        existingCompanyCB.getItems().addAll(CourierCompanyService.displayRecords());
        officeExistingCompanyCB.getItems().addAll(CourierCompanyService.displayRecords());
        administratorExistingCompanyCB.getItems().addAll(CourierCompanyService.displayRecords());
    }

    @FXML
    void logoutAdminPanel(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(login));
    }

    @FXML
    void createOffice() {
        Address address = new Address(officeCity.getText(), officeDistrict.getText());
        AddressService.createAddress(address);
        CourierOfficeService.createCourierOffice(AddressService.findRecordById(address.getId()), (CourierCompany) officeExistingCompanyCB.getSelectionModel().getSelectedItem());
    }

    @FXML
    void updateOfficeExistingCompanyCB() {
        officeExistingCompanyCB.getItems().clear();
        officeExistingCompanyCB.getItems().addAll(CourierCompanyService.displayRecords());
    }

    @FXML
    void updateExistingOfficeCB() {
        existingOfficeCB.getItems().clear();
        existingOfficeCB.getItems().addAll(CourierOfficeService.displayRecords());
    }

    @FXML
    void createCompany() {
        Address address = new Address(companyCity.getText(), companyDistrict.getText());
        AddressService.createAddress(address);
        CourierCompanyService.createCourierCompany(address);
    }

    @FXML
    void updateExistingCompanyCB() {
        existingCompanyCB.getItems().clear();
        existingCompanyCB.getItems().addAll(CourierCompanyService.displayRecords());
    }

    @FXML
    void createAdministrator() {
        AdministratorService.createAdministrator(administratorUsername.getText(),administratorPassword.getText(), (CourierCompany) administratorExistingCompanyCB.getSelectionModel().getSelectedItem());
    }

    @FXML
    void updateAdministratorCompanyCB(){
        administratorExistingCompanyCB.getItems().clear();
        administratorExistingCompanyCB.getItems().addAll(CourierCompanyService.displayRecords());
    }

    @FXML
    void createCourier() {
        CourierService.createCourier(new Courier(courierUsername.getText(),courierPassword.getText(),courierFirstName.getText(),courierLastName.getText()));
    }
}
