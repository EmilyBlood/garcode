package exerciseCreator.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class Account {
    private StringProperty name;
    

    private ObservableList<Task> tasks;

    public Account(String name) {
        this.name = new SimpleStringProperty(name);
        this.tasks = FXCollections.observableArrayList();
    }

    public final String getName() {
        return name.getValue();
    }

    public final StringProperty getNameProperty() {
        return name;
    }

    public final ObservableList<Task> getTasks() {
        return tasks;
    }

    public final void addtask(Task task) {
        this.tasks.add(task);
    }

    public void removetask(Task task) {
        this.tasks.remove(task);
    }

}
