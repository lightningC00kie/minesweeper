package com.minesweeper.view;

import javax.swing.JButton;

import com.minesweeper.controller.GameController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class CellButton extends JButton {
    private int row;
    private int column;
    private boolean isFlagged = false;
    private Color originalColor = Color.LIGHT_GRAY;
    private Color flaggedColor = Color.RED;
    private GameController gameController;

    public CellButton(int row, int column, GameController gameController) {
        this.row = row;
        this.column = column;
        this.gameController = gameController;
        // System.out.println(this.gameController == null);
        // this.isFlagged = false;

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

    public void reveal(int adjacentMines) {
        // Set the background color to white
        setText(String.valueOf(adjacentMines));
        // Set the text of the button to the number of mines
        // Example: setText("3");
        // setText("3");
    }

    private void onLeftClick() {
        // Call the controller function for a left click
        // Example: GameController.handleLeftClick(row, column);
        this.gameController.handleLeftClick(this);
    }
    
    private void onRightClick() {
        // Call the controller function for a right click
        // this.gameController.handleLeftClick(row, column);
        // System.out.println(this.gameController == null);
        this.gameController.handleRightClick(this);
        // setBackground(Color.RED);
        // Example: GameController.handleRightClick(row, column);
    }

    public void flag() {
        setBackground(flaggedColor);
        this.isFlagged = true;
    }

    public void unflag() {
        setBackground(originalColor);
        this.isFlagged = false;
    }

    public boolean isFlagged() {
        System.out.println("Is flagged: " + this.isFlagged);
        return this.isFlagged;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }   
}