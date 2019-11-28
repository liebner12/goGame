package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;


import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.GoGameGui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoGameGuiController {
    private GoGameGui mainFrame;
    private JButton exitButton;
    private JPanel UI;
    private JPanel board;
    public GoGameGuiController(){
        initComponents();
        initListeners();
    }
    private void initComponents(){
        mainFrame = new GoGameGui();
        exitButton = mainFrame.getExitButton();
        UI = mainFrame.getUIPanel();
        board = mainFrame.getBoard();
    }
    public void showMainFrameWindow(){
        mainFrame.setVisible(true);

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
