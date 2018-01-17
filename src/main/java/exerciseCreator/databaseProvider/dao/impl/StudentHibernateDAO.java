package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.IStudentDAO;
import exerciseCreator.databaseProvider.entity.Student;
import exerciseCreator.databaseProvider.session.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;

public class StudentHibernateDAO implements IStudentDAO{

    @Override
    public Student findByIndexNumber(String indexNumber) {
        try (Session session = HibernateSession.getSession()) {
            TypedQuery<Student> query = session.createQuery("from Student where indexNumber=:indexNumber", Student.class);
            query.setParameter("indexNumber", indexNumber);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addStudent(Student student) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.delete(student);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
