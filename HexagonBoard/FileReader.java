package hexagonboard;
/**
 * FileReader class
 * This class contains a method that reads the set of tile file, parse informations,
 * and creates tile object array to store them.
 * @author Roaf Htun
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    /**
     * This method reads the set of tile file, parse informations, and creates tile object array to store them.
     * @param inFile the file to be read
     * @return the tile array
     */
    public static Hexagon[] readTiles(String inFile) {
        File file = new File(inFile);
        Hexagon[] tiles = new Hexagon[7];
        try{
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                for(int i = 0; i < tiles.length; i++) {
                    String line = reader.nextLine();

                    String[] tokens = line.split(" ");

                    //parse tile numbers
                    int tileNo = Integer.parseInt(tokens[1].replaceAll(":", ""));
                    //parse colors
                    String colors = tokens[2].replaceAll(",", "");
                    //Put colors into char array
                    char colorArrays[] = colors.toCharArray();

                    tiles[i] = new Hexagon(tileNo, colorArrays);

                }
            }
            reader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        return tiles;

    }
}
