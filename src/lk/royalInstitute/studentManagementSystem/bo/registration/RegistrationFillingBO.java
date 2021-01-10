package lk.royalInstitute.studentManagementSystem.bo.registration;

import lk.royalInstitute.studentManagementSystem.bo.SuperBO;
import lk.royalInstitute.studentManagementSystem.dto.RegistrationDTO;
import lk.royalInstitute.studentManagementSystem.dto.StudentDTO;

import java.util.List;

public interface RegistrationFillingBO extends SuperBO {
    boolean saveRegistration(StudentDTO studentDTO, List<RegistrationDTO> registrationDTOS);
}
