package lk.royalInstitute.studentManagementSystem.dto;

public class RegistrationDTO {
    private int regNo;
    private String regDate;
    private double regFee;
    private StudentDTO studentDTO;
    private CourseDTO courseDTO;
    public RegistrationDTO() {
    }

    public RegistrationDTO(int regNo, String regDate, double regFee, StudentDTO studentDTO, CourseDTO courseDTO) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentDTO = studentDTO;
        this.courseDTO = courseDTO;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }
}
