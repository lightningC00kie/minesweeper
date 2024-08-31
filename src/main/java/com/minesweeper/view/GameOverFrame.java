package com.minesweeper.view;

import javax.swing.*;
import java.awt.*;

/**
 * The GameOverFrame class represents a JFrame that displays a "Game Over!" message.
 * This frame is used to notify the user that the game has ended.
 */
public class GameOverFrame extends JFrame {

    /**
     * Constructs a new GameOverFrame.
     * Sets up the frame with a title, size, default close operation, and centers it on the screen.
     * Adds a centered label with the text "Game Over!" to the frame.
     */
    public GameOverFrame() {
        setTitle("Game Over!");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout()); // Use BorderLayout to easily center components

        JLabel centeredLabel = new JLabel("Game Over!", SwingConstants.CENTER);

        centeredLabel.setFont(new Font("Arial", Font.BOLD, 18));

        centeredLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centeredLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(centeredLabel, BorderLayout.CENTER);

    }
}
