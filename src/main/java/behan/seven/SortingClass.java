package behan.seven;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class SortingClass {

    //Method to convert excel to array list
    public ArrayList<String> fileInput(File selectedFile){
        ArrayList<String> fileInput = new ArrayList<>();

        try
        {
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(selectedFile);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    //System.out.print(cell.getStringCellValue());
                    fileInput.add(cell.getStringCellValue());
                }
                System.out.println("");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fileInput;
    }

    //Selection Sort method
    public ArrayList<String> selectionSort(File selectedFile){
        ArrayList<String > selectionOutput = fileInput(selectedFile);
        for (int i = 0; i < selectionOutput.size(); i++) {
            String smallest = selectionOutput.get(i);
            int smallestIndex = i;
            for (int j = i; j < selectionOutput.size(); j++) {
                String value = selectionOutput.get(j);
                if (value.compareTo(smallest) < 0) {
                    smallest = value;
                    smallestIndex = j;
                }
            }
            if (smallestIndex != i) {
                String head = selectionOutput.get(i);
                selectionOutput.set(i, smallest);
                selectionOutput.set(smallestIndex, head);
            }
        }return selectionOutput;
    }

    //Insertion Sort
    public ArrayList<String> insertionSort(File selectedFile){
        ArrayList<String> insertionOutput = fileInput(selectedFile);
        for (int j = 1; j < insertionOutput.size(); j++) {
            String current = insertionOutput.get(j);
            System.out.println(current);
            int i = j-1;
            while ((i > -1) && ((insertionOutput.get(i).compareTo(current)) > 0)) {
                insertionOutput.set(i+1, insertionOutput.get(i));
                i--;
            }
            insertionOutput.set(i+1, current);
            //System.out.println(insertionOutput);
        }
        return insertionOutput;
    }

    //Merge sort
    //Initial merge sort method to convert selected file into array list and input into real merge sort method
    ArrayList<String> mergeOutput;

    public ArrayList<String> mergeSort(File selectedFile) {
        mergeOutput = fileInput(selectedFile);
        realMergeSort(mergeOutput);
        return mergeOutput;
    }

    //Merge sort method that actually merge sorts
    public ArrayList <String> realMergeSort(ArrayList <String> arrayList) {
        ArrayList <String> sorted;
        ArrayList <String> list = arrayList;

        if (list.size() == 1) {
            sorted = list;
        } else {
            int mid1 = list.size() /2;

            ArrayList< String > left = new ArrayList< String >();
            ArrayList< String > right = new ArrayList< String >();

            for ( int x = 0; x < mid1; x++) {
                left.add(list.get(x));

            }
            for ( int x = mid1; x < list.size(); x++) {
                right.add(list.get(x));
            }

            System.out.println("Left Array: " + left);
            System.out.println("Right Array)" + right);

            left = realMergeSort(left);
            right = realMergeSort(right);
            sorted = mergeArray(left,right);
        }

        return sorted;
    }

    public ArrayList< String > mergeArray(ArrayList<String> left, ArrayList<String> right){
        ArrayList<String> merged = new ArrayList<>();

        int l = 0;
        int r = 0;

        while (l<left.size() && r<right.size()) {
            if ((left.get(l)).compareTo(right.get(r))<0)
            {
                merged.add(left.get(l));
                l++;
            }
            else {
                merged.add(right.get(r));
                r++;
            }
        }

        while (l < left.size()) {
            merged.add(left.get(l));
            l++;
        }

        // Append rest of the values in the right half, if any...
        while (r < right.size()) {
            merged.add(right.get(r));
            r++;
        }
        mergeOutput = merged;
        return merged;
    }


}

