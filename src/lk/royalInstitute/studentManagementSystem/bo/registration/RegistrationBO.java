package lk.royalInstitute.studentManagementSystem.bo.registration;


import lk.royalInstitute.studentManagementSystem.bo.SuperBO;
import lk.royalInstitute.studentManagementSystem.dto.RegistrationDTO;
import lk.royalInstitute.studentManagementSystem.entity.Registration;

import java.util.List;

public interface RegistrationBO extends SuperBO {
    boolean addCourse(RegistrationDTO registrationDTO) throws Exception;
    boolean updateCourse(RegistrationDTO registrationDTO) throws Exception;
    boolean deleteCourse(RegistrationDTO registrationDTO) throws Exception;
    List<RegistrationDTO> getAll() throws Exception;
    RegistrationDTO search(RegistrationDTO registrationDTO) throws Exception;
    List<RegistrationDTO> getAllRegistration(String studentId) throws Exception;

}
