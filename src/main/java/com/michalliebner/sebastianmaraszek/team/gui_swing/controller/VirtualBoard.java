package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.BlackPiece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.WhitePiece;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;

public class VirtualBoard{
    Boolean turn=false;
    List<Piece> PieceList;




        public VirtualBoard() {

            PieceList=new ArrayList<Piece>();
        }



    public void addPiece(int x, int y){
            if(checkFree(x,y)){
                changeTurn();
            if(!turn){
                 Piece black=new BlackPiece();
                 black.setX(x);
                 black.setY(y);
                 if(black.isInCorner())
                     black.setBreath(2);
                 else if(black.isOnBorder())
                     black.setBreath(3);
                 else
                     black.setBreath(4);
                 PieceList.add(black);
                 breathCheck(black);
                }
             else{
                 Piece white=new WhitePiece();
                 white.setX(x);
                 white.setY(y);
                if(white.isInCorner())
                    white.setBreath(2);
                else if(white.isOnBorder())
                    white.setBreath(3);
                else
                    white.setBreath(4);
                    PieceList.add(white);
                    breathCheck(white);
             }
        }
    }

    public List<Piece> getGameBoard(){
        killCheck();
        return PieceList;
    }

    private boolean checkFree(int x, int y){
        Piece piece2;
        int counter=0;
        if(!turn){
            piece2=new WhitePiece();
            piece2.setX(x);
            piece2.setY(y);
            if(piece2.isInCorner())
                piece2.setBreath(2);
            else if(piece2.isOnBorder())
                piece2.setBreath(3);
            else
                piece2.setBreath(4);
        }
        else {
            piece2=new BlackPiece();
            piece2.setX(x);
            piece2.setY(y);
        }
        for(Piece piece : PieceList){
            if(piece.getX()==x && piece.getY()==y){
                return false;}
                        if (neighbourPieces(piece, piece2)) {
                            if (piece.getColor() != piece2.getColor()) {
                               counter++;
                            }
                        }
                    }
        if(counter==piece2.getBreath()){
            return false;
        }
        return true;
    }


    public void killCheck() {
        List<Piece> toRemove = new ArrayList<Piece>();
        for (Piece piece : PieceList) {
            if (piece.getBreath() == 0) {
                toRemove.add(piece);
            }
        }
        for(Piece piece : PieceList) {
            for (Piece piece1 : toRemove) {
                if (neighbourPieces(piece, piece1)) {
                    if (piece.getColor() != piece1.getColor()) {
                        piece.setBreath(piece.getBreath()+1);
                    }

                }}}
                PieceList.removeAll(toRemove);

        }
    private void breathCheck(Piece piece1){
            for (Piece piece2 : PieceList){
                if(neighbourPieces(piece1,piece2)){
                if(piece1.getColor()!=piece2.getColor()){
                    piece1.takeBreath();
                    piece2.takeBreath();
                }
                }
            }

        }





    private void changeTurn(){
        if(this.turn){
            turn=false;
        }
        else{turn=true;}
    }



    private boolean neighbourPieces(Piece piece1, Piece piece2){
        if((Math.abs(piece1.getX()-piece2.getX())==0 && Math.abs(piece1.getY()-piece2.getY())== 1) || (Math.abs(piece1.getX()-piece2.getX())==1 && Math.abs(piece1.getY()-piece2.getY())== 0)){
            return true;
        }
        return false;
    }}