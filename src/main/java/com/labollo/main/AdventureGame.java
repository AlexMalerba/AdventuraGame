package com.labollo.main;

import javax.swing.*;
import java.awt.*;

public class AdventureGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(true);
            window.setTitle("Adventure Game");

            GamePanel gamePanel = new GamePanel();
            MenuPanel menuPanel = new MenuPanel(gamePanel);

            window.setLayout(new CardLayout());
            window.add(menuPanel, "menu");
            window.add(gamePanel, "game");

            window.setSize(768, 576);
            window.setLocationRelativeTo(null);
            window.setVisible(true);

            // Inizialmente mostra il pannello del menu
            ((CardLayout) window.getContentPane().getLayout()).show(window.getContentPane(), "menu");
        });
    }
}
