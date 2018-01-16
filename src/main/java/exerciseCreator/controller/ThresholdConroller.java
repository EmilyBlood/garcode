package exerciseCreator.controller;

import exerciseCreator.databaseProvider.entity.Threshold;
import exerciseCreator.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ThresholdConroller {

    private Task task;

    private TaskManagingController appController;

    private Stage dialogStage;

    @FXML
    private TextField grade3TextField;

    @FXML
    private TextField grade35TextField;

    @FXML
    private TextField grade4TextField;

    @FXML
    private TextField grade45TextField;

    @FXML
    private TextField grade5TextField;

    @FXML
    private TextField maxTimeTextField;



    public void setAppController(TaskManagingController appController) {
        this.appController = appController;
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setThresholds(ActionEvent event) {


        try {
            ArrayList<Threshold> thresholds = new ArrayList<>();
            thresholds.add(new Threshold("3", Float.parseFloat(grade3TextField.getText())));
            thresholds.add(new Threshold("3.5", Float.parseFloat(grade35TextField.getText())));
            thresholds.add(new Threshold("4", Float.parseFloat(grade4TextField.getText())));
            thresholds.add(new Threshold("4.5", Float.parseFloat(grade45TextField.getText())));
            thresholds.add(new Threshold("5", Float.parseFloat(grade5TextField.getText())));

            if(!isInputValid(thresholds)){
                throw new Exception();
            }

            thresholds.forEach(t -> task.addThreshold(t));

            dialogStage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Niepoprawne dane");
            alert.setContentText("Wprowadzone dane są niepoprawne!");

            alert.showAndWait();
        }
    }

    private boolean isInputValid(ArrayList<Threshold> thresholds) {

        float thresholdToCompare = 0.0f ;

        for (Threshold tr : thresholds){
            if(0 > tr.getThreshold() && tr.getThreshold() > 100){
                return false;
            }

            if(thresholdToCompare >= tr.getThreshold())
                return false;
            thresholdToCompare = tr.getThreshold();

        }
        return true;
    }


    public void onCancelAction(ActionEvent event) {dialogStage.close();  }

    public void setData(Task task) {
        this.task = task;
        updateControls();
    }


    private void updateControls() {

        for (Threshold threshold: task.getThresholds()){
            switch(threshold.getGrade()){
                case "3":
                    grade3TextField.setText(Float.toString(threshold.getThreshold()));
                    break;
                case "3.5":
                    grade35TextField.setText(Float.toString(threshold.getThreshold()));
                    break;
                case "4":
                    grade4TextField.setText(Float.toString(threshold.getThreshold()));
                    break;
                case "4.5":
                    grade45TextField.setText(Float.toString(threshold.getThreshold()));
                    break;
                case "5":
                    grade5TextField.setText(Float.toString(threshold.getThreshold()));
                    break;
            }
        }

    }
}

