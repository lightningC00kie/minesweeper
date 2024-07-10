package com.minesweeper.controller;

import com.minesweeper.model.Game;
import com.minesweeper.model.GameMode;
import com.minesweeper.view.CellButton;
import com.minesweeper.view.GameFrame;

public class GameController {
    private GameMode gameMode;
    private Game game;
    private GameFrame gameFrame = new GameFrame(this);
    
    private int flagsRemaining;
    
    public GameController(GameMode gameMode) {
        this.gameMode = gameMode;
        this.game = new Game(gameMode);
        this.flagsRemaining = game.getNumMines();
        gameFrame.setVisible(true);
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public int getRows() {
        return this.game.getRows();
    }

    public int getColumns() {
        return this.game.getColumns();
    }
    
    public void handleRightClick(CellButton cellButton) {
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
        game.revealCell(cellButton.getRow(), cellButton.getColumn());
        if (cellButton.isFlagged()) {
            return;
        }
        if (game.GameOver()) {
            // Game over
            System.out.println("Game Over");
        }
        else {
            // Continue playing
            System.out.println("Continue playing");
            cellButton.reveal(game.getAdjacentMines(cellButton.getRow(), cellButton.getColumn()));
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