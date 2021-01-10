package lk.royalInstitute.studentManagementSystem.bo.registration.Impl;

import lk.royalInstitute.studentManagementSystem.bo.registration.StudentBO;
import lk.royalInstitute.studentManagementSystem.dao.DAOFactory;
import lk.royalInstitute.studentManagementSystem.dao.registration.StudentDAO;
import lk.royalInstitute.studentManagementSystem.dto.StudentDTO;
import lk.royalInstitute.studentManagementSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO;

    public StudentBOImpl() {
        studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getSuperDAO(DAOFactory.DAOType.Student);
    }

    @Override
    public boolean addCourse(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(makeStudent(studentDTO));
    }

    @Override
    public boolean updateCourse(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(makeStudent(studentDTO));
    }

    @Override
    public boolean deleteCourse(StudentDTO studentDTO) throws Exception {
        return studentDAO.delete(makeStudent(studentDTO));
    }

    @Override
    public List<StudentDTO> getAll() throws Exception {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> students = studentDAO.getAll();
        for (Student student : students) {
            studentDTOS.add(makeStudentDTO(student));
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO search(StudentDTO studentDTO) throws Exception {
        return makeStudentDTO(studentDAO.search(makeStudent(studentDTO)));
    }

    private Student makeStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(), studentDTO.getStudentName(), studentDTO.getAddress(),
                studentDTO.getContact(), studentDTO.getDate(), studentDTO.getGender());
    }

    private StudentDTO makeStudentDTO(Student student) {
        return new StudentDTO(student.getId(), student.getStudentName(), student.getAddress(),
                student.getContact(), student.getDate(), student.getGender());
    }
}
