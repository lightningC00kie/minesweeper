package com.minesweeper.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.minesweeper.controller.GameController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * The GameFrame class represents the main window of the Minesweeper game.
 * It initializes the user interface and handles interactions with the game controller.
 */
public class GameFrame extends JFrame {
    private JLabel flagsRemainingLabel;
    private GamePanel gridPanel;
    private GameController gameController;

    /**
     * Constructs a new GameFrame with the specified game controller.
     *
     * @param gameController The controller that manages the game logic.
     */
    public GameFrame(GameController gameController) {
        this.gameController = gameController;
        initUI(this.gameController.getRows(), this.gameController.getColumns());
    }

    /**
     * Initializes the user interface of the game window.
     * Creates the top panel with flags remaining and new game buttons, and the game grid panel.
     * The game grid panel is populated with CellButton components.
     * The window is centered on the screen and set to exit on close.
     * The window size is calculated based on the number of rows and columns in the game grid.
     * The top panel contains the flags remaining label and new game button.
     * The game grid panel contains the CellButton components.
     * The flags remaining label is updated with the current number of flags remaining.
     * The new game button starts a new game with the same game mode.
     * The solve button reveals all mines and flags on the game grid.
     * The reveal all button reveals all cells on the game grid.
     * The game grid panel is updated with the current game state.
     *
     * @param rows    The number of rows in the game grid.
     * @param columns The number of columns in the game grid.
     */
    private void initUI(int rows, int columns) {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final int cellWidth = 30; // Width of each cell in the grid
        final int cellHeight = 30; // Height of each cell in the grid
        final int windowWidthPadding = 500; // Extra width for window decorations and padding
        final int windowHeightPadding = 500; // Extra height for window decorations, padding, and top panel
        
        int windowWidth = columns * cellWidth + windowWidthPadding;
        int windowHeight = rows * cellHeight + windowHeightPadding;
        setSize(windowWidth, windowHeight);

        setLocationRelativeTo(null); // Center the window

        JPanel topPanel = new JPanel(new BorderLayout());
        flagsRemainingLabel = new JLabel("Flags remaining: " + this.gameController.getFlagsRemaining());
        topPanel.add(flagsRemainingLabel, BorderLayout.WEST);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            gameController.startNewGame(gameController.getGameMode());
            this.dispose(); // Close the current game 
        });

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> {
            gameController.solveGame();
        });

        JButton revealAllButton = new JButton("Reveal All");
        revealAllButton.addActionListener(e -> {
            gameController.revealAllCells();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(newGameButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(revealAllButton);
        topPanel.add(buttonPanel, BorderLayout.CENTER);



        add(topPanel, BorderLayout.NORTH);

        gridPanel = new GamePanel(rows, columns, this.gameController);
        add(gridPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the flags remaining label with the specified number of flags remaining.
     *
     * @param flagsRemaining The number of flags remaining in the game.
     */
    public void updateFlagsRemaining(int flagsRemaining) {
        this.flagsRemainingLabel.setText("Flags remaining: " + gameController.getFlagsRemaining());
    }

    /**
     * Returns the CellButton component at the specified row and column.
     *
     * @param row    The row of the CellButton.
     * @param column The column of the CellButton.
     * @return The CellButton component at the specified row and column, or null if not found.
     */
    public CellButton getCellButton(int row, int column) {
        return gridPanel.getCellButton(row, column);
    }
}

