package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import static java.awt.Color.black;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class  GoGameGui extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;
    private Board Board;
    private JButton exitButton;
    private JPanel UI;
    private Container container = getContentPane();

    public GoGameGui() {
        UI = new JPanel();
        Board = new Board();
        exitButton= new JButton();

        setLayout(null);
        setTitle("GoGame");
        setSize(WIDTH,HEIGHT);
        setContentPane(container);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public JButton getExitButton(){
        exitButton.setBounds(1175,5,20,20);
        exitButton.setBorderPainted(false);
        add(exitButton);
        return exitButton;
    }
    public JPanel getUIPanel(){
        UI.setMinimumSize(new Dimension(600,800));
        UI.setBackground(new Color(45, 45, 45));
        UI.setBounds(700,0,500,800);
        add(UI);
        return UI;
    }
    public Board getBoard(){
        add(Board);
        return Board;
    }
}

