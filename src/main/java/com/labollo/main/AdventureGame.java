package com.labollo.main;

import javax.swing.*;
import java.awt.*;

public class AdventureGame {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            // Creating the game window
            JFrame window = new JFrame(); // Creates an object of type JFrame named "window"
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the window when the "EXIT" button is pressed
            window.setResizable(false); // Disables window resizing to maintain a fixed layout
            window.setTitle("Adventure Game"); // Sets the title of the window

            // Creating the game/menu panel
            GamePanel gamePanel = new GamePanel(); // Creates an object of type GamePanel named "gamePanel"
            MenuPanel menuPanel = new MenuPanel(gamePanel);

            // Configuring the window layout using a CardLayout
            window.setLayout(new CardLayout()); // Sets a CardLayout as the layout manager to handle panels in the window
            window.add(menuPanel);
            window.add(gamePanel);
            window.pack();

            gamePanel.setupGame();
            gamePanel.startGameThread();

            // Configuring window dimensions and position
            window.setSize(768, 576); // Sets the window dimensions: 768x576
            window.setLocationRelativeTo(null); // Sets the window to the center of the screen
            window.setVisible(true); // Sets the "window" object visible
            // Initially shows the game panel
            ((CardLayout) window.getContentPane().getLayout()).show(window.getContentPane(), "game");
        });
    }
}