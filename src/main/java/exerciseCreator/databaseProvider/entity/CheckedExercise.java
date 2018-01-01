package exerciseCreator.databaseProvider.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHECKED_EXERCISE")
public class CheckedExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EXERCISE_ID")
    private Exercise exercise;

    private Integer points;

    public CheckedExercise() {
    }

    public CheckedExercise(Student student, Exercise exercise, Integer points) {
        this.student = student;
        this.exercise = exercise;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public Integer getPoints() {
        return points;
    }
}
