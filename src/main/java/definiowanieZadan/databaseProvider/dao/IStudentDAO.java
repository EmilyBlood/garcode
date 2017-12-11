package definiowanieZadan.databaseProvider.dao;

import definiowanieZadan.databaseProvider.entity.Student;

public interface IStudentDAO {

    Student findByIndexNumber(String indexNumber);

    void addStudent(Student student);

    void deleteStudent(Student student);
}
