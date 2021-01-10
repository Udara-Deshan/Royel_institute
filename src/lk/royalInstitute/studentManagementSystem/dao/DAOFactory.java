package lk.royalInstitute.studentManagementSystem.dao;

import lk.royalInstitute.studentManagementSystem.dao.registration.Impl.CourseDAOImpl;
import lk.royalInstitute.studentManagementSystem.dao.registration.Impl.RegistrationDAOImpl;
import lk.royalInstitute.studentManagementSystem.dao.registration.Impl.StudentDAOImpl;

public class DAOFactory {
    public enum DAOType{
        Course,Student,Registration
    }
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory != null) ? daoFactory : (daoFactory = new DAOFactory());
    }

    public  SuperDAO getSuperDAO(DAOType daoType){
        switch (daoType){
            case Course:
                return new CourseDAOImpl();
            case Student:
                return new StudentDAOImpl();
            case Registration:
                return new RegistrationDAOImpl();
            default:
                return null;
        }
    }
}
