package lk.royalInstitute.studentManagementSystem.dao.registration.Impl;

import lk.royalInstitute.studentManagementSystem.dao.registration.CourseDAO;
import lk.royalInstitute.studentManagementSystem.entity.Course;
import lk.royalInstitute.studentManagementSystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private SessionFactory sessionFactory;

    public CourseDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean add(Course entity) throws Exception {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Serializable save = session.save(entity);
        session.getTransaction().commit();
        return save !=null;
    }

    @Override
    public boolean update(Course entity) throws Exception {
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
    public boolean delete(Course entity) throws Exception {
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
    public List<Course> getAll() throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Course> list=session.createNativeQuery("SELECT * FROM course",Course.class).list();
            session.getTransaction().commit();
            return list;
        }catch (Exception exception){

        }

        return null;
    }

    @Override
    public Course search(Course entity) throws Exception {
            try {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                Course course=session.find(Course.class, entity.getCode());
                session.getTransaction().commit();
                return course;
            }catch (Exception exception){

            }

        return null;
    }
}
