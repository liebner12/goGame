package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;

public interface Piece {
    int getX();
    int getY();
    int getBreathNumber();
    boolean[][] getBreathPosition();
    void fullBreathNumber();
    void setX(int x);
    void setY(int y);
    boolean isInCorner();
    boolean isInCentre();
    boolean isOnBorder();
    void takeBreath(int x,int y);

    int getSize();
    void setBreathNumber(int x);
    Color getColor();
    void setColor(Color c);
    void setSize(int i);

}
