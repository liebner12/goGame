package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.StartButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.ExitButton;


import javax.swing.*;
import java.awt.*;

public class StartFramePanel extends JPanel {
    private static final int WIDTH = 424;
    private static final int HEIGHT = 200;
    private ConnectButton connectButton;
    private OfflineButton offlineButton;
    private JLabel gameMode;

    public StartFramePanel() {
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Color color2 = new Color(113, 89, 68);
        Color color1 = new Color(193 ,161, 121);
        GradientPaint gp = new GradientPaint(90, 300, color1, 4, HEIGHT, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints qualityHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(qualityHints);
        g2d.setColor(new Color(45, 45, 45));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

    }

    public void initStartFrame(){
        gameMode = new JLabel("Choose game mode");
        gameMode.setBackground(new Color(60, 58, 60));
        gameMode.setBounds(90,30,240,32);
        gameMode.setFont(new Font("Arial", Font.BOLD, 24));
        gameMode.setForeground(new Color(245, 245, 249));
        add(gameMode);
        connectButton=new ConnectButton();
        add(connectButton);
        offlineButton=new OfflineButton();
        add(offlineButton);

    }

}
