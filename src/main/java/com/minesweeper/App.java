package com.minesweeper;

import javax.swing.SwingUtilities;

import com.minesweeper.controller.GameController;
import com.minesweeper.view.MainMenu;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameController gameController = new GameController();
            MainMenu mainMenu = new MainMenu(gameController);
            mainMenu.setVisible(true);
        });
    }
}