package lk.royalInstitute.studentManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private BorderPane main;

    @FXML
    private Button btnRegistration;

    @FXML
    private Button btnStudentDetails;

    @FXML
    private Button btnPrograms;

    @FXML
    private BorderPane display;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void loadDashboard(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/royalInstitute/studentManagementSystem/view/Dashboard.fxml"));
            main.setBackground(Background.EMPTY);
            main.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadProgramForm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/royalInstitute/studentManagementSystem/view/CourseForm.fxml"));
            main.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadRegistrationFrom(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/royalInstitute/studentManagementSystem/view/StudentRegistrationForm.fxml"));
            main.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadStudentDetailsForm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/royalInstitute/studentManagementSystem/view/StudentDetailsForm.fxml"));
            main.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
