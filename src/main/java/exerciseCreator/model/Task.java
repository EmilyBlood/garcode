package exerciseCreator.model;

import exerciseCreator.databaseProvider.entity.Threshold;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Task {

    private StringProperty Id = new SimpleStringProperty("-1");

    private StringProperty title;

    private StringProperty description;

   // private StringProperty pathToStudentAnswers;

    private ObservableList<TestCase> testCases;

    private ObservableList<Threshold> thresholds;

    public Task(String title, String description) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.testCases = FXCollections.observableArrayList();
        this.thresholds = FXCollections.observableArrayList();
       // this.pathToStudentAnswers = new SimpleStringProperty("");
       // this.Id = new SimpleStringProperty("-1");
    }

    public ObservableList<Threshold> getThresholds() {
        return thresholds;
    }

    public void setThresholds(ObservableList<Threshold> thresholds) {
        this.thresholds = thresholds;
    }
    public Task() {
        this.title = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.testCases = FXCollections.observableArrayList();
        this.thresholds = FXCollections.observableArrayList();
        //this.Id = new SimpleStringProperty("-1");
       // this.pathToStudentAnswers = new SimpleStringProperty("");
       // this.id = -1;
    }


    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public final String getTitle() {
        return title.getValue();
    }

    public final StringProperty getTitleProperty() {
        return title;
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public final String getDescription() {
        return description.getValue();
    }

    public final StringProperty getDescriptionProperty() {
        return description;
    }

   
    public final ObservableList<TestCase> getTestCases() {
        return testCases;
    }

    public final void addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
    }

    public final void addThreshold(Threshold threshold) {
        this.thresholds.add(threshold);
    }

    public static final Task newTask() {
        return new Task();
    }

    public void removeTestCase(TestCase testCase) {
        this.testCases.remove(testCase);
    }

    public void setId(int id) {
        this.Id.setValue(Integer.toString(id));
    }

    public final int getId() {
        return Integer.parseInt(Id.getValue());
    }

    public final StringProperty getIdProperty() {
        return Id;
    }

}
