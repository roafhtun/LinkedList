package linkedlist;
/**
 * Array2DTester class
 * This class contains the main method to test and demonstrate the functions of homework5
 * @author Roaf Htun
 */

public class Array2DTester {
    public static void main(String[] args) {
        Array2D linkedList = new Array2D();

        //test addFirstRow
        System.out.println("Add first row: 1 2 3");
        linkedList.addFirstRow(1,2,3);
        System.out.println(linkedList.toString());
       System.out.println(linkedList.toStringColByCol());

        //test addFirstCol
        System.out.println("Add 4 in the first col");
        linkedList.addFirstCol(4);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //test addLastRow
        System.out.println("Add last row: 1 7 8 9");
       linkedList.addLastRow(1,7,8,9);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //test addLastCol
        System.out.println("Add 4 2 in the last col");
        linkedList.addLastCol(4,2);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //add more rows
        System.out.println("Add two more last rows for testing");
        linkedList.addLastRow(5,4,3,2,1);
        linkedList.addLastRow(4,5,6,7,8);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //delete first row
        System.out.println("Delete first row");
        linkedList.deleteFirstRow();
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //delete last row
        System.out.println("Delete last row");
        linkedList.deleteLastRow();
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //add more rows
        System.out.println("Add two more last rows for testing");
        linkedList.addLastRow(6,3,7,8,9);
        linkedList.addLastRow(4,5,6,7,8);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //delete rowindex 2
        System.out.println("Delete rowindex 2");
        linkedList.deleteRow(2);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //delete first col
        System.out.println("Delete first col");
        linkedList.deleteFirstCol();
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //delete last col
        System.out.println("Delete last col");
        linkedList.deleteLastCol();
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //delete col index 1
        System.out.println("Delete col index 1");
        linkedList.deleteCol(1);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //insertCol at index 1
        System.out.println("Insert col: 2 3 4 at index 1");
        linkedList.insertCol(1,2,3,4);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //insertRow at index 1
        System.out.println("Insert row: 1 2 3 at index 1");
        linkedList.insertRow(1,1,2,3);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //getCol 2
        System.out.println("Get col index 1");
        System.out.println(linkedList.getCol(1));

        //getRow 2
        System.out.println("Get row index 1");
        System.out.println(linkedList.getRow(1));

        //get at row 1 col 0
        System.out.println("Get at row 1 col 0");
        System.out.println(linkedList.get(1,0));

        //set at row 1 col 0
        System.out.println("Set at row 1 col 0, to 5");
        linkedList.set(1,0,5);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.toStringColByCol());

        //test 2D array constructor
        System.out.println("Test 2D array constructor");
        Integer[][] testList;
        testList = new Integer[][]{{1,2,3},{4,5,6},{7,8,9}, {10,11,12}};
        System.out.println("create 2D array with numbers and print it");
        //print the 2D array
        for(int i = 0; i < testList.length; i++){
            for(int j = 0; j < testList[i].length; j++){
                System.out.print(testList[i][j] + " ");
            }
            System.out.println();
        }

        Array2D<Integer> linkedList2 = new Array2D<>(testList);
        //print the linked list created from the 2D array
        System.out.println("print the linked list created from the 2D array");
        System.out.println(linkedList2.toString());
        System.out.println(linkedList2.toStringColByCol());
        System.out.println("Row Size : ");
        linkedList2.rowSize();

        System.out.println("Col Size : ");
        linkedList2.colSize();




    }


    public static void testAddFirstRow() {
        System.out.println("Running Test addFirstRow():------------------------\n");
        Array2D array2D = new Array2D();
        array2D.addFirstRow(1, 2, 3);


        String expected = "3 2 1";
        String actual = array2D.toString().trim();
        boolean isCorrect = expected.equals(actual);

        System.out.println("Expected:\t" + expected);
        System.out.println("Actual:\t\t" + actual);
        System.out.println("Correct?:\t" + isCorrect);

        System.out.println("End Test addFirst()-------------------\n");

    }

    public static void testAddLastRow() {
        System.out.println("Running Test addLastRow():------------------------\n");
        Array2D array2D = new Array2D();
        array2D.addLastRow(1, 2, 3);
        array2D.addLastRow(4, 5, 6);

        String expected = "1 2 3";
        expected += "\n4 5 6";
        String actual = array2D.toString().trim();
        boolean isCorrect = expected.equals(actual);

        System.out.println("Expected:\t" + expected);
        System.out.println("Actual:\t" + actual);
        System.out.println("Correct?:\t" + isCorrect);

        System.out.println("End Test addLastRow()-------------------\n");

    }
}
