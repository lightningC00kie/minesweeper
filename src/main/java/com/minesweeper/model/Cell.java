package com.minesweeper.model;

public class Cell {
    private boolean isMine = false;
    private int adjacentMines;
    private boolean isRevealed = false;
    private int row;
    private int column;
    private boolean isFlagged = false;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isMine() {
        return this.isMine;
    }

    public void setMine() {
        this.isMine = true;
    }

    public void setCount(int count) {
        // Set the number of mines surrounding this cell
        this.adjacentMines = count;
    }

    public int getCount() {
        // Get the number of mines surrounding this cell
        return this.adjacentMines;
    }

    public boolean isFlagged() {
        return this.isFlagged;
    }

    public void setRevealed() {
        // Set the cell to be revealed
        this.isRevealed = true;
    }

    public boolean isRevealed() {
        // Check if the cell is revealed
        return this.isRevealed;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
    
    public void flag() {
        this.isFlagged = true;
    }

    public void unflag() {
        this.isFlagged = false;
    }
}
