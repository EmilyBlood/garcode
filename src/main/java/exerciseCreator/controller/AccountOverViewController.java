package exerciseCreator.controller;

import exerciseCreator.model.Account;
import exerciseCreator.model.Task;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountOverViewController {

    private Account account;

    private TaskManagingController appController;

    private Stage dialogStage;

    private boolean approved;

    @FXML
    private TableView<Task> tasksTable;

    @FXML
    private TableColumn<Task, String> titleColumn;
    

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
            //RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(task, account);
            //commandRegistry.executeCommand(removeTaskCommand);
            account.removetask(task);
        }
    }

    @FXML
    private void handleEditTaskAction(ActionEvent event) {
        Task task = tasksTable.getSelectionModel()
                .getSelectedItem();
        if (task != null && appController.showAddTaskAction(task)) {

            //EditTaskCommand editTaskCommand = new EditTaskCommand(task, account);
            //commandRegistry.executeCommand(editTaskCommand);
        }
    }

    @FXML
    private void handleAddTaskAction(ActionEvent event) {
        Task task = Task.newTask();

        if (appController.showAddTaskAction(task)) {
            //AddTaskCommand addTaskCommand = new AddTaskCommand(task, account);
            account.addtask(task);
        }
    }

    @FXML
    private void handleGradeTaskAction(ActionEvent event) {
        
    }


    public void setData(Account account) {
        this.account = account;
        tasksTable.setItems(account.getTasks());
    }

    public void setAppController(TaskManagingController
                                         appController) {
        this.appController = appController;
    }

   
}
