package com.minesweeper.model;

public class Game {
    private int flagsRemaining = 10;
    private int rows;
    private int columns;
    private Cell[][] grid;
    private boolean gameOver = false;
    private int numMines;

    public Game(GameMode mode) {
        switch (mode) {
            case EASY:
                initGame(9, 9);
                numMines = 10;
                placeMines(numMines);
                break;
            case MEDIUM:
                initGame(16, 16);
                numMines = 40;
                placeMines(numMines);
                break;
            case HARD:
                initGame(16, 30);
                numMines = 99;
                placeMines(numMines);
                break;
        }
        setCounts();
    }

    public int getNumMines() {
        return numMines;
    }

    public void initGame(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        grid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void placeMines(int numMines) {
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * columns);
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine();
                minesPlaced++;
            }
        }
    }

    public void setCounts() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!grid[i][j].isMine()) {
                    int count = 0;
                    for (int r = i - 1; r <= i + 1; r++) {
                        for (int c = j - 1; c <= j + 1; c++) {
                            if (r >= 0 && r < rows && c >= 0 && c < columns && grid[r][c].isMine()) {
                                count++;
                            }
                        }
                    }
                    grid[i][j].setCount(count);
                }
            }
        }
    }
    
    public void revealCell(int row, int col) {
        if (grid[row][col].isMine()) {
            gameOver = true;
            return;
        }
        grid[row][col].setRevealed();
    }

    public int getAdjacentMines(int row, int col) {
        return grid[row][col].getCount();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean GameOver() {
        return gameOver;
    }
}
