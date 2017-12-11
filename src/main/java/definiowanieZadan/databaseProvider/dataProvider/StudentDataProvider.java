package definiowanieZadan.databaseProvider.dataProvider;

import definiowanieZadan.databaseProvider.dao.IStudentDAO;
import definiowanieZadan.databaseProvider.dao.impl.StudentHibernateDAO;
import definiowanieZadan.databaseProvider.entity.Student;
import definiowanieZadan.databaseProvider.session.HibernateSession;

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
