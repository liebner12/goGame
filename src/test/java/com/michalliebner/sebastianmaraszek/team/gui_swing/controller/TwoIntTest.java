package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoIntTest {

    int x=1;
    int y=0;
    final TwoInt twoInt=new TwoInt(1,0);

    @Test
    public void getX() {
        assertTrue(twoInt.x==twoInt.getX());
    }

    @Test
    public void getY() {
        assertTrue(twoInt.y==twoInt.getY());
    }
}