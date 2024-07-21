package com.minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Game {
    private int rows;
    private int columns;
    private Cell[][] grid;
    private boolean gameOver = false;
    private int numMines;
    private List<Cell> newlyRevealedCells = new ArrayList<>();
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

    public int getNumMines() {
        return numMines;
    }

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
    
    public void revealCell(Cell cell) {
        if (cell.isMine()) {
            gameOver = true;
            return;
        }
        revealAll(cell);
    }

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
        System.out.println("neighbors: " + neighbors.size());
        return neighbors;
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

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public List<Cell> getNewlyRevealedCells() {
        return newlyRevealedCells;
    }

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
