package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import static java.awt.Cursor.getDefaultCursor;
import static org.junit.Assert.*;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.ExitButton.ExitButtonLister;
import org.junit.Test;

public class ExitButtonTest {
    final ExitButton exitButton=new ExitButton();
    final ExitButtonLister exitButtonLister=new ExitButtonLister();
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