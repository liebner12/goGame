package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import static java.awt.Color.black;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class GoGameGui extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    private JPanel panelMain;
    private JPanel Board;
    private JPanel UI;
    public GoGameGui() {
        Board = new Board();
        setTitle("GoGame");
        setSize(WIDTH,HEIGHT);
        setContentPane(Board);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

