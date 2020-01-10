/**
 * Every living cell dies if they have less than two living neighbours.
 * Every living cell keeps on living during the following iteration (i.e. turn) if they have two or three living neighbours.
 * Every living cell dies if they have more than three living neighbours.
 * Every dead cell is turned back to life if they have exactly three living neighbours.
 *
 */
package game;

import gameoflife.Simulator;

public class Main {

    public static void main(String[] args) {
        PersonalBoard board = new PersonalBoard(100, 100);
        board.initiateRandomCells(0.7);

        Simulator simulator = new Simulator(board);
        simulator.simulate();
    }
}
