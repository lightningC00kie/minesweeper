package com.minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Game class represents the Minesweeper game logic.
 * It manages the game grid, handles user interactions, and tracks the game state.
 */
public class Game {
    private int rows;
    private int columns;
    private Cell[][] grid;
    private boolean gameOver = false;
    private int numMines;
    private List<Cell> newlyRevealedCells = new ArrayList<>();

    /**
     * Constructs a new Game with the specified game mode.
     * Initializes the game grid and places mines based on the difficulty level.
     *
     * @param mode The game mode which determines the difficulty level.
     *             It can be one of the following:
     *             <ul>
     *                 <li>EASY: 9x9 grid with 10 mines</li>
     *                 <li>MEDIUM: 16x16 grid with 40 mines</li>
     *                 <li>HARD: 16x30 grid with 99 mines</li>
     *             </ul>
     */
    public Game(GameMode mode) {
        switch (mode) {
            case EASY:
                this.rows = 9;
                columns = 9;
                numMines = 10;
                initGame(rows, columns);
                placeMines(numMines);
                break;
            case MEDIUM:
                rows = 16;
                columns = 16;
                numMines = 40;
                initGame(rows, columns);
                placeMines(numMines);
                break;
            case HARD:
                rows = 16;
                columns = 30;
                numMines = 99;
                initGame(rows, columns);
                placeMines(numMines);
                break;
        }
        setCounts();
    }

    /**
     * Returns the number of mines in the game.
     *
     * @return the number of mines.
     */
    public int getNumMines() {
        return numMines;
    }

    /**
     * Initializes the game grid with the specified number of rows and columns.
     *
     * @param rows    The number of rows in the game grid.
     * @param columns The number of columns in the game grid.
     */
    public void initGame(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        grid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell(i, j);
                grid[i][j] = cell;
            }
        }
    }

    /**
     * Places the specified number of mines randomly on the game grid.
     *
     * @param numMines The number of mines to be placed.
     */
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

    /**
     * Sets the count of adjacent mines for each non-mine cell in the game grid.
     */
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
    
    /**
     * Reveals the specified cell. If the cell contains a mine, the game is marked as over.
     * Otherwise, it reveals all adjacent cells.
     *
     * @param cell The cell to be revealed.
     */
    public void revealCell(Cell cell) {
        if (cell.isMine()) {
            gameOver = true;
            return;
        }
        revealAll(cell);
    }

    /**
     * Reveals all cells adjacent to the specified cell. If the cell has a neighbor with no adjacent mines,
     * it recursively reveals all of its neighbors.
     *
     * @param cell The cell to be revealed.
     */
    public void revealAll(Cell cell) {
        newlyRevealedCells = new ArrayList<>();
        Queue<Cell> queue = new LinkedList<>();
        List<Cell> neighbors;
        queue.add(cell);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            current.setRevealed();
            newlyRevealedCells.add(current);
            if (current.getCount() == 0) {
                neighbors = getNeighbors(current);
                for (Cell neighbor : neighbors) {
                    if (!neighbor.isRevealed()){
                        queue.add(neighbor);
                    }
                }
                
            }
        }
    }

    /**
     * Gets the neighbors of the specified cell.
     *
     * @param cell The cell to get neighbors for.
     * @return A list of neighboring cells.
     */
    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int[] directions = {-1, 0, 1}; 
    
        for (int dx : directions) {
            for (int dy : directions) {
                if (dx == 0 && dy == 0) {
                    // Skip the cell itself
                    continue;
                }
                System.out.println(cell.getRow() + " " + cell.getColumn());
                int newX = cell.getRow() + dx;
                int newY = cell.getColumn() + dy;
                // Check boundaries
                if (newX >= 0 && newX < rows && newY >= 0 && newY < columns) {
                    neighbors.add(grid[newX][newY]);
                }
            }
        }
        return neighbors;
    }

    /**
     * Returns the number of adjacent mines for the cell at the specified row and column.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return the number of adjacent mines.
     */
    public int getAdjacentMines(int row, int col) {
        return grid[row][col].getCount();
    }

    /**
     * Returns the number of rows in the game grid.
     *
     * @return the number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the game grid.
     *
     * @return the number of columns.
     */
    public int getColumns() {
        return columns;
    }

     /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean GameOver() {
        return gameOver;
    }

    /**
     * Gets the cell at the specified row and column.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return the cell at the specified row and column.
     */
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Gets the game grid.
     *
     * @return The game grid.
     */
    public Cell[][] getGrid() {
        return grid;
    }

    /**
     * Gets the list of newly revealed cells.
     *
     * @return The list of newly revealed cells.
     */
    public List<Cell> getNewlyRevealedCells() {
        return newlyRevealedCells;
    }

    /**
     * Checks if the game is won. Game is won if all non-mine cells are revealed.
     *
     * @return true if the game is won, false otherwise.
     */
    public boolean isWon() {
        int numRevealed = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j].isRevealed()) {
                    numRevealed++;
                }
            }
        }
        return numRevealed == rows * columns - numMines;
    }
}
