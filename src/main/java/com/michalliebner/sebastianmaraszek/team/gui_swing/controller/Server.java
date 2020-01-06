package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private ServerSocket server;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private VirtualBoard board;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private int type=2;
    private int changeTurn=0;
    public Server(){
        board=new VirtualBoard();
        System.out.println("Server started");
    }
    public void startRunning(){
        try{
            server = new ServerSocket(2137);
            waitForConnection();
            gameMode();
            connection.close();

            while (true){
                try{
                    if(type==0)
                        board.PlayWithBot();
                    if(type==1)
                        board.PlayWithHuman();
                    if(changeTurn==1)
                        passTurn();
                    waitForConnection();
                    boardReceived();
                    boardOutput();
                }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally{
                    closeCrap();
                }
            }
        }catch(Exception Exception){
            Exception.printStackTrace();
        }
    }

    private void closeCrap() throws IOException {
        inputStream.close();
        input.close();
        output.close();
        outputStream.close();
        connection.close();
    }

    private void waitForConnection()throws IOException{
        connection = server.accept();
        System.out.println("connected");

    }

    private void boardOutput()throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.writeObject(board.getGameBoard());
        output.flush();
        outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeInt(board.WhiteTerritory());
        outputStream.flush();
        outputStream.writeInt(board.BlackTerritory());
        outputStream.flush();
        outputStream.writeInt(board.WhitePrisoners());
        outputStream.flush();
        outputStream.writeInt(board.BlackPrisoners());
        outputStream.flush();
    }
    private void boardReceived() throws IOException, ClassNotFoundException {
        if(type==0||type==1) {
            inputStream = new DataInputStream(connection.getInputStream());
            changeTurn = inputStream.readInt();
            System.out.println(changeTurn);
            passTurn();
            input = new ObjectInputStream(connection.getInputStream());
            TwoInt received = (TwoInt) input.readObject();
            board.addPiece(received.getX(), received.getY());

        }
    }

    private void gameMode() throws IOException{
        if(type==0)
            board.PlayWithBot();
        if(type==1)
            board.PlayWithHuman();
        if(type==2){
            inputStream = new DataInputStream(connection.getInputStream());
            type = inputStream.readInt();
            System.out.println(type);
            inputStream.close();
        }

    }
    private void passTurn(){
        if(changeTurn==1) {
            board.changeTurn();
        }
    }


}
