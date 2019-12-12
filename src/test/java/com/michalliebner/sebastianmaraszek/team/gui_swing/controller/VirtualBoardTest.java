package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import static org.junit.Assert.*;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.BlackPiece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.sun.corba.se.impl.interceptors.PICurrent;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class VirtualBoardTest {
    @Test
    public void addPiece() {
        VirtualBoard board=new VirtualBoard();
        board.addPiece(1,2);
        assertEquals(board.getGameBoard().size(),1);
    }

    @Test
    public void singlePlayer() {
        VirtualBoard board=new VirtualBoard();
        board.PlayWithBot();
        board.SinglePlayer(2,5);
        assertEquals(board.getGameBoard().size(),1);
    }

    @Test
    public void getGameBoard() {
        VirtualBoard board=new VirtualBoard();
        List<Piece> Pieces=new ArrayList<>();
        Piece piece=new BlackPiece();
        piece.setY(2);
        piece.setX(1);
        board.PlayWithHuman();
        board.addPiece(1,2);
        Pieces.add(piece);
        assertEquals(Pieces.size(),board.getGameBoard().size());
    }

    @Test
    public void killCheck(){
        VirtualBoard board=new VirtualBoard();
        board.PlayWithHuman();
        board.addPiece(0,0);
        board.getGameBoard();
        board.addPiece(1,0);
        board.getGameBoard();
        board.addPiece(2,2);
        board.getGameBoard();
        board.addPiece(0,1);
        board.getGameBoard();
        board.killCheck();
        assertEquals(board.getGameBoard().size(),3);
    }

    @Test
    public void neighbourPieces() {
    }
}