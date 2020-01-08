package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WinnerFrame;

import javax.swing.*;
import java.awt.*;

public class WinnerFrame extends JFrame {
    private static final int WIDTH = 424;
    private static final int HEIGHT = 124;
    private final Container container = getContentPane();
    private WinnerFramePanel winnerFramePanel;

    public WinnerFrame(){
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\liebn\\OneDrive\\Dokumenty\\goGame\\src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\IconGo.png");
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setTitle("Winner!!!");
        setSize(WIDTH,HEIGHT);
        setContentPane(components());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
    }
    public WinnerFramePanel getStartFramePanel(){
        return winnerFramePanel;
    }
    private Container components(){
        winnerFramePanel = new WinnerFramePanel();
        winnerFramePanel.initStartFrame();
        add(winnerFramePanel);
        return container;
    }
}
