package lk.royalInstitute.studentManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.royalInstitute.studentManagementSystem.bo.BOFactory;
import lk.royalInstitute.studentManagementSystem.bo.registration.CourseBO;
import lk.royalInstitute.studentManagementSystem.bo.registration.RegistrationBO;
import lk.royalInstitute.studentManagementSystem.bo.registration.RegistrationFillingBO;
import lk.royalInstitute.studentManagementSystem.dto.CourseDTO;
import lk.royalInstitute.studentManagementSystem.dto.RegistrationDTO;
import lk.royalInstitute.studentManagementSystem.dto.StudentDTO;
import lk.royalInstitute.studentManagementSystem.entity.Student;
import lk.royalInstitute.studentManagementSystem.view.tm.ProgramTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentRegistrationFormController implements Initializable {

    @FXML
    private TextField txtStudentID;

    @FXML
    private TextField txtRegNo;

    @FXML
    private JFXDatePicker dtAddedDate;

    @FXML
    private TextField txtName;

    @FXML
    private JFXDatePicker dtDOB;

    @FXML
    private RadioButton rabMale;

    @FXML
    private RadioButton rabFemale;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private JFXComboBox<String> cmbTrainingProgram;

    @FXML
    private JFXTextField txtProgramID;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtFee;

    @FXML
    private JFXButton btnAddProgram;

    @FXML
    private TableView<ProgramTM> tblProgram;

    @FXML
    private TableColumn<ProgramTM, String> colProgramID;

    @FXML
    private TableColumn<ProgramTM, String> colProgram;

    @FXML
    private TableColumn<ProgramTM, String> colDuration;

    @FXML
    private TableColumn<ProgramTM, Double> colFee;

    @FXML
    private JFXTextField txtTotalFee;

    @FXML
    private JFXButton btnRemoveProgram;

    @FXML
    private JFXButton btnRegister;

    ObservableList<ProgramTM> programTMS = FXCollections.observableArrayList();
    List<CourseDTO> courseDTOS = null;
    CourseDTO selectedCourseDTO = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeTable();
        makeToggleGroup();
        loadAllCourse();
        programTMS.addListener((ListChangeListener.Change<? extends ProgramTM> c) -> {
            double fee = 0;
            for (ProgramTM programTM : programTMS) {
                fee += Double.parseDouble(String.valueOf(programTM.getFee()));
            }
            txtTotalFee.setText(String.valueOf(fee));
        });
        dtAddedDate.setValue(LocalDate.now());

    }

    private void loadAllCourse() {
        CourseBO courseBO = (CourseBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.Course);
        try {
            List<CourseDTO> courseDTOS = courseBO.getAll();
            this.courseDTOS = courseDTOS;
            for (CourseDTO course :
                    courseDTOS) {
                cmbTrainingProgram.getItems().add(course.getCourseName());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void makeToggleGroup() {
        ToggleGroup tgGender = new ToggleGroup();
        rabMale.setToggleGroup(tgGender);
        rabFemale.setToggleGroup(tgGender);
    }

    private void makeTable() {
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblProgram.setItems(programTMS);
    }

    @FXML
    void btnAddProgram(ActionEvent event) {
        if (selectedCourseDTO != null) {
            ProgramTM programTM = new ProgramTM(
                    selectedCourseDTO.getCode(),
                    selectedCourseDTO.getCourseName(),
                    selectedCourseDTO.getDuration(),
                    selectedCourseDTO.getCourseFee()
            );
            programTMS.add(programTM);
            tblProgram.refresh();
            courseDTOS.remove(selectedCourseDTO);
            cmbTrainingProgram.getItems().clear();
            for (CourseDTO course : courseDTOS) {
                cmbTrainingProgram.getItems().add(course.getCourseName());
            }
            clearProgramFields();
            selectedCourseDTO = null;
        }
    }

    private void clearProgramFields() {
        txtProgramID.clear();
        txtDuration.clear();
        txtFee.clear();
    }

    private StudentDTO makeStudentDTO() {
        StudentDTO temp = new StudentDTO();
        temp.setId(txtStudentID.getText());
        temp.setStudentName(txtName.getText());
        temp.setAddress(txtAddress.getText());
        temp.setContact(txtMobileNumber.getText());
        temp.setDate(String.valueOf(dtDOB.getValue()));
        if (rabMale.isSelected()) {
            temp.setGender("Male");
        }
        if (rabFemale.isSelected()) {
            temp.setGender("Female");
        }
        return temp;
    }

    @FXML
    void btnRegister(ActionEvent event) {
        StudentDTO studentDTO = makeStudentDTO();
        List<CourseDTO> selectedCourseDTOs = new ArrayList<>();
        for (ProgramTM programTM : programTMS) {
            selectedCourseDTOs.add(makeCourseDTO(programTM));
        }
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        for (CourseDTO courseDTO : selectedCourseDTOs) {
            registrationDTOS.add(makeRegistrationDTOs(studentDTO, courseDTO));
        }
        RegistrationFillingBO registrationFillingBO = (RegistrationFillingBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.RegistrationFilling);
        boolean saveRegistration = registrationFillingBO.saveRegistration(studentDTO, registrationDTOS);
        if (saveRegistration) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registration");
            alert.setHeaderText(null);
            alert.setContentText("Student registered successfully !");
            alert.showAndWait();
            clearFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Student Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please Check the Values !");
            alert.showAndWait();
        }

    }

    private void clearFields() {
        txtStudentID.clear();
        txtName.clear();
        programTMS.clear();
        txtAddress.clear();
        txtMobileNumber.clear();
        rabFemale.setSelected(false);
        rabMale.setSelected(false);
        dtDOB.setValue(null);
    }

    private RegistrationDTO makeRegistrationDTOs(StudentDTO studentDTO, CourseDTO courseDTO) {
        return new RegistrationDTO(0, String.valueOf(LocalDate.now()), courseDTO.getCourseFee(), studentDTO, courseDTO);
    }

    @FXML
    void btnRemoveProgram(ActionEvent event) {
        if (tblProgram.getSelectionModel().getSelectedItem() != null) {
            ProgramTM selectedItem = tblProgram.getSelectionModel().getSelectedItem();
            programTMS.remove(selectedItem);
            this.courseDTOS.add(makeCourseDTO(selectedItem));
            cmbTrainingProgram.getItems().add(selectedItem.getProgram());
        }
    }

    private CourseDTO makeCourseDTO(ProgramTM selectedItem) {
        return new CourseDTO(selectedItem.getProgramID(), selectedItem.getProgram(), selectedItem.getDuration(), selectedItem.getFee());
    }

    @FXML
    void cmdSelectProgram(ActionEvent event) {
        String course = cmbTrainingProgram.getSelectionModel().getSelectedItem();
        CourseDTO selectedCourseDTO = null;
        for (CourseDTO courseDTO : courseDTOS) {
            if (courseDTO.getCourseName().compareToIgnoreCase(course) == 0) {
                selectedCourseDTO = courseDTO;
                this.selectedCourseDTO = courseDTO;
            }
        }
        if (selectedCourseDTO != null) {
            txtProgramID.setText(selectedCourseDTO.getCode());
            txtDuration.setText(selectedCourseDTO.getDuration());
            txtFee.setText(String.valueOf(selectedCourseDTO.getCourseFee()));
        }
    }
}
