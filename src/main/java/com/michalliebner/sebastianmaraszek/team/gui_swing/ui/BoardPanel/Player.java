package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    private Color color;
    private int prisoners;
    private int territory;
    public PiecesChain tempToDelete = new PiecesChain();
    public PiecesChain tempToAdd = new PiecesChain();
    public List<Piece> PieceList = new ArrayList();
    public List<PiecesChain> ChainList = new ArrayList();
    public List<PiecesChain> ChainsThatWillBeJoined = new ArrayList();
    public boolean isBot;

    public Player() {
    }


    private void ChainJoiner(PiecesChain chain1, PiecesChain chain2) {
        this.tempToDelete = chain2;
        this.tempToAdd = chain1;
    }

    public void setChainList(){
        for(PiecesChain chain : ChainList){
            chain.setBreaths();
            chain.setEnemiesPosition();

        }    }
    private void ChainNeighbours(PiecesChain chain1, PiecesChain chain2) {
        Iterator var3 = chain1.getChain().iterator();

        while(var3.hasNext()) {
            Piece piece1 = (Piece)var3.next();
            Iterator var5 = chain2.getChain().iterator();

            while(var5.hasNext()) {
                Piece piece2 = (Piece)var5.next();
                if (this.neighbourPieces(piece1, piece2)) {
                    this.ChainJoiner(chain1, chain2);
                }
            }
        }

    }

    public void scanForJoins() {
        Iterator var1 = this.ChainList.iterator();

        while(var1.hasNext()) {
            PiecesChain chain = (PiecesChain)var1.next();
            Iterator var3 = this.ChainList.iterator();

            while(var3.hasNext()) {
                PiecesChain chain1 = (PiecesChain)var3.next();
                if (chain != chain1) {
                    this.ChainNeighbours(chain1, chain);
                }
            }
        }

        var1 = this.tempToDelete.getChain().iterator();

        while(var1.hasNext()) {
            Piece piece = (Piece)var1.next();
            this.tempToAdd.addPiece(piece);
        }

        this.ChainList.remove(this.tempToDelete);
    }

    public void CheckAndAddToChain(Piece piece) {
        Iterator var2 = this.PieceList.iterator();

        while(var2.hasNext()) {
            Piece pieceinlist = (Piece)var2.next();
            if (this.neighbourPieces(piece, pieceinlist)) {
                this.IsInChain(pieceinlist).addPiece(piece);
            }
        }

    }

    public PiecesChain IsInChain(Piece piece) {
        for(int i = 0; i < this.ChainList.size(); ++i) {
            for(int j = 0; j < ((PiecesChain)this.ChainList.get(i)).getChain().size(); ++j) {
                if (((Piece)((PiecesChain)this.ChainList.get(i)).getChain().get(j)).getX() == piece.getX() && ((Piece)((PiecesChain)this.ChainList.get(i)).getChain().get(j)).getY() == piece.getY()) {
                    return (PiecesChain)this.ChainList.get(i);
                }
            }
        }

        PiecesChain newChain = new PiecesChain();
        newChain.addPiece(piece);
        this.ChainList.add(newChain);
        return newChain;
    }

    public boolean ChainContainsPiece(Piece piece) {
        for(int i = 0; i < this.ChainList.size(); ++i) {
            for(int j = 0; j < ((PiecesChain)this.ChainList.get(i)).getChain().size(); ++j) {
                if (((Piece)((PiecesChain)this.ChainList.get(i)).getChain().get(j)).getX() == piece.getX() && ((Piece)((PiecesChain)this.ChainList.get(i)).getChain().get(j)).getY() == piece.getY()) {
                    return true;
                }
            }
        }

        return false;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void clearTerritory() {
        this.territory = 0;
    }

    public void addPrisoner() {
        ++this.prisoners;
    }

    public void addTerritory() {
        ++this.territory;
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
    public boolean neighbourPieces(Piece piece1, Piece piece2) {
        return Math.abs(piece1.getX() - piece2.getX()) == 0 && Math.abs(piece1.getY() - piece2.getY()) == 1 || Math.abs(piece1.getX() - piece2.getX()) == 1 && Math.abs(piece1.getY() - piece2.getY()) == 0;
    }
}