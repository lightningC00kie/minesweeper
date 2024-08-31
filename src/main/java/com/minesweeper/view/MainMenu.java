package com.minesweeper.view;

import javax.swing.*;
import java.awt.*;

import com.minesweeper.controller.GameController;
import com.minesweeper.model.GameMode;

/**
 * The MainMenu class represents the main menu of the Minesweeper game.
 * It provides options to start a new game in different difficulty modes.
 */
public class MainMenu extends JFrame {

    private GameController gameController;

    /**
     * Initializes the main menu of the Minesweeper game.
     */
    public MainMenu() {
        initUI();
    }

    /**
     * Initializes the user interface of the main menu.
     * Creates buttons for starting new games in easy, medium, and hard modes.
     * The window is centered on the screen and set to exit on close.
     * The window size is set to a fixed width and height.
     * The easy mode button starts a new game in easy mode.
     * The medium mode button starts a new game in medium mode.
     * The hard mode button starts a new game in hard mode.
     * The main menu window is disposed when a new game is started.
     * The main menu window is displayed to the user.
     */
    private void initUI() {
        setTitle("Minesweeper Main Menu");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton easyModeButton = new JButton("New Easy Game");
        JButton mediumModeButton = new JButton("New Medium Game");
        JButton hardModeButton = new JButton("New Hard Game");
        

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
}