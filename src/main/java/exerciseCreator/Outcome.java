package exerciseCreator;

public class Outcome {

    private final float grade;
    private final String FirstName;
    private final String LastName;
    private final String StudentData;
    private final String comments;

    public Outcome(float grade, String FirsName, String LastName, String StudentData, String comments){
        this.grade = grade;
        this.FirstName = FirsName;
        this.LastName = LastName;
        this.StudentData = StudentData;
        this.comments = comments;
    }

    public float getGrade() {
        return grade;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getStudentData() {
        return StudentData;
    }

    public String getComments() {
        return comments;
    }
}
