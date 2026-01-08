package Controller;

/**
 * This is the main class for our application, it starts the application when it is run by
 * calling the login fxml file and the login controller.
 *
 * @Authors: Jasman Sidhu (tqx609), Aisha Bah (ozj281), David Maestas (xzu322),
 * Michael Ginsburg (sfi208), Hunter Long (mia014)
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        primaryStage.setTitle("Plants for Life");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}