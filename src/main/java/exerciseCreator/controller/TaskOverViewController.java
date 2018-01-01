package exerciseCreator.controller;

import exerciseCreator.command.TestCaseCommand.AddTestCaseCommand;
import exerciseCreator.command.TestCaseCommand.CommandRegistry;
import exerciseCreator.command.TestCaseCommand.EditTestCaseCommand;
import exerciseCreator.command.TestCaseCommand.RemoveTestCaseCommand;
import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class TaskOverViewController {

    private Task task;

    private TaskManagingController appController;

    private CommandRegistry commandRegistry;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TableView<TestCase> testCasesTable;

    @FXML
    private TableColumn<TestCase, String> parametersInputColumn;

    @FXML
    private TableColumn<TestCase, String> resultOutputColumn;

    @FXML
    private TableColumn<TestCase, String> maxTimeColumn;

    @FXML
    private Button deleteTestCaseButton;

    @FXML
    private Button editTestCaseButton;

    @FXML
    private Button addTestCaseButton;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button undoButton;

    @FXML
    private Button redoButton;

    private Stage dialogStage;

    private boolean approved;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(Task task) {
        this.task = task;
        testCasesTable.setItems(task.getTestCases());
        updateControls();
    }

    public boolean isApproved() {
        return approved;
    }


    @FXML
    private void initialize() {
        testCasesTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        parametersInputColumn.setCellValueFactory(taskValue -> taskValue.getValue()
                .getParametersInputProperty());
        resultOutputColumn.setCellValueFactory(taskValue -> taskValue.getValue()
                .getResultOutputProperty());
        maxTimeColumn.setCellValueFactory(taskValue -> taskValue.getValue()
                .getMaxTimeProperty());
        deleteTestCaseButton.disableProperty().bind(
                Bindings.isEmpty(testCasesTable.getSelectionModel()
                        .getSelectedItems()));

        editTestCaseButton.disableProperty().bind(
                Bindings.size(
                        testCasesTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
    }

    @FXML
    private void handleDeleteTestCaseAction(ActionEvent event) {
        for (TestCase testCase : testCasesTable.getSelectionModel()
                .getSelectedItems()) {
            RemoveTestCaseCommand removeTestCaseCommand = new RemoveTestCaseCommand(testCase, task);
            commandRegistry.executeCommand(removeTestCaseCommand);
        }
    }

    @FXML
    private void handleEditTestCaseAction(ActionEvent event) {
        TestCase testCase = testCasesTable.getSelectionModel()
                .getSelectedItem();
        if (testCase != null) {
            appController.showTestCaseAction(testCase);
            EditTestCaseCommand editTestCaseCommand = new EditTestCaseCommand(testCase, task);
            commandRegistry.executeCommand(editTestCaseCommand);
        }
    }

    @FXML
    private void handleAddTestCaseAction(ActionEvent event) {
        TestCase testCase = TestCase.newTestCase();

        if (appController.showTestCaseAction(testCase)) {
            AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, task);
            commandRegistry.executeCommand(addTestCaseCommand);
        }
    }

    @FXML
    private void handleUndoAction(ActionEvent event) {
        commandRegistry.undo();
    }

    @FXML
    private void handleRedoAction(ActionEvent event) {
        commandRegistry.redo();
    }

    @FXML
    private void onfinalAddTaskAction(ActionEvent event) {
        if (isInputValid()) {
            updateModel();
            approved = true;
            dialogStage.close();
        }
    }


    public void setAppController(TaskManagingController appController) {
        this.appController = appController;
    }

    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;

    }


    @FXML
    private void handleCancelAction(ActionEvent event) {
        dialogStage.close();
    }

    private boolean isInputValid() {
        if(!titleTextField.getText().isEmpty() &&
                !descriptionTextArea.getText().isEmpty() &&
                task.getTestCases().size() >= 2)
            return true;
        return false;
    }

    private void updateModel() {
        task.setTitle(titleTextField.getText());
        task.setDescription(descriptionTextArea.getText());


    }

    private void updateControls() {
        titleTextField.setText(task.getTitle());
        descriptionTextArea.setText(task.getDescription());
    }
}
