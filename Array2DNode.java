package linkedlist;
/**
 * Array2DNode class
 * This class is used to create a node and store the data in the node
 * with nextRow and nextCol.
 * @author Roaf Htun
 */

public class Array2DNode<E> {
    protected E item;
    protected Array2DNode<E> nextRow;
    protected Array2DNode<E> nextCol;

    /**
     * Constructor
     * @param item item to be stored in the node
     *             with nextRow and nextCol set to null
     */
    public Array2DNode(E item) {
        this.item = item;
        this.nextRow = null;
        this.nextCol = null;
    }


    /**
     * toString method
     * @return String of the item
     */
    @Override
    public String toString() {
        return this.item.toString();
    }
}
