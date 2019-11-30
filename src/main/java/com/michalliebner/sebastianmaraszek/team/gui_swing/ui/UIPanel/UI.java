package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 700;
    private JLabel graWGo;
    private SurrenderButton surrenderButton;
    private PassButton passButton;
    private Results results;
    private StartButton startButton;
    private UndoButton undoButton;

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
        graWGo = new JLabel("GRA W GO");
        graWGo.setBounds(120,40,200,32);
        graWGo.setFont(new Font("SansSerif", Font.BOLD, 32));
        graWGo.setForeground(new Color(245, 245, 249));

        results = new Results();
        startButton = new StartButton();
        passButton = new PassButton();
        undoButton = new UndoButton();
        surrenderButton = new SurrenderButton();
        add(graWGo);
        add(results);
        add(startButton);
        add(passButton);
        add(undoButton);
        add(surrenderButton);
    }


    public Results getResults(){
        return results;
    }
    public SurrenderButton getSurrenderButton(){
        return surrenderButton;
    }
    public PassButton getNextButton(){
        return passButton;
    }
    public StartButton getStartButton(){
        return startButton;
    }
    public UndoButton getUndoButton(){
        return undoButton;
    }
}
