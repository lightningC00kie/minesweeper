package com.minesweeper.view;

import com.minesweeper.controller.GameController;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class GamePanel extends JPanel {
    private GameController gameController;

    public GamePanel(int rows, int cols, GameController gameController) {
        this.gameController = gameController;
        setLayout(new GridLayout(rows, cols));
        initializeGrid(rows, cols);
    }
    
    private void initializeGrid(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                CellButton cellButton = new CellButton(row, column, this.gameController);
                add(cellButton);
            }
        }
    }


    public CellButton getCellButton(int row, int column) {
        for (int i = 0; i < getComponentCount(); i++) {
            CellButton cellButton = (CellButton) getComponent(i);
            if (cellButton.getRow() == row && cellButton.getColumn() == column) {
                return cellButton;
            }
        }
        return null;
    }
    // Additional methods to handle game logic and UI updates
}