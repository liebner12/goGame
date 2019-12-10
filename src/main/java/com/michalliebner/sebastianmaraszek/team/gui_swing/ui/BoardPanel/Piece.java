package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;

public interface Piece {
    int getX();
    int getY();
    int getBreath();
    void setX(int x);
    void setY(int y);
    boolean isInCorner();
    boolean isInCentre();
    boolean isOnBorder();
    void takeBreath();

    int getSize();
    void setBreath(int x);
    Color getColor();
    void setColor(Color c);
    void setSize(int i);

}
