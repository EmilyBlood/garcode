package exerciseCreator;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagingController {


    public void addTaskAction(ActionEvent event) {

    }

    public void addTestCaseAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewTestCasePane.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Garcode - dodaj zadanie");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void showAddTaskAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTaskPane.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Garcode - dodaj zadanie");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
