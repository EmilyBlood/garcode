package exerciseCreator.databaseProvider.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Column(length = 1024)
    private String description;

    public String pathToExercises =  "src/main/resources/studentExercises/";


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<TestCase> testCases = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Set<CheckedExercise> checkedExercises;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "EXERCISE_FK")
    private List<Threshold> thresholds = new ArrayList<>();

    public Exercise(){
    }

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
        this.checkedExercises = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathToExercises() {
        return pathToExercises;
    }

    public void setPathToExercises(String pathToExercises) {
        this.pathToExercises = pathToExercises;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
    }

    public void addThreshold(Threshold threshold) {
        this.thresholds.add(threshold);
    }

    public Set<CheckedExercise> getCheckedExercises() {
        return checkedExercises;
    }

    public List<Threshold> getThresholds() { return thresholds;}
}
