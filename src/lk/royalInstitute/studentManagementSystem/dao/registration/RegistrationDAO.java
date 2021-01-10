package lk.royalInstitute.studentManagementSystem.dao.registration;

import lk.royalInstitute.studentManagementSystem.dao.CrudDAO;
import lk.royalInstitute.studentManagementSystem.entity.Registration;

import java.util.List;

public interface RegistrationDAO extends CrudDAO<Registration,String> {
    List<Registration> getAllRegistration(String studentId) throws Exception;
}
