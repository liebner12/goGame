package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import static java.lang.Math.abs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private Color color;
    private int prisoners;
    private int territory;
    private boolean turn;

    public PiecesChain tempToDelete=new PiecesChain();
    public PiecesChain tempToAdd=new PiecesChain();
    public final List<Piece> PieceList= new ArrayList<>();
    public final List<PiecesChain> ChainList= new ArrayList<>();
    public boolean isBot;


    private void ChainJoiner(PiecesChain chain1, PiecesChain chain2){
        tempToDelete=chain2;
        tempToAdd=chain1;
        }

    private void ChainNeighbours(PiecesChain chain1, PiecesChain chain2){
        for(Piece piece1 : chain1.getChain()){
            for(Piece piece2  : chain2.getChain()){
                if(neighbourPieces(piece1,piece2))
                  ChainJoiner(chain1,chain2);
            }
        }
    }
    public void scanForJoins(){
        for(PiecesChain chain : ChainList){
            for(PiecesChain chain1 : ChainList){
                if(chain==chain1)
                    continue;
                ChainNeighbours(chain1,chain);
            }
        }
    }


    public void CheckAndAddToChain(Piece piece){
        for(Piece pieceinlist : PieceList){
            if(neighbourPieces(piece,pieceinlist)){
                IsInChain(pieceinlist).addPiece(piece);
            }

    }
        tempToAdd.getChain().addAll(tempToDelete.getChain());
        ChainList.remove(tempToDelete);
    //    System.out.println(color+" "+ChainList.size());
    }

    public PiecesChain IsInChain(Piece piece){
        for(PiecesChain chain : ChainList){
            if(chain.getChain().contains(piece)){
                return chain;
            }
        }
        PiecesChain newChain = new PiecesChain();
        newChain.addPiece(piece);
        ChainList.add(newChain);
        return newChain;
    }

    public boolean ChainContainsPiece(Piece piece){
        for(PiecesChain chain : ChainList){
            if(chain.getChain().contains(piece)){
                return true;

            }        }
    return false;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color=color;
    }
    public void clearTerritory(){
        territory=0;
    }
    public void addPrisoner(){
        prisoners++;
    }
    public void addTerritory(){
        territory++;
    }
    public int setTerritory(int x){
        return territory=x;
    }
    public int setPrisoners(int x){
        return prisoners=x;
    }
    public int getPrisoners(){
        return prisoners;
    }
    public int getTerritory(){
        return territory;
    }
    public boolean neighbourPieces(Piece piece1, Piece piece2){
        return (abs(piece1.getX() - piece2.getX()) == 0 && abs(piece1.getY() - piece2.getY()) == 1) || (
                abs(piece1.getX() - piece2.getX()) == 1 && abs(piece1.getY() - piece2.getY()) == 0);
    }

}
