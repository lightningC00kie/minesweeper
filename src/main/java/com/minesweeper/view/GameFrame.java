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
        setSize(800, 800);
        setLocationRelativeTo(null); // Center the window

        JPanel topPanel = new JPanel(new BorderLayout());
        // this.setFlagsRemainingLabel();
        flagsRemainingLabel = new JLabel("Flags remaining: " + this.gameController.getFlagsRemaining()); // Example number
        topPanel.add(flagsRemainingLabel, BorderLayout.WEST);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            gameController.startNewGame(gameController.getGameMode());
            this.dispose(); // Close the current game 
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(newGameButton);
        topPanel.add(buttonPanel, BorderLayout.CENTER);


        add(topPanel, BorderLayout.NORTH);

        gridPanel = new GamePanel(rows, columns, this.gameController);
        add(gridPanel, BorderLayout.CENTER);
    }

    public void updateFlagsRemaining(int flagsRemaining) {
        this.flagsRemainingLabel.setText("Flags remaining: " + gameController.getFlagsRemaining());
    }
}

