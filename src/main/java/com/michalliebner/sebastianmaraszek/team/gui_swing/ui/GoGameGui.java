package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import java.awt.*;
import javax.swing.*;

public class GoGameGui extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;
    private Board Board;
    private ExitButton exitButton;
    private UI UI;
    private Container container = getContentPane();

    public GoGameGui() {
        setLayout(null);
        setTitle("GoGame");
        setSize(WIDTH,HEIGHT);
        setContentPane(components());
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public JButton getExitButton(){
        return exitButton;
    }
    public JPanel getUIPanel(){
        return UI;
    }
    public JPanel getBoard(){
        return Board;
    }
    private Container components(){
        UI = new UI();
        Board = new Board();
        exitButton= new ExitButton();
        add(exitButton);
        add(UI);
        add(Board);
        return container;
    }
}

