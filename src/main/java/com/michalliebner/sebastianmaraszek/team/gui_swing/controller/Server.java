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
    private int type=2;
    public Server(){
        board=new VirtualBoard();
        System.out.println("Server started");

    }
    public void startRunning(){
        try{
            server = new ServerSocket(49152);
            waitForConnection();
            gameMode();
            connection.close();

            while (true){
                try{
                    if(type==0)
                        board.PlayWithBot();
                    if(type==1)
                        board.PlayWithHuman();
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
        input.close();
        output.close();
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
    }
    private void boardReceived() throws IOException, ClassNotFoundException {
        if(type==0||type==1) {
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

}
