package com.minesweeper.view;

import javax.swing.JButton;

import com.minesweeper.controller.GameController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

/**
 * The CellButton class represents a button in the Minesweeper game grid.
 * It handles user interactions such as left and right clicks to reveal or flag cells.
 */
public class CellButton extends JButton {
    private int row;
    private int column;
    private boolean isFlagged = false;
    private Color originalColor = Color.LIGHT_GRAY;
    private Color flaggedColor = Color.RED;
    private GameController gameController;

    /**
     * Constructs a new CellButton with the specified row, column, and game controller.
     *
     * @param row            The row of the CellButton.
     * @param column         The column of the CellButton.
     * @param gameController The game controller to handle game logic.
     */
    public CellButton(int row, int column, GameController gameController) {
        this.row = row;
        this.column = column;
        this.gameController = gameController;

        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    onLeftClick();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    onRightClick();
                }
            }
        });
    }

    /**
     * Reveals the cell as a mine by changing its background color to red.
     */
    public void revealMine() {
        setBackground(Color.RED);
    }

    /**
     * Reveals the cell with the number of adjacent mines.
     * Changes the background color to white and sets the text to the number of adjacent mines.
     *
     * @param adjacentMines The number of mines adjacent to this cell.
     */
    public void reveal(int adjacentMines) {
        setBackground(Color.WHITE);
        setText(String.valueOf(adjacentMines));
    }

    /**
     * Handles the left-click event on the cell button.
     * Reveals the cell and updates the game state.
     */
    private void onLeftClick() {
        this.gameController.handleLeftClick(this);
    }
    
    /**
     * Handles the right-click event on the cell button.
     * Toggles the flagged state of the cell.
     */
    private void onRightClick() {
        this.gameController.handleRightClick(this);
    }
    
    /**
     * Flags the cell by changing its background color to the flagged color
     * and setting the flagged state to true.
     */
    public void flag() {
        setBackground(flaggedColor);
        this.isFlagged = true;
    }

    /**
     * Unflags the cell by changing its background color to the original color
     * and setting the flagged state to false.
     */
    public void unflag() {
        setBackground(originalColor);
        this.isFlagged = false;
    }

    /**
     * Checks if the cell is flagged.
     *
     * @return true if the cell is flagged, false otherwise.
     */
    public boolean isFlagged() {
        return this.isFlagged;
    }

    /**
     * Gets the row index of the cell.
     *
     * @return The row index.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the column index of the cell.
     *
     * @return The column index.
     */
    public int getColumn() {
        return this.column;
    }   
}