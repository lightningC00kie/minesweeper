package com.minesweeper.model;

/**
 * The Cell class represents a single cell in the Minesweeper game grid.
 * It contains information about whether the cell is a mine, the number of adjacent mines,
 * and its state (revealed, flagged).
 */
public class Cell {
    private boolean isMine = false;
    private int adjacentMines;
    private boolean isRevealed = false;
    private int row;
    private int column;
    private boolean isFlagged = false;

    /**
     * Constructs a new Cell with the specified row and column.
     *
     * @param row    The row index of the cell.
     * @param column The column index of the cell.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Checks if the cell is a mine.
     *
     * @return true if the cell is a mine, false otherwise.
     */
    public boolean isMine() {
        return this.isMine;
    }

    /**
     * Sets the cell as a mine.
     */
    public void setMine() {
        this.isMine = true;
    }

    /**
     * Sets the number of mines surrounding this cell.
     *
     * @param count The number of adjacent mines.
     */
    public void setCount(int count) {
        this.adjacentMines = count;
    }

    /**
     * Gets the number of mines surrounding this cell.
     *
     * @return The number of adjacent mines.
     */
    public int getCount() {
        return this.adjacentMines;
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
     * Sets the cell to be revealed.
     */
    public void setRevealed() {
        // Set the cell to be revealed
        this.isRevealed = true;
    }

    /**
     * Checks if the cell is revealed.
     *
     * @return true if the cell is revealed, false otherwise.
     */
    public boolean isRevealed() {
        // Check if the cell is revealed
        return this.isRevealed;
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
    
    /**
     * Flags the cell.
     */
    public void flag() {
        this.isFlagged = true;
    }

    /**
     * Unflags the cell.
     */
    public void unflag() {
        this.isFlagged = false;
    }
}
