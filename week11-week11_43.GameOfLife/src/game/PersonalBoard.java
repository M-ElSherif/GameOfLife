package game;

import gameoflife.GameOfLifeBoard;
import java.util.Random;

/**
 *
 * @author thesh
 */
public class PersonalBoard extends GameOfLifeBoard {

    public PersonalBoard(int width, int height) {
        super(width, height);
    }

    // METHOD. Returns number of cells required alive based on probability and
    // board's height and width
    public double probableCellsAlive(double probabilityForEachCell) {
        boolean[][] board = super.getBoard();
        double cellsAlive = super.getHeight() * super.getWidth() * probabilityForEachCell;
        System.out.println(cellsAlive);
        return cellsAlive;
    }

    // METHOD. Returns a randomly selected cell based on the board's width and 
    // height.
    public int[] randomCellSelector(double numberOfCells) {
        Random r = new Random();
        int boardWidth = super.getWidth();
        int boardHeight = super.getHeight();
        int randomX = r.nextInt(boardWidth);
        int randomY = r.nextInt(boardHeight);
        int[] cellCoords = {randomX, randomY};
        return cellCoords;
    }

    // INHERITED METHOD. Initiates all the cells of the board. Every cell is 
    // alive with a probability of parameter probabilityForEachCell. 
    // The probability is a double value between [0, 1]. 
    @Override
    public void initiateRandomCells(double probabilityForEachCell) {
        boolean[][] board = super.getBoard();
        double cellsAlive = this.probableCellsAlive(probabilityForEachCell); // retrieve the required number of cells alive based on probability
        int i = 1;  // loop counter
        int[] cellCoords = {};  // current cell coords
        while (true) {
            // break if counter exceeds number of cells required alive
            if (i > cellsAlive) {
                break;
            }
            cellCoords = this.randomCellSelector(cellsAlive);   // sets current cell to a random cell within board
            int x = cellCoords[0];  // x coord is first index of cellCoords
            int y = cellCoords[1];  // y coord is second index of cellCoords
            // if no coord is repeated and selected cell is not already alive
            if (board[x][y] != true) {
                board[x][y] = true;
                i++;
            }
        }
        // if probability is 1, all cells alive
        // if probability is 0, all cells dead
        for (int y = 0; y < super.getHeight(); y++) {
            for (int x = 0; x < super.getWidth(); x++) {
                if (probabilityForEachCell == 1.0) {
                    board[x][y] = true;
                }
                if (probabilityForEachCell == 0.0) {
                    board[x][y] = false;
                }
            }
        }
    }

    // INHERITED METHOD. Check if cell at specified coord is alive
    @Override
    public boolean isAlive(int x, int y) {
        if (insideTheBoard(x, y) && super.getBoard()[x][y] == true) {
            return true;
        }
        return false;
    }

    // METHOD. Turn cell at parameter coords to alive. Does nothing if 
    // outside board dimensions
    @Override
    public void turnToLiving(int x, int y) {
        if (insideTheBoard(x, y)) {
            boolean[][] board = super.getBoard();
            board[x][y] = true;
        }
    }

    // METHOD. Turn cell at parameter coords to dead. Does nothing if outside 
    // board dimensions
    @Override
    public void turnToDead(int x, int y) {
        if (insideTheBoard(x, y)) {
            super.getBoard()[x][y] = false;
        }
    }

// ABSTRACT METHOD. Retrieves the number of living neighbouring cells
//@Override
//        public int getNumberOfLivingNeighbours(int x, int y) {
//        int numberOfLivingNeighbours = 0;
//        for (int i = x - 1; i <= x + 1; i++) {
//            for (int j = y - 1; j <= y + 1; j++) {
//                if (isAlive(i, j)) {
//                    numberOfLivingNeighbours++;
//                }
//            }
//        }
//        if (isAlive(x, y)) {
//            numberOfLivingNeighbours--; //take 1 to account for original cell if alive
//        }
//        return numberOfLivingNeighbours;
//    }

    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
        // counter that counts number of neighbouring cells alive
        int count = 0;
        // if (observed cell is within board bounds) {
        try {
            // if (cell is alive) {
            if (this.isAlive(x - 1, y - 1) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (this.isAlive(x - 1, y) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (this.isAlive(x, y - 1) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (this.isAlive(x + 1, y + 1) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (this.isAlive(x + 1, y) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (this.isAlive(x, y + 1) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (this.isAlive(x - 1, y + 1) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (this.isAlive(x + 1, y - 1) == true) {
                count++;
            }
        } catch (Exception e) {
        }
        return count;
    }
    
    // ABSTRACT METHOD. Manages the cell life based on number of living neighbours 
    // it has
    @Override
        public void manageCell(int x, int y, int livingNeighbours) {
        if (livingNeighbours < 2 || livingNeighbours > 3) {
            turnToDead(x, y);
        } else if (livingNeighbours == 3) {
            turnToLiving(x, y);
        }
    }

    public boolean insideTheBoard(int x, int y) {
        if (x < 0 || y < 0 || x >= super.getWidth() || y >= super.getHeight()) {
            return false;
        }

        return true;
    }
}
