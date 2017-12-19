package exerciseCreator.controller;


import exerciseCreator.EntityModel.ModelToEntity;
import exerciseCreator.command.TestCaseCommand.CommandRegistry;
import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.dataProvider.TestCaseDataProvider;
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

    private ExerciseDataProvider exerciseDataProvider = new ExerciseDataProvider();
    private TestCaseDataProvider testCaseDataProvider = new TestCaseDataProvider();
    private ModelToEntity modelToEntity = new ModelToEntity(testCaseDataProvider, exerciseDataProvider);
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

    public boolean showAddTaskAction(Task task) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../AddTaskPane.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();



            Stage stage = new Stage();
            stage.setTitle("Garcode - dodaj zadanie");
            stage.setScene(new Scene(root1));

            TaskOverViewController controller = fxmlLoader.getController();
            controller.setAppController(this);
            controller.setData(task);
            controller.setCommandRegistry(commandRegistry);
            controller.setDialogStage(stage);


            stage.showAndWait();

            if(controller.isApproved()){
                modelToEntity.addTaskAndTestCasesToDatabase(task);
            }


            return controller.isApproved();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
