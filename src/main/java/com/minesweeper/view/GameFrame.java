package com.minesweeper.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.minesweeper.controller.GameController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class GameFrame extends JFrame {
    private JLabel minesRemainingLabel;
    private GamePanel gridPanel;
    private GameController gameController;

    public GameFrame(GameController gameController) {
        this.gameController = gameController;
        initUI();
    }

    private void initUI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // Center the window

        JPanel topPanel = new JPanel(new BorderLayout());

        minesRemainingLabel = new JLabel("10"); // Example number
        topPanel.add(minesRemainingLabel, BorderLayout.WEST);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            gameController.startNewGame();
            this.dispose(); // Close the current game 
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(newGameButton);
        topPanel.add(buttonPanel, BorderLayout.CENTER);


        add(topPanel, BorderLayout.NORTH);

        gridPanel = new GamePanel(16, 16); // Assuming a 16x16 grid
        add(gridPanel, BorderLayout.CENTER);
    }

    public void setMinesRemaining(int mines) {
        minesRemainingLabel.setText("Mines remaining: " + mines);
    }
}