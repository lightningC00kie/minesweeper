package com.minesweeper.view;

import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {
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
