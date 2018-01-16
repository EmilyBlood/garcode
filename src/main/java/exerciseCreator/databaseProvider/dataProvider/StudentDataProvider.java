package exerciseCreator.databaseProvider.dataProvider;

import exerciseCreator.databaseProvider.dao.IStudentDAO;
import exerciseCreator.databaseProvider.dao.impl.StudentHibernateDAO;
import exerciseCreator.databaseProvider.entity.Student;

public class StudentDataProvider {

    IStudentDAO studentDAO = new StudentHibernateDAO();

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
