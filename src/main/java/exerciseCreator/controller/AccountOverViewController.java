package exerciseCreator.controller;

import exerciseCreator.EntityModel.ModelToEntity;
import exerciseCreator.databaseProvider.dataProvider.ExerciseDataProvider;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.executor.mock.StudentsPopulator;
import exerciseCreator.model.Account;
import exerciseCreator.model.Task;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AccountOverViewController {

    private Account account;

    private TaskManagingController appController;

    private ModelToEntity modelToEntity;
    private ExerciseDataProvider exerciseDataProvider;

    private Stage dialogStage;

    private boolean approved;

    @FXML
    private TableView<Task> tasksTable;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private Button handleAddTaskPaneButton;

    @FXML
    private Button editTaskButton;

    @FXML
    private  Button deleteTaskButton;

    @FXML
    private  Button gradeTaskButton;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public boolean isApproved() {
        return approved;
    }


    @FXML
    private void initialize() {
        tasksTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        titleColumn.setCellValueFactory(dataValue -> dataValue.getValue()
                .getTitleProperty());
        descriptionColumn.setCellValueFactory(dataValue -> dataValue.getValue()
                .getDescriptionProperty());
        
        deleteTaskButton.disableProperty().bind(
                Bindings.isEmpty(tasksTable.getSelectionModel()
                        .getSelectedItems()));

        editTaskButton.disableProperty().bind(
                Bindings.size(
                        tasksTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));

        gradeTaskButton.disableProperty().bind(
                Bindings.size(
                        tasksTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
    }

    @FXML
    private void handleDeleteTaskAction(ActionEvent event) {
        for (Task task : tasksTable.getSelectionModel()
                .getSelectedItems()) {
            Exercise exercise = exerciseDataProvider.getExerciseById(task.getId());
            exerciseDataProvider.removeExercise(exercise);
            account.removetask(task);
        }
    }

    @FXML
    private void handleEditTaskAction(ActionEvent event) {
        Task task = tasksTable.getSelectionModel()
                .getSelectedItem();
        int exerciseIDbeforeEdit = task.getId();


        if (task != null && appController.showAddTaskAction(task)) {
            Exercise exercise = exerciseDataProvider.getExerciseById(exerciseIDbeforeEdit);
            exerciseDataProvider.removeExercise(exercise);

            modelToEntity.addTaskAndTestCasesToDatabase(task);

        }
    }

    @FXML
    private void handleAddTaskAction(ActionEvent event) {
        Task task = Task.newTask();

        if (appController.showAddTaskAction(task)) {
            modelToEntity.addTaskAndTestCasesToDatabase(task);
            account.addtask(task);
        }
    }

    @FXML
    private void handleGradeTaskAction(ActionEvent event) {
        StudentsPopulator populator = new StudentsPopulator();
        populator.populateStudents();
    }


    public void setData(Account account) {
        this.account = account;
        tasksTable.setItems(account.getTasks());
    }

    public void setAppController(TaskManagingController
                                         appController) {
        this.appController = appController;
    }


    public void setModelToEntity(ModelToEntity modelToEntity) {
        this.modelToEntity = modelToEntity;
    }

    public void setExerciseDataProvider(ExerciseDataProvider exerciseDataProvider) {
        this.exerciseDataProvider = exerciseDataProvider;
    }
}
