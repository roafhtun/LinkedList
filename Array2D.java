package linkedlist;
/**
 * Array2D class
 * This class contains necessary methods to create linked list
 * also contains many functions to add,remove,insert to the list.
 * as well as a constructor to create a linked list from a 2D array.
 * @author Roaf Htun
 */

import java.util.ArrayList;

public class Array2D<E> {
    private Array2DNode<E> head;
    private Array2DNode<E> rowTail;
    private Array2DNode<E> colTail;

    private int colSize;
    private int rowSize;

    public Array2D() {
        this.head = null;
        this.rowTail = null;
        this.colTail = null;
        this.colSize = 0;
        this.rowSize = 0;
    }

    /**
     * This constructor creates a linked list from a 2D array.
     * @param values 2D array
     */
    public Array2D(E[][] values) {
       for(int i = 0; i < values.length; i++) {
           //store each row in a temporary array
           E[] array = values[i];
           //add array to last row
           addLastRow(array);
       }

    }

    /**
     * This method adds a column with given values to the left of the list.
     * @param values column values
     */
    public void addFirstCol(E ... values) {
        //error case
        if(this.colSize >= 1) {
            if(values.length != this.rowSize) {
                throw new IllegalArgumentException("The number of values must be equal to the number of rows");
            }
        }
            for (int i = values.length -1; i >= 0; i--) {
                //store the values[i] in value
                E value = values[i];
                //create a new node
                Array2DNode<E> node = new Array2DNode<>(value);
                //empty case
                if (this.isEmpty()) {
                    this.head = node;
                    this.colTail = node;
                    this.rowTail = node;
                    this.rowSize++;
                }
                //if there is already a column
                else if(colSize >= 1){
                    //connect the new to the existing column
                    if(i==values.length-1){
                        node.nextCol = this.head;
                        this.head = node;
                        this.rowTail = node;
                    }
                    //connect rest of the coming nodes
                    if(i < values.length-1){
                        //set the next row and next col af the new node
                        node.nextRow = this.head;
                        node.nextCol = this.head.nextCol;
                        this.head = node;
                        Array2DNode<E> current = this.head;
                        Array2DNode<E> current2 = this.head.nextRow;
                        while(current.nextRow != null){
                            current2.nextCol = current.nextCol.nextRow;
                            current = current.nextRow;
                            current2 = current2.nextRow;
                        }
                    }
                }
                //after adding one node.
                else {
                    node.nextRow = this.head;
                    this.head = node;
                    this.colTail = node;
                    if(this.colSize == 1) {
                        this.rowTail = node;
                    }
                    this.rowSize++;
                }
            }
            this.colSize++;

    }

    /**
     * This method adds a row of values to the top of the list.
     * @param values row values
     */
    public void addFirstRow(E ... values) {
        //error case
        if(this.rowSize >= 1) {
            if(values.length != this.colSize) {
                throw new IllegalArgumentException("The number of values must be equal to the number of columns");
            }
        }
                for (int i = values.length -1; i >= 0; i--) {
                    //store the values[i] in value
                    E value = values[i];
                    //create a new node
                    Array2DNode<E> node = new Array2DNode<>(value);
                    if (this.isEmpty()) {
                        this.head = node;
                        this.rowTail = node;
                        this.colTail = node;
                        this.colSize++;
                    }
                    //if there is already a row
                    else if(rowSize >= 1){
                        //connect the new to the existing row
                        if(i==values.length-1){
                            node.nextRow = this.head;
                            this.head = node;
                            this.colTail = node;
                        }
                        //connect rest of the coming nodes
                        if(i < values.length-1){
                            node.nextCol = this.head;
                            node.nextRow = this.head.nextRow;
                            this.head = node;
                            //set current to head and current2 to its next col
                            Array2DNode<E> current = this.head;
                            Array2DNode<E> current2 = this.head.nextCol;
                            //make connections
                            while(current.nextCol != null){
                                current2.nextRow = current.nextRow.nextCol;
                                current = current.nextCol;
                                current2 = current2.nextCol;
                            }
                        }
                    }
                    //after adding one node.
                    else {
                        node.nextCol = this.head;
                        this.head = node;
                        this.rowTail = node;
                        if(this.rowSize == 1) {
                            this.rowTail = node;
                        }
                        this.colSize++;
                    }
                }

                this.rowSize++;

    }

    /**
     * This method adds a column of values to the right of the list.
     * @param values column values
     */
    public void addLastCol(E ... values) {
        //error case
        if(this.colSize >= 1) {
            if(values.length != this.rowSize) {
                throw new IllegalArgumentException("The number of values must be equal to the number of rows");
            }
        }
        for (int i = values.length -1; i >= 0; i--) {
            E value = values[i];
            Array2DNode<E> node = new Array2DNode<>(value);
            //empty case
            if (this.isEmpty()) {
                this.head = node;
                this.colTail = node;
                this.rowTail = node;
                this.rowSize++;
            }
            //if there is already a column
            else if(colSize >= 1){
                //case for first node
                if(i== values.length-1){
                    //connect col tail nextcol to new node
                    this.colTail.nextCol = node;
                    this.colTail = node;
                }
                //case for rest of the nodes if there are any
                if(i < values.length-1){
                    //connect new node in a row to the col tail
                    node.nextRow = this.colTail;
                    //set heads for jumping one before the last node column top
                    Array2DNode<E> current = this.head;
                    Array2DNode<E> previous = this.head;
                    while(current.nextCol != null) {
                        previous = current;
                        current = current.nextCol;
                    }
                    //connect previous node column to the new node
                    previous.nextCol = node;
                    //set new node as col tail
                    this.colTail = node;
                    //make connections for the rest of the nodes
                    Array2DNode<E> currentTail = this.colTail;
                    while(currentTail.nextRow != null) {
                        previous.nextRow.nextCol = currentTail.nextRow;
                        previous = previous.nextRow;
                        currentTail = currentTail.nextRow;
                    }
                }
            }
            //after adding one node
            else {
                node.nextRow = this.head;
                this.head = node;
                this.colTail = node;
                if(this.colSize == 1) {
                    this.rowTail = node;
                }
                this.rowSize++;
            }
        }
        this.colSize++;

    }

    /**
     * This method adds a row of values to the bottom of the list.
     * @param values row values
     */
    public void addLastRow(E ... values) {
        //error case
        if(this.rowSize >= 1) {
            if(values.length != this.colSize) {
                throw new IllegalArgumentException("The number of values must be equal to the number of columns");
            }
        }
        for (int i = values.length - 1; i >= 0; i--) {
            E value = values[i];
            Array2DNode<E> node = new Array2DNode<>(value);
            //empty case
            if (this.isEmpty()) {
                this.head = node;
                this.colTail = node;
                this.rowTail = node;
                this.colSize++;
            }
            //if there is already a row
            else if(rowSize >= 1){
                //case for first node
                if(i== values.length - 1) {
                    //connect row tail nextrow to new node
                    this.rowTail.nextRow = node;
                    this.rowTail = node;
                }
                //case for rest of the nodes if there are any
                if(i < values.length - 1){
                    //set node next col to row tail
                    node.nextCol = this.rowTail;
                    //set heads for jumping one before the last node row top
                    Array2DNode<E> current = this.head;
                    Array2DNode<E> previous = this.head;
                    while(current.nextRow != null) {
                        previous = current;
                        current = current.nextRow;
                    }
                    //connect previous next row to the new node
                    previous.nextRow = node;
                    //set that to rowtail
                    this.rowTail = node;
                    Array2DNode<E> currentTail = this.rowTail;
                    //while nextcol is not null
                    while(currentTail.nextCol != null) {
                        //connect previous next col next row to current tail next col
                        previous.nextCol.nextRow = currentTail.nextCol;
                        //move forward
                        previous = previous.nextCol;
                        currentTail = currentTail.nextCol;
                    }

                }
            }
            //after adding one node
            else {
                node.nextCol = this.head;
                this.head = node;
                this.rowTail = node;
                if(this.rowSize == 1) {
                    this.colTail = node;
                }
                this.colSize++;
            }
        }
        this.rowSize++;
    }


    /**
     * This method adds a row of given values to the given index row in the list.
     * @param index index of the row
     * @param values row values
     */
    public void insertRow(int index, E ... values) {
        //error cases
        if (index < 0 || index > this.rowSize) {
            throw new IllegalArgumentException("The index must be between 0 and " + (this.rowSize));
        }
        if (this.rowSize >= 1) {
            if (values.length != this.colSize) {
                throw new IllegalArgumentException("The number of values must be equal to the number of columns");
            }
        }
        //if the index is 0
        if(index == 0) {
            this.addFirstRow(values);
        }
        //if the index is row size
        else if(index == this.rowSize) {
            this.addLastRow(values);
        }
        //if the index is elsewhere
        else {
            for(int i = values.length -1; i >= 0; i--) {
                E value = values[i];
                Array2DNode<E> node = new Array2DNode<>(value);
                //for the first node
                if(i == values.length - 1) {
                    //jump one row before the index
                    Array2DNode<E> current = this.head;
                    int currentIndex = 0;
                    while(currentIndex < (index-1)) {
                        current = current.nextRow;
                        currentIndex++;
                    }
                    //conect new node next row to current next row
                    node.nextRow = current.nextRow;
                    //change current next row to node
                    current.nextRow = node;
                }
                //for the rest of the nodes if there are any
                if(i < values.length - 1) {
                    //jump one row before the index
                    Array2DNode<E> current = this.head;
                    int currentIndex = 0;
                    while(currentIndex < (index -1)) {
                        current = current.nextRow;
                        currentIndex++;
                    }
                    //connect new node next col to current next row (horizontal connection)
                    node.nextCol = current.nextRow;
                    //connect new node next row to current next next row (vertical connection)
                    node.nextRow = current.nextRow.nextRow;
                    //set current next row to node
                    current.nextRow = node;
                    Array2DNode<E> newRow = node;
                    //go horizontally to the end of the row
                    while(current.nextCol != null && newRow.nextCol != null) {
                        //upperrow connection to new row
                        current.nextCol.nextRow = newRow.nextCol;
                        //new row bottom connection to existing row
                        newRow.nextCol.nextRow = newRow.nextRow.nextCol;
                        current = current.nextCol;
                        newRow = newRow.nextCol;


                    }
                }
            }
            this.rowSize++;
        }

    }

    /**
     * This method adds a column of given values to the given index column in the list.
     * @param index index of the column
     * @param values column values
     */
    public void insertCol(int index, E ... values) {
        //error cases
        if (index < 0 || index > this.colSize) {
            throw new IllegalArgumentException("The index must be between 0 and " + (this.colSize));
        }
        if (this.colSize >= 1) {
            if (values.length != this.rowSize) {
                throw new IllegalArgumentException("The number of values must be equal to the number of rows");
            }
        }
        //if the index is 0
        if (index == 0) {
            this.addFirstCol(values);
        }
        //if the index is col size
        else if (index == this.colSize) {
            this.addLastCol(values);
        }
        //if the index is elsewhere
        else {
            for (int i = values.length -1; i >= 0; i--) {
                E value = values[i];
                Array2DNode<E> node = new Array2DNode<>(value);
                //for the first node
                if (i == values.length - 1) {
                    //jump one col before the index
                    Array2DNode<E> current = this.head;
                    int currentIndex = 0;
                    while (currentIndex < (index - 1)) {
                        current = current.nextCol;
                        currentIndex++;
                    }
                    //conect new node next col to current next col
                    node.nextCol = current.nextCol;
                    //change current next col to node
                    current.nextCol = node;
                }
                //for the rest of the nodes if there are any
                if (i < values.length - 1) {
                    //jump one col before the index
                    Array2DNode<E> current = this.head;
                    int currentIndex = 0;
                    while (currentIndex < (index - 1)) {
                        current = current.nextCol;
                        currentIndex++;
                    }
                    //connect new node next row to current next col (vertical connection)
                    node.nextRow = current.nextCol;
                    //connect new node next col to current next next col (horizontal connection)
                    node.nextCol = current.nextCol.nextCol;
                    //set current next col to node
                    current.nextCol = node;
                    Array2DNode<E> newCol = node;
                    //go vertically to the end of the col
                    while (current.nextRow != null && newCol.nextRow != null) {
                        //make column connection top to bottom for current next row nextcol
                        //to new col next row
                        current.nextRow.nextCol = newCol.nextRow;
                        //make column connection top to bottom for new col next row nextcol
                        //to the existing right side nodes
                        newCol.nextRow.nextCol = newCol.nextCol.nextRow;
                        current = current.nextRow;
                        newCol = newCol.nextRow;
                    }
                }
            }
            this.colSize++;
        }
    }

    /**
     * This method delete the row of values at the top of the list.
     */
    public void deleteFirstRow(){
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        else{
            //change head to current head next row
            this.head = this.head.nextRow;
            //change col tail to current col tail next row
            this.colTail = this.colTail.nextRow;
        }
        this.rowSize--;
    }

    /**
     * This method delete the row of values at the bottom of the list.
     */
    public void deleteLastRow(){
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        else{
            //jump to the row before last row
            Array2DNode<E> current = this.head;
            Array2DNode<E> previous = this.head;
            while(current.nextRow != null){
                previous = current;
                current = current.nextRow;
            }
            //set its next row to null
            previous.nextRow = null;
            //set rowtail to previous
            this.rowTail = previous;
            //go from left to right
            while(previous.nextCol != null){
                //set the rest of its next col next row to null
                previous.nextCol.nextRow = null;
                previous = previous.nextCol;
            }

        }
        this.rowSize--;
    }

    /**
     * This method delete the column of values at the left of the list.
     */
    public void deleteFirstCol(){
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        else{
            //change head to current head next col
            this.head = this.head.nextCol;
            //change row tail to current row tail next col
            this.rowTail = this.rowTail.nextCol;
            }
            this.colSize--;
    }

    /**
     * This method delete the column of values at the right of the list.
     */
    public void deleteLastCol(){
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        else{
            //jump to the col before last col
            Array2DNode<E> current = this.head;
            Array2DNode<E> previous = this.head;
            while(current.nextCol != null){
                previous = current;
                current = current.nextCol;
            }
            //set its next col to null
            previous.nextCol = null;
            //set coltail to previous
            this.colTail = previous;
            //go from top to bottom
            while(previous.nextRow != null){
                //set rest of its next row next col to null
                previous.nextRow.nextCol = null;
                previous = previous.nextRow;
            }

        }
        this.colSize--;
    }

    /**
     * This method deletes the row of values at the specified index.
     * @param index the index of the row to be deleted
     */
    public void deleteRow(int index){
        //error cases
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        if(index < 0 || index >= this.rowSize) {
            throw new IllegalArgumentException("The index is out of bound");
        }
        //if index is 0 delete first row
        if(index == 0){
            this.deleteFirstRow();
        }
        //if index is last row delete last row
        else if(index == this.rowSize - 1){
            this.deleteLastRow();
        }
        else{
            //jump to the row before the index
            Array2DNode<E> current = this.head;
            Array2DNode<E> previous = this.head;
            int currentIndex = 0;
            while(currentIndex < index){
                previous = current;
                current = current.nextRow;
                currentIndex++;
            }
            //connect previous next row to currnent next row (current will unlinked)
            previous.nextRow = current.nextRow;

            //go from left to right
            while(previous.nextCol != null){
                //connect previous next col next row connection to
                //current next col next row
                previous.nextCol.nextRow = current.nextCol.nextRow;
                previous = previous.nextCol;
                current = current.nextCol;
            }
            this.rowSize--;
        }
    }

    /**
     * This method deletes the column of values at the specified index.
     * @param index the index of the column to be deleted
     */
    public void deleteCol(int index){
        //error cases
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        if(index < 0 || index >= this.colSize) {
            throw new IllegalArgumentException("The index is out of bound");
        }
        //if index is 0 delete first col
        if(index == 0){
            this.deleteFirstCol();
        }
        //if index is last col delete last col
        else if(index == this.colSize - 1){
            this.deleteLastCol();
        }
        else{
            //jump to the col before the index
            Array2DNode<E> current = this.head;
            Array2DNode<E> previous = this.head;
            int currentIndex = 0;
            while(currentIndex < index){
                previous = current;
                current = current.nextCol;
                currentIndex++;
            }
            //connect previous next col to currnent next col (current will unlinked)
            previous.nextCol = current.nextCol;
            //go from top to bottom to make rest of column connections
            while(previous.nextRow != null){
                previous.nextRow.nextCol = current.nextRow.nextCol;
                previous = previous.nextRow;
                current = current.nextRow;
            }
            this.colSize--;
        }
    }

    /**
     * This method returns the value at the specified row and column.
     * @param rowIndex the row index of the value to be returned
     * @param colIndex the column index of the value to be returned
     * @return the value at the specified row and column
     */
    public E get(int rowIndex, int colIndex){
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        if(rowIndex < 0 || rowIndex >= this.rowSize) {
            throw new IllegalArgumentException("The row index is out of bound");
        }
        if(colIndex < 0 || colIndex >= this.colSize) {
            throw new IllegalArgumentException("The col index is out of bound");
        }
        Array2DNode<E> current = this.head;
        E item;
        int currentRow = 0;
        while(currentRow < rowIndex){
            current = current.nextRow;
            currentRow++;
        }
        int currentCol = 0;
        while(currentCol < colIndex){
            current = current.nextCol;
            currentCol++;
        }
        item = current.item;
        return item;
    }


    /**
     * This method returns the column of values at the specified index as an array list.
     * @param colIndex the column index of the column to be returned
     * @return the column of values at the specified index as an array list
     */
    public ArrayList<E> getCol(int colIndex){
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        if(colIndex < 0 || colIndex >= this.colSize) {
            throw new IllegalArgumentException("The col index is out of bound");
        }
        Array2DNode<E> current = this.head;
        int currentCol = 0;
        //jump to col index
        while(currentCol < colIndex){
            current = current.nextCol;
            currentCol++;
        }
        //create array list
        ArrayList<E> column = new ArrayList<E>();
        while(current != null){
            //add value of current node to array list
            column.add(current.item);
            //move to next row
            current = current.nextRow;
        }
        return column;
    }

    /**
     * This method returns the row of values at the specified index as an array list.
     * @param rowIndex the row index of the row to be returned
     * @return the row of values at the specified index as an array list
     */
    public ArrayList<E> getRow(int rowIndex){
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        if(rowIndex < 0 || rowIndex >= this.rowSize) {
            throw new IllegalArgumentException("The row index is out of bound");
        }
        Array2DNode<E> current = this.head;
        int currentRow = 0;
        //jump to the row index
        while(currentRow < rowIndex){
            current = current.nextRow;
            currentRow++;
        }
        //create array list
        ArrayList<E> row = new ArrayList<E>();
        while(current != null){
            //add value of current node to array list
            row.add(current.item);
            //move to next column
            current = current.nextCol;
        }
        return row;
    }

    /**
     * This method set a given item at the specified row and column.
     * @param rowIndex the row index of the item to be set
     * @param colIndex the column index of the item to be set
     * @param item the item to be set
     */
    public void set(int rowIndex, int colIndex, E item){
        //error cases
        if(this.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }
        if(rowIndex < 0 || rowIndex >= this.rowSize) {
            throw new IllegalArgumentException("The row index is out of bound");
        }
        if(colIndex < 0 || colIndex >= this.colSize) {
            throw new IllegalArgumentException("The column index is out of bound");
        }
        Array2DNode<E> current = this.head;
        int currentRow = 0;
        //jump to row index
        while(currentRow < rowIndex){
            current = current.nextRow;
            currentRow++;
        }
        //jump to col index
        int currentCol = 0;
        while(currentCol < colIndex){
            current = current.nextCol;
            currentCol++;
        }
        //set item
        current.item = item;
    }

    /**
     * This method checks if the list is empty.
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        if(this.head == null & this.colTail == null & this.rowTail == null) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the column size of the list.
     */
    public void colSize(){
        System.out.println(this.colSize);
    }

    /**
     * This method returns the row size of the list.
     */
    public void rowSize(){
        System.out.println(this.rowSize);
    }

    //print rows and columns
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Array2DNode<E> current = this.head;
        Array2DNode<E> current2 = this.head;

        if(colSize >= 2){
            while (current != null) {
                sb.append(current + " ");
                current = current.nextCol;
                if(current.nextCol == null) {
                    sb.append(current + " ");
                    sb.append("\n");
                    current = current2.nextRow;
                    current2 = current2.nextRow;
                }
            }
        }
        else{
            while (current != null) {
                sb.append(current + "\n");
                current = current.nextRow;
            }
        }
        return sb.toString();
    }

    public String toStringColByCol() {
        StringBuilder sb = new StringBuilder();
        Array2DNode<E> current = this.head;
        Array2DNode<E> current2 = this.head;

        if(rowSize>=2){
            while (current != null) {
                sb.append(current + " ");
                current = current.nextRow;
                if(current.nextRow == null) {
                    sb.append(current + " ");
                    sb.append("\n");
                    current = current2.nextCol;
                    current2 = current2.nextCol;
                }
            }
        }
        else{
            while (current != null) {
                sb.append(current + " ");
                current = current.nextCol;
            }
        }
        return sb.toString();
    }
}
