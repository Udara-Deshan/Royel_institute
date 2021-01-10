package lk.royalInstitute.studentManagementSystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    private String id;
    private String studentName;
    private String address;
    private String contact;
    private String Date;
    private String gender;

    @OneToMany(mappedBy = "student",cascade= CascadeType.ALL)
    private List<Registration> registrations=new ArrayList();

    public Student() {
    }

    public Student(String id, String studentName, String address, String contact, String date, String gender) {
        this.id = id;
        this.studentName = studentName;
        this.address = address;
        this.contact = contact;
        Date = date;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", Date='" + Date + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}
