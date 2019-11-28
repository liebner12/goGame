package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;


import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.GoGameGui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoGameGuiController {
    private GoGameGui Board;
    private JButton exitButton;
    private JPanel panel1;
    private JPanel panel2;
    public GoGameGuiController(){
        initComponents();
        initListeners();
    }
    private void initComponents(){
        Board = new GoGameGui();
        exitButton = Board.getExitButton();
    }
    public void showMainFrameWindow(){
        Board.setVisible(true);

    }
    private void initListeners(){
        exitButton.addActionListener(new ExitButtonLister());
    }

    private class ExitButtonLister implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
