package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

import static java.awt.Color.black;

public class Board extends JPanel {

    private ActionListener actionListener;
    private JButton[][] Buttons;
    private static final int WIDTH = 705;
    private static final int HEIGHT = 800;
    private Graphics2D g2;
    private final int BoardSizeInSquares = 12;
    private final int OneSquareSize = 50;
    private final int BoardCentre =
        (BoardSizeInSquares + 2) * OneSquareSize / 2;
    private final int PointSize = 10;

    public Board() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        this.setLayout(null);

        setSize(OneSquareSize * BoardSizeInSquares + 2,
            OneSquareSize * BoardSizeInSquares);
        setSize(WIDTH, HEIGHT);
        setBackground(new Color(124, 124, 124));
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

    public void addButtons(ActionListener al) {
        Buttons = new JButton[BoardSizeInSquares + 1][BoardSizeInSquares + 1];
        for (int i = 0; i <=BoardSizeInSquares; i = i + 1) {
            for (int j = 0; j <=BoardSizeInSquares; j = j + 1) {
                JButton button = new JButton();
                button.setBounds((OneSquareSize)*(i)+35,(OneSquareSize)*(j)+35,30,30);
                button.addActionListener(al);
                add(button);
                Buttons[i][j] = button;
            }
        }
    }

}

