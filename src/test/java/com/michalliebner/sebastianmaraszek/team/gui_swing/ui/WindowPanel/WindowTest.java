package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.junit.Test;

public class WindowTest {
    final Window window=new Window();
    Graphics g;
    Graphics2D g2;
    @Test
    public void initWindow(){
        assertEquals(window.getHeight(),900);
        window.initWindow();
}}
