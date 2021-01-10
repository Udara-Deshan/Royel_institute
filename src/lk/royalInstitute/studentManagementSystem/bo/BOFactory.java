package lk.royalInstitute.studentManagementSystem.bo;

import lk.royalInstitute.studentManagementSystem.bo.registration.Impl.CourseBOImpl;
import lk.royalInstitute.studentManagementSystem.bo.registration.Impl.RegistrationBOImpl;
import lk.royalInstitute.studentManagementSystem.bo.registration.Impl.RegistrationFillingBOImpl;
import lk.royalInstitute.studentManagementSystem.bo.registration.Impl.StudentBOImpl;

public class BOFactory {

    public enum BOType{
        Course,Student,Registration,RegistrationFilling
    }

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBOFactory(){
        return (boFactory != null) ?boFactory:(boFactory=new BOFactory());
    }

    public SuperBO getSuperBO(BOType boType){
        switch (boType){
            case Registration:
                return new RegistrationBOImpl();
            case Student:
                return new StudentBOImpl();
            case Course:
                return new CourseBOImpl();
            case RegistrationFilling:
                return new RegistrationFillingBOImpl();
            default:
                return null;
        }
    }

}
