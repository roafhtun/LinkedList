package hexagonboard;
/**
 * Board class
 * This class contains recursive methods and printing the board, also checking if colors match.
 * All methods contained in the class is to help solve hexagon puzzle.
 * @author Roaf Htun
 */

public class Board {

    public Board(Hexagon[] tiles){
        solveBoard(tiles);
    }

    /**
     * this method checks if the color of the center tile matches with color of surrounding tiles
     * also checks if the surrounding tiles have matching colors in adjacent
     * @param board The hexagon board
     */
    private boolean isValid(Hexagon[] board) {

        //check if center tile match with other tiles
        if(board[1] != null && board[0].getColor(0) != board[1].getColor(3)){
            return false;
        }
        if(board[2] != null && board[0].getColor(1) != board[2].getColor(4)){
            return false;
        }
        if(board[3] != null && board[0].getColor(2) != board[3].getColor(5)){
            return false;
        }
        if(board[4] != null && board[0].getColor(3) != board[4].getColor(0)){
            return false;
        }
        if(board[5] != null &&  board[0].getColor(4) != board[5].getColor(1)){
            return false;
        }
        if(board[6] != null && board[0].getColor(5) != board[6].getColor(2)){
            return false;
        }

        //check if match surrounding tiles with each other
        if(board[1] != null && board[2] != null && board[1].getColor(2) != board[2].getColor(5)){
            return false;
        }
        if(board[2] != null && board[3] != null && board[2].getColor(3) != board[3].getColor(0)){
            return false;
        }
        if(board[3] != null && board[4] != null && board[3].getColor(4) != board[4].getColor(1)){
            return false;
        }
        if(board[4] != null && board[5] != null && board[4].getColor(5) != board[5].getColor(2)){
            return false;
        }
        if(board[5] != null && board[6] != null && board[5].getColor(0) != board[6].getColor(3)){
            return false;
        }
        if(board[6] != null && board[1] != null && board[6].getColor(1) != board[1].getColor(4)){
            return false;
        }

        //otherwise, return true
        return true;
    }

    /**
     * This is a recursive method that solves the board by
     * placing tiles in the center and calling to recursive helper to solve further.
     * @param tiles the tiles information read from the tile file
     */
    private void solveBoard(Hexagon[] tiles){
        //create a Hexagon board to put tiles in
        Hexagon[] board = new Hexagon[7];

        //for loop to try each tile at center board[0]

        for(int i=0; i<tiles.length; i++){

            //set the first tile at board[0] index
            board[0] = tiles[i];
            //set tile to use
            tiles[i].setUsedTile(1);
            //recurse with board incremented to set up board[1]
            solveBoard(tiles, board, 1);
            //backtrack
            //set tile to unused
            tiles[i].setUsedTile(0);
        }
        //if no solution, print out no solution
        if(tiles[0].getSolutionNumber() == 0){
            System.out.println("No solution");
        }


    }

    /**
     * This is a recursive helper method which places tiles in the surrounding the center
     * and checking if colors match with each other recursively, also rotating the tiles if needed.
     * @param tiles the tiles information read from the tile file
     * @param board the hexagon board
     * @param boardIndex the board index
     */
    private void solveBoard(Hexagon[] tiles, Hexagon[] board, int boardIndex) {
        if(boardIndex == board.length){
            if (isValid(board)) {
                tiles[0].incrementSolutionNumber();
                printBoard(board, tiles);
            }
        }
        else{
            for (int i = 0; i < tiles.length; i++) {
                //look for tiles that are not used
                if(tiles[i].getUnUsedTile() == 0){
                    //set the tile at board[boardIndex]
                    board[boardIndex] = tiles[i];
                    //set the tile to used
                    tiles[i].setUsedTile(1);
                    //check if colors match
                    if (isValid(board)) {
                        //if match, recurse to the next board index
                        solveBoard(tiles, board, boardIndex + 1);
                    }
                    //if colors do not match, rotate tile and check again with 5 rotations.
                    for (int j = 0; j <5; j++) {
                        tiles[i].rotate();
                        if(isValid(board)){
                            solveBoard(tiles, board, boardIndex + 1);
                        }

                    }
                    //backtracking
                    //remove the board[boardIndex] tile
                    board[boardIndex] = null;
                    //set tile to unused
                    tiles[i].setUsedTile(0);
                    //rotate tile to default position
                    tiles[i].rotate();
                }
            }

        }
    }

    /**
     * This method prints out the board with the solution number
     * @param board the hexagon board
     * @param tiles the tiles information read from the tile file
     */
    private void printBoard(Hexagon[] board, Hexagon[] tiles){
        System.out.println("Solution #" + tiles[0].getSolutionNumber() + "----------------------------------------");
        System.out.println("                       SA SB SC SD SE SF");
        for(int i=0; i<board.length; i++){
            System.out.print("Position " + (i+1)  + " Tile #" + board[i].getTileNo() + ":    ");

            for(int j=0; j<6; j++){
                System.out.print(board[i].getColor(j) +"  ");
            }
            System.out.println();

        }
        System.out.println("--------------------------------------------------");
    }


}







