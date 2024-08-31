package com.minesweeper.view;

import com.minesweeper.controller.GameController;

import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * The GamePanel class represents the panel where the Minesweeper game grid is displayed.
 * It initializes the grid with CellButton components and provides methods to interact with them.
 */
public class GamePanel extends JPanel {
    private GameController gameController;

     /**
     * Constructs a new GamePanel with the specified number of rows and columns.
     * Initializes the grid layout and populates it with CellButton components.
     *
     * @param rows           The number of rows in the grid.
     * @param cols           The number of columns in the grid.
     * @param gameController The game controller to handle game logic.
     */
    public GamePanel(int rows, int cols, GameController gameController) {
        this.gameController = gameController;
        setLayout(new GridLayout(rows, cols));
        initializeGrid(rows, cols);
    }
    
    /**
     * Initializes the grid layout and populates it with CellButton components.
     *
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     */
    private void initializeGrid(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                CellButton cellButton = new CellButton(row, column, this.gameController);
                add(cellButton);
            }
        }
    }

    /**
     * Returns the CellButton component at the specified row and column.
     *
     * @param row    The row of the CellButton.
     * @param column The column of the CellButton.
     * @return The CellButton component at the specified row and column, or null if not found.
     */
    public CellButton getCellButton(int row, int column) {
        for (int i = 0; i < getComponentCount(); i++) {
            CellButton cellButton = (CellButton) getComponent(i);
            if (cellButton.getRow() == row && cellButton.getColumn() == column) {
                return cellButton;
            }
        }
        return null;
    }
}