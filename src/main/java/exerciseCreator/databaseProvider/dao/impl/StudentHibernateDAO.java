package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.IStudentDAO;
import exerciseCreator.databaseProvider.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;

public class StudentHibernateDAO implements IStudentDAO{

    Session session;

    public StudentHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public Student findByIndexNumber(String indexNumber) {
        TypedQuery<Student> query = session.createQuery("from Student where indexNumber=:indexNumber", Student.class);
        query.setParameter("indexNumber", indexNumber);
        return query.getSingleResult();
    }

    @Override
    public void addStudent(Student student) {
        Transaction tx = session.beginTransaction();
        session.save(student);
        tx.commit();
    }

    @Override
    public void deleteStudent(Student student) {
        Transaction tx = session.beginTransaction();
        session.delete(student);
        tx.commit();
    }
}
