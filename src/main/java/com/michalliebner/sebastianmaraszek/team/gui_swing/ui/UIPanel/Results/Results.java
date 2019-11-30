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
    private JLabel blackResultLabel;
    private JLabel whiteResultLabel;
    private JLabel score;
    private ResultScore blackResultScore;
    private ResultScore whiteResultScore;
    private JLabel territory;
    private ResultScore blackResultTerritory;
    private ResultScore whiteResultTerritory;
    private JLabel prisoners;
    private ResultScore blackResultPrisoners;
    private ResultScore whiteResultPrisoners;
    private static final int WIDTHFIELD = 172;
    private static final int HEIGHTFIELD = 40;
    private static final int WIDTHLABEL = 120;
    private static final int HEIGHTLABEL = 30;
    private BufferedImage myPicture1;
    private BufferedImage myPicture2;
    private BufferedImage myPicture3;
    private BufferedImage myPicture4;
    private BufferedImage myPicture5;
    public Results() throws IOException {
        initComponents();
        setLayout(null);
        setBounds(0, 80, WIDTH, HEIGHT);
        setOpaque(false);
    }

    public void initComponents() throws IOException {
        myPicture4 = ImageIO.read(new File("C:\\Users\\Seba\\IdeaProjects\\goGame\\src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\79407459_679389379255901_3066448809295872000_n.png"));
        blackResultLabel = new JLabel(new ImageIcon(myPicture4));
        blackResultLabel.setBounds(45, 20, WIDTHLABEL, HEIGHTLABEL+20);
        myPicture5 = ImageIO.read(new File("C:\\Users\\Seba\\IdeaProjects\\goGame\\src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\białe.png"));
        whiteResultLabel = new JLabel(new ImageIcon(myPicture5));
        whiteResultLabel.setBounds(235, 20, WIDTHLABEL, HEIGHTLABEL+20);
        myPicture2 = ImageIO.read(new File("C:\\Users\\Seba\\IdeaProjects\\goGame\\src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\icons8-leaderboard-50.png"));
        score = new JLabel(new ImageIcon(myPicture2));
        score.setBounds(137, 70, WIDTHLABEL, HEIGHTLABEL);
        blackResultScore = new ResultScore();
        blackResultScore.setBounds(17, 120, WIDTHFIELD, HEIGHTFIELD);
        whiteResultScore = new ResultScore();
        whiteResultScore.setBounds(207, 120, WIDTHFIELD, HEIGHTFIELD);

        myPicture3 = ImageIO.read(new File("C:\\Users\\Seba\\IdeaProjects\\goGame\\src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\77328842_573493556801356_6734551369153249280_n.png"));
        territory = new JLabel(new ImageIcon(myPicture3));
        territory.setBounds(135, 200, WIDTHLABEL, HEIGHTLABEL+20);
        blackResultTerritory = new ResultScore();
        blackResultTerritory.setBounds(17, 270, WIDTHFIELD, HEIGHTFIELD);
        whiteResultTerritory = new ResultScore();
        whiteResultTerritory.setBounds(207, 270, WIDTHFIELD, HEIGHTFIELD);



        myPicture1 = ImageIO.read(new File("C:\\Users\\Seba\\IdeaProjects\\goGame\\src\\main\\java\\com\\michalliebner\\sebastianmaraszek\\team\\gui_swing\\ui\\UIPanel\\Results\\icons8-prisoner-60.png"));
        prisoners = new JLabel(new ImageIcon(myPicture1));
        prisoners.setBounds(167,345,60,60);
        blackResultPrisoners = new ResultScore();
        blackResultPrisoners.setBounds(17, 420, WIDTHFIELD, HEIGHTFIELD);
        whiteResultPrisoners = new ResultScore();
        whiteResultPrisoners.setBounds(207, 420, WIDTHFIELD, HEIGHTFIELD);

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
    }

}
