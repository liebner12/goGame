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
import java.util.List;

public class VirtualBoard{
    Boolean turn=false;
    List<Piece> PieceList=new ArrayList<>();
    boolean takenAll[][];
    boolean takenWhite[][];
    boolean takenBlack[][];
    int x;
    int y;



    public VirtualBoard() {
        takenAll = new boolean[13][13];
        takenWhite = new boolean[13][13];
        takenBlack = new boolean[13][13];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                takenAll[i][j] = false;
            }
        }
    }


public void deletePiece(int x, int y){}

public void addPiece(int x, int y){
        if(!turn){
            if(!takenAll[x][y]){
                changeTurn();
            takenAll[x][y]=true;
            takenBlack[x][y]=true;

        }
        }
    else{
        if(!takenAll[x][y]){
            changeTurn();
            takenAll[x][y]=true;
            takenWhite[x][y]=true;

        }}

    }
public List<Piece> getGameBoard(){
        PieceList=new ArrayList<Piece>();
    for (int i = 0; i < 13; i++) {
        for (int j = 0; j < 13; j++) {
            if(takenAll[i][j]){
                if(takenBlack[i][j]){
                    BlackPiece piece=new BlackPiece();
                    piece.setX(i);
                    piece.setY(j);
                    PieceList.add(piece);
                    if(kill(piece)){
                        PieceList.remove(piece);
                    }
                    System.out.println(i+" "+j);
                }
                else {
                    WhitePiece piece=new WhitePiece();
                    piece.setX(i);
                    piece.setY(j);
                    PieceList.add(piece);
                    if(kill(piece)){
                        PieceList.remove(piece);
                    }
                    System.out.println(i+" "+j);
                }
            }
        }}
    return PieceList;}


private boolean isInConrer(int i, int j){
          if((i==0 && j==0) || (i==12 & j==0) || (i==12 & j==12) || (i==0 & j==12)){
                return true;
            }
            else return false;
}
private boolean isOnBorder(int i,int j){
        if(!isInConrer(i,j)) {
            if (i == 0 || j == 0 || i == 12 || j == 12) {

                return true;
            }}
                return false;
        }
    private boolean isCentre(int i,int j){
        if(!isOnBorder(i,j) && !isInConrer(i,j)){
            return true;
        }
        return false;
    }

private boolean kill(Piece piece) {
    if (isInConrer(piece.getX(), piece.getY())) {
        if (piece.getX() == 0 && piece.getY() == 0) {
            if (piece.getColor() == Color.BLACK) {
                if (takenWhite[1][0] && takenWhite[0][1]) {
                    takenAll[0][0] = false;
                    takenBlack[0][0] = false;
                    return true;
                }
            } else if (piece.getColor() == Color.WHITE) {
                if (takenBlack[1][0] && takenBlack[0][1]) {
                    takenAll[0][0] = false;
                    takenWhite[0][0] = false;
                    return true;
                }
            }

        }
        if (piece.getX() == 12 && piece.getY() == 0) {
            if (piece.getColor() == Color.BLACK) {
                if (takenWhite[12][1] && takenWhite[11][0]) {
                    takenAll[12][0] = false;
                    takenBlack[12][0] = false;
                    return true;
                }
            } else if (piece.getColor() == Color.WHITE) {
                if (takenBlack[12][1] && takenBlack[11][0]) {
                    takenAll[12][0] = false;
                    takenWhite[12][0] = false;
                    return true;
                }
            }
        }
        if (piece.getX() == 12 && piece.getY() == 12) {
            if (piece.getColor() == Color.BLACK) {
                if (takenWhite[12][11] && takenWhite[11][12]) {
                    takenAll[12][12] = false;
                    takenBlack[12][12] = false;
                    return true;
                }
            } else if (piece.getColor() == Color.WHITE) {
                if (takenBlack[12][11] && takenBlack[11][12]) {
                    takenAll[12][12] = false;
                    takenWhite[12][12] = false;
                    return true;
                }
            }
        }

        if (piece.getX() == 0 && piece.getY() == 12) {
            if (piece.getColor() == Color.BLACK) {
                if (takenWhite[0][11] && takenWhite[1][12]) {
                    takenAll[0][12] = false;
                    takenBlack[0][12] = false;
                    return true;
                }
            } else if (piece.getColor() == Color.WHITE) {
                if (takenBlack[0][11] && takenBlack[1][12]) {
                    takenAll[0][12] = false;
                    takenWhite[0][12] = false;
                    return true;
                }
            }
        }
    }
    else if (isCentre(piece.getX(), piece.getY())) {
        if (piece.getColor() == Color.white) {
            if (takenBlack[piece.getX()][piece.getY() + 1] && takenBlack[
                piece.getX() + 1][piece.getY()] && takenBlack[piece.getX()
                - 1][piece.getY()] && takenBlack[piece.getX()][
                piece.getY() - 1]) {
                takenWhite[piece.getX()][piece.getY()] = false;
                takenAll[piece.getX()][piece.getY()] = false;
                return true;
            }
        } else if (piece.getColor() == Color.BLACK) {
            if (takenWhite[piece.getX()][piece.getY() + 1]
                && takenWhite[piece.getX() + 1][piece.getY()]
                && takenWhite[piece.getX()][piece.getY() - 1]
                && takenWhite[piece.getX() - 1][piece.getY()]) {
                takenBlack[piece.getX()][piece.getY()] = false;
                takenAll[piece.getX()][piece.getY()] = false;
                return true;
            }

        }    }
    else if(isOnBorder(piece.getX(),piece.getY())){
        if(piece.getX()==0){
            if (piece.getColor() == Color.BLACK) {
                if(takenWhite[piece.getX()+1][piece.getY()]
                    && takenWhite[piece.getX()][piece.getY()+1]
                    && takenWhite[piece.getX()][piece.getY()-1]){
                    takenAll[0][piece.getY()] = false;
                    takenBlack[0][piece.getY()] = false;
                    return true;
                }
            }
            if (piece.getColor() == Color.WHITE) {
                if(takenBlack[piece.getX()+1][piece.getY()]
            && takenBlack[piece.getX()][piece.getY()+1]
            && takenBlack[piece.getX()][piece.getY()-1]){
            takenAll[0][piece.getY()] = false;
            takenWhite[0][piece.getY()] = false;
            return true;
                }}}
        if(piece.getY()==0){
            if (piece.getColor() == Color.BLACK) {
                if(takenWhite[piece.getX()][piece.getY()+1]
                    && takenWhite[piece.getX()+1][piece.getY()]
                    && takenWhite[piece.getX()-1][piece.getY()]){
                    takenAll[piece.getX()][0] = false;
                    takenBlack[piece.getX()][0] = false;
                    return true;
                }
            }
            if (piece.getColor() == Color.WHITE) {
                if(takenBlack[piece.getX()][piece.getY()+1]
                    && takenBlack[piece.getX()+1][piece.getY()]
                    && takenBlack[piece.getX()-1][piece.getY()]){
                    takenAll[piece.getX()][0] = false;
                    takenWhite[piece.getX()][0] = false;
                    return true;
        }}}
        if(piece.getX()==12){
            if (piece.getColor() == Color.BLACK) {
                if(takenWhite[piece.getX()-1][piece.getY()]
                    && takenWhite[piece.getX()+1][piece.getY()]
                    && takenWhite[piece.getX()][piece.getY()+1]){
                    takenAll[12][piece.getY()] = false;
                    takenBlack[12][piece.getY()] = false;
                    return true;
                }
            }
            if (piece.getColor() == Color.WHITE) {
                if(takenBlack[piece.getX()-1][piece.getY()]
                    && takenBlack[piece.getX()+1][piece.getY()]
                    && takenBlack[piece.getX()][piece.getY()+1]){
                    takenAll[12][piece.getY()] = false;
                    takenWhite[12][piece.getY()] = false;
                    return true;
        }}}
        if(piece.getY()==12) {
            if (piece.getColor() == Color.BLACK) {
                if (takenWhite[piece.getX() - 1][piece.getY()]
                    && takenWhite[piece.getX() + 1][piece.getY()]
                    && takenWhite[piece.getX()][piece.getY() -1]) {
                    takenAll[piece.getX()][piece.getY()] = false;
                    takenBlack[piece.getX()][piece.getY()] = false;
                    return true;
                }
            }
            if (piece.getColor() == Color.WHITE) {
                if (takenBlack[piece.getX() - 1][piece.getY()]
                    && takenBlack[piece.getX() + 1][piece.getY()]
                    && takenBlack[piece.getX()][piece.getY() - 1]) {
                    takenAll[piece.getX()][piece.getY()] = false;
                    takenWhite[piece.getY()][piece.getY()] = false;
                    return true;
                }
            }
        }}
    return false;}

private void changeTurn(){
        if(this.turn){
            turn=false;
        }
        else{turn=true;}
}
}
