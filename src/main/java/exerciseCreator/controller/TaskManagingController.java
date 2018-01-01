package exerciseCreator.controller;


import exerciseCreator.EntityModel.EntityToModel;
import exerciseCreator.EntityModel.ModelToEntity;
import exerciseCreator.command.TestCaseCommand.CommandRegistry;
import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.dataProvider.TestCaseDataProvider;
import exerciseCreator.model.Account;
import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;
import exerciseCreator.presenter.TestCasePanePresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagingController {

    private final Stage primaryStage;
    private ExerciseDataProvider exerciseDataProvider = new ExerciseDataProvider();
    private TestCaseDataProvider testCaseDataProvider = new TestCaseDataProvider();
    private ModelToEntity modelToEntity = new ModelToEntity(testCaseDataProvider, exerciseDataProvider);
    private EntityToModel entityToModel = new EntityToModel(testCaseDataProvider, exerciseDataProvider);
    private CommandRegistry commandRegistry = new CommandRegistry();

    public TaskManagingController(Stage primaryStage){
        this.primaryStage = primaryStage;
    }


    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OverviewPane.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout, 600, 600);

            primaryStage.setTitle("FXML Welcome");
            primaryStage.setScene(scene);

            AccountOverViewController controller = loader.getController();
            controller.setAppController(this);
            Account account = entityToModel.getAllTasksFromDatabase();
            controller.setData(account);
            controller.setModelToEntity(modelToEntity);
            controller.setExerciseDataProvider(exerciseDataProvider);
            controller.setDialogStage(primaryStage);


            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


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

            return controller.isApproved();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
