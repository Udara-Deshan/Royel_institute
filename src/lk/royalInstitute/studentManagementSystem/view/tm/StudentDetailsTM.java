package lk.royalInstitute.studentManagementSystem.view.tm;

public class StudentDetailsTM {
    private String studentID;
    private int RegNo;
    private String studentName;
    private String programName;
    private String address;
    private String contact;
    private String dOB;
    private String gender;

    public StudentDetailsTM() {
    }

    public StudentDetailsTM(String studentID, int regNo, String studentName, String programName, String address, String contact, String dOB, String gender) {
        this.studentID = studentID;
        this.RegNo = regNo;
        this.studentName = studentName;
        this.programName = programName;
        this.address = address;
        this.contact = contact;
        this.dOB = dOB;
        this.gender = gender;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getRegNo() {
        return RegNo;
    }

    public void setRegNo(int regNo) {
        RegNo = regNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
