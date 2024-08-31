package com.minesweeper;

import javax.swing.SwingUtilities;
import com.minesweeper.view.MainMenu;

/**
 * The App class serves as the entry point for the Minesweeper application.
 * It initializes and displays the main menu of the game.
 */
public class App {
    /**
     * The main method which serves as the entry point for the application.
     * It schedules a job for the event-dispatching thread to create and show the main menu.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}