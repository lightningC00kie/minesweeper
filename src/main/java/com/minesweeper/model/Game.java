package com.minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Game {
    private int flagsRemaining = 10;
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

        queue.add(cell);
        // newlyRevealedCells.add(cell);
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            current.setRevealed();
            newlyRevealedCells.add(current);
            if (current.getCount() == 0) {
                List<Cell> neighbors = getNeighbors(current);
                for (Cell neighbor : neighbors) {
                    if (!neighbor.isRevealed() && neighbor.getCount() == 0){
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        for (int r = cell.getRow() - 1; r <= cell.getRow() + 1; r++) {
            for (int c = cell.getColumn() - 1; c <= cell.getColumn() + 1; c++) {
                if (r >= 0 && r < rows && c >= 0 && c < columns && !grid[r][c].isRevealed()) {
                    neighbors.add(grid[r][c]);
                }
            }
        }
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

    public List<Cell> getRevealedCells() {
        // List<Cell> revealedCells = new ArrayList<>();
        // Arrays.stream(grid) // Stream the outer array
        //     .flatMap(Arrays::stream) // Flatten the inner arrays into a single stream
        //     .filter(cell -> cell.isRevealed()) // Filter cells that are revealed
        //     .forEach(revealedCells::add); // Collect the results
        // System.out.println(revealedCells.size());
        // return revealedCells;
        return newlyRevealedCells;
    }
}
