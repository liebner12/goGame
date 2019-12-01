package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.BlackPiece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.WhitePiece;

import java.util.ArrayList;
import java.util.List;

public class VirtualBoard {
    Boolean turn = false;
    List<Piece> PieceList = new ArrayList<>();
    boolean takenAll[][];
    boolean takenWhite[][];
    boolean takenBlack[][];

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


    public void deletePiece(int x, int y) {
    }

    public void addPiece(int x, int y) {
        if (!turn) {
            if (!takenAll[x][y]) {
                changeTurn();
                takenAll[x][y] = true;
                takenBlack[x][y] = true;

            }
        } else {
            if (!takenAll[x][y]) {
                changeTurn();
                takenAll[x][y] = true;
                takenWhite[x][y] = true;

            }
        }

    }

    public List<Piece> getGameBoard() {
        isKilledWhiteOne();
        PieceList = new ArrayList<Piece>();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (takenAll[i][j]) {
                    if (takenBlack[i][j]) {
                        BlackPiece piece = new BlackPiece();
                        piece.setX(i);
                        piece.setY(j);
                        PieceList.add(piece);
                        System.out.println(i + " " + j);
                    } else {
                        WhitePiece piece = new WhitePiece();
                        piece.setX(i);
                        piece.setY(j);
                        PieceList.add(piece);
                        System.out.println(i + " " + j);
                    }
                }
            }
        }
        return PieceList;
    }

    private void isKilledWhiteOne() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (takenAll[i][j]) {
                    if (isInConrer(i, j)) {
                        if (takenWhite[i][j]) {
                            System.out.println(i + " " + j + "czemuzyje");
                            takenWhite[i][j] = false;
                            takenAll[i][j] = false;
                        }
                    }
                }
            }
        }
    }


    private boolean isInConrer(int i, int j) {
        if ((i == 0 && j == 0) || (i == 13 && j == 0) || (i == 13 && j == 13) || (i == 0 && j == 13)) {
            return true;
        } else return false;
    }

    private void changeTurn() {
        if (this.turn) {
            turn = false;
        } else {
            turn = true;
        }
    }
}
