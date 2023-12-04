package com.labollo.main;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.awt.CardLayout;

public class AdventureGame {

    // Properties of this class
    public static GamePanel gamePanel = new GamePanel(); // Creates an object of type GamePanel named "gamePanel"
    public static MenuPanel menuPanel = new MenuPanel(gamePanel); // Creates an object of type MenuPanel named "menuPanel"

    public static void main(String[] args) {

        // Invokes the code block inside a Swing event dispatch thread
        // This ensures that Swing operations are safely executed in the UI thread
        SwingUtilities.invokeLater(() -> {

            // Creating the game window
            JFrame window = new JFrame(); // Creates an object of type JFrame named "window"
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the window when the "EXIT" button is pressed
            window.setResizable(false); // Disables window resizing to maintain a fixed layout
            window.setTitle("The legend of sword"); // Sets the title of the window

            // Configuring the window layout using a CardLayout
            window.setLayout(new CardLayout()); // Sets a CardLayout as the layout manager to handle panels in the window
            window.add(menuPanel); // Adds the menu panel to the window.
            window.add(gamePanel); // Adds the game panel to the window.
            window.pack(); // Sizes the window to fit its preferred size, taking into account the added panels.

            gamePanel.setupGame(); // Initializes the game settings and components.
            gamePanel.startGameThread(); // Starts the game thread responsible for continuous updates and rendering.

            // Configuring window dimensions and position
            window.setSize(gamePanel.screenWidth, gamePanel.screenHeight); // Sets the window dimensions: 768x576
            window.setLocationRelativeTo(null); // Sets the window to the center of the screen
            window.setVisible(true); // Sets the "window" object visible

            ((CardLayout) window.getContentPane().getLayout()).show(window.getContentPane(), "game"); // Initially shows the game panel
        });
    }
}