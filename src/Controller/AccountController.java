package Controller;

/**
 * AccountController is the backbone of the new user page and will be used to either
 * get all the users information and checks that the username is not already in use
 * and that the passwords match. If everything is filled in correctly then the information
 * is put into the according csv's and the user is directed to the new field page. The page also
 * features a button to allow the user to exit the application at any time.
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class AccountController
{
    @FXML
    private Button cAccountB;
    @FXML
    private Button exitB;
    @FXML
    private TextField fNameTF;
    @FXML
    private TextField lNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passphrasePF;
    @FXML
    private PasswordField cPassphrasePF;
    @FXML
    private Label messageLabel;

    /**
     * createAccountButtonAction - Takes in the users mouse click and will check if all
     * text fields are filled in. If they are then the program will call another
     * function to store all the users information, but if the fields are not filled in
     * then the program will prompt the user to fill in the missing fields
     * @param event - mouse click
     */
    public void createAccountButtonAction(ActionEvent event) throws IOException {
        if (!fNameTF.getText().isEmpty() && !lNameTF.getText().isEmpty() && !emailTF.getText().isEmpty() && !usernameTF.getText().isEmpty() && !passphrasePF.getText().isEmpty() && !cPassphrasePF.getText().isEmpty())
        {
            inputUser();
        }
        else
        {
            messageLabel.setText("Please fill out all fields, then try again");
        }
    }

    /**
     * exitButtonAction will cause the window to close if the user
     * presses the exit button
     * @param event - mouse click
     */
    public void exitButtonAction(ActionEvent event)
    {
        Stage stage = (Stage) exitB.getScene().getWindow();
        stage.close();
    }

    /**
     * inputUser is called by createAccountButtonAction() and will be used to
     * store all of the users information into various csv's to be used in
     * different portions of the application later on. Also once all the information
     * is stored, the function will send the new user to the new field screen so
     * they may continue their account creation.
     */
    public void inputUser() throws IOException
    {
        String user = "";
        String line = "";
        String filenameI = "src/Model/user_information.csv";
        String filenameA = "src/Model/user_accounts.csv";

        try{
            BufferedReader br = new BufferedReader(new FileReader(filenameI));

            while ((line = br.readLine()) != null)
            {
                String[] accounts = line.split(",");

                if (usernameTF.getText().equals(accounts[3]))
                {
                    messageLabel.setText("Username already in use, please select another");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (passphrasePF.getText().equals(cPassphrasePF.getText()))
        {
            FileWriter csvWriter = new FileWriter(filenameI, true);
            csvWriter.append("\n");
            csvWriter.append(fNameTF.getText());
            csvWriter.append(",");
            csvWriter.append(lNameTF.getText());
            csvWriter.append(",");
            csvWriter.append(emailTF.getText());
            csvWriter.append(",");
            csvWriter.append(usernameTF.getText());
            csvWriter.append(",");
            csvWriter.append(passphrasePF.getText());
            csvWriter.flush();
            csvWriter.close();

            FileWriter accountWriter = new FileWriter(filenameA, true);
            accountWriter.append("\n");
            accountWriter.append(usernameTF.getText());
            accountWriter.append(",");
            accountWriter.append(passphrasePF.getText());
            accountWriter.flush();
            accountWriter.close();

            BufferedWriter newFile = new BufferedWriter((new FileWriter("src/Model/" + usernameTF.getText() + ".csv")));
            newFile.append("Field Name");
            newFile.append(",");
            newFile.append("Location");
            newFile.append(",");
            newFile.append("Date");
            newFile.append(",");
            newFile.append("Crops:");
            newFile.flush();
            newFile.close();

            createFieldPage();
            Stage stage = (Stage) cAccountB.getScene().getWindow();
            stage.close();
        }
        else
        {
            messageLabel.setText("Passphrases do not match, please try again");
        }
    }

    /**
     * createFieldPage function will create a new stage and link the fxml
     * to the newField.fxml. It will also open the new stage in a new window.
     */
    public void createFieldPage()
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/newField.fxml"));
            Stage newField = new Stage();
            newField.setTitle("Plants for Life");
            newField.setScene(new Scene(root, 600, 400));
            newField.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
