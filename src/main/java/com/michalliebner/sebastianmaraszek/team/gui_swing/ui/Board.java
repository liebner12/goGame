package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.black;

public class Board extends JPanel {
    List<Piece> pieceList=new ArrayList<>();
    private ActionListener actionListener;
    public JButton[][] Buttons;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private Graphics2D g2;
    private Graphics2D g;
    private final int BoardSizeInSquares = 12;
    private final int OneSquareSize = 50;
    private final int BoardCentre =
        (BoardSizeInSquares + 2) * OneSquareSize / 2;
    private final int PointSize = 10;

    public Board() {

        this.setLayout(null);

        setSize(OneSquareSize * BoardSizeInSquares + 2,
            OneSquareSize * BoardSizeInSquares);
        setBounds(30,30, WIDTH, HEIGHT);
        setBackground(new Color(124, 91, 39));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
        if(pieceList.size()>0){
            for(Piece p : pieceList){
            g2.setPaint(p.getColor());
            g2.fillOval(p.getX()*OneSquareSize+25,p.getY()*OneSquareSize+25,50,50);
        }
    }}

    public void addButtons(ActionListener al) {
        Buttons = new JButton[BoardSizeInSquares + 1][BoardSizeInSquares + 1];
        for (int i = 0; i <=BoardSizeInSquares; i = i + 1) {
            for (int j = 0; j <=BoardSizeInSquares; j = j + 1) {
                Buttons[i][j] = new JButton();
                Buttons[i][j].setBounds((OneSquareSize)*(i)+35,(OneSquareSize)*(j)+35,30,30);
                Buttons[i][j].addActionListener(al);
                Buttons[i][j].setBorderPainted(false);
                Buttons[i][j].setContentAreaFilled(false);
                add(Buttons[i][j]);
            }
        }
    }
    public void addPiece(int x, int y){
        BlackPiece piece=new BlackPiece();
        piece.setX(x);
        piece.setY(y);
        pieceList.add(piece);
        repaint();
    }

}

