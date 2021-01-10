package lk.royalInstitute.studentManagementSystem.dto;

public class CourseDTO {
    private String code;
    private String courseName;
    private String duration;
    private double courseFee;

    public CourseDTO() {
    }

    public CourseDTO(String code, String courseName, String duration, double courseFee) {
        this.code = code;
        this.courseName = courseName;
        this.duration = duration;
        this.courseFee = courseFee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }
}
