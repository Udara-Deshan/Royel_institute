package lk.royalInstitute.studentManagementSystem.bo.registration;


import lk.royalInstitute.studentManagementSystem.bo.SuperBO;
import lk.royalInstitute.studentManagementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean addCourse(StudentDTO studentDTO) throws Exception;
    boolean updateCourse(StudentDTO studentDTO) throws Exception;
    boolean deleteCourse(StudentDTO studentDTO) throws Exception;
    List<StudentDTO> getAll() throws Exception;
    StudentDTO search(StudentDTO studentDTO) throws Exception;
}

