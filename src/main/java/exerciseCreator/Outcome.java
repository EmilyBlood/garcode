package exerciseCreator;

public class Outcome {

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;
    private final String titleDesc;
    private final String exerciseDesc;
    private final String grade;
    private final int points;
    private final int maxPoints;

    public Outcome(String grade, String firstName, String lastName, String phoneNumber, String email, String exerciseDesc, String titleDesc, int points, int maxPoints){
        this.grade = grade;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.exerciseDesc = exerciseDesc;
        this.titleDesc = titleDesc;
        this.points = points;
        this.maxPoints = maxPoints;
    }

    public String getGrade() {
        return grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getExerciseDesc() {
        return exerciseDesc;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public int getPoints() {
        return points;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
