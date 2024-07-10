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
        JButton easyModeButton = new JButton("New Easy Game");
        JButton mediumModeButton = new JButton("New Medium Game");
        JButton hardModeButton = new JButton("New Hard Game");
        

        easyModeButton.addActionListener(e -> {
            gameController = new GameController(GameMode.EASY); // Create a new game controller with the selected game mode (easy, medium, or hard
            gameController.startNewGame(GameMode.EASY);
            this.dispose(); // Close the main menu
        });
        
        mediumModeButton.addActionListener(e -> {
            gameController.startNewGame(GameMode.MEDIUM);
            this.dispose(); // Close the main menu
        });
        
        hardModeButton.addActionListener(e -> {
            gameController.startNewGame(GameMode.HARD);
            this.dispose(); // Close the main menu
        });
        // newGameButton.addActionListener(e -> {
        //     gameController.startNewGame();
        //     this.dispose(); // Close the main menu
        // });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(easyModeButton, gbc);
        add(mediumModeButton, gbc);
        add(hardModeButton, gbc);
        // add(newGameButton, gbc);
    }
}