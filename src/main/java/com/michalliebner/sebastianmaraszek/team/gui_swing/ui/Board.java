package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.black;

public class Board extends JPanel {

    private Graphics2D g2;
    private final int BoardSizeInSquares=12;
    private final int OneSquareSize=50;
    private final int BoardCentre=(BoardSizeInSquares+2)*OneSquareSize/2;
    private final int PointSize= 10;

    public Board(){
        setLayout(null);
        setSize(OneSquareSize*BoardSizeInSquares+2,OneSquareSize*BoardSizeInSquares);
        setSize(800,800);
        setBackground(new Color(218, 218, 218));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g.create();
        for (int i = OneSquareSize; i <= OneSquareSize * BoardSizeInSquares;
             i = i + OneSquareSize) {
            for (int j = OneSquareSize; j <= OneSquareSize * BoardSizeInSquares;
                 j = j + OneSquareSize) {
                double thickness = 2;
                g2.setStroke(new BasicStroke((float) thickness));
                g2.drawRect(i, j, OneSquareSize, OneSquareSize);
            }
        }

        g2.setPaint(black);
        g2.fillOval(3 * OneSquareSize - PointSize / 2,
                3 * OneSquareSize - PointSize / 2, PointSize, PointSize);
        g2.fillOval(11 * OneSquareSize - PointSize / 2,
                3 * OneSquareSize - PointSize / 2, PointSize, PointSize);
        g2.fillOval(11 * OneSquareSize - PointSize / 2,
                11 * OneSquareSize - PointSize / 2, PointSize, PointSize);
        g2.fillOval(3 * OneSquareSize - PointSize / 2,
                11 * OneSquareSize - PointSize / 2, PointSize, PointSize);
        g2.fillOval(BoardCentre - PointSize / 2, BoardCentre - PointSize / 2,
                PointSize, PointSize);
    }
}
