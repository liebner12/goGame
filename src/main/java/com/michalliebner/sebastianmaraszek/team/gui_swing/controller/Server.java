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
    private byte type;
    public Server(){
        board=new VirtualBoard();
        System.out.println("Server started");
        board.PlayWithHuman();
    }
    public void startRunning(){
        try{
            server = new ServerSocket(510);
            while (true){
                try{
                    waitForConnection();
                    boardReceived();
                    boardOutput();
                }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally{
                    closeCrap();
                }
            }
        }catch(IOException ioException){
            ioException.printStackTrace();
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
        inputStream = new DataInputStream(connection.getInputStream());
        type = inputStream.readByte();

        switch(type) {
            case 0:

                break;
            case 1:


                break;
            case 2:
                System.out.println(type);
                input = new ObjectInputStream(connection.getInputStream());
                TwoInt received = (TwoInt) input.readObject();
                board.addPiece(received.getX(), received.getY());
                break;

        }
    }

}
