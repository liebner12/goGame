package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private Color color;
    private int prisoners;
    private int territory;
    private boolean turn;
    public List<Piece> PieceList=new ArrayList<Piece>();
    public List<PiecesChain> ChainList;
    public boolean isBot;

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
    public void setTerritory(int x){
        territory=x;
    }
    public int getPrisoners(){
        return prisoners;
    }
    public int getTerritory(){
        return territory;
    }

}
