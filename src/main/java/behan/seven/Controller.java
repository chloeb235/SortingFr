/*
Chloe Behan
Cmdr Schenk
Period 7
24 October 2022
Sorting Project: Controller Class
 */

package behan.seven;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;

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
    private SortingClass sorting;

    File selectedFile;

    @FXML
    private File onChooseFileButton(){
        selectedFile = fileChooser.showOpenDialog(new Stage());
        ArrayList<String> unsorted = sorting.fileInput(selectedFile);
        ObservableList<String> unsortedObservable = FXCollections.observableArrayList(unsorted);
        unsortedDisplay.setItems(unsortedObservable);
        return selectedFile;
    }


    @FXML
    private void onSelectionSort(){

        ArrayList <String> selectionSorted = sorting.selectionSort(selectedFile);
        ObservableList <String> selectionSortedObservable = FXCollections.observableArrayList(selectionSorted);
        selectionDisplay.setItems(selectionSortedObservable);
    }

    @FXML
    private void onInsertionSort(){
        ArrayList<String> insertionSorted = sorting.insertionSort(selectedFile);
        ObservableList <String> insertionSortedObservable = FXCollections.observableArrayList(insertionSorted);
        insertionDisplay.setItems(insertionSortedObservable);
    }

    @FXML
    private void onMergeSort(){
        ArrayList<String> mergeSorted = sorting.mergeSort(selectedFile);
        ObservableList<String> mergeSortedObservable = FXCollections.observableArrayList(mergeSorted);
        mergeDisplay.setItems(mergeSortedObservable);
    }

    @FXML
    private void onSortAll(){
        onSelectionSort();
        onInsertionSort();
        onMergeSort();
    }

    @FXML
    private void onReset(){
        //unsortedDisplay.setItems();
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
