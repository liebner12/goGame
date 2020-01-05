package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

public class ServerTest {
    @Test
    public void main(){
        VirtualBoard board=new VirtualBoard();
        board.PlayWithHuman();
        board.addPiece(0,0);
        board.addPiece(5,12);
        board.addPiece(4,2);
        board.PlayWithBot();
        assertEquals(board.PieceList.size(),3);


        String[] args = null;


    }
}