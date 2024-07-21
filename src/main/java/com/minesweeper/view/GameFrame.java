package com.minesweeper.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.minesweeper.controller.GameController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class GameFrame extends JFrame {
    private JLabel flagsRemainingLabel;
    private GamePanel gridPanel;
    private GameController gameController;

    public GameFrame(GameController gameController) {
        this.gameController = gameController;
        initUI(this.gameController.getRows(), this.gameController.getColumns());
    }

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

    public void updateFlagsRemaining(int flagsRemaining) {
        this.flagsRemainingLabel.setText("Flags remaining: " + gameController.getFlagsRemaining());
    }

    public CellButton getCellButton(int row, int column) {
        return gridPanel.getCellButton(row, column);
    }
}

