package com.labollo.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuPanel extends JPanel {

    public MenuPanel(GamePanel gamePanel) {
        // To set a layout for panel
        setLayout(new GridBagLayout());

        // To create a background using JLabel
        /*ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(MenuPanel.class.getResource("/menu/menu00.png")));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        add(backgroundLabel, new GridBagConstraints());*/

        // Add button to the panel
        JButton newGame = new JButton("New Game");
        newGame.setBackground(Color.BLUE); // Background color of the button
        newGame.setForeground(Color.WHITE); // Text color of the button
        newGame.setFont(new Font("Arial", Font.BOLD, 18)); // Font and dimension
        add(newGame, new GridBagConstraints());

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setVisible(true);
                setVisible(false);
            }
        });
    }
}
