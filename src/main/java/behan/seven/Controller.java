/*
Chloe Behan
Cmdr Schenk
Period 7
24 October 2022
Sorting Project: Controller Class
 */

package behan.seven;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import com.jfoenix.controls.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    // FXIDs for all GUI items
    @FXML   // make all ListView displays for menu before + after sorting
            JFXListView unsortedDisplay;
    @FXML
    JFXListView selectionDisplay;
    @FXML
    JFXListView insertionDisplay;
    @FXML
    JFXListView mergeDisplay;

    @FXML   // make Buttons
    JFXButton selectFile;
    @FXML
    JFXButton download;
    @FXML
    JFXButton selectionSort;
    @FXML
    JFXButton insertionSort;
    @FXML
    JFXButton mergeSort;
    @FXML
    JFXButton sortAll;
    @FXML
    FileChooser fileChooser;

    @FXML
    private File onChooseFileButton(){
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        return selectedFile;
    }

    File selectedFile = onChooseFileButton();

    @FXML
    private void onSelectionSort(){

    }

    @FXML
    private void onInsertionSort(){

    }

    @FXML
    private void onMergeSort(){

    }

    @FXML
    private void onExit(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setHeaderText("You are about to exit this program!");
        exitAlert.setContentText("Are you sure you would like to leave?");

        //Close program if user confirms "OK" they want to exit
        if (exitAlert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    public void onAbout(ActionEvent event) {
        Dialog<String> aboutProgram = new Dialog<String>();
        aboutProgram.setTitle("About The Program");
        ButtonType type = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
        aboutProgram.setContentText("Begin by going to File, then Select File to choose a file to extract and sort its contents!");
        aboutProgram.getDialogPane().getButtonTypes().add(type);
        aboutProgram.show();
        aboutProgram.setHeight(aboutProgram.getHeight() + 100);
    }


}
