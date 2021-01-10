package lk.royalInstitute.studentManagementSystem.dao.registration.Impl;

import lk.royalInstitute.studentManagementSystem.dao.CrudDAO;
import lk.royalInstitute.studentManagementSystem.entity.Student;
import lk.royalInstitute.studentManagementSystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class StudentDAOImpl implements CrudDAO<Student,String> {
    private SessionFactory sessionFactory;

    public StudentDAOImpl() {
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean add(Student entity) throws Exception {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Serializable save = session.save(entity);
        session.getTransaction().commit();
        return save !=null;
    }

    @Override
    public boolean update(Student entity) throws Exception {
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
    public boolean delete(Student entity) throws Exception {
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
    public List<Student> getAll() throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Student> list=session.createNativeQuery("SELECT * FROM student",Student.class).list();
            session.getTransaction().commit();
            return list;
        }catch (Exception exception){

        }
        return null;
    }

    @Override
    public Student search(Student entity) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Student student=session.find(Student.class, entity.getId());
            session.getTransaction().commit();
            return student;
        }catch (Exception exception){

        }
        return null;
    }
}
