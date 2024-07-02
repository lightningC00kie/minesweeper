package com.minesweeper.controller;

import com.minesweeper.view.GameFrame;

public class GameController {
    public void startNewGame() {
        GameFrame gameFrame = new GameFrame(this);
        gameFrame.setVisible(true);
    }
}