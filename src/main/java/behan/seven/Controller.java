/*
Chloe Behan & Jafar Hashim
Cmdr Schenk
Period 7
24 October 2022
Sorting Project: Controller Class
 */

package behan.seven;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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

    // create option to access and select files
    FileChooser fileChooser = new FileChooser();
    File selectedFile;

    // access SortingClass methods to enable all 3 sorting methods
    private SortingClass sorting = new SortingClass();  // 3 sorts will be displayed in GUI components

    //Choose file button behavior - sets unsorted listview
    @FXML
    private File onChooseFileButton() {
        selectedFile = fileChooser.showOpenDialog(new Stage());
        ArrayList<String> unsorted = sorting.fileInput(selectedFile);
        ObservableList<String> unsortedObservable = FXCollections.observableArrayList(unsorted);
        unsortedDisplay.setItems(unsortedObservable);
        return selectedFile;
    }

    //Triggers selection sort & sets listview
    @FXML
    private void onSelectionSort() {
        ArrayList<String> selectionSorted = sorting.selectionSort(selectedFile);
        ObservableList<String> selectionSortedObservable = FXCollections.observableArrayList(selectionSorted);
        selectionDisplay.setItems(selectionSortedObservable);
    }

    //Triggers insertion sort & sets listview
    @FXML
    private void onInsertionSort() {
        ArrayList<String> insertionSorted = sorting.insertionSort(selectedFile);
        ObservableList<String> insertionSortedObservable = FXCollections.observableArrayList(insertionSorted);
        insertionDisplay.setItems(insertionSortedObservable);
    }

    //Triggers merge sort & sets listview
    @FXML
    private void onMergeSort() {
        ArrayList<String> mergeSorted = sorting.mergeSort(selectedFile);
        ObservableList<String> mergeSortedObservable = FXCollections.observableArrayList(mergeSorted);
        mergeDisplay.setItems(mergeSortedObservable);
    }

    //Triggers all three sorts & their listviews
    @FXML
    private void onSortAll() {
        // run method to put file through all sorting methods + display results
        onSelectionSort();
        onInsertionSort();
        onMergeSort();
    }

    //Resets all listviews
    @FXML
    private void onReset() {
        // clear the displays within ListViews
        ObservableList<String> empty = FXCollections.observableArrayList();
        unsortedDisplay.setItems(empty);
        selectionDisplay.setItems(empty);
        insertionDisplay.setItems(empty);
        mergeDisplay.setItems(empty);
    }

    //Triggers exit sequence
    @FXML
    private void onExit(javafx.event.ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setHeaderText("You are about to exit this program!"); // alert
        exitAlert.setContentText("Are you sure you would like to leave?");

        //Close program if user confirms "OK" they want to exit
        if (exitAlert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    //About panel
    @FXML
    public void onAbout(javafx.event.ActionEvent event) {
        Dialog<String> aboutProgram = new Dialog<String>();
        aboutProgram.setTitle("About The Program");
        ButtonType type = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
        // show warning text
        aboutProgram.setContentText("Begin by clicking the Select File button OR going to File, then Select File to choose a file to extract and sort its contents!");
        aboutProgram.getDialogPane().getButtonTypes().add(type);
        aboutProgram.show();
        aboutProgram.setHeight(aboutProgram.getHeight() + 100);
    }

    //Save sorted arraylist as a separate excel file
    @FXML
    private void onPrintSorted() {
        //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet("Starbucks Menu");

        // Get Data and Write into the Excel
        int rowNum = 0;
        ArrayList<String> sortedList = sorting.selectionSort(selectedFile);
        for (int x = 0; sortedList.size() > x; x++) {
            int cellNum = 0;
            XSSFRow detailsRow = spreadsheet.createRow(rowNum++);
            writeIntoCell(detailsRow, sortedList.get(x), cellNum++);
        }


        try {
            //Download workbook
            FileOutputStream out = new FileOutputStream("StarbucksMenu.xlsx");
            workbook.write(out);
            out.close();

            //Notifies user about download status
            Notifications.create()
                    .darkStyle()
                    .hideAfter(Duration.millis(5000))
                    .title("Sorted List Downloaded")
                    .text("I love Starbucks")
                    .showInformation();
            System.out.println("StarbucksMenu.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to write given value into excel sheet
    private void writeIntoCell(XSSFRow row, Object value, int cellNum) {
        // add sorted elements to new excel sheet cells
        XSSFCell cell = row.createCell(cellNum);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
