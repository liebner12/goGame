package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;
import java.io.Serializable;

public class WhitePiece implements Piece, Serializable {
    private int size=50;
    private Color color=Color.WHITE;
    private int posX;
    private int posY;
    private int breath=4;
    private boolean[][] BreathPosition;


    public WhitePiece(){
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
    public boolean isInCorner() {
        if((getX()==0 && getY()==0) || (getX()==12 & getY()==0) || (getX()==12 & getY()==12) || (getX()==0 & getY()==12)){
            return true;
        }
        else return false;
    }

    @Override
    public boolean isInCentre() {
        if(!isOnBorder() && !isInCorner()){
            return true;
        }
        return false;

    }

    @Override
    public boolean isOnBorder() {
        if(!isInCorner()) {
            if (getX() == 0 || getY() == 0 || getX() == 12 || getY() == 12) {

                return true;
            }}
        return false;
    }

    @Override
    public boolean[][] getBreathPosition(){
        return BreathPosition;
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

