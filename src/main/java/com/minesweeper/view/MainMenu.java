package com.minesweeper.view;

import javax.swing.*;
import java.awt.*;

import com.minesweeper.controller.GameController;
import com.minesweeper.model.GameMode;

public class MainMenu extends JFrame {

    private GameController gameController;

    public MainMenu() {
        initUI();
    }

    private void initUI() {
        setTitle("Minesweeper Main Menu");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // JButton newGameButton = new JButton("New Game");
        JButton easyModeButton = createCustomButton("Beginner");
        JButton mediumModeButton = createCustomButton("Intermediate");
        JButton hardModeButton = createCustomButton("Expert");
        

        easyModeButton.addActionListener(e -> {
            gameController = new GameController(GameMode.EASY); 
            gameController.startNewGame(GameMode.EASY);
            this.dispose(); 
        });
        
        mediumModeButton.addActionListener(e -> {
            gameController = new GameController(GameMode.MEDIUM); 
            gameController.startNewGame(GameMode.MEDIUM);
            this.dispose(); 
        });
        
        hardModeButton.addActionListener(e -> {
            gameController = new GameController(GameMode.HARD); 
            gameController.startNewGame(GameMode.HARD);
            this.dispose(); 
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(easyModeButton, gbc);
        add(mediumModeButton, gbc);
        add(hardModeButton, gbc);
    }

    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);

        // Set custom font and size
        button.setFont(new Font("", Font.BOLD, 16));

        // Set button colors
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);

        button.setBorder(BorderFactory.createLineBorder(new Color(0, 6, 97), 4));

        // Set custom opacity
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.setBorder(BorderFactory.createCompoundBorder(
                button.getBorder(), // Keep the existing border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add extra space around the button
        ));
        return button;
    }
}