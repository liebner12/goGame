package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class PiecesChainTest {
    PiecesChain piecesChain=new PiecesChain();

    @Test
    public void piecesChain() {
    }

    @Test
    public void getChain() {
        Piece piece=new BlackPiece();
        Piece piece2=new WhitePiece();
        piecesChain.addPiece(piece);
        piecesChain.addPiece(piece2);
        assertEquals(piecesChain.getChain().size(),2);
        }

    @Test
    public void modifyBreaths() {
        Piece piece = new BlackPiece();
        Piece piece2 = new BlackPiece();
        piece.setBreath(4);
        piece2.setBreath(4);
        piecesChain.addPiece(piece);
        piecesChain.addPiece(piece2);
        piecesChain.returnPiecesBreaths();
        assertEquals(piecesChain.getBreaths(), piece.getBreath() + piece2.getBreath());
    }

    @Test
    public void getBreaths() {
    }

    @Test
    public void takeBreath() {

        int i=piecesChain.getBreaths();
        piecesChain.takeBreath();
        assertNotEquals(i,piecesChain.getBreaths());
    }
}