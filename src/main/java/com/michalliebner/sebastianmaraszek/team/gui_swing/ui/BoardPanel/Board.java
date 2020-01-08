//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel {
    Cursor cursor;
    List<Piece> pieceList = new ArrayList();
    private ActionListener actionListener;
    public JButton[][] Buttons;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private Graphics2D g2;
    private Graphics2D g;
    private final int BoardSizeInSquares = 12;
    private final int OneSquareSize = 50;
    private final int BoardCentre = 350;
    private final int PointSize = 10;

    public Board() {
        this.setLayout((LayoutManager)null);
        this.cursor = new Cursor(12);
        this.setSize(602, 600);
        this.setBounds(30, 30, 700, 700);
        this.setBackground(new Color(193, 161, 121));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g2 = (Graphics2D)g.create();
        Color color2 = new Color(113, 89, 68);
        Color color1 = new Color(193, 161, 121);
        GradientPaint gp = new GradientPaint(90.0F, 300.0F, color1, 4.0F, 700.0F, color2);
        this.g2.setPaint(gp);
        this.g2.fillRect(0, 0, 700, 700);
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g2.setPaint(Color.black);
        this.g2.fillOval(145, 145, 10, 10);
        this.g2.fillOval(545, 145, 10, 10);
        this.g2.fillOval(545, 545, 10, 10);
        this.g2.fillOval(145, 545, 10, 10);
        this.g2.fillOval(345, 345, 10, 10);

        for(int i = 50; i <= 600; i += 50) {
            for(int j = 50; j <= 600; j += 50) {
                double thickness = 2.0D;
                this.g2.setStroke(new BasicStroke((float)thickness));
                this.g2.drawRect(i, j, 50, 50);
            }
        }

        if (this.pieceList.size() > 0) {
            Iterator var9 = this.pieceList.iterator();

            while(var9.hasNext()) {
                Piece p = (Piece)var9.next();
                this.g2.setPaint(p.getColor());
                this.g2.fillOval(p.getX() * 50 + 25, p.getY() * 50 + 25, 50, 50);
            }
        }

    }

    public void addButtons(ActionListener al) {
        this.Buttons = new JButton[13][13];

        for(int i = 0; i <= 12; ++i) {
            for(int j = 0; j <= 12; ++j) {
                this.Buttons[i][j] = new JButton();
                this.Buttons[i][j].setBounds(50 * i + 35, 50 * j + 35, 30, 30);
                this.Buttons[i][j].addActionListener(al);
                this.Buttons[i][j].setBorderPainted(false);
                this.Buttons[i][j].setContentAreaFilled(false);
                this.Buttons[i][j].setFocusPainted(false);
                this.Buttons[i][j].setCursor(this.cursor);
                this.Buttons[i][j].setEnabled(false);
                this.add(this.Buttons[i][j]);
            }
        }

    }

    public void play(List<Piece> pieceList) {
        this.pieceList = pieceList;
        this.repaint();
    }
}
