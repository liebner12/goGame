package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel;

import javax.swing.*;
import java.awt.*;

public class Results extends JPanel {
    private static final int WIDTH = 320;
    private static final int HEIGHT = 500;
    private JLabel blackResultLabel;
    private JLabel whiteResultLabel;
    private JTextField blackResult;
    private JTextField whiteResult;

    Results() {
        initComponents();
        setLayout(null);
        setBounds(40, 100, WIDTH, HEIGHT);
        setOpaque(false);
    }

    public void initComponents() {
        blackResultLabel = new JLabel("Black");
        blackResultLabel.setBounds(50,50,50,30);
        whiteResultLabel = new JLabel("White");
        whiteResultLabel.setBounds(100,50,50,30);
        blackResult = new JTextField();
        blackResult.setBounds(50,100,50,50);
        whiteResult = new JTextField();
        whiteResult.setBounds(100,100,50,50);
        add(blackResultLabel);
        add(whiteResultLabel);
        add(blackResult);
        add(whiteResult);
    }

    public JLabel getBlackResultLabel(){
        return blackResultLabel;
    }
    public JLabel getWhiteResultLabel(){
        return whiteResultLabel;
    }
    public JTextField getBlackResult(){
        return blackResult;
    }
    public JTextField getWhiteResult(){
        return whiteResult;
    }
}
