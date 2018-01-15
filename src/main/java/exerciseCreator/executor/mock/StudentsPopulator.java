package exerciseCreator.executor.mock;

import exerciseCreator.databaseProvider.dataProvider.StudentDataProvider;
import exerciseCreator.databaseProvider.entity.Student;

public class StudentsPopulator {

    private StudentDataProvider studentDataProvider = new StudentDataProvider();

    public void populateStudents(){
        Student student = new Student();
        student.setFirstName("Tom");
        student.setLastName("Hanks");
        student.setIndexNumber("12345");
        student.setPhoneNumber("123456789");
        student.setEmail("tomhanks@mail.com");
        studentDataProvider.createStudent(student);
    }

}
