package exerciseCreator;


import exerciseCreator.controller.TaskManagingController;
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    private Stage primaryStage;
    private TaskManagingController taskManagingController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//        DirectoryChooser chooser = new DirectoryChooser();
//        chooser.setTitle("JavaFX Projects");
//        File defaultDirectory = new File("/");
//        chooser.setInitialDirectory(defaultDirectory);
//        File selectedDirectory = chooser.showDialog(primaryStage);
//        System.out.println(selectedDirectory.getAbsolutePath());



        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FXML Welcome");

        this.taskManagingController = new TaskManagingController(primaryStage);
        this.taskManagingController.initRootLayout();

        //Parent root = null;
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("OverviewPane.fxml"));
//            //loader.setLocation(getClass().getResource("../OverviewPane.fxml"));
//            BorderPane rootLayout = (BorderPane) loader.load();
//            //root = FXMLLoader.load(getClass().getResource("OverviewPane.fxml"));
//
//
//        Scene scene = new Scene(rootLayout, 600, 600);
//
//        primaryStage.setTitle("FXML Welcome");
//        primaryStage.setScene(scene);
//
//        AccountOverViewController controller = loader.getController();
//        controller.setAppController(new TaskManagingController());
//            EntityToModel entityToModel = new EntityToModel();
//        Account account =
//        controller.setData(account);
//        controller.setDialogStage(primaryStage);
//
//
//        primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    private void initRootLayout(Account account) {
//        try {
//            // load layout from FXML file
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("view/OverviewPane.fxml"));
//            BorderPane rootLayout = (BorderPane) loader.load();
//
//            // add layout to a scene and show them all
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//            AccountOverViewController controller = loader.getController();
//            controller.setAppController(new TaskManagingController());
//            controller.setData(account);
//            controller.setDialogStage(primaryStage);
//
//        } catch (IOException e) {
//            // don't do this in common apps
//            e.printStackTrace();
//        }
//    }
}
