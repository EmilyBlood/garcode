package exerciseCreator.databaseProvider.dataProvider;

import exerciseCreator.databaseProvider.dao.IStudentDAO;
import exerciseCreator.databaseProvider.dao.impl.StudentHibernateDAO;
import exerciseCreator.databaseProvider.entity.Student;
import exerciseCreator.databaseProvider.session.HibernateSession;

public class StudentDataProvider {

    IStudentDAO studentDAO = new StudentHibernateDAO(HibernateSession.getSession());

    public Student getStudentByIndexNumber(String indexNumber) {
        return studentDAO.findByIndexNumber(indexNumber);
    }

    public void createStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public void removeStudent(Student student) {
        studentDAO.deleteStudent(student);
    }
}
