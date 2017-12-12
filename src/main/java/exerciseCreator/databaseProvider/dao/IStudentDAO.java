package exerciseCreator.databaseProvider.dao;

import exerciseCreator.databaseProvider.entity.Student;

public interface IStudentDAO {

    Student findByIndexNumber(String indexNumber);

    void addStudent(Student student);

    void deleteStudent(Student student);
}
