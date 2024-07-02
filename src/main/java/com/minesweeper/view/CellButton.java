package com.minesweeper.view;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class CellButton extends JButton {
    private int row;
    private int column;
    private boolean isMarked = false;
    private Color originalColor = Color.LIGHT_GRAY;
    private Color markedColor = Color.RED;

    public CellButton(int row, int column) {
        this.row = row;
        this.column = column;
        this.isMarked = false;

        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Left click
                    onLeftClick();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    // Right click
                    onRightClick();
                }
            }
        });
    }

    private void onLeftClick() {
        // Call the controller function for a left click
        System.out.println("Left Click at [" + row + ", " + column + "]");
        // Example: GameController.handleLeftClick(row, column);
    }

    private void onRightClick() {
        // Call the controller function for a right click
        if (isMarked) {
            isMarked = false;
            setBackground(originalColor);
        } else {
            isMarked = true;
            setBackground(markedColor);
        }
        // setBackground(Color.RED);
        System.out.println("Right Click at [" + row + ", " + column + "]");
        // Example: GameController.handleRightClick(row, column);
    }
}