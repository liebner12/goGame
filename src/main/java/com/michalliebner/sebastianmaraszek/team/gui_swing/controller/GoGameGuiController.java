package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.ExitButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoGameGuiController {
    private GoGameGui mainFrame;
    private ExitButton exitButton;
    private UI ui;
    private Window window;
    private PassButton passButton;
    private SurrenderButton surrenderButton;
    private Board board;
    private Results results;
    private StartButton startButton;
    private UndoButton undoButton;

    public GoGameGuiController(){
        initComponents();
        initListeners();
        addBoardButtons();
    }
    private void initComponents(){
        mainFrame = new GoGameGui();

        window = mainFrame.getWindow();
        exitButton = window.getExitButton();

        ui = mainFrame.getUIPanel();
        results = ui.getResults();
        startButton = ui.getStartButton();
        passButton = ui.getNextButton();
        surrenderButton = ui.getSurrenderButton();
        undoButton = ui.getUndoButton();

        board = mainFrame.getBoard();
    }
    public void showMainFrameWindow(){
        mainFrame.setVisible(true);
    }
    private void initListeners() {
        exitButton.addActionListener(new ExitButton.ExitButtonLister());
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

}
