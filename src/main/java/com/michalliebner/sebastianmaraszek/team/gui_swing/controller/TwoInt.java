package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import java.io.Serializable;

public class TwoInt implements Serializable {
    int x;
    int y;

    public TwoInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
