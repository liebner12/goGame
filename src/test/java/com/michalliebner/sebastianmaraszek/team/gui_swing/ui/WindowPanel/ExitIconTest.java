package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExitIconTest {
    final ExitIcon e = new ExitIcon();


    @Test
    public void getIconWidth() {

        assertEquals(e.getIconWidth(),32);
    }

    @Test
    public void getIconHeight() {

        assertEquals(e.getIconHeight(),32);
    }
}