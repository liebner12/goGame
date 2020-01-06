package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import static java.awt.Cursor.HAND_CURSOR;
import static java.awt.Cursor.getDefaultCursor;
import static org.junit.Assert.*;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.ExitButton.ExitButtonLister;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import org.junit.Test;

public class ExitButtonTest {
    ExitButton exitButton=new ExitButton();
    ExitButtonLister exitButtonLister=new ExitButtonLister();
    @Test
    public void ExitButton(){

    }
    @Test
    public void exitBtn(){
        exitButton.addActionListener(exitButtonLister);
        assertNotEquals(exitButton.getCursor(), getDefaultCursor());
        assertEquals(exitButton.getActionListeners().length,1);
    }
}