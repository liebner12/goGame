package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.Results;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Results extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 520;
    private ResultScore blackResultScore;
    private ResultScore whiteResultScore;
    private ResultScore blackResultTerritory;
    private ResultScore whiteResultTerritory;
    private ResultScore blackResultPrisoners;
    private ResultScore whiteResultPrisoners;
    private static final int WIDTHFIELD = 172;
    private static final int HEIGHTFIELD = 40;
    private static final int WIDTHLABEL = 120;
    private static final int HEIGHTLABEL = 30;
    private JLabel turnPoint1;
    private JLabel turnPoint2;

    public Results() {
        setLayout(null);
        setBounds(770, 80, WIDTH, HEIGHT);
        setOpaque(false);
    }

    public void initResults() throws IOException {
        whoTurn();
        BufferedImage myPicture4 = ImageIO.read(new File("src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\79407459_679389379255901_3066448809295872000_n.png"));
        JLabel blackResultLabel = new JLabel(new ImageIcon(myPicture4));
        blackResultLabel.setBounds(45, 0, WIDTHLABEL, HEIGHTLABEL+20);
        BufferedImage myPicture5 = ImageIO.read(new File("src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\białe.png"));
        JLabel whiteResultLabel = new JLabel(new ImageIcon(myPicture5));
        whiteResultLabel.setBounds(235, 0, WIDTHLABEL, HEIGHTLABEL+20);
        BufferedImage myPicture2 = ImageIO.read(new File("src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\icons8-leaderboard-50.png"));
        JLabel score = new JLabel(new ImageIcon(myPicture2));
        score.setBounds(137, 70, WIDTHLABEL, HEIGHTLABEL);
        blackResultScore = new ResultScore();
        blackResultScore.setBounds(17, 120, WIDTHFIELD, HEIGHTFIELD);
        blackResultScore.setText("0");
        whiteResultScore = new ResultScore();
        whiteResultScore.setBounds(207, 120, WIDTHFIELD, HEIGHTFIELD);
        whiteResultScore.setText("0");
        BufferedImage myPicture3 = ImageIO.read(new File("src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\77328842_573493556801356_6734551369153249280_n.png"));
        JLabel territory = new JLabel(new ImageIcon(myPicture3));
        territory.setBounds(135, 200, WIDTHLABEL, HEIGHTLABEL+20);
        blackResultTerritory = new ResultScore();
        blackResultTerritory.setBounds(17, 270, WIDTHFIELD, HEIGHTFIELD);
        blackResultTerritory.setText("0");
        whiteResultTerritory = new ResultScore();
        whiteResultTerritory.setBounds(207, 270, WIDTHFIELD, HEIGHTFIELD);
        whiteResultTerritory.setText("0");

        BufferedImage myPicture1 = ImageIO.read(new File("src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\icons8-prisoner-60.png"));
        JLabel prisoners = new JLabel(new ImageIcon(myPicture1));
        prisoners.setBounds(167,345,60,60);
        blackResultPrisoners = new ResultScore();
        blackResultPrisoners.setBounds(17, 420, WIDTHFIELD, HEIGHTFIELD);
        blackResultPrisoners.setText("0");
        whiteResultPrisoners = new ResultScore();
        whiteResultPrisoners.setBounds(207, 420, WIDTHFIELD, HEIGHTFIELD);
        whiteResultPrisoners.setText("0");
        add(blackResultLabel);
        add(whiteResultLabel);

        add(score);
        add(blackResultScore);
        add(whiteResultScore);

        add(territory);
        add(blackResultTerritory);
        add(whiteResultTerritory);

        add(prisoners);
        add(blackResultPrisoners);
        add(whiteResultPrisoners);

        add(turnPoint1);
        add(turnPoint2);
    }
    public void whoTurn(){
        turnPoint1 = new JLabel("*");
        turnPoint1.setForeground(Color.DARK_GRAY);
        turnPoint1.setFont(new Font("Arial", Font.BOLD, 24));
        turnPoint1.setBounds(30,20,24,24);
        turnPoint2 = new JLabel("*");
        turnPoint2.setForeground(Color.DARK_GRAY);
        turnPoint2.setFont(new Font("Arial", Font.BOLD, 24));
        turnPoint2.setBounds(363,20,24,24);
    }

    public ResultScore getBlackResultTerritory(){
        return blackResultTerritory;
    }
    public ResultScore getWhiteResultTerritory(){
        return whiteResultTerritory;
    }
    public ResultScore getBlackResultScore(){
        return blackResultScore;
    }
    public ResultScore getWhiteResultScore(){
        return whiteResultScore;
    }
    public ResultScore getBlackResultPrisoners(){
        return blackResultPrisoners;
    }
    public ResultScore getWhiteResultPrisoners(){
        return whiteResultPrisoners;
    }
    public JLabel getPointTurn1(){
        return turnPoint1;
    }
    public JLabel getPointTurn2(){
        return turnPoint2;
    }

}
