package com.minesweeper.controller;

import java.util.List;

import com.minesweeper.Player.Player;
import com.minesweeper.model.Cell;
import com.minesweeper.model.Game;
import com.minesweeper.model.GameMode;
import com.minesweeper.view.CellButton;
import com.minesweeper.view.GameFrame;
import com.minesweeper.view.GameOverFrame;
import com.minesweeper.view.MainMenu;

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
            cell.flag();
        }
        else {
            if (flagsRemaining > 0) {
                cellButton.flag();
                flagsRemaining--;
                cell.unflag();
            }
        }
        gameFrame.updateFlagsRemaining(flagsRemaining);
    }

    public void handleLeftClick(CellButton cellButton) {
        // Reveal the cell at the given row and column
        Cell cell = game.getCell(cellButton.getRow(), cellButton.getColumn());
        if (cellButton.isFlagged()) {
            return;
        }

        game.revealCell(cell);
        if (game.GameOver()) {
            // Game over

            revealAllCells();
            GameOverFrame gameOverFrame = new GameOverFrame();
            gameOverFrame.setVisible(true);
        }
        else {
            List<Cell> newlyRevealedCells = game.getNewlyRevealedCells();
            for (Cell curCell : newlyRevealedCells) {
                    if (curCell.isRevealed()) {
                        CellButton curCellButton = gameFrame.getCellButton(curCell.getRow(), curCell.getColumn());
                        curCellButton.reveal(curCell.getCount());
                    }
                }   
        }
    }

    public void showMainMenuFrame() {
        gameFrame.dispose();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }

    public void flagCell(CellButton cellButton) {
        if (cellButton.isFlagged()) {
            flagsRemaining++;
            cellButton.unflag();
        }
        else {
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
        new GameController(gameMode);
        this.gameFrame.dispose();
    }

    public void solveGame() {
        Player p1 = new Player(game);
        p1.play();
    }

    public void revealAllCells() {
        for (int i = 0; i < game.getRows(); i++) {
            for (int j = 0; j < game.getColumns(); j++) {
                Cell cell = game.getCell(i, j);
                CellButton cellButton = gameFrame.getCellButton(i, j);
                if (cell.isMine()) {
                    cellButton.revealMine();
                }
                else {
                    cellButton.reveal(cell.getCount());
                }
            }
        }
    }

}