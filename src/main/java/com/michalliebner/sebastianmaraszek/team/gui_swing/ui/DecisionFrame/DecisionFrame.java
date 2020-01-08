package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.DecisionFrame;

import javax.swing.*;
import java.awt.*;

public class DecisionFrame extends JFrame {
    private static final int WIDTH = 424;
    private static final int HEIGHT = 400;
    private final Container container = getContentPane();
    private DecisionFramePanel decisionFramePanel;
    public DecisionFrame(){
        setLayout(null);
        setTitle("StartGo");
        setSize(WIDTH,HEIGHT);
        setContentPane(components());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
    }
    public DecisionFramePanel getDecisionFramePanel(){
        return decisionFramePanel;
    }
    private Container components(){
        decisionFramePanel = new DecisionFramePanel();
        decisionFramePanel.initStartFrame();
        add(decisionFramePanel);
        return container;
    }
}
