package hexagonboard;
/**
 * Hexagon class
 * This class necessary variable, getters, setters for the Hexagon object and also
 * contains a method to rotate(shift) the array of Hexagon.
 * @author Roaf Htun
 */

public class Hexagon {
    private int tileNo;
    private char[] colors;
    private int usedTile;
    private int solutionNumber;

    /**
     * Constructor for the Hexagon object
     * @param tileNo Tile number
     * @param colors colors in the tiles
     */

    public Hexagon(int tileNo, char[] colors) {
        this.tileNo = tileNo;
        this.colors = colors;
        this.usedTile = 0;
        this.solutionNumber = 0;
    }

    public int getSolutionNumber() {
        return solutionNumber;
    }

    public void incrementSolutionNumber() {
        solutionNumber++;
    }

    //get tile number
    public int getTileNo() {
        return tileNo;
    }

    //get color
    public char getColor(int index) {
        return colors[index];
    }

    public void setUsedTile(int indicator) {
        usedTile = indicator;
    }

    public int getUnUsedTile() {
        return usedTile;
    }

    @Override
    public String toString() {
        String result = "";
        result+= "Tile: " + tileNo + " Colors: ";
        for (int i = 0; i < colors.length; i++) {
            result += colors[i] + " ";
        }

        return result;
    }

    /**
     * This method shifts the array of Hexagon object by one position to right
     */
    public void rotate() {
        char[] rightShifted = new char[colors.length];
        for (int i = 0; i < colors.length; i++) {
            rightShifted[i] = colors[(i - 1 + colors.length) % colors.length];
        }
        colors = rightShifted;
    }

}
