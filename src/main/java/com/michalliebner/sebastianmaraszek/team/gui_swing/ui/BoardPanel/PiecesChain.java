package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;
import static java.lang.Math.abs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PiecesChain {
    private List<Piece> pieces=new ArrayList<Piece>();
    private Color color;
    public int koleszka;
    public int wspulnywruk;
    private int breaths;
    private boolean[][] BreathsPosition=new boolean[13][13];

    public PiecesChain(){

    }

    public void setBreathsPosition(){
        for(int i=0; i<13;i++){
            for(int j=0; j<13;j++){
        for(Piece piece: pieces){
            if(piece.getBreathPosition()[i][j]){
                BreathsPosition[i][j]=true;
            }
        }}}
        wspulnywruk=0;
        for(Piece piece: pieces) {
            if (BreathsPosition[piece.getX()][piece.getY()]){
                BreathsPosition[piece.getX()][piece.getY()] = false;
                wspulnywruk++;
            }
        }
    }

    public void neighbourhood(){
        koleszka=0;
        for(Piece piece1: pieces) {
            for(Piece piece2: pieces) {
                    if(neighbourPieces(piece1,piece2)){
                        koleszka++;
                    }
            }}
            koleszka=(int)koleszka/2;
        }

        public int countBreath(){
        setBreathsPosition();
        int counter=0;
        for(int i=0; i<13; i++){
            for(int j=0; j<13 ; j++){
                if(BreathsPosition[i][j]){
                    counter++;
                }
            }
        }

    return counter;}



    public int BreathNumber(){
        setBreathsPosition();
        BreathCounter();
        neighbourhood();
        return breaths;

    }    public List<Piece> getChain(){
        return pieces;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
        BreathCounter();

    }

    public void BreathCounter(){
        breaths=0;
        for(Piece piece: pieces){
            breaths+=piece.getBreathNumber();
        }
    }
            private boolean neighbourPieces(Piece piece1, Piece piece2){
                if((abs(piece1.getX()-piece2.getX())==0 && abs(piece1.getY()-piece2.getY())== 1) || (
                    abs(piece1.getX()-piece2.getX())==1 && abs(piece1.getY()-piece2.getY())== 0)){
                    return true;
                }
                return false;
            }
        }