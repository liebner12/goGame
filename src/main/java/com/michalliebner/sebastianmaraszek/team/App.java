package com.michalliebner.sebastianmaraszek.team;


import com.michalliebner.sebastianmaraszek.team.gui_swing.controller.GoGameGuiController;

public class App {
    public static void main(final String args[]) {
        GoGameGuiController goGameGuiController = new GoGameGuiController();
        goGameGuiController.showMainFrameWindow();
    }
}