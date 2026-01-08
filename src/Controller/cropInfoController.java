package Controller;

/**
 * cropInfoController is the controller class for the crop info screen, displaying relevant
 * information about the selected crop in the add crops menu. It contains five labels (the
 * crop name and four information fields), an image view containing an image of the crop, and
 * a back button to return to the add crops menu.
 *
 * Author: Hunter Long (mia014)
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class cropInfoController {

    @FXML
    private ImageView cropImage;

    @FXML
    private Label cropName;

    @FXML
    private Label recommendedFertalizer;

    @FXML
    private Label timeToHarvest;

    @FXML
    private Label irrigationSchedule;

    @FXML
    private Label plantingSeason;

    @FXML
    private Button backButton;

    /**
     * The backButtonPressed method is the event handler method for when the back button is pressed,
     * simply returning the user to the add crops menu.
     * @param: none
     * @return: void
     */
    @FXML
    void backButtonPressed() {

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

    }

}