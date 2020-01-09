package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel;
import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 700;
    private SurrenderButton surrenderButton;
    private StartButton startButton;
    private PassButton passButton;

    public UI(){
        setLayout(null);
        setBounds(770,30,WIDTH,HEIGHT);
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
    public void initUI(){
        passButton = new PassButton();
        startButton = new StartButton();
        surrenderButton = new SurrenderButton();
        add(passButton);
        add(surrenderButton);
        add(startButton);

    }

    public StartButton getStartButton(){
        return startButton;
    }
    public SurrenderButton getSurrenderButton(){
        return surrenderButton;
    }
    public PassButton getPassButton(){
        return passButton;
    }


}