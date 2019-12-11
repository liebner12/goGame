package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PiecesChain {
    private List<Piece> pieces=new ArrayList<Piece>();
    private Color color;
    private int breaths;

    public void PiecesChain(){
    }
    public List<Piece> getChain(){
        return pieces;
    }
    public void addPiece(Piece piece){
        int j=breaths;
        pieces.add(piece);
        modifyBreaths(piece);
        returnPiecesBreaths();
        System.out.println(this.getBreaths());
        }

    public void modifyBreaths(Piece piece){

        for(Piece piece2 : pieces){
         if((Math.abs(piece.getX()-piece2.getX())==0 && Math.abs(piece.getY()-piece2.getY())== 1) || (Math.abs(piece.getX()-piece2.getX())==1 && Math.abs(piece.getY()-piece2.getY())== 0)){
           takeBreath();
           takeBreath();
        }
        }

    }

    public void returnPiecesBreaths(){
        this.breaths=0;
        for(Piece piece : pieces){
            this.breaths+=piece.getBreath();
        }
    }
    public int getBreaths(){
        return breaths;
    }
    public void  takeBreath(){
        breaths=breaths-1;
    }
}
