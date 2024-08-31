package com.minesweeper.view;

import javax.swing.*;
import java.awt.*;

import com.minesweeper.controller.GameController;
import com.minesweeper.model.GameMode;

/**
 * The MainMenu class represents the main menu of the Minesweeper game.
 * It allows the user to select the difficulty level and start a new game.
 */
public class MainMenu extends JFrame {

    private GameController gameController;

     /**
     * Constructs a new MainMenu.
     * Initializes the user interface components.
     */
    public MainMenu() {
        initUI();
    }

    /**
     * Initializes the user interface components of the main menu.
     * Sets up the frame properties and adds buttons for selecting the game mode.
     */
    private void initUI() {
        setTitle("Minesweeper Main Menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

     /**
     * Creates a custom JButton with the specified text.
     * Sets the font, background color, foreground color, and border of the button.
     *
     * @param text the text to be displayed on the button
     * @return the customized JButton
     */
    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("", Font.BOLD, 16));

        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);

        button.setBorder(BorderFactory.createLineBorder(new Color(0, 6, 97), 4));

        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.setBorder(BorderFactory.createCompoundBorder(
                button.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return button;
    }
}