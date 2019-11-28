package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import java.awt.Color;

public interface Piece {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    int getSize();
Color getColor();
void setColor(Color c);
void setSize(int i);
}
