package hexagonboard;
/**
 * Main class
 * This class contains the main method to test out the homework 4.
 * @author Roaf Htun
 */

public class Main {
    public static void main(String[] args) {
        String filePath = "files/tile3-1.txt";
        Hexagon[] tiles = FileReader.readTiles(filePath);
        Board solveBoard = new Board(tiles);
    }
}
