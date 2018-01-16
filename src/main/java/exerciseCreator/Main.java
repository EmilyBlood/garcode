package exerciseCreator;


import exerciseCreator.controller.TaskManagingController;
import exerciseCreator.databaseProvider.session.HibernateSession;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

    private Stage primaryStage;
    private TaskManagingController taskManagingController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FXML Welcome");

        this.taskManagingController = new TaskManagingController(primaryStage);
        this.taskManagingController.initRootLayout();

        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                 HibernateSession.closeSession();
            }
        });
    }
}
