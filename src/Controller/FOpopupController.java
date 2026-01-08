package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * FOpopupController is the controller class for the error message popups on the
 * field overview screen. It contains one label (the error message) and a close
 * button to close the popup once the user has read the error message.
 *
 * Author: Hunter Long (mia014)
 */

public class FOpopupController {

    @FXML
    private Label message;

    @FXML
    private Button closeButton;

    /**
     * The closeButtonPressed method is the event handler method for when the close button
     * is pressed, simply closing the popup error message and returning the user to the
     * field overview screen.
     * @param: none
     * @return: void
     */
    @FXML
    void closeButonPressed(ActionEvent event) {

        try {
            Parent fieldOverviewparent = FXMLLoader.load(getClass().getResource("/View/fieldOverview.fxml"));
            Scene fieldOverviewScene = new Scene(fieldOverviewparent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(fieldOverviewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}