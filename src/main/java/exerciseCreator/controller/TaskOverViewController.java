package exerciseCreator.controller;

import exerciseCreator.command.*;
import exerciseCreator.model.Task;
import exerciseCreator.model.TestCase;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class TaskOverViewController {

    private Task data;

    private TaskManagingController appController;

    private CommandRegistry commandRegistry;

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

    @FXML
    private void initialize() {
        testCasesTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        parametersInputColumn.setCellValueFactory(dataValue -> dataValue.getValue()
                .getParametersInputProperty());
        resultOutputColumn.setCellValueFactory(dataValue -> dataValue.getValue()
                .getResultOutputProperty());
        maxTimeColumn.setCellValueFactory(dataValue -> dataValue.getValue()
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
            RemoveTestCaseCommand removeTestCaseCommand = new RemoveTestCaseCommand(testCase, data);
            commandRegistry.executeCommand(removeTestCaseCommand);
        }
    }

    @FXML
    private void handleEditTestCaseAction(ActionEvent event) {
        TestCase testCase = testCasesTable.getSelectionModel()
                .getSelectedItem();
        if (testCase != null) {
            appController.showTestCaseAction(testCase);
            EditTestCaseCommand editTestCaseCommand = new EditTestCaseCommand(testCase, data);
            commandRegistry.executeCommand(editTestCaseCommand);
        }
    }

    @FXML
    private void handleAddTestCaseAction(ActionEvent event) {
        TestCase testCase = TestCase.newTestCase();

        if (appController.showTestCaseAction(testCase)) {
            AddTestCaseCommand addTestCaseCommand = new AddTestCaseCommand(testCase, data);
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
    private void onfinalAddTestCaseAction(ActionEvent event){

    }
    
    
    public void setData(Task data) {
        this.data = data;
        testCasesTable.setItems(data.getTestCases());
    }

    public void setAppController(TaskManagingController appController) {
        this.appController = appController;
    }

    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;

    }
}
