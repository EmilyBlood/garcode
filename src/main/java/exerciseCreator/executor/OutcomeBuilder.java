package exerciseCreator.executor;

import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.Student;

public class OutcomeBuilder {

    private Outcome outcome;

    public OutcomeBuilder(){
        this.outcome = new Outcome();
    }

    public OutcomeBuilder student(Student student){
        this.outcome.setFirstName(student.getFirstName());
        this.outcome.setLastName(student.getLastName());
        this.outcome.setEmail(student.getEmail());
        this.outcome.setPhoneNumber(student.getPhoneNumber());
        return this;
    }

    public OutcomeBuilder exercise(Exercise exercise){
        this.outcome.setExerciseDesc(exercise.getDescription());
        this.outcome.setTitleDesc(exercise.getTitle());
        this.outcome.setMaxPoints(exercise.getTestCases().size());
        return this;
    }

    public Outcome build(){
        return this.outcome;
    }
}
