package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.GoGameGui;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class GoGameGuiControllerTest {
    GoGameGuiController goGameGuiController;

    {
        try {
            goGameGuiController = new GoGameGuiController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addBoardButtons(){
    }

    @Test
    public void showMainFrameWindow() {
        assertTrue(true);
    }
    @Test public void actionPerformed(){

    }

    @Test
    public void processInformation() {
        try {
            goGameGuiController.processInformation(1,1);
            goGameGuiController.processInformation(12,1);
            goGameGuiController.processInformation(1,13);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}