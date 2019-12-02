package com.michalliebner.sebastianmaraszek.team;


import com.michalliebner.sebastianmaraszek.team.gui_swing.controller.GoGameGuiController;

import java.io.IOException;
import java.io.Serializable;

public class App{
    public static void main(final String args[]) throws IOException {
        GoGameGuiController goGameGuiController = new GoGameGuiController();
        goGameGuiController.showMainFrameWindow();
    }
}