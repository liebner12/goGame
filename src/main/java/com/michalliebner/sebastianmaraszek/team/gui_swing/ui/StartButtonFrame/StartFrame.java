package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    private static final int WIDTH = 424;
    private static final int HEIGHT = 200;
    private Container container = getContentPane();
    private StartFramePanel startFramePanel;
    public StartFrame(){
        setLayout(null);
        setTitle("StartGo");
        setSize(WIDTH,HEIGHT);
        setContentPane(components());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
    }
    public StartFramePanel getStartFramePanel(){
        return startFramePanel;
    }
    private Container components(){
        startFramePanel = new StartFramePanel();
        startFramePanel.initStartFrame();
        add(startFramePanel);
        return container;
    }
}
