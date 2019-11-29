package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;


import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.NextButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.SurrenderButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.ExitButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoGameGuiController {
    private GoGameGui mainFrame;
    private ExitButton exitButton;
    private com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.UI UI;
    private Window window;
    private NextButton nextButton;
    private SurrenderButton surrenderButton;
    private Board board;

    public GoGameGuiController(){
        initComponents();
        initListeners();
        addBoardButtons();
    }
    private void initComponents(){
        mainFrame = new GoGameGui();
        window = mainFrame.getWindow();
        exitButton = mainFrame.getExitButton();
        UI = mainFrame.getUIPanel();
        board = mainFrame.getBoard();
        nextButton = mainFrame.getNextButton();
        surrenderButton = mainFrame.getSurrenderButton();
    }
    public void showMainFrameWindow(){
        mainFrame.setVisible(true);
    }
    private void initListeners() {
        exitButton.addActionListener(new ExitButtonLister());
    }

    private void addBoardButtons() {
        board.addButtons(new BoardPiecesListener());
    }
    private class BoardPiecesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            for(int i=0; i<13; i++){
                for(int j=0; j<13; j++){
                    if(e.getSource()==board.Buttons[i][j]){
                        System.out.println(i+" "+j);
                        board.addPiece(i,j);
                    }
                }
            }
        }
    }
    private class ExitButtonLister implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
