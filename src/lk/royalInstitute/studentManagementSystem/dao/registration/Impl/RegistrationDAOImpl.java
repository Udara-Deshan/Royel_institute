package lk.royalInstitute.studentManagementSystem.dao.registration.Impl;

import lk.royalInstitute.studentManagementSystem.dao.CrudDAO;
import lk.royalInstitute.studentManagementSystem.dao.registration.RegistrationDAO;
import lk.royalInstitute.studentManagementSystem.entity.Registration;
import lk.royalInstitute.studentManagementSystem.entity.Student;
import lk.royalInstitute.studentManagementSystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    private SessionFactory sessionFactory;

    public RegistrationDAOImpl(){
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean add(Registration entity) throws Exception {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Serializable save = session.save(entity);
        session.getTransaction().commit();
        return save !=null;
    }

    @Override
    public boolean update(Registration entity) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return true;
        }catch (Exception exception){

        }
        return false;
    }

    @Override
    public boolean delete(Registration entity) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        }catch (Exception exception){

        }
        return false;
    }

    @Override
    public List<Registration> getAll() throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Registration> list=session.createNativeQuery("SELECT * FROM registration",Registration.class).list();
            session.getTransaction().commit();
            return list;
        }catch (Exception exception){

        }
        return null;
    }

    @Override
    public Registration search(Registration entity) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Registration registration=session.find(Registration.class, entity.getRegNo());
            session.getTransaction().commit();
            return registration;
        }catch (Exception exception){

        }
        return null;
    }
    public List<Registration> getAllRegistration(String studentId) throws Exception{
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Registration> list = session.createNativeQuery("SELECT * FROM registration WHERE course_code=?", Registration.class)
                    .setParameter(1,studentId).list();
            session.getTransaction().commit();
            return list;
        }catch (Exception exception){

        }
        return null;
    };

}
