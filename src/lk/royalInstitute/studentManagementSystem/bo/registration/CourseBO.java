package lk.royalInstitute.studentManagementSystem.bo.registration;

import jdk.nashorn.internal.runtime.ECMAException;
import lk.royalInstitute.studentManagementSystem.bo.SuperBO;
import lk.royalInstitute.studentManagementSystem.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {

     boolean addCourse(CourseDTO courseDTO) throws Exception;
     boolean updateCourse(CourseDTO courseDTO) throws Exception;
     boolean deleteCourse(CourseDTO courseDTO) throws Exception;
     List<CourseDTO> getAll() throws Exception;
     CourseDTO search(CourseDTO courseDTO) throws Exception;

}
