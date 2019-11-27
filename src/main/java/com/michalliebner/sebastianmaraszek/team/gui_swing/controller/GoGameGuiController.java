package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;


import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.GoGameGui;

public class GoGameGuiController {
    private GoGameGui mainFrame;
    public GoGameGuiController(){
        initComponents();
        initListeners();
    }
    private void initComponents(){
        mainFrame = new GoGameGui();
    }
    public void showMainFrameWindow(){
        mainFrame.setVisible(true);
    }
    private void initListeners(){

    }
}
