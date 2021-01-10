package lk.royalInstitute.studentManagementSystem.util;

import lk.royalInstitute.studentManagementSystem.entity.Course;
import lk.royalInstitute.studentManagementSystem.entity.Registration;
import lk.royalInstitute.studentManagementSystem.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory=buildSessionFactory();

    private static SessionFactory buildSessionFactory(){

        StandardServiceRegistry sReg=new StandardServiceRegistryBuilder()
                .loadProperties("lk/royalInstitute/studentManagementSystem/application.properties")
                .build();

        Metadata mData=new MetadataSources(sReg)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Registration.class)
                .addAnnotatedClass(Course.class)
                .getMetadataBuilder().build();
        return mData.getSessionFactoryBuilder().build();

    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
