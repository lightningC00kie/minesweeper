package com.minesweeper.controller;

import java.util.List;

import com.minesweeper.Player.Player;
import com.minesweeper.model.Cell;
import com.minesweeper.model.Game;
import com.minesweeper.model.GameMode;
import com.minesweeper.view.CellButton;
import com.minesweeper.view.GameFrame;

/**
 * Controls the flow of the Minesweeper game, handling user interactions and game state updates.
 */
public class GameController {
    private GameMode gameMode;
    private Game game;
    private GameFrame gameFrame;
    private int flagsRemaining;
    
    /**
     * Initializes a new GameController with the specified game mode.
     *
     * @param gameMode The difficulty level of the game.
     */
    public GameController(GameMode gameMode) {
        this.gameMode = gameMode;
        this.game = new Game(gameMode);
        this.flagsRemaining = game.getNumMines();
        this.gameFrame = new GameFrame(this);
        gameFrame.setVisible(true);
    }

    /**
     * Returns the current game mode.
     *
     * @return the current game mode.
     */
    public GameMode getGameMode() {
        return this.gameMode;
    }

    /**
     * Returns the number of rows in the game grid.
     *
     * @return the number of rows.
     */
    public int getRows() {
        return this.game.getRows();
    }

    /**
     * Returns the number of columns in the game grid.
     *
     * @return the number of columns.
     */
    public int getColumns() {
        return this.game.getColumns();
    }
    
    /**
     * Handles a right click on the specified cell button. If the cell is not revealed, it flags the cell.
     * If the cell is already flagged, it unflags the cell.
     * If the cell is revealed, it does nothing.
     * Decrements the flags remaining count if a cell is flagged, and updates the flags remaining display.
     *
     * @param cellButton The cell button that was right-clicked.
     */
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

    /**
     * Handles a left click on the specified cell button. If the cell is flagged, it does nothing.
     * Otherwise, it reveals the cell and updates the game state.
     * If the game is over, it displays a game over message and closes the game frame.
     *
     * @param cellButton The cell button that was left-clicked.
     */
    public void handleLeftClick(CellButton cellButton) {
        // Reveal the cell at the given row and column
        Cell cell = game.getCell(cellButton.getRow(), cellButton.getColumn());
        if (cellButton.isFlagged()) {
            return;
        }

        game.revealCell(cell);
        if (game.GameOver()) {
            // Game over
            System.out.println("Game over!");
            this.gameFrame.dispose();
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

    /**
     * Flags the specified cell button if it is not flagged, and unflags it if it is flagged.
     * Decrements the flags remaining count if a cell is flagged, and updates the flags remaining display.
     *
     * @param cellButton The cell button to flag/unflag.
     */
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

    /**
     * Returns the number of flags remaining.
     *
     * @return the number of flags remaining.
     */
    public int getFlagsRemaining() {
        return this.flagsRemaining;
    }

    /**
     * Starts a new game with the specified game mode.
     *
     * @param gameMode The difficulty level of the new game.
     */
    public void startNewGame(GameMode gameMode) {
        new GameController(gameMode);
        this.gameFrame.dispose();
    }

    /**
     * Solves the game using an automated player.
     * The player will make moves until the game is either won or lost.
     */
    public void solveGame() {
        Player p1 = new Player(game);
        p1.play();
    }

    /**
     * Reveals all cells in the game grid, showing mines and counts.
     * If a cell contains a mine, it will be revealed as a mine.
     * Otherwise, the cell will display the number of adjacent mines.
     */
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