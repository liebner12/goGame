package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.DecisionFrame;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class DecisionFramePanel extends JPanel {
    private static final int WIDTH = 424;
    private static final int HEIGHT = 400;
    private static final int WIDTHFIELD = 362;
    private static final int HEIGHTFIELD = 40;
    private BlackButton blackButton;
    private WhiteButton offlineButton;
    private JLabel cont;
    private EndGameButton endGameButton;
    private JLabel blackScore;
    private JLabel whiteScore;
    private JFormattedTextField blackScoreField;
    private JFormattedTextField whiteScoreField;

    public DecisionFramePanel() {
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color2 = new Color(113, 89, 68);
        Color color1 = new Color(193, 161, 121);
        GradientPaint gp = new GradientPaint(90, 300, color1, 4, HEIGHT, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void setText(){
        cont = new JLabel("Continue");
        cont.setBackground(new Color(60, 58, 60));
        cont.setBounds(135, 15, 390, 32);
        cont.setFont(new Font("Arial", Font.BOLD, 30));
        cont.setForeground(new Color(245, 245, 249));
    }
    public void setText2nd(){
        whiteScore = new JLabel("Set White Score");
        whiteScore.setBackground(new Color(60, 58, 60));
        whiteScore.setBounds(110, 60, 240, 124);
        whiteScore.setFont(new Font("Arial", Font.BOLD, 24));
        whiteScore.setForeground(new Color(245, 245, 249));
    }
    public void setText3nd(){
        blackScore = new JLabel("Set Black Score");
        blackScore.setBackground(new Color(60, 58, 60));
        blackScore.setBounds(110, 150, 300, 124);
        blackScore.setFont(new Font("Arial", Font.BOLD, 24));
        blackScore.setForeground(new Color(245, 245, 249));
    }
    public void setBoundsWhiteBlackScoreField(){
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        blackScoreField = new JFormattedTextField(formatter);
        blackScoreField.setHorizontalAlignment(JTextField.CENTER);
        blackScoreField.setFont(new Font("SansSerif", Font.BOLD, 18));
        blackScoreField.setBounds(20,240,WIDTHFIELD,HEIGHTFIELD);
        blackScoreField.setForeground(new Color(245, 245, 249));
        blackScoreField.setBackground(new Color(49, 48, 54));
        whiteScoreField = new JFormattedTextField(formatter);
        whiteScoreField.setHorizontalAlignment(JTextField.CENTER);
        whiteScoreField.setFont(new Font("SansSerif", Font.BOLD, 18));
        whiteScoreField.setBounds(20,150,WIDTHFIELD,HEIGHTFIELD);
        whiteScoreField.setForeground(new Color(245, 245, 249));
        whiteScoreField.setBackground(new Color(49, 48, 54));

    }

    public void initStartFrame() {
        setText();
        setText2nd();
        setText3nd();
        setBoundsWhiteBlackScoreField();
        blackButton = new BlackButton();
        offlineButton = new WhiteButton();
        endGameButton = new EndGameButton();
        add(cont);
        add(blackButton);
        add(offlineButton);
        add(endGameButton);
        add(blackScore);
        add(whiteScore);
        add(blackScoreField);
        add(whiteScoreField);

    }

    public BlackButton getBlackButton() {
        return blackButton;
    }
    public WhiteButton getWhiteButton(){
        return offlineButton;
    }
    public EndGameButton getEndGameButton(){
        return endGameButton;
    }
    public JFormattedTextField getWhiteScoreField(){
        return whiteScoreField;
    }
    public JFormattedTextField getBlackScoreField(){
        return blackScoreField;
    }


}
