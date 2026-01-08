package Controller;

/**
 * LoginController is the backbone of the login page and will be used to either
 * validate login credentials and advance the user to their field or the page
 * will be used to direct new users to the create an account page. The page also
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
import java.nio.file.FileSystem;

public class LoginController
{
    @FXML
    private Button exitB;
    @FXML
    private Button loginB;
    @FXML
    private Button newAccountB;
    @FXML
    private Label messageText;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passphrasePF;

    /**
     * loginButtonAction - Takes in the users mouse click and will check if all
     * text fields are filled in. If they are then the program will call another
     * function to validate the login request, but if both fields are not filled in
     * then the program will prompt the user to fill in the missing fields.
     * @param event - mouse click
     */
    public void loginButtonAction(ActionEvent event)
    {
        if (!usernameTF.getText().isEmpty() && !passphrasePF.getText().isEmpty())
        {
            validateLogin();
        }
        else
        {
            messageText.setText("Please enter username and passphrase");
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
     * createAccountButtonAction will direct the user to the account creation screen
     * so they may register and close out the login page once they are redirected.
     * @param event - mouse click
     */
    public void createAccountButtonAction(ActionEvent event)
    {
        createAccountPage();
        Stage stage = (Stage) newAccountB.getScene().getWindow();
        stage.close();
    }

    /**
     * validateLogin is called by loginButtonAction() to check the users
     * credentials against all known users in the user_accounts csv. The
     * function will do checks on both the username and passphrase and if they
     * are both correct, will send the user to their overview page. If the user enters
     * a wrong username or passphrase the function will prompt the user to try again.
     */
    public void validateLogin(){
        int i;
        boolean isUser = false;
        String line = "";
        String filename = "src/Model/user_accounts.csv";

        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null)
            {
                String[] accounts = line.split(",");

                if (usernameTF.getText().equals(accounts[0]))
                {
                    isUser = true;
                    if (passphrasePF.getText().equals((accounts[1])))
                    {
                        fieldOverviewPage();
                        Stage stage = (Stage) loginB.getScene().getWindow();
                        stage.close();
                    }
                }
            }
            if (isUser == true)
            {
                messageText.setText("Incorrect Passphrase");
            }
            else
            {
                messageText.setText("Username not found, try again or create a new account");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * createAccountPage function will create a new stage and link the fxml
     * to the newAccount.fxml. It will also open the new stage in a new window.
     */
    public void createAccountPage()
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/newAccount.fxml"));
            Stage register = new Stage();
            register.setTitle("Plants for Life");
            register.setScene(new Scene(root, 600, 400));
            register.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * fieldOverviewPage function will create a new stage and link the fxml
     * to the fieldOverview.fxml. It will also open the new stage in a new window.
     */
    public void fieldOverviewPage()
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/fieldOverview.fxml"));
            Stage overview = new Stage();
            overview.setTitle("Plants for Life");
            overview.setScene(new Scene(root, 600, 600));
            overview.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
