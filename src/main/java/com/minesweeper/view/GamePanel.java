package com.minesweeper.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    public GamePanel(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        initializeGrid(rows, cols);
    }

    private void initializeGrid(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                CellButton cellButton = new CellButton(row, column);
                add(cellButton);
            }
        }
    }

    // Additional methods to handle game logic and UI updates
}