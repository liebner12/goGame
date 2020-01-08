package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;

public interface Piece {
    int getX();

    int getY();

    int getBreathNumber();

    boolean[][] getBreathPosition();

    void addBreath(int var1, int var2);

    void fullBreathNumber();

    void setX(int var1);

    void setY(int var1);

    boolean isInCorner();

    boolean isInCentre();

    boolean isOnBorder();

    void takeBreath(int var1, int var2);

    int getSize();

    void setBreathNumber(int var1);

    Color getColor();

    void setColor(Color var1);

    void setSize(int var1);
}