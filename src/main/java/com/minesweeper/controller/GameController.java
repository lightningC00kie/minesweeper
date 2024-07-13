package com.minesweeper.controller;

import java.util.List;

import com.minesweeper.model.Cell;
import com.minesweeper.model.Game;
import com.minesweeper.model.GameMode;
import com.minesweeper.view.CellButton;
import com.minesweeper.view.GameFrame;

public class GameController {
    private GameMode gameMode;
    private Game game;
    private GameFrame gameFrame;
    
    private int flagsRemaining;
    
    public GameController(GameMode gameMode) {
        this.gameMode = gameMode;
        this.game = new Game(gameMode);
        this.flagsRemaining = game.getNumMines();
        this.gameFrame = new GameFrame(this);
        gameFrame.setVisible(true);
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public int getRows() {
        System.out.println("-------------------------------: ");

        return this.game.getRows();
    }

    public int getColumns() {
        return this.game.getColumns();
    }
    
    public void handleRightClick(CellButton cellButton) {

        Cell cell = game.getCell(cellButton.getRow(), cellButton.getColumn());
        if (cell.isRevealed()) {
            return;
        }
        // Flag the cell at the given row and column
        if (cellButton.isFlagged()) {
            flagsRemaining++;
            cellButton.unflag();
        }
        else {
            if (flagsRemaining > 0) {
                cellButton.flag();
                flagsRemaining--;
            }
        }
        gameFrame.updateFlagsRemaining(flagsRemaining);
    }

    public void handleLeftClick(CellButton cellButton) {
        // Reveal the cell at the given row and column
        System.out.println("Left Click at [" + cellButton.getRow() + ", " + cellButton.getColumn() + "]");
        Cell cell = game.getCell(cellButton.getRow(), cellButton.getColumn());
        if (cellButton.isFlagged()) {
            return;
        }

        game.revealCell(cell);
        System.out.println(cell.isRevealed());
        if (game.GameOver()) {
            // Game over
            this.gameFrame.dispose();
            System.out.println("Game Over");
        }
        else {
            // Continue playing
            System.out.println("Continue playing");
            List<Cell> revealedCells = game.getRevealedCells();
            System.out.println(revealedCells.size());
            for (Cell c : revealedCells) {
                System.out.println("Revealed cell at [" + c.getRow() + ", " + c.getColumn() + "]");
                CellButton curCellButton = gameFrame.getCellButton(c.getRow(), c.getColumn());
                curCellButton.reveal(game.getAdjacentMines(curCellButton.getRow(), curCellButton.getColumn()));
                // System.out.println("Revealed cell at [" + c.getRow() + ", " + c.getColumn() + "]");
            }
            // Assuming game.grid is a List<Cell>
            
        }

    }

    public void flagCell(CellButton cellButton) {
        // Flag the cell at the given row and column
        if (cellButton.isFlagged()) {
            flagsRemaining++;
            cellButton.unflag();
        }
        else {
            // flagsRemaining = flagsRemaining > 0 ? flagsRemaining-- : this.flagsRemaining;
            if (flagsRemaining > 0) {
                flagsRemaining--;
                cellButton.flag();
            }
        }
    }

    public int getFlagsRemaining() {
        return this.flagsRemaining;
    }

    public void startNewGame(GameMode gameMode) {
        // GameFrame gameFrame = new GameFrame(this);
        // gameFrame.setVisible(true);
        new GameController(gameMode);
        this.gameFrame.dispose();
    }

}