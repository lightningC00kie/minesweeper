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

/**
 * The GameController class manages the game logic and user interactions for the Minesweeper game.
 * It handles user inputs, updates the game state, and communicates with the view components.
 */
public class GameController {
    private GameMode gameMode;
    private Game game;
    private GameFrame gameFrame;
    
    private int flagsRemaining;
    
    /**
     * Constructs a new GameController with the specified game mode.
     * Initializes the game, sets up the game frame, and displays it.
     *
     * @param gameMode the game mode to be used for the new game
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
     * @return the current game mode
     */
    public GameMode getGameMode() {
        return this.gameMode;
    }

    /**
     * Returns the number of rows in the game grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return this.game.getRows();
    }

    /**
     * Returns the number of columns in the game grid.
     *
     * @return the number of columns
     */
    public int getColumns() {
        return this.game.getColumns();
    }
    
    /**
     * Handles a right-click event on a cell button.
     * Flags or unflags the cell and updates the flags remaining count.
     *
     * @param cellButton the cell button that was right-clicked
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
     * Handles a left-click event on a cell button.
     * Reveals the cell and checks for game over conditions.
     *
     * @param cellButton the cell button that was left-clicked
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

    /**
     * Displays the main menu frame and disposes of the current game frame.
     */
    public void showMainMenuFrame() {
        gameFrame.dispose();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }

    /**
     * Flags or unflags a cell button and updates the flags remaining count.
     *
     * @param cellButton the cell button to be flagged or unflagged
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
     * @return the number of flags remaining
     */
    public int getFlagsRemaining() {
        return this.flagsRemaining;
    }

    /**
     * Starts a new game with the specified game mode and disposes of the current game frame.
     *
     * @param gameMode the game mode for the new game
     */
    public void startNewGame(GameMode gameMode) {
        new GameController(gameMode);
        this.gameFrame.dispose();
    }

    /**
     * Solves the game using a CSP algorithm.
     */
    public void solveGame() {
        Player p1 = new Player(game);
        p1.play();
    }

    /**
     * Reveals all cells in the game grid.
     * Mines are revealed with a mine icon, and other cells show their adjacent mine count.
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