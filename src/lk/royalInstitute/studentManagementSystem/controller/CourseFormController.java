package lk.royalInstitute.studentManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.royalInstitute.studentManagementSystem.bo.BOFactory;
import lk.royalInstitute.studentManagementSystem.bo.SuperBO;
import lk.royalInstitute.studentManagementSystem.bo.registration.CourseBO;
import lk.royalInstitute.studentManagementSystem.dto.CourseDTO;
import lk.royalInstitute.studentManagementSystem.view.tm.ProgramTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseFormController implements Initializable {

    @FXML
    private TextField txtProgramID;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private JFXButton btnSaveProgram;

    @FXML
    private TableView<ProgramTM> tblProgram;

    @FXML
    private TableColumn<ProgramTM, String> colProgramID;

    @FXML
    private TableColumn<ProgramTM, String> colProgramName;

    @FXML
    private TableColumn<ProgramTM, String> colDuration;

    @FXML
    private TableColumn<ProgramTM, Double> colFee;

    @FXML
    private JFXButton btnSeeRegisteredStudent;

    @FXML
    private JFXButton btnRemoveProgram;

    ObservableList<ProgramTM> programTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeTable();
        loadAllPrograms();

    }

    private void makeTable() {
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblProgram.setItems(programTMS);
    }

    @FXML
    void saveProgram(ActionEvent event) {
        CourseDTO courseDTO = makeCourseDTO();
        ProgramTM programTM = makeProgramTM(courseDTO);
        CourseBO courseBO = (CourseBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.Course);
        try {
            boolean saved = courseBO.addCourse(courseDTO);
            if (saved) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Course");
                alert.setHeaderText(null);
                alert.setContentText("Course Saved successfully !");
                alert.showAndWait();
                programTMS.add(programTM);
                tblProgram.refresh();
                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Course Save Failed");
                alert.setHeaderText(null);
                alert.setContentText("Please Check the Values !");
                alert.showAndWait();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void clearFields() {
        txtCourseFee.clear();
        txtProgramName.clear();
        txtProgramID.clear();
        txtDuration.clear();
    }

    private CourseDTO makeCourseDTO() {
        return new CourseDTO(
                txtProgramID.getText(),
                txtProgramName.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtCourseFee.getText()));
    }

    private ProgramTM makeProgramTM(CourseDTO courseDTO) {
        return new ProgramTM(
                courseDTO.getCode(),
                courseDTO.getCourseName(),
                courseDTO.getDuration(),
                courseDTO.getCourseFee());
    }

    private void loadAllPrograms() {
        CourseBO courseBO = (CourseBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.Course);
        try {
            List<CourseDTO> allCoursesDTOs = courseBO.getAll();
            for (CourseDTO courseDTO :
                    allCoursesDTOs) {
                programTMS.add(makeProgramTM(courseDTO));
            }
            tblProgram.refresh();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void removeProgram(ActionEvent event) {
        if (tblProgram.getSelectionModel().getSelectedItem() != null) {
            ProgramTM selectedProgram = tblProgram.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Program");
            alert.setHeaderText("Are you sure ?");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnYes, btnCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btnYes) {
                CourseBO courseBO = (CourseBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.Course);
                try {
                    boolean deleteCourse = courseBO.deleteCourse(makeCourseDTO(selectedProgram));
                    if (deleteCourse){
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Course");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Course is removed !");
                        alert1.showAndWait();
                        programTMS.remove(selectedProgram);
                        tblProgram.refresh();
                    }else {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Error");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Course remove Failed !");
                        alert1.showAndWait();
                        programTMS.remove(selectedProgram);
                        tblProgram.refresh();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a program !");
            alert.showAndWait();
        }
    }

    private CourseDTO makeCourseDTO(ProgramTM selectedProgram) {
        return new CourseDTO(selectedProgram.getProgramID(),
                selectedProgram.getProgram(),
                selectedProgram.getDuration(),
                selectedProgram.getFee());
    }

    @FXML
    void seeRegisteredStudent(ActionEvent event) {

    }
}
