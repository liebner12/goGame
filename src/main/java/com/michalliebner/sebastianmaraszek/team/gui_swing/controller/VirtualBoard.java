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
    List<Piece> backup;
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
                 PieceList.add(black);
                breathCheck(black);}
             else{
                 Piece white=new WhitePiece();
                 white.setX(x);
                 white.setY(y);
                 PieceList.add(white);
                breathCheck(white);
             }
        }
    }

    public List<Piece> getGameBoard(){
        return PieceList;
    }

    private boolean checkFree(int x, int y){
        for(Piece piece : PieceList){
            if(piece.getX()==x && piece.getY()==y){
                return false;
            }}
        return true;
    }


    private void killCheck() {
        List<Piece> toRemove = new ArrayList<Piece>();
        for (Piece piece : PieceList) {
            if (piece.getBreath() == 0) {
                toRemove.add(piece);
            }
        }
        backup.removeAll(toRemove);
        PieceList.removeAll(toRemove);
    }
    private void breathCheck(Piece piece){
        backup=PieceList;
        for(Piece piece1 : PieceList){
                if(neighbourPieces(piece1,piece) && piece1.getColor()!=piece.getColor()){
                    piece1.takeBreath();
                    }}
        killCheck();
        PieceList=backup;
        }





    private void changeTurn(){
        if(this.turn){
            turn=false;
        }
        else{turn=true;}
    }
    private boolean neighbourPieces(Piece piece1, Piece piece2){
        if(Math.abs(piece1.getX()-piece2.getX())==1 ^ Math.abs(piece1.getY()-piece2.getY())== 1){
            return true;
        }
        return false;
    }}