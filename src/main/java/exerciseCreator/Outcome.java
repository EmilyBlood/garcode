package exerciseCreator;

public class Outcome {

    private final float grade;
    private final String firstName;
    private final String lastName;
    private final String studentData;
    private final String comments;

    public Outcome(float grade, String firstName, String lastName, String studentData, String comments){
        this.grade = grade;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentData = studentData;
        this.comments = comments;
    }

    public float getGrade() {
        return grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentData() {
        return studentData;
    }

    public String getComments() {
        return comments;
    }
}
