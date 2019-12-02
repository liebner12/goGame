package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;
import java.io.Serializable;

public class WhitePiece implements Piece, Serializable {
    private int size=50;
    private Color color=Color.WHITE;
    private int posX;
    private int posY;

    public WhitePiece(){

    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public void setX(int x) {
        posX=x;
    }

    @Override
    public void setY(int y) {
        posY=y;

    }

    @Override
    public int getSize(){

        return size;
    }
    @Override
    public Color getColor()
    {
        return color;
    }

    @Override
    public void setColor(Color c) {
        this.color=c;
    }

    @Override
    public void setSize(int i) {
        this.size=i;
    }
}
