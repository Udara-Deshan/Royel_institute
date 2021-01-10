package lk.royalInstitute.studentManagementSystem.bo.registration.Impl;

import lk.royalInstitute.studentManagementSystem.bo.BOFactory;
import lk.royalInstitute.studentManagementSystem.bo.registration.RegistrationBO;
import lk.royalInstitute.studentManagementSystem.bo.registration.RegistrationFillingBO;
import lk.royalInstitute.studentManagementSystem.bo.registration.StudentBO;
import lk.royalInstitute.studentManagementSystem.dao.DAOFactory;
import lk.royalInstitute.studentManagementSystem.dao.registration.RegistrationDAO;
import lk.royalInstitute.studentManagementSystem.dao.registration.StudentDAO;
import lk.royalInstitute.studentManagementSystem.dto.CourseDTO;
import lk.royalInstitute.studentManagementSystem.dto.RegistrationDTO;
import lk.royalInstitute.studentManagementSystem.dto.StudentDTO;
import lk.royalInstitute.studentManagementSystem.entity.Course;
import lk.royalInstitute.studentManagementSystem.entity.Registration;
import lk.royalInstitute.studentManagementSystem.entity.Student;
import lk.royalInstitute.studentManagementSystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistrationFillingBOImpl implements RegistrationFillingBO {

    private SessionFactory sessionFactory;

    public RegistrationFillingBOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean saveRegistration(StudentDTO studentDTO, List<RegistrationDTO> registrationDTOS) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Student student=makeStudent(studentDTO);
            List<Registration> registrations = new ArrayList<>();
            for (RegistrationDTO registrationDTO : registrationDTOS) {
                registrations.add(makeRegistration(registrationDTO));

            }

            for (Registration reg : registrations) {
                   student.getRegistrations().add(reg);
            }
            for (Registration reg : registrations) {
                reg.setStudent(student);
            }
            for (Registration registration : registrations) {
                Serializable saved = session.save(registration);
                if (saved == null) {
                    session.getTransaction().rollback();
                    return false;
                }
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private Student makeStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(), studentDTO.getStudentName(), studentDTO.getAddress(),
                studentDTO.getContact(), studentDTO.getDate(), studentDTO.getGender());
    }

    private Registration makeRegistration(RegistrationDTO registrationDTO) {
        return new Registration(registrationDTO.getRegDate(), registrationDTO.getRegFee(),
                null, makeCourse(registrationDTO.getCourseDTO()));
    }

    private Course makeCourse(CourseDTO courseDTO) {
        return new Course(courseDTO.getCode(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getCourseFee());
    }
}
