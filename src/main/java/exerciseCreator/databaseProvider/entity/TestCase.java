package exerciseCreator.databaseProvider.entity;

import javax.persistence.*;

@Entity
@Table(name = "TEST_CASE")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "PARAMETERS_INPUT", nullable = false)
    private String parametersInput;

    @Column(name = "RESULT_OUTPUT", nullable = false)
    private String resultOutput;

    @Column(name = "TIME_LIMIT", nullable = false)
    private Integer timeLimit;

    @Column(name = "POINTS_FOR_TEST", nullable = false)
    private Integer pointsForTest;

    public TestCase(){
    }

    public TestCase(String parametersInput, String resultOutput, Integer timeLimit, Integer pointsForTest) {
        this.parametersInput = parametersInput;
        this.resultOutput = resultOutput;
        this.timeLimit = timeLimit;
        this.pointsForTest = pointsForTest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParametersInput() {
        return parametersInput;
    }

    public void setParametersInput(String parametersInput) {
        this.parametersInput = parametersInput;
    }

    public String getResultOutput() {
        return resultOutput;
    }

    public void setResultOutput(String resultOutput) {
        this.resultOutput = resultOutput;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getPointsForTest() {
        return pointsForTest;
    }

    public void setPointsForTest(Integer pointsForTest) {
        this.pointsForTest = pointsForTest;
    }
}
