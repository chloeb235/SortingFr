/*
Chloe Behan & Jafar Hashim
Cmdr Schenk
Period 7
24 October 2022
Sorting Project: Sorting Class
 */
package behan.seven;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class SortingClass {

    // create method to collect excel sheet contents into array list
    public ArrayList<String> fileInput(File selectedFile){
        ArrayList<String> fileInput = new ArrayList<>();    // convert excel -> arraylist
        try {
            //Creates excel workbook
            XSSFWorkbook workbook = new XSSFWorkbook(selectedFile);

            //Gets first sheet from workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Uses iterator to loop through rows
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                // each row is scanned and each cell's contents are stored
                Row thisRow = rowIterator.next();
                Iterator<Cell> cellIterator = thisRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell thisCell = cellIterator.next();
                    // add contents to arraylist
                    fileInput.add(thisCell.getStringCellValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return array list
        return fileInput;
    }

    //Selection Sort method
    public ArrayList<String> selectionSort(File selectedFile){
        // set array list to be sorted (selection) + broadcast to excel file contents
        ArrayList<String> selectionOutput = fileInput(selectedFile);
        for (int i = 0; i < selectionOutput.size(); i++) {  // run through each arraylist item
            // set the first item of the arraylist
            String smallest = selectionOutput.get(i);
            int smallestIndex = i;
            // run through each arraylist item
            for (int j = i; j < selectionOutput.size(); j++) {
                // compare next item to the previous
                String value = selectionOutput.get(j);
                if (value.compareTo(smallest) < 0) {
                    // set the smaller of the values
                    smallest = value;
                    smallestIndex = j;
                }
            }
            // orient the "smallest" item vs the next
            if (smallestIndex != i) {
                String leading = selectionOutput.get(i);
                selectionOutput.set(i, smallest);   // order items
                selectionOutput.set(smallestIndex, leading);
            }
        }
        return selectionOutput;
    }

    //Insertion Sort
    public ArrayList<String> insertionSort(File selectedFile){
        ArrayList<String> insertionOutput = fileInput(selectedFile);
        for (int j = 1; j < insertionOutput.size(); j++) {
            // insert each element into the array
            String current = insertionOutput.get(j);
            System.out.println(current);
            int i = j - 1;
            while ((i > -1) && ((insertionOutput.get(i).compareTo(current)) > 0)) {
                insertionOutput.set(i+1, insertionOutput.get(i));
                i--;
            }
            // implement each item + orient
            insertionOutput.set(i+1, current);

        }
        return insertionOutput;
    }

    //Merge sort
    ArrayList<String> mergeOutput;
    //Initial merge sort method to convert selected file into array list and input into real merge sort method
    public ArrayList<String> mergeSort(File selectedFile) {
        mergeOutput = fileInput(selectedFile);
        realMergeSort(mergeOutput);
        return mergeOutput;
    }

    //Part of merge sort that merge splits array in half and adds the different sides into separate arrays
    public ArrayList <String> realMergeSort(ArrayList <String> arrayList) {
        ArrayList <String> mergeSorted;
        ArrayList <String> list = arrayList;

        //If-else to ensure the arraylist is not just one item
        if (list.size() == 1) {
            mergeSorted = list;
        } else {
            int center = list.size()/2;

            //Left and right arrays for separate sections of arraylist
            ArrayList<String> left = new ArrayList<>();
            ArrayList<String> right = new ArrayList<>();
            for (int x = 0; x < center; x++) {  // add elements left
                left.add(list.get(x));
            }
            for (int x = center; x < list.size(); x++) {   // add elements right
                right.add(list.get(x));
            }
            // set separate arraylists
            left = realMergeSort(left);
            right = realMergeSort(right);
            mergeSorted = mergeArray(left,right);
        }
        return mergeSorted;
    }

    //Merged Array (post Sort)
    //Compares left and right sides of array and returns it to previous method for further sorting
    public ArrayList<String> mergeArray(ArrayList<String> left, ArrayList<String> right){
        ArrayList<String> merged = new ArrayList<>();
        int x = 0;
        int y = 0;
        // add elements to left + right array
        while (x<left.size() && y<right.size()) {
            if ((left.get(x)).compareTo(right.get(y))<0) {
                merged.add(left.get(x));
                x++;
            } else {
                merged.add(right.get(y));
                y++;
            }
        }
        while (x < left.size()) {   // add left
            merged.add(left.get(x));
            x++;
        }
        while (y < right.size()) {  // add right
            merged.add(right.get(y));
            y++;
        }
        // return merged sorted list
        mergeOutput = merged;
        return merged;
    }

}