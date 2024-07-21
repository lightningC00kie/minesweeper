### Minesweeper Game with CSP Solver Documentation

---

#### Table of Contents
1. Introduction
2. Game Setup
3. User Interface
4. Game Rules
5. Solver Integration
6. Building and Running the Project

---

### 1. Introduction

This document provides detailed instructions for understanding and using the Minesweeper game implemented in Java using the Swing graphics library. Additionally, the game features a solver implemented with the ChocoSolver Constraint Satisfaction Problem (CSP) library.

### 2. Game Setup

Ensure you have the following installed:
- Java Development Kit (JDK) 11 or later
- Apache Maven (for dependency management and building)

### 3. User Interface

The user interface for the Minesweeper game consists of:
- A grid of buttons representing the minefield
- A status bar displaying the remaining mines
- Menu options for starting a new game, solving the game, and revealing all squares

### 4. Game Rules

The objective of Minesweeper is to uncover all the squares on the grid that do not contain mines:
- Left-click to uncover a square.
- Right-click to place a flag on a square you suspect contains a mine.
- The numbers displayed on uncovered squares indicate how many mines are adjacent to that square.

### 5. Solver Integration

The game includes a solver that uses the ChocoSolver library to determine safe moves or solve the entire game:
- The solver can be activated via the "Solve" option in the menu.
- It uses constraint satisfaction techniques to analyze the current state of the board and suggest or make moves.

### 6. Building and Running the Project

#### Steps to build:
1. Clone the repository:
    ```bash
    git clone https://github.com/lightningC00kie/minesweeper
    cd minesweeper
    ```

2. Use Maven to build the project:
    ```bash
    mvn clean install
    ```

#### Steps to run:
1. Run the game using the following command:
    ```bash
    mvn exec:java -Dexec.mainClass="com.minesweeper.App"
    ```

### 7. Code Overview

The project consists of the following main components:

#### Game Classes
- `GameMode.java`: Enum for game modes (easy, medium, hard)
- `Game.java`: Handles the creation and management of the minefield.
- `Cell.java`: Represents each cell on the board.

#### UI Classes
- `CellButton.java`: Extends JButton to represent a cell in the UI.
- `GameFrame.java`: Represents the entire game window.
- `GamePanel.java`: Represents just the game grid.
- `MainMenu.java`: Handles main menu events.

#### Controller Class
- `GameController.java`: Handles interaction between model and view classes.

#### Solver Classes
- `Player.java`: Integrates the ChocoSolver to provide solving capabilities.
