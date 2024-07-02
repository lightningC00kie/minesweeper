package com.minesweeper.view;

import javax.swing.*;
import java.awt.*;

import com.minesweeper.controller.GameController;

public class MainMenu extends JFrame {

    private GameController gameController;

    public MainMenu(GameController gameController) {
        this.gameController = gameController;
        initUI();
    }

    private void initUI() {
        setTitle("Minesweeper Main Menu");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            gameController.startNewGame();
            this.dispose(); // Close the main menu
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(newGameButton, gbc);
    }
}