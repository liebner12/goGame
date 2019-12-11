package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] argv) throws Exception {
        ServerSocket s = new ServerSocket(510);
        VirtualBoard board=new VirtualBoard();
        System.out.println("Server started");
        while (true) {
            Socket t = s.accept();// wait for client to connect
            ObjectInputStream b = new ObjectInputStream(t.getInputStream());
            TwoInt received = (TwoInt) b.readObject();
            board.addPiece(received.getX(),received.getY());

            ObjectOutputStream objectOutputStream= new ObjectOutputStream(t.getOutputStream());
            objectOutputStream.writeObject(board.getGameBoard());
            objectOutputStream.flush();

            b.close();
            objectOutputStream.close();
            t.close();
        }

    }


}
