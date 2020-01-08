package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Board;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.DecisionFrame.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.ConnectButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.OfflineButton;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.StartFrame;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.*;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame.StartFramePanel;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.Results.ResultScore;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel.Window;


import javax.swing.*;
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
    private DataInputStream inputStream;
    private int type = 2;
    private int changeTurn = 0;
    private int inRow = 0;
    private int turn = 1;
    private GoGameGui mainFrame;
    private UI ui;
    private Window window;
    private Board board;
    private DecisionFrame decisionFrame;
    private StartFrame startFrame;
    private StartButton startButton;
    private SurrenderButton surrenderButton;
    private ConnectButton connectButton;
    private PassButton passButton;
    private TwoInt twoInt;
    private OfflineButton offlineButton;
    private ResultScore whiteResultTerritory;
    private ResultScore blackResultTerritory;
    private ResultScore whiteResultPrisoners;
    private ResultScore blackResultPrisoners;
    private ResultScore whiteResultScore;
    private ResultScore blackResultScore;
    private JFormattedTextField whiteScoresField;
    private JFormattedTextField blackScoresField;
    private BlackButton blackButton;
    private WhiteButton whiteButton;
    private EndGameButton endGameButton;
    private int black;
    private int white;
    private String blackString;
    private String whiteString;
    public GoGameGuiController() throws IOException {
        initComponents();
        initListeners();
    }

    private void initComponents() throws IOException {
        mainFrame = new GoGameGui();
        startFrame = new StartFrame();
        decisionFrame = new DecisionFrame();
        window = mainFrame.getWindow();
        ui = mainFrame.getUIPanel();
        board = mainFrame.getBoard();
        DecisionFramePanel decisionFramePanel = decisionFrame.getDecisionFramePanel();
        StartFramePanel startFramePanel = startFrame.getStartFramePanel();
        startButton = ui.getStartButton();
        surrenderButton = ui.getSurrenderButton();
        connectButton = startFramePanel.getConnectButton();
        passButton = ui.getPassButton();
        offlineButton = startFramePanel.getOfflineButton();
        whiteResultTerritory = mainFrame.getResults().getWhiteResultTerritory();
        blackResultTerritory = mainFrame.getResults().getBlackResultTerritory();
        whiteResultPrisoners = mainFrame.getResults().getWhiteResultPrisoners();
        blackResultPrisoners = mainFrame.getResults().getBlackResultPrisoners();
        whiteResultScore = mainFrame.getResults().getWhiteResultScore();
        blackResultScore = mainFrame.getResults().getBlackResultScore();
        whiteScoresField = decisionFramePanel.getWhiteScoreField();
        blackScoresField = decisionFramePanel.getBlackScoreField();
        blackButton = decisionFramePanel.getBlackButton();
        whiteButton = decisionFramePanel.getWhiteButton();
        endGameButton = decisionFramePanel.getEndGameButton();
    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }

    private void initListeners() {
        window.initListeners();
        addButtonsListener();
    }

    private class BoardPiecesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 13; j++) {
                    if (e.getSource() == board.Buttons[i][j]) {
                        try {
                            if(inRow<2) {
                                inRow=0;
                                processInformation(i, j);

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void addButtonsListener() {
        board.addButtons(new BoardPiecesListener());
        startButton.addActionListener(new startButtonListener());
        surrenderButton.addActionListener(new surrenderButtonListener());
        connectButton.addActionListener(new connectButtonListener());
        passButton.addActionListener(new passButtonListener());
        offlineButton.addActionListener(new offlineButtonListener());
        endGameButton.addActionListener(new endGameButtonListener());
        whiteButton.addActionListener(new whiteButtonListener());
        blackButton.addActionListener(new blackButtonListener());
    }

    private class startButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            startFrame.setVisible(true);
        }
    }

    private class offlineButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            type = 0;
            try {
                multiGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ableToPlace(true);
            connectButton.setEnabled(false);
            startButton.setEnabled(false);
            startFrame.setVisible(false);
        }
    }

    private class connectButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            type = 1;
            try {
                multiGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ableToPlace(true);
            startButton.setEnabled(false);
            startFrame.setVisible(false);
        }
    }

    private class passButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (type == 1 || type == 0) {
                changeTurn=1;
                inRow++;
                if(turn==0)
                    turn = 1;
                else if(turn==1)
                    turn = 0;
            }
            if(inRow>=2){
                decisionFrame.setVisible(true);
            }
        }
    }

    private class surrenderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(turn==1){
                System.out.println("White wins");
            }
            if(turn==0){
                System.out.println("Black wins");
            }
            ableToPlace(false);
            passButton.setEnabled(false);
            surrenderButton.setEnabled(false);
            startButton.setEnabled(false);
        }
    }

    private class endGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            whiteString = whiteScoresField.getText();
            blackString = blackScoresField.getText();
            inRow=0;
            decisionFrame.setVisible(false);
            ableToPlace(false);
            passButton.setEnabled(false);
            surrenderButton.setEnabled(false);
            blackResultScore.setText(blackString);
            whiteResultScore.setText(whiteString);
        }
    }
    private class blackButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            turn=1;
            inRow=0;
            decisionFrame.setVisible(false);
        }
    }
    private class whiteButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            turn=0;
            inRow=0;
            decisionFrame.setVisible(false);
        }
    }
    private void ableToPlace(final boolean lean) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (type == 1) {
                    board.Buttons[i][j].setEnabled(lean);
                } else if (type == 0) {
                    board.Buttons[i][j].setEnabled(true);
                }
            }
        }
    }

    private void multiGame() throws IOException {
        connectToServer();
        outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeInt(type);
        outputStream.flush();
        outputStream.close();
        connection.close();
    }


    private void closeServer() {
        try {
            outputStream.close();
            output.close();
            input.close();
            inputStream.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {
        connection = new Socket("localhost", 2137);
    }

    private void boardInput() throws IOException {
        try {
            input = new ObjectInputStream(connection.getInputStream());
            List<Piece> received = (List<Piece>) input.readObject();
            board.play(received);
            inputStream = new DataInputStream(connection.getInputStream());
            int whiteTerritory = inputStream.readInt();
            int blackTerritory = inputStream.readInt();
            whiteResultTerritory.setText(Integer.toString(whiteTerritory));
            blackResultTerritory.setText(Integer.toString(blackTerritory));
            int whitePrisoners = inputStream.readInt();
            int blackPrisoners = inputStream.readInt();
            whiteResultPrisoners.setText(Integer.toString(whitePrisoners));
            blackResultPrisoners.setText(Integer.toString(blackPrisoners));
            white = whiteTerritory + whitePrisoners;
            black = blackTerritory + blackPrisoners;
            whiteResultScore.setText(Integer.toString(white));
            blackResultScore.setText(Integer.toString(black));
            turn = inputStream.readInt();
            System.out.println(turn);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    private void boardOutput() throws IOException {
        outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeInt(changeTurn);
        outputStream.flush();
        outputStream.writeInt(turn);
        outputStream.flush();
        output = new ObjectOutputStream(connection.getOutputStream());
        output.writeObject(twoInt);
        output.flush();
        if(changeTurn==1){
            changeTurn=0;
        }
    }

    public void processInformation(int x, int y) throws Exception {
        connectToServer();
        twoInt = new TwoInt(x, y);
        boardOutput();
        boardInput();
        closeServer();
    }


}
