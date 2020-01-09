package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel;

import com.michalliebner.sebastianmaraszek.team.gui_swing.controller.TwoInt;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PiecesChain implements Cloneable {
    private List<Piece> pieces = new ArrayList();
    private Color color;
    private int breaths;
    public int enemies;
    private int contacts;
    private boolean[][] BreathsPosition = new boolean[13][13];

    public PiecesChain() {
    }


    public void setEnemiesPosition() {
        this.enemies = 0;
        this.BreathsPosition = new boolean[13][13];

        int i;
        int j;
        for(i = 0; i < 13; ++i) {
            for(j = 0; j < 13; ++j) {
                Iterator var3 = this.pieces.iterator();
                while(var3.hasNext()) {
                    Piece piece = (Piece)var3.next();
                    if (piece.getBreathPosition()[i][j]) {
                        this.BreathsPosition[i][j] = true;
                        ++this.enemies;
                    }
                }
            }
        }

        for(i = 0; i < 13; ++i) {
            for(j = 0; j < 13; ++j) {
                if (this.BreathsPosition[i][j]) {

                }
            }
        }

    }

    public void contactsNumber() {
        this.contacts = 0;
        TwoInt last = new TwoInt(50, 50);
        TwoInt last2 = new TwoInt(50, 50);

        for(int i = 0; i < this.pieces.size(); ++i) {
            for(int j = i + 1; j < this.pieces.size(); ++j) {
                if (this.neighbourPieces((Piece)this.pieces.get(i), (Piece)this.pieces.get(j)) && (((Piece)this.pieces.get(i)).getX() != last.getX() || ((Piece)this.pieces.get(i)).getY() != last.getY() || ((Piece)this.pieces.get(j)).getX() != last2.getX() || ((Piece)this.pieces.get(j)).getY() != last2.getY())) {
                    last = new TwoInt(((Piece)this.pieces.get(i)).getX(), ((Piece)this.pieces.get(i)).getY());
                    last2 = new TwoInt(((Piece)this.pieces.get(j)).getX(), ((Piece)this.pieces.get(j)).getY());
                    ++this.contacts;
                }
            }
        }

    }

    public void setBreaths() {
        this.breaths = 0;
        TwoInt last = new TwoInt(50, 50);
        Iterator var2 = this.pieces.iterator();

        while(true) {
            Piece piece;
            do {
                if (!var2.hasNext()) {
                    return;
                }

                piece = (Piece)var2.next();
            } while(piece.getX() == last.getX() && piece.getY() == last.getY());

            last = new TwoInt(piece.getX(), piece.getY());
            piece.fullBreathNumber();
            this.breaths += piece.getBreathNumber();
        }
    }

    public int EnemiesNumber() {
        this.setEnemiesPosition();
        return this.enemies;
    }

    public int ContactsNumber() {
        this.contactsNumber();
        return this.contacts;
    }

    public int BreathsNumber() {
        this.setBreaths();
        this.breaths = this.breaths - this.EnemiesNumber() - 2 * this.ContactsNumber();
        return this.breaths;
    }

    public List<Piece> getChain() {
        return this.pieces;
    }

    public void addPiece(Piece piece) {
        boolean can = true;
        Iterator var3 = this.pieces.iterator();

        while(var3.hasNext()) {
            Piece piece1 = (Piece)var3.next();
            if (piece.getX() == piece1.getX() && piece.getY() == piece1.getY()) {
                can = false;
            }
        }

        if (can) {
            this.pieces.add(piece);
        }

        this.setEnemiesPosition();

        this.color = piece.getColor();
    }

    public void clearChain() {
        this.pieces.clear();
    }

    public Color getColor() {
        return this.color;
    }

    private boolean neighbourPieces(Piece piece1, Piece piece2) {
        return Math.abs(piece1.getX() - piece2.getX()) == 0 && Math.abs(piece1.getY() - piece2.getY()) == 1 || Math.abs(piece1.getX() - piece2.getX()) == 1 && Math.abs(piece1.getY() - piece2.getY()) == 0;
    }
}
