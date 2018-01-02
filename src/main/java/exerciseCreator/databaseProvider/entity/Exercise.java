package exerciseCreator.databaseProvider.entity;

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



    @OneToMany(cascade = CascadeType.REMOVE)
    private List<TestCase> testCases = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    private Set<CheckedExercise> checkedExercises;

    @OneToMany(cascade = CascadeType.ALL)
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

    public void setThresholds(List<Threshold> thresholds) { this.thresholds = thresholds; }
}
