package exerciseCreator.presenter;

import exerciseCreator.model.TestCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TestCasePanePresenter {
    private TestCase testCase;

    @FXML
    private TextField parametersInputTextField;

    @FXML
    private TextField resultOutputTextField;

    @FXML
    private TextField maxTimeTextField;

    private Stage dialogStage;

    private boolean approved;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(TestCase testCase) {
        this.testCase = testCase;
        updateControls();
    }

    public boolean isApproved() {
        return approved;
    }

    @FXML
    private void onfinaladdTestCaseAction(ActionEvent event) {
        if (isInputValid()) {
            updateModel();
            approved = true;
            dialogStage.close();
        }
    }

    @FXML
    private void onCancelAction(ActionEvent event) {
        dialogStage.close();
    }

    private boolean isInputValid() {
        if(!parametersInputTextField.getText().isEmpty() &&
                !resultOutputTextField.getText().isEmpty() &&
                !maxTimeTextField.getText().isEmpty())
            return true;
        return false;

    }

    private void updateModel() {
        testCase.setParametersInput(parametersInputTextField.getText());
        testCase.setResultOutput(resultOutputTextField.getText());
        testCase.setMaxTime(maxTimeTextField.getText());

    }

    private void updateControls() {
        parametersInputTextField.setText(testCase.getParametersInput());
        resultOutputTextField.setText(testCase.getResultOutput());
        maxTimeTextField.setText(testCase.getMaxTime());
    }
}
