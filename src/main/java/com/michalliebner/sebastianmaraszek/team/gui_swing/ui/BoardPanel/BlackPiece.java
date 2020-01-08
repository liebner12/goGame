package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;
import java.io.Serializable;

public class BlackPiece implements Piece, Serializable {
    private int size=50;
    private final boolean[][] BreathPosition;
    private Color color=Color.BLACK;
    private int posX;
    private int posY;
    private int breath=4;

    public BlackPiece(){
        BreathPosition=new boolean[13][13];
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
    public int getBreathNumber() {
        return breath;
    }

    @Override
    public void fullBreathNumber(){
        if(isInCorner())
            setBreathNumber(2);
        else if(isOnBorder())
            setBreathNumber(3);
        else
            setBreathNumber(4); //
    }

    @Override
    public  void setBreathNumber(int x) {
        this.breath=x;
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
    public boolean[][] getBreathPosition(){
        return BreathPosition;
    }

    @Override
    public boolean isInCorner() {
        return (getX() == 0 && getY() == 0) || (getX() == 12 & getY() == 0) || (getX() == 12 & getY() == 12) || (getX() == 0 & getY() == 12);
    }

    @Override
    public boolean isInCentre() {
        return !isOnBorder() && !isInCorner();
    }

    @Override
    public boolean isOnBorder() {
        if(!isInCorner()) {
            return getX() == 0 || getY() == 0 || getX() == 12 || getY() == 12;
        }
        return false;
    }

    @Override
    public void takeBreath(int x, int y) {
        BreathPosition[x][y]=true;
        this.breath=this.breath-1;
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
