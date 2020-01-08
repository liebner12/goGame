package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;
import java.io.Serializable;

public class BlackPiece implements Piece, Serializable {
    private int size = 50;
    private boolean[][] BreathPosition;
    private Color color;
    private int posX;
    private int posY;
    private int breath;

    public BlackPiece() {
        this.color = Color.BLACK;
        this.breath = 4;
        this.BreathPosition = new boolean[13][13];
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public int getBreathNumber() {
        return this.breath;
    }

    public void fullBreathNumber() {
        if (this.isInCorner()) {
            this.setBreathNumber(2);
        } else if (this.isOnBorder()) {
            this.setBreathNumber(3);
        } else {
            this.setBreathNumber(4);
        }

    }

    public void setBreathNumber(int x) {
        this.breath = x;
    }

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }

    public boolean[][] getBreathPosition() {
        return this.BreathPosition;
    }

    public boolean isInCorner() {
        return this.getX() == 0 && this.getY() == 0 || this.getX() == 12 & this.getY() == 0 || this.getX() == 12 & this.getY() == 12 || this.getX() == 0 & this.getY() == 12;
    }

    public boolean isInCentre() {
        return !this.isOnBorder() && !this.isInCorner();
    }

    public boolean isOnBorder() {
        return !this.isInCorner() && (this.getX() == 0 || this.getY() == 0 || this.getX() == 12 || this.getY() == 12);
    }

    public void takeBreath(int x, int y) {
        this.BreathPosition[x][y] = true;
        --this.breath;
    }

    public void addBreath(int x, int y) {
        this.BreathPosition[x][y] = false;
        ++this.breath;
    }

    public int getSize() {
        return this.size;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setSize(int i) {
        this.size = i;
    }
}
