package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import java.io.Serializable;

public class TwoInt implements Serializable {
    final int x;
    final int y;
    public TwoInt(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
