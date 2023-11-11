package com.labollo.main;

import javax.swing.*;

public class AdventureGame {

    public static void main(String[] args) {
        JFrame window = new JFrame(); // JFrame object

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // To close the window with the close button
        window.setResizable(false); // To not resize the window
        window.setTitle("Adventure Game"); // The title of the game

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();

        window.setLocationRelativeTo(null); // To center the window
        window.setVisible(true); // To display the window

    }
}