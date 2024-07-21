package com.minesweeper.Player;

import com.minesweeper.model.Game;
import com.minesweeper.model.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;


public class Player {
    private Game game;
    private Model model = new Model();
    private BoolVar[][] cellVars;
    public Player(Game game) {
        this.game = game;
    }

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

    public void getHint() {
        for (int row = 0; row < game.getRows(); row++) {
            for (int column = 0; column < game.getColumns(); column++) {
                Cell cell = game.getCell(row, column);
                if (!cell.isRevealed() && cell.isMine()) {
                    cell.flag();
                }
            }
        }
    }

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

    public void firstMove() {
        Random random = new Random();
        int row = random.nextInt(game.getRows());

        int column = random.nextInt(game.getColumns());
        game.revealCell(game.getCell(row, column));
    }
}
