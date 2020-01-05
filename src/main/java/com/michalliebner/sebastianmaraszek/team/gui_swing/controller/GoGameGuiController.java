package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.ConnectButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.OfflineButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.StartFrame;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.StartFramePanel;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.List;


public class GoGameGuiController {
    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Boolean turn;
    private DataOutputStream outputStream;
    private byte type;
    private GoGameGui mainFrame;
    private UI ui;
    private Window window;
    private Board board;
    private StartFramePanel startFramePanel;
    private StartFrame startFrame;
    private StartButton startButton;
    private SurrenderButton surrenderButton;
    private ConnectButton connectButton;
    private PassButton passButton;
    private TwoInt twoInt;
    private List<Piece> received;
    private OfflineButton offlineButton;
    public GoGameGuiController() throws IOException {
        initComponents();
        initListeners();
    }
    private void initComponents() throws IOException {
        turn= false;
        mainFrame = new GoGameGui();
        startFrame = new StartFrame();
        window = mainFrame.getWindow();
        ui = mainFrame.getUIPanel();
        board = mainFrame.getBoard();
        startFramePanel = startFrame.getStartFramePanel();
        startButton = ui.getStartButton();
        surrenderButton = ui.getSurrenderButton();
        connectButton = startFramePanel.getConnectButton();
        passButton = ui.getPassButton();
        offlineButton = startFramePanel.getOfflineButton();
    }
    public void showMainFrameWindow(){
        mainFrame.setVisible(true);
    }
    private void initListeners() {
        window.initListeners();
        addButtonsListener();
    }


    private class BoardPiecesListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            type=2;
            for(int i=0; i<13; i++){
                for(int j=0; j<13; j++){
                    if(e.getSource()==board.Buttons[i][j]){
                        try {
                            processInformation(i,j);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    private void addButtonsListener(){

        board.addButtons(new BoardPiecesListener());
        startButton.addActionListener(new startButtonListener());
        surrenderButton.addActionListener(new surrenderButtonListener());
        connectButton.addActionListener(new connectButtonListener());
        passButton.addActionListener(new passButtonListener());
        offlineButton.addActionListener(new offlineButtonListener());
    }
    private class startButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            startFrame.setVisible(true);
        }
    }
    private class offlineButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            type=0;
            ableToPlace(true);
            try {

            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private class connectButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            type=1;

            try {

            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private class passButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            ableToPlace(true);
        }
    }
    private class surrenderButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ableToPlace(false);
        }
    }
    private void ableToPlace(final boolean lean){
        for(int i=0; i<13; i++) {
            for (int j = 0; j < 13; j++) {
                board.Buttons[i][j].setEnabled(lean);
            }
        }
    }


    private void closeServer(){
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    private void connectToServer() throws IOException{
        connection = new Socket("localhost", 510);
    }

    private void boardInput()throws IOException{
            try {
                input = new ObjectInputStream(connection.getInputStream());
                received = (List<Piece>) input.readObject();
                board.play(received);
            }catch(ClassNotFoundException classNotFoundException){
            }

    }
    private void boardOutput()throws IOException{
        outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeByte(type);
        outputStream.flush();
        output = new ObjectOutputStream(connection.getOutputStream());
        output.writeObject(twoInt);
        output.flush();
    }
    private void singleMulti()throws IOException{
        connectToServer();
        outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeByte(type);
        outputStream.flush();
        outputStream.close();
    }
    public void processInformation(int x, int y) throws Exception {
        connectToServer();
        twoInt=new TwoInt(x,y);
        boardOutput();
        boardInput();
        ableToPlace(false);
        closeServer();
    }

}
