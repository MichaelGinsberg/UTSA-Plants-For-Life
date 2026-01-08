package Controller; /**
 * Controller.newFieldController is the backbone of the new field page and will be used to get the
 * background information on the field and then will take the user to the overview page.
 * The page also features a button to allow the user to exit the application at any time.
 *
 * Author: Michael Ginsberg (sfi208)
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class newFieldController
{
    @FXML
    private Button createFieldB;
    @FXML
    private Button exitB;
    @FXML
    private TextField fieldNameTF;
    @FXML
    private TextField locationTF;
    @FXML
    private TextField dateTF;
    @FXML
    private Label messageLabel;

    /**
     * createFieldButtonAction - Takes in the users mouse click and will check if all
     * text fields are filled in. If they are then the program will call another
     * function to store all the users information, but if the fields are not filled in
     * then the program will prompt the user to fill in the missing fields
     * @param event - mouse click
     */
    public void createFieldButtonAction(ActionEvent event) throws IOException {
        if (!fieldNameTF.getText().isEmpty() && !locationTF.getText().isEmpty() && !dateTF.getText().isEmpty())
        {
            inputField();
        }
        else
        {
            messageLabel.setText("Please fill out all fields, then try again");
        }
    }

    /**
     * exitButtonAction will cause the window to close if the user
     * presses the exit button.
     * @param event - mouse click
     */
    public void exitButtonAction(ActionEvent event)
    {
        Stage stage = (Stage) exitB.getScene().getWindow();
        stage.close();
    }

    /**
     * Inputfield is used to get the users input into their account csv
     * and then takes the user to the overview page of the application.
     * @throws IOException
     */
    public void inputField() throws IOException {
        String filenameU = "";
        String filenameA = "src/Model/user_accounts.csv";
        String line = "";
        String user = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(filenameA));

            while ((line = br.readLine()) != null)
            {
                String[] accounts = line.split(",");

                    user = accounts[0];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        filenameU = "src/Model/" + user + ".csv";

        FileWriter fieldWriter = new FileWriter(filenameU, true);
        fieldWriter.append("\n");
        fieldWriter.append(fieldNameTF.getText());
        fieldWriter.append(",");
        fieldWriter.append(locationTF.getText());
        fieldWriter.append(",");
        fieldWriter.append(dateTF.getText());
        fieldWriter.flush();
        fieldWriter.close();

        addCropsPage();
        Stage stage = (Stage) createFieldB.getScene().getWindow();
        stage.close();
    }

    /**
     * fieldOverviewPage function will create a new stage and link the fxml
     * to the fieldOverview.fxml. It will also open the new stage in a new window.
     */
    public void addCropsPage()
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/addCrops.fxml"));
            Stage overview = new Stage();
            overview.setTitle("Plants for Life");
            overview.setScene(new Scene(root, 600, 400));
            overview.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
