package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;
import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.BlackPiece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.PiecesChain;
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
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javafx.util.Pair;

public class VirtualBoard{
    Boolean turn=false;
    Boolean bot=true;
    List<Piece> PieceList;
    List<PiecesChain> ChainList;




        public VirtualBoard() {
            ChainList=new ArrayList<PiecesChain>();
            PieceList=new ArrayList<Piece>();
        }


    public void addPiece(int x, int y){
            if(!bot)
            Multiplayer(x,y);
            else{
            try {
               SinglePlayer(x,y);
            }
            catch (Exception e){}
        }}

    private void Multiplayer(int x, int y){
        if(checkFree(x,y)){
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
                    breathCheck(white);}
             }
        }

 public void SinglePlayer(int x, int y){
         Random random=new Random();
         if(turn){
             if (checkFree(x, y)){  changeTurn();
         Piece black = new BlackPiece();
         black.setX(x);
         black.setY(y);
         if (black.isInCorner())
             black.setBreath(2);
         else if (black.isOnBorder())
             black.setBreath(3);
         else
             black.setBreath(4);
             PieceList.add(black);
             breathCheck(black);}}
        else{
            changeTurn();
            List<Piece> availble=new ArrayList<>();
            List<Piece> firstchoice=new ArrayList<>();
            for(int i=0; i<13 ; i ++){
                for (int j=0; j<13; j++){
                    if(checkFree(i,j)){
                        Piece piece=new WhitePiece();
                        piece.setY(j);
                        piece.setX(i);
                        availble.add(piece);
                        }}}
            for(Piece piece: PieceList){
                for(Piece piece1: availble){
                    if(neighbourPieces(piece1,piece) && piece1.getColor()==piece.getColor()){
                            firstchoice.add(piece1);
                        }
                    } }
            Piece white = new WhitePiece();
            int r=abs(random.nextInt())%2;
             System.out.println("ava"+availble.size());
             System.out.println("cho"+firstchoice.size());
            if(availble.size()>0){
                int choice=abs(random.nextInt()%availble.size());
                System.out.println("moj wybor"+ choice);
                white=availble.get(choice);}
            if(r==1){
            if(firstchoice.size()>0){
                int choice= abs(random.nextInt()%firstchoice.size());
                System.out.println("moj wybor"+ choice);
                white=firstchoice.get(choice);}}

            if (white.isInCorner())
                white.setBreath(2);
            else if (white.isOnBorder())
                  white.setBreath(3);
            else
                white.setBreath(4);
                PieceList.add(white);
                breathCheck(white);}}

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
                if(ChainList.size()>0){
                for(PiecesChain chain : ChainList){
                    if(!chain.getChain().contains(piece)){
                          toRemove.add(piece);}}}
                else{
                    toRemove.add(piece);
                    }
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
                    piece2.takeBreath();
                    piece1.takeBreath();
                }
                else{
                    List<PiecesChain> chains=new ArrayList<>();
                    if(ChainList.size()>0){
                    for(PiecesChain chain : ChainList){
                       if(chain.getChain().contains(piece2)){
                           chain.addPiece(piece1);
                        }
                       else{
                           PiecesChain piecesChain= new PiecesChain();
                           piecesChain.addPiece(piece1);
                           piecesChain.addPiece(piece2);
                           chains.add(piecesChain);
                       }
                    }}
                       else{
                           PiecesChain piecesChain= new PiecesChain();
                           piecesChain.addPiece(piece1);
                           piecesChain.addPiece(piece2);
                           System.out.println(piecesChain.getBreaths());
                           ChainList.add(piecesChain);
                       }

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



    public boolean neighbourPieces(Piece piece1, Piece piece2){
        if((abs(piece1.getX()-piece2.getX())==0 && abs(piece1.getY()-piece2.getY())== 1) || (
            abs(piece1.getX()-piece2.getX())==1 && abs(piece1.getY()-piece2.getY())== 0)){
            return true;
        }
        return false;
    }}