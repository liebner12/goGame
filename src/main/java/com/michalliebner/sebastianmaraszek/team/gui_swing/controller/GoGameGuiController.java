package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GoGameGuiController {
    Boolean turn;
    private VirtualBoard virtualBoard;
    private GoGameGui mainFrame;
    private UI ui;
    private Window window;
    private Board board;


    public GoGameGuiController() throws IOException {
        initComponents();
        initListeners();
        addBoardButtons();
    }
    private void initComponents() throws IOException {
        turn=new Boolean(false);
        virtualBoard=new VirtualBoard();
        mainFrame = new GoGameGui();
        window = mainFrame.getWindow();
        ui = mainFrame.getUIPanel();
        board = mainFrame.getBoard();
    }
    public void showMainFrameWindow(){
        mainFrame.setVisible(true);
    }
    private void initListeners() {
        window.initListeners();
    }

    private void addBoardButtons() {
        board.addButtons(new BoardPiecesListener());
    }
    private class BoardPiecesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            for(int i=0; i<13; i++){
                for(int j=0; j<13; j++){
                    if(e.getSource()==board.Buttons[i][j]){
                        virtualBoard.addPiece(i,j);

                        board.play(virtualBoard.getGameBoard());
                    }
                }
            }
        }
    }

}
