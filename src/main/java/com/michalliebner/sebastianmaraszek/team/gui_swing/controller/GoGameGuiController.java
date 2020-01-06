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
    private DataOutputStream outputStream;
    private int type;
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
            try {
                multiGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ableToPlace(true);
        }
    }
    private class connectButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            type=1;
            try {
                multiGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ableToPlace(true);
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
    private void ableToPlace(final boolean lean) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (type == 1) {
                    board.Buttons[i][j].setEnabled(lean);
                } else if(type==0){
                    board.Buttons[i][j].setEnabled(true);
                }
            }
        }
    }
    private void multiGame() throws IOException{
        connectToServer();
        outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeInt(type);
        outputStream.flush();
        outputStream.close();
        connection.close();
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
        connection = new Socket("localhost", 49152);
    }

    private void boardInput()throws IOException{
        try {
            input = new ObjectInputStream(connection.getInputStream());
            received = (List<Piece>) input.readObject();
            board.play(received);
        }catch(ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }

    }
    private void boardOutput()throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.writeObject(twoInt);
        output.flush();
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
