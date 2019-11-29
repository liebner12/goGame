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
        setBackground(new Color(5, 161, 121));
    }

    public void initComponents() {
        blackResultLabel = new JLabel();
        whiteResultLabel = new JLabel();
        blackResult = new JTextField();
        whiteResult = new JTextField();
        add(blackResultLabel);
        add(whiteResultLabel);
        add(blackResult);
        add(whiteResult);

    }
}
