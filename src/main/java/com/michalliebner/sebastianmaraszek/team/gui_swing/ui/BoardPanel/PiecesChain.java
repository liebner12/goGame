package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;
import static java.lang.Math.abs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class PiecesChain {
    private final List<Piece> pieces= new ArrayList<>();
    private Color color;
    private int breaths,enemies;
    private final boolean[][] BreathsPosition=new boolean[13][13];

    public PiecesChain(){}

    public void setEnemiesPosition(){
        enemies=0;
        for(int i=0; i<13;i++){
            for(int j=0; j<13;j++){
        for(Piece piece: pieces){
            if(piece.getBreathPosition()[i][j]){
                BreathsPosition[i][j]=true;
                enemies++;
            }
        }}}
        for(Piece piece: pieces){
            if (BreathsPosition[piece.getX()][piece.getY()]){
                BreathsPosition[piece.getX()][piece.getY()] = false;
                enemies--;
            }
        }
    }

    public void correctBreathNumber(Piece piece){
        for (Piece piece2 : pieces){
            if(neighbourPieces(piece,piece2)){//inny kolor
                piece2.takeBreath(piece.getX(),piece2.getY());
                piece.takeBreath(piece2.getX(),piece2.getY());}}
    }

    public void setBreaths(){
        breaths=0;
        setEnemiesPosition();
        for(Piece piece : pieces){
            breaths+=piece.getBreathNumber();
        }
    }

    public int EnemiesNumber(){
        setEnemiesPosition();
        return enemies;

    }
    public int BreathsNumber(){
        setBreaths();
        return breaths;
    }
    public List<Piece> getChain(){
        return pieces;
    }

    public void addPiece(Piece piece){
        correctBreathNumber(piece);
        pieces.add(piece);
        color=piece.getColor();

    }
    public void clearChain(){
        pieces.clear();
    }
    public Color getColor(){
        return color;
    }

    private boolean neighbourPieces(Piece piece1, Piece piece2){
        return (abs(piece1.getX() - piece2.getX()) == 0 && abs(piece1.getY() - piece2.getY()) == 1) || (
                abs(piece1.getX() - piece2.getX()) == 1 && abs(piece1.getY() - piece2.getY()) == 0);
    }
        }