package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;


import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.GoGameGui;

import javax.swing.*;

public class GoGameGuiController {
    private GoGameGui Board;

    public GoGameGuiController(){
        initComponents();
        initListeners();
    }
    private void initComponents(){
        Board = new GoGameGui();
    }
    public void showMainFrameWindow(){
        Board.setVisible(true);

    }
    private void initListeners(){

    }

}
