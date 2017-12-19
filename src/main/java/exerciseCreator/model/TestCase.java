package exerciseCreator.model;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;


public class TestCase {

    private static final String EMPTY = "";

    private StringProperty parametersInput;
    private StringProperty resultOutput;
    private StringProperty maxTime;


    private TestCase() { this(EMPTY, EMPTY, EMPTY);
    }
    

    public TestCase(String parametersInput, String resultOutput, String maxTime) {
        this.parametersInput = new SimpleStringProperty(parametersInput);
        this.resultOutput = new SimpleStringProperty(resultOutput);
        this.maxTime = new SimpleStringProperty(maxTime);

    }

    public final String getParametersInput() {
        return parametersInput.get();
    }

    public final void setParametersInput(String parametersInput) {
        this.parametersInput.set(parametersInput);
    }

    public final StringProperty getParametersInputProperty() {
        return parametersInput;
    }

    public final String getResultOutput() {
        return resultOutput.get();
    }

    public final void setResultOutput(String resultOutput) {
        this.resultOutput.set(resultOutput);
    }

    public final StringProperty getResultOutputProperty() {
        return resultOutput;
    }

    public final String getMaxTime() {
        return maxTime.get();
    }

    public final void setMaxTime(String maxTime) {
        this.maxTime.set(maxTime);
    }

    public final StringProperty getMaxTimeProperty() {
        return maxTime;
    }

    public static final TestCase newTestCase() {
        return new TestCase();
    }

    @Override
    public String toString() {
        return String.join(",", parametersInput.get(), resultOutput.get(), maxTime.get());
    }

}
