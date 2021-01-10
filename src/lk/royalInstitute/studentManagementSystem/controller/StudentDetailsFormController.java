package lk.royalInstitute.studentManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.royalInstitute.studentManagementSystem.bo.BOFactory;
import lk.royalInstitute.studentManagementSystem.bo.registration.CourseBO;
import lk.royalInstitute.studentManagementSystem.bo.registration.RegistrationBO;
import lk.royalInstitute.studentManagementSystem.dto.CourseDTO;
import lk.royalInstitute.studentManagementSystem.dto.RegistrationDTO;
import lk.royalInstitute.studentManagementSystem.entity.Registration;
import lk.royalInstitute.studentManagementSystem.view.tm.StudentDetailsTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentDetailsFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbTrainingProgram;

    @FXML
    private JFXTextField txtProgramID;

    @FXML
    private JFXTextField txtStudentID;

    @FXML
    private TableView<StudentDetailsTM> tblStudentDetails;

    @FXML
    private TableColumn<StudentDetailsTM, String> colStudentID;

    @FXML
    private TableColumn<StudentDetailsTM, String> colRegNo;

    @FXML
    private TableColumn<StudentDetailsTM, String> colStudentName;

    @FXML
    private TableColumn<StudentDetailsTM, String> colProgramName;

    @FXML
    private TableColumn<StudentDetailsTM, String> colAddress;

    @FXML
    private TableColumn<StudentDetailsTM, String> colContact;

    @FXML
    private TableColumn<StudentDetailsTM, String> colDOB;

    @FXML
    private TableColumn<StudentDetailsTM, String> colGender;

    @FXML
    private JFXButton btnStudentInAll;


    ObservableList<StudentDetailsTM> studentDetailsTMS= FXCollections.observableArrayList();

    List<CourseDTO> courseDTOS=null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("RegNo"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dOB"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudentDetails.setItems(studentDetailsTMS);
        loadAllCourses();
    }

    private void loadAllCourses() {
        CourseBO courseBO= (CourseBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.Course);
        try {
            List<CourseDTO> courseDTOS = courseBO.getAll();
            for (CourseDTO courseDTO :courseDTOS) {
                cmbTrainingProgram.getItems().add(courseDTO.getCourseName());
            }
            this.courseDTOS=courseDTOS;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void cmdSelectProgram(ActionEvent event) {
        String course = cmbTrainingProgram.getSelectionModel().getSelectedItem();
        CourseDTO selectedCourseDTO = null;
        for (CourseDTO courseDTO : courseDTOS) {
            if (!course.isEmpty() && courseDTO.getCourseName().compareToIgnoreCase(course) == 0) {
                selectedCourseDTO = courseDTO;
            }
        }
        RegistrationBO registrationBO= (RegistrationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.Registration);
        try {
            if (selectedCourseDTO != null) {
                List<RegistrationDTO> allRegistration = registrationBO.getAllRegistration(selectedCourseDTO.getCode());
                studentDetailsTMS.clear();
                for (RegistrationDTO registrationDTO : allRegistration) {
                    studentDetailsTMS.add(makeStudentDetails(registrationDTO,selectedCourseDTO));
                    tblStudentDetails.refresh();
                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    private StudentDetailsTM makeStudentDetails(RegistrationDTO registrationDTO, CourseDTO selectedCourseDTO) {
        StudentDetailsTM studentDetailsTM= new StudentDetailsTM();
        studentDetailsTM.setRegNo(registrationDTO.getRegNo());
        studentDetailsTM.setProgramName(selectedCourseDTO.getCourseName());
        studentDetailsTM.setStudentID(registrationDTO.getStudentDTO().getId());
        studentDetailsTM.setStudentName(registrationDTO.getStudentDTO().getStudentName());
        studentDetailsTM.setAddress(registrationDTO.getStudentDTO().getAddress());
        studentDetailsTM.setContact(registrationDTO.getStudentDTO().getContact());
        studentDetailsTM.setGender(registrationDTO.getStudentDTO().getGender());
        studentDetailsTM.setdOB(registrationDTO.getStudentDTO().getDate());
        return studentDetailsTM;
    }

    @FXML
    void seeAll(ActionEvent event) {

    }

}
