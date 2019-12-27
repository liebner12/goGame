package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import static java.lang.Thread.sleep;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.StartFrame;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.StartFramePanel;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JEditorPane;

public class GoGameGuiController {
    Socket s;
    ObjectOutputStream p;
    Boolean turn;

    private GoGameGui mainFrame;
    private UI ui;
    private Window window;
    private Board board;
    private StartFramePanel startFramePanel;
    private StartFrame startFrame;
    public GoGameGuiController() throws IOException {
        initComponents();
        initListeners();
        addBoardButtons();
    }
    private void initComponents() throws IOException {
        turn= false;
        mainFrame = new GoGameGui();
        startFrame = new StartFrame();
        window = mainFrame.getWindow();
        ui = mainFrame.getUIPanel();
        board = mainFrame.getBoard();
        startFramePanel = startFrame.getStartFramePanel();
    }
    public void showMainFrameWindow(){
        mainFrame.setVisible(true);
    }
    private void initListeners() {
        window.initListeners();
        ui.initListeners();
    }

    private void addBoardButtons() {
        board.addButtons(new BoardPiecesListener());
    }


    private class BoardPiecesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            for(int i=0; i<13; i++){
                for(int j=0; j<13; j++){
                    if(e.getSource()==board.Buttons[i][j]){
                        try {
                            processInformation(i,j);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        //board.play(virtualBoard.getGameBoard());
                    }
                }
            }
        }
    }
    public void processInformation(int x, int y) throws Exception {
        Socket s = new Socket("localhost", 510);
        ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());

        TwoInt twoInt=new TwoInt(x,y);
        p.writeObject(twoInt);
        p.flush();

        ObjectInputStream b = new ObjectInputStream(s.getInputStream());
        List<Piece> received = (List<Piece>) b.readObject();
        System.out.println("got");
        board.play(received);

        p.close();
        b.close();
        s.close();
    }

}
