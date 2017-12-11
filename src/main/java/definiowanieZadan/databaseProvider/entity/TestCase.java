package definiowanieZadan.databaseProvider.entity;

import javax.persistence.*;

@Entity
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String parametersInput;

    private String resultOutput;

    public TestCase(){
    }

    public TestCase(String parametersInput, String resultOutput) {
        this.parametersInput = parametersInput;
        this.resultOutput = resultOutput;
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
}
