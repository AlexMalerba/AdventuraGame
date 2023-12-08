package com.labollo.main;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuPanel extends JPanel {

    public MenuPanel(GamePanel gamePanel) {
        setLayout(null);

        // Add a background like JLabel
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(MenuPanel.class.getResource("/menu/menu00.png"))); // Get the image from the path
        JLabel backgroundLabel = new JLabel(backgroundImage); // Create a JLabel with the image

        backgroundLabel.setBounds(0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT); // Set the dimension of the background
        add(backgroundLabel); // Add backgroundPanel to the window

        // New Game button
        JButton newGame = new JButton("New Game"); // Create a button with the text: New Game

        newGame.setFont(new Font("algerian", Font.PLAIN, 30)); // Sets font: algerian - Style: plain (normal) - Size: 30
        newGame.setBackground(new Color(0, 0, 0, 0)); // Set the fill color to transparent
        newGame.setForeground(Color.WHITE); // Set the text color white

        newGame.setBorderPainted(false); // To delete the button border
        newGame.setFocusPainted(false); // To delete the text border
        newGame.setContentAreaFilled(true); // To show the button contents

        int buttonWidth = 200; // Button width (px)
        int buttonHeight = 60; // Button height (px)
        int buttonX = (gamePanel.SCREEN_WIDTH - buttonWidth) / 2; // To calculate the x coordinates of the button (264 px from left)
        int buttonY = (gamePanel.SCREEN_HEIGHT - buttonHeight) / 2; // To calculate the y coordinates of the button (258 px from above)

        newGame.setBounds(buttonX, buttonY, buttonWidth, buttonHeight); // To set the button bounds (264, 258, 200, 60)
        add(newGame); // Add the button on window

        /*
         * Add the event's button
         * Made the transition between the panels
         * When the "new Game" button is pressed make the gamePanel visible and the menuPanel invisible
        */
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // When the button is pressed
                gamePanel.setVisible(true); // Set the gamePanel visible
                setVisible(false); // Set the menuPanel invisible
            }
        });
    }
}
