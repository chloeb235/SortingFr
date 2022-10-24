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
    JFXButton display;
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

    File selectedeFile = onChooseFileButton();

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
        Dialog<String> aboutDiagnosticProgram = new Dialog<String>();
        aboutDiagnosticProgram.setTitle("About The Diagnostic");
        ButtonType type = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
        aboutDiagnosticProgram.setContentText("Begin by inputting your name. Press Start Diagnostic to proceed, then answer 7 carefully selected questions on each unit of the AP Physics 1 2022 curriculum. Submit your responses by selecting the Submit All button, then view your results!");
        aboutDiagnosticProgram.getDialogPane().getButtonTypes().add(type);
        aboutDiagnosticProgram.show();
        aboutDiagnosticProgram.setHeight(aboutDiagnosticProgram.getHeight() + 100);
    }


}
