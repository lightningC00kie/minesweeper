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

        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    onLeftClick();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    onRightClick();
                }
            }
        });
    }

    public void revealMine() {
        setBackground(Color.RED);
    }

    public void reveal(int adjacentMines) {
        setBackground(Color.WHITE);
        setText(String.valueOf(adjacentMines));
    }

    private void onLeftClick() {
        this.gameController.handleLeftClick(this);
    }
    
    private void onRightClick() {
        this.gameController.handleRightClick(this);
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