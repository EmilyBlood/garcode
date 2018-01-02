package exerciseCreator.databaseProvider.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String grade;
    private float threshold;

    public Threshold() {
    }

    public Threshold(String grade, float threshold) {
        this.grade = grade;
        this.threshold = threshold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public String getGrade() {
        return grade;
    }

    public float getThreshold() {
        return threshold;
    }
}
