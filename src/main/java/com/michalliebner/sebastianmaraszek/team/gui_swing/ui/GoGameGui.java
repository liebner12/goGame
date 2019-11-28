package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import static java.awt.Color.black;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GoGameGui extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    private JPanel Board;
    private JButton exitButton;
    private JPanel UI;
    private Container container = getContentPane();
    public GoGameGui() {
        this.setMaximumSize(new Dimension(1200,800));
        UI = new JPanel();
        UI.setMinimumSize(new Dimension(800,800));
        UI.setSize(400,800);
        UI.setBackground(Color.BLACK);
        UI.setBounds(800,0,400,800);

        Board = new Board();
        exitButton= new JButton();
        Board.add(exitButton);
        container.add(Board);
        container.add(UI);


        setLayout(null);
        setTitle("GoGame");
        setSize(WIDTH,HEIGHT);
        setContentPane(container);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public JButton getExitButton(){
        exitButton.setSize(50,50);
        exitButton.setBorderPainted(false);
        add(exitButton);
        return exitButton;
    }
}

