package com.minesweeper.Player;

import com.minesweeper.model.Game;
import com.minesweeper.model.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;

/**
 * The Player class represents a player in the Minesweeper game who uses constraint satisfaction
 * to solve the game. It interacts with the game board and uses a CSP solver to make decisions.
 */
public class Player {
    private Game game;
    private Model model = new Model();
    private BoolVar[][] cellVars;

    /**
     * Constructs a Player with the specified game.
     *
     * @param game The Minesweeper game instance that the player will interact with.
     */
    public Player(Game game) {
        this.game = game;
    }

    /**
     * Starts the game-playing process. The player will continuously make moves until the game is over
     * or won. It uses a constraint satisfaction problem (CSP) solver to determine safe moves and
     * flag mines. The player will reveal safe cells and flag mines based on the CSP solution.
     * If the solver cannot find a solution, the player will ask for a hint which flags a mine cell for the player.
     * The game will end when the player wins or loses.
     */
    public void play() {
        getHint();
        while (!game.GameOver() && !game.isWon()) {
            setVariables(); // Initialize or update CSP variables based on the game state
            setConstraints();
    
            if (model.getSolver().solve()) {
                // If the model finds a solution, make moves based on that solution
                // Reveal safe cells and flag mines as determined by the CSP solver
                for (int row = 0; row < cellVars.length; row++) {
                    for (int column = 0; column < cellVars[row].length; column++) {
                        // Check if the cell is already revealed to avoid unnecessary actions
                        Cell cell = game.getCell(row, column);
                        if (!cell.isRevealed()) {
                            if (cellVars[row][column].getValue() == 0) {
                                // The solver determined this cell is safe (no mine), so reveal it
                                game.revealCell(cell);
                            } else {
                                cell.flag();
                            }
                        }
                    }
                }
            } else {
                getHint();
            }

        }
        if (game.isWon()) {
            printBoard();
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    /**
     * Asks for a hint by flagging a mine cell on the board.
     */
    public void getHint() {
        for (int row = 0; row < game.getRows(); row++) {
            for (int column = 0; column < game.getColumns(); column++) {
                Cell cell = game.getCell(row, column);
                if (!cell.isRevealed() && cell.isMine()) {
                    cell.flag();
                    return;
                }
            }
        }
    }

    /**
     * Prints the current state of the game board to the console.
     * Revealed cells display their mine count, flagged cells display 'F', and unrevealed cells display 'X'.
     * This method provides a visual representation of the game board for debugging or informational purposes.
     */
    private void printBoard() {
        for (int row = 0; row < game.getRows(); row++) {
            for (int column = 0; column < game.getColumns(); column++) {
                Cell cell = game.getCell(row, column);
                if (cell.isRevealed()) {
                    System.out.print(cell.getCount() + " ");
                } else if (cell.isFlagged()) {
                    System.out.print("F ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Initializes the CSP variables for each cell in the game board.
     * This method sets up the boolean variables representing whether each cell contains a mine.
     * It also assigns values to these variables based on the current state of the game board.
     * If a cell is already revealed, its variable is set to false.
     * If a cell is flagged, its variable is set to true.
     * If a cell is neither revealed nor flagged, its variable is left unassigned.
     * The variables are stored in a 2D array for easy access.
     * The variables are named based on their coordinates (e.g., cell_0_0, cell_0_1, etc.).
     * The variables are used to create constraints for the CSP solver.
     * The constraints are based on the revealed cells and their surrounding mines.
     * The constraints ensure that the number of surrounding mines matches the count on the revealed cell.
     */
    private void setVariables() {
        // Set the variables for the model
        int rows = game.getRows();
        int columns = game.getColumns();
        cellVars = new BoolVar[rows][columns];

        // Initialize each cell variable with a unique name based on its coordinates
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cellVars[row][column] = model.boolVar("cell_" + row + "_" + column);
                Cell curCell = game.getCell(row, column);
                if (curCell.isRevealed()) {
                    // If the cell is already revealed, set its value to false
                    cellVars[row][column] = model.boolVar(false);
                }
                if (curCell.isFlagged()) {
                    // If the cell is flagged, set its value to true
                    cellVars[row][column] = model.boolVar(true);
                }
            }
        }
    }

    /**
     * Sets the constraints for the CSP model based on the revealed cells and their surrounding mines.
     * This method creates constraints to ensure that the number of surrounding mines matches the count on the revealed cell.
     * It iterates through each revealed cell and checks its surrounding cells to create constraints.
     * The constraints are added to the model to be solved by the CSP solver.
     */
    private void setConstraints() {
        int rows = game.getRows();
        int columns = game.getColumns();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.println("Checking cell: " + row + ", " + column);
                Cell curCell = game.getCell(row, column);
                System.out.println("Checking cell: " + row + ", " + column);
                if (curCell.isRevealed() && !curCell.isMine()) {
                    int numberOnCell = game.getCell(row, column).getCount();
                    List<BoolVar> surroundingCells = new ArrayList<>();

                    // Check all surrounding cells
                    for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, rows - 1); i++) {
                        for (int j = Math.max(column - 1, 0); j <= Math.min(column + 1, columns - 1); j++) {
                            if (i == row && j == column) continue; // Skip the current cell
                            if (!game.getCell(i, j).isRevealed()) {
                                surroundingCells.add(cellVars[i][j]);
                            }
                        }
                    }

                    // Convert List to array for the constraint method
                    
                    BoolVar[] surroundingCellsArray = surroundingCells.toArray(new BoolVar[0]);
                    // Create a sum constraint
                    if (surroundingCellsArray.length > 0) {
                        model.sum(surroundingCellsArray, "=", numberOnCell).post();
                    } else {
                        System.out.println("here");
                    }
                }
            }
        }
    }
}
