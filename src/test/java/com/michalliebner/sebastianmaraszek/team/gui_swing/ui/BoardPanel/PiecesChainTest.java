package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import static org.junit.Assert.*;

import org.junit.Test;

public class PiecesChainTest {
    final PiecesChain piecesChain=new PiecesChain();


    @Test
    public void piecesChain() {
    }

    @Test
    public void eliminateCommonBreath(){
        PiecesChain piecesChain=new PiecesChain();
        Piece piece=new BlackPiece();
        Piece piece2=new BlackPiece();
        piece.setX(5);
        piece.setY(5);
        piece2.setX(6);
        piece2.setY(5);

        piecesChain.addPiece(piece);
        piecesChain.addPiece(piece2);
        piece.addBreath(5,12);
        piece2.addBreath(11,6);

        piecesChain.setBreaths();
        piecesChain.setEnemiesPosition();
        assertEquals(0,   piecesChain.EnemiesNumber());
    }
    @Test
    public void getChain() {
        Piece piece=new BlackPiece();
        Piece piece2=new WhitePiece();
        piecesChain.addPiece(piece);
        piecesChain.addPiece(piece2);
        assertEquals(piecesChain.getChain().size(),1);
        }

    @Test
    public void modifyBreaths() {
        Piece piece = new BlackPiece();
        Piece piece2 = new BlackPiece();
        piece.setBreathNumber(4);
        piece2.setBreathNumber(4);
        piecesChain.addPiece(piece);
        piecesChain.addPiece(piece2);
       // piecesChain.returnPiecesBreaths();
       // assertEquals(piecesChain.getBreaths(), piece.getBreathNumber() + piece2.getBreathNumber());
    }

    @Test
    public void getBreaths() {
    }

    @Test
    public void takeBreath() {

     //   int i=piecesChain.getBreaths();
    //    piecesChain.takeBreath();
     //   assertNotEquals(i,piecesChain.getBreaths());
    }
}