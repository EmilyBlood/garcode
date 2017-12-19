package exerciseCreator.controller;


import exerciseCreator.command.CommandRegistry;
import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;
import exerciseCreator.presenter.TestCasePanePresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagingController {


    private CommandRegistry commandRegistry = new CommandRegistry();

    public boolean showTestCaseAction(TestCase testcase) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../NewTestCasePane.fxml"));
         //   fxmlLoader.setLocation(getClass().getResource("../NewTestCasePane.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Garcode - dodaj zadanie");
            stage.setScene(new Scene(root1));

            TestCasePanePresenter presenter = fxmlLoader.getController();
            presenter.setDialogStage(stage);
            presenter.setData(testcase);


            stage.showAndWait();
            return presenter.isApproved();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showAddTaskAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../AddTaskPane.fxml"));

             Parent root1 = (Parent) fxmlLoader.load();

            TaskOverViewController controller = fxmlLoader.getController();
            controller.setAppController(this);
            controller.setData(new Task());
            controller.setCommandRegistry(commandRegistry);

            Stage stage = new Stage();
            stage.setTitle("Garcode - dodaj zadanie");
            stage.setScene(new Scene(root1));
            stage.showAndWait();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
