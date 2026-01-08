package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * addCropsController is the controller class for the add crops menu screen. It allows the user to
 * add as many crops as they'd like to their field, view information on a selected crop, and
 * when they finish click the done button and move on to their field overview screen. It contains a
 * "title" label (Add Crops), a list view (contains all available crops), a view crop information button
 * that will display the selected crops information screen, an add button that will add the selected crop to
 * the users field, and a done button that will apply the changes to the users field and take them to the
 * field overview screen.
 *
 * @Author: Hunter Long (mia014)
 */

public class addCropsController {

    @FXML
    private Label addCropsLabel;

    @FXML
    private Button viewCropButton;

    @FXML
    private Button addButton;

    @FXML
    private Button doneButton;

    @FXML
    private ListView<String> cropsListView;

    private String[] temp;

    private final ArrayList<String> addedCropList = new ArrayList<>();

    private String cropHolder = "";

    private final ObservableList<String> availableCrops = FXCollections.observableArrayList();

    private boolean exists;

    private String crop1;

    private String[] fieldinfo = new String[10];

    /**
     * The addButtonPressed method is the event handler method for when the add button
     * is pressed. It checks to ensure a crop has been selected from the listView and
     * if one has, adds it to the addedCropList ArrayList which acts as a "buffer" that
     * will later be written to the users records when they've selected all the crops they
     * would like to add.
     * @param: ActionEvent event
     * @return: void
     */
    @FXML
    void addButtonPressed(ActionEvent event) {

        if( cropHolder.isEmpty() ){

            try {
                Parent popupOneparent = FXMLLoader.load(getClass().getResource("/View/popupTwo.fxml"));
                Scene popupOneScene = new Scene(popupOneparent);
                Stage popupOnewindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
                popupOnewindow.setScene(popupOneScene);
                popupOnewindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        addedCropList.add(cropHolder);

    }

    /**
     * The doneButtonPressed method is the event handler method for when the done button is
     * pressed. It first checks to see if the user has added one or more crops to the field,
     * or if there are already existing crops (there must be at least one crop in a field). If
     * this is the case it then adds the crops the user has selected to the users records or
     * just returns to field overview (again if there are already crops in the field).
     * @param: ActionEvent event
     * @return: void
     */
    @FXML
    void doneButtonPressed(ActionEvent event) {

        String filenameU = "";
        String line = "";
        String user = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader( "src/Model/user_accounts.csv" ));
            while ((line = br.readLine()) != null)
            {
                String[] accounts = line.split(",");
                user = accounts[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        filenameU = "src/Model/" + user + ".csv";

        try{
            BufferedReader br = new BufferedReader(new FileReader( filenameU ));
            while ((line = br.readLine()) != null)
            {
                this.fieldinfo = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if( addedCropList.isEmpty() && (fieldinfo.length <= 3) ){
            try {
                Parent popupOneparent = FXMLLoader.load(getClass().getResource("/View/popupOne.fxml"));
                Scene popupOneScene = new Scene(popupOneparent);
                Stage popupOnewindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
                popupOnewindow.setScene(popupOneScene);
                popupOnewindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else{

            try {
                int i = 0;
                while( i < addedCropList.size() ){
                    String temp = "," + addedCropList.get(i);
                    Files.write(Paths.get(filenameU), temp.getBytes(), StandardOpenOption.APPEND);
                    i++;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

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

    /**
     * The viewCropButtonPressed method is the event handler for when the view crop information button is
     * pressed. It first checks to ensure that a crop has been selected from the list, and then it pulls
     * up the selected crops information screen.
     * @param: ActionEvent event
     * @return: void
     */
    @FXML
    void viewCropButtonPressed(ActionEvent event) {

        if( cropHolder.isEmpty() ){
            try {
                Parent popupOneparent = FXMLLoader.load(getClass().getResource("/View/popupThree.fxml"));
                Scene popupOneScene = new Scene(popupOneparent);
                Stage popupOnewindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
                popupOnewindow.setScene(popupOneScene);
                popupOnewindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else{
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/View/" + cropHolder + "Info.fxml"));
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

    /**
     * The initialize method is called whenever the addCrops fxml file is called and does two things. First it
     * reads in the currently available crops and initializes the list view with them. Then it sets up a listener
     * for the list view, so that whenever a crop is selected it is stored in the class variable cropHolder to
     * be added to the addedCropList buffer to later be written to the users record, or to decide what crop
     * information screen will be displayed when the viewCropButton is pressed.
     * @param: none
     * @return: void
     */
    public void initialize(){

        try{
            File cropData = new File( "src/Model/cropData.csv" );
            Scanner scanner = new Scanner( cropData );
            while( scanner.hasNext() ){
                String[] temp = scanner.nextLine().split(",");
                availableCrops.add(temp[0]);
            }
            scanner.close();
        } catch( FileNotFoundException e){
            e.printStackTrace();
        }

        cropsListView.setItems(availableCrops);

        cropsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cropHolder = cropsListView.getSelectionModel().getSelectedItem();
            }
        });

    }

}