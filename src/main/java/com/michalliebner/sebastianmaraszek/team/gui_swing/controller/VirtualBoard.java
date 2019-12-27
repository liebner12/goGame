package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;
import static java.awt.Color.white;
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
    Boolean turn=false; //turn=true oznacza ze ruszaja sie biale
    Boolean bot=true;   //czy w grze uczestniczy bot
    List<Piece> PieceList;// lista pionkow na planszy
    List<PiecesChain> ChainList; // lista wszystkich lancuchow na planszy

    TwoInt ko;
    TwoInt ko2;

    Piece LastPiece;

    int BlackTerritory;
    int WhiteTerritory;

    int WhitePrisoners;
    int BlackPrisoners;


        public VirtualBoard() {
            ChainList=new ArrayList<PiecesChain>();
            PieceList=new ArrayList<Piece>();
        }


    public void addPiece(int x, int y){
            if(!bot){ //jesli nie bierze udzialu bot, gramy multiplayera
            Multiplayer(x,y);}
            else{ //jesli bierze udzial bot no to single playera
            try {
               SinglePlayer(x,y);
            }
            catch (Exception e){}
        }}

    private void Multiplayer(int x, int y){
        if(checkFree(x,y)){
            changeTurn();
           // najpierw sprawdz czy miejsce na ktorym stawiasz pionka jest wolne
                if(!turn){ //jesli ma byc stawiany czarny
                    Piece black=new BlackPiece();
                    black.setX(x);
                    black.setY(y);
                 if(black.isInCorner())
                     black.setBreathNumber(2);
                 else if(black.isOnBorder())
                     black.setBreathNumber(3);
                 else
                     black.setBreathNumber(4); // ustaw liczbe oddechow w zaleznosci od tego gdzie dany pionek sie znajduje
                     PieceList.add(black);
                     breathCheck(black); //funkcja breathcheck
               }

             else{ //jesli ma byc stawiany bialy
                 Piece white=new WhitePiece();
                 white.setX(x);
                 white.setY(y);
                if(white.isInCorner())
                    white.setBreathNumber(2);
                else if(white.isOnBorder())
                    white.setBreathNumber(3);
                else
                    white.setBreathNumber(4);
                    PieceList.add(white);
                    breathCheck(white);}
             }
        }

 public void SinglePlayer(int x, int y){
         Random random=new Random();
         if(turn){ //ruch uzytkownika taki sam jak w multiplayerze
             if (checkFree(x, y)){
                 changeTurn();
         Piece black = new BlackPiece();
         black.setX(x);
         black.setY(y);
         if (black.isInCorner())
             black.setBreathNumber(2);
         else if (black.isOnBorder())
             black.setBreathNumber(3);
         else
             black.setBreathNumber(4);
             PieceList.add(black);
             breathCheck(black);}}
        else{           //ruch bota
            changeTurn();
            List<Piece> availble=new ArrayList<>();  //wszystkie miejsca na ktore mozna postawic pionka
            List<Piece> firstchoice=new ArrayList<>(); // wszystkie miejsca gdzie mozna postawic pionka aby stal obok swojego kolegi
            for(int i=0; i<13 ; i ++){
                for (int j=0; j<13; j++){
                    if(checkFree(i,j)){
                        Piece piece=new WhitePiece();
                        piece.setY(j);
                        piece.setX(i);
                        availble.add(piece);  //uzupełniamy liste dostepnych pol
                        }}}
            for(Piece piece: PieceList){
                for(Piece piece1: availble){
                    if(neighbourPieces(piece1,piece) && piece1.getColor()==piece.getColor()){
                            firstchoice.add(piece1); //uzupelniamy liste miejsc gdzie bedzie sasiadowal z kolegom
                        }
                    } }
            Piece white = new WhitePiece();
            int r=abs(random.nextInt())%2;

            if(availble.size()>0){
                int choice=abs(random.nextInt()%availble.size()); //wybieramy losowo jeden element z listy dostepnych pol
                white=availble.get(choice);}
            if(r==1){
            if(firstchoice.size()>0){
                int choice= abs(random.nextInt()%firstchoice.size()); //wybieramy losowo jeden tak aby sasiadowal(w co 2 przypadku)

                white=firstchoice.get(choice);}}

            if (white.isInCorner())
                white.setBreathNumber(2);
            else if (white.isOnBorder())
                  white.setBreathNumber(3);
            else
                white.setBreathNumber(4);
                PieceList.add(white);
                breathCheck(white);}}

                /**  zwracanie listy pionkow i ich pozycji*/
    public List<Piece> getGameBoard(){
            killCheck();
            chainKillCheck();
            return PieceList;
    }
/** funkcja zwracajaca TRUE jesli miejsce jest wolne, lub FALSE jesli miejsce jest niedostepne
 * Miejsce jest niedostepne, jesli stoi na nim inny pionek, badz postawienie na nim pionka bedzie wiazalo sie z samobojstwem*/
    private boolean checkFree(int x, int y) {
        Piece piece2;
        int counter = 0;
        if (!turn) {
            piece2 = new WhitePiece();
            piece2.setX(x);
            piece2.setY(y);
            if (piece2.isInCorner())
                piece2.setBreathNumber(2);
            else if (piece2.isOnBorder())
                piece2.setBreathNumber(3);
            else
                piece2.setBreathNumber(4);
        } else {
            piece2 = new BlackPiece();
            piece2.setX(x);
            piece2.setY(y);
            if (piece2.isInCorner())
                piece2.setBreathNumber(2);
            else if (piece2.isOnBorder())
                piece2.setBreathNumber(3);
            else
                piece2.setBreathNumber(4);
        }
        for (Piece piece : PieceList) {
            if (piece.getX() == x && piece.getY() == y) {
                return false;
            }
            if (neighbourPieces(piece, piece2)) {
                if (piece.getColor() != piece2.getColor()) {
                    counter++; //za kazdym razem jak jest kolo pionka pionek innego koloru dodaj 1, jesli bedziesz mial 4 znaczy ze nie mozesz postawic
                }
            }
        }
        if(counter == piece2.getBreathNumber()) {
            if (LastPiece != null && neighbourPieces(LastPiece, piece2) && LastPiece.getColor() != piece2.getColor()){
                return false;}
            else{
                LastPiece = piece2;
            }
        }
            return true;

    }


    public void chainKillCheck(){
        for (PiecesChain pieceChain : ChainList) {
                //System.out.println(pieceChain.BreathNumber());
                //System.out.println(pieceChain.koleszka+ "koleszkow");
                //System.out.println(pieceChain.wspulnywruk+ "wspolnych wroguw");
            }
    }
/** Sprawdzamy co na calej planszy ma 0 zyc i usuwamy z tego, po usunieciu przywracamy oddech tym obok niego(no bo znikł ten co zabierał)*/
    public void killCheck(){
        List<Piece> toRemove = new ArrayList<Piece>();
        for (Piece piece : PieceList) {
            if (piece.getBreathNumber() == 0) {
                if(LastPiece!=null && piece.getX()==LastPiece.getX() && piece.getY()==LastPiece.getY()){
                    LastPiece.setBreathNumber(0);
                    piece=LastPiece;
                }
                if (ChainList.size() > 0) {
                    for (PiecesChain chain : ChainList){
                        if (!chain.getChain().contains(piece)) {
                            if(piece.getColor()==Color.black){
                                BlackPrisoners++;
                            }
                            else{
                                WhitePrisoners++;
                            }
                            toRemove.add(piece);
                        }
                    }
                } else {
                    if(LastPiece!=null && neighbourPieces(LastPiece,piece)){
                        toRemove.remove(LastPiece);
                    }
                    if(piece.getColor()==Color.black){
                    BlackPrisoners++;
                }
                else{
                    WhitePrisoners++;
                }
                    toRemove.add(piece);
                }
            }
        }
        for (Piece piece : PieceList) {
            for (Piece piece1 : toRemove) {
                if (neighbourPieces(piece, piece1)) {
                    if (piece.getColor() != piece1.getColor()){
                        piece.setBreathNumber(piece.getBreathNumber() + 1);
                    }


                }
            }
        }
        PieceList.removeAll(toRemove);


    }
    /** Bardzo wazna i rozbudowana funkcja, okreslajace zachowanie pionka kiedy stoi obok innego pionka
     * Jesli znajdzie sie obok pionka innego koloru, zabiera obu oddech, a jesli stoi obok tego samego koloru
     * dodaje go do lancucha*/
    private void breathCheck(Piece piece1){
            for (Piece piece2 : PieceList){
                        if(neighbourPieces(piece1,piece2)){
                            if(piece1.getColor()!=piece2.getColor()){//inny kolor
                                piece2.takeBreath(piece1.getX(),piece1.getY());
                                piece1.takeBreath(piece2.getX(),piece2.getY());
                            }
                else{//ten sam kolor
                    List<PiecesChain> chains=new ArrayList<>();
                    Boolean contains=false;
                    if(ChainList.size()>0){ //jesli jest jakis lancuch na calej planszy
                        int k=ChainList.size();
                        for(int i =0 ; i<k; i++){ //sprawdz wszystkie lancuchy, czy ktorys zawiera pionka do ktorego dostawiamy
                            if(ChainList.get(i).getChain().contains(piece2)){
                                ChainList.get(i).addPiece(piece1);
                                contains=true;
                        }}
                       if (!contains){ //jesli nie zawiera zaden to stworz nowy lancuch skladajacy sie z tych 2 pionkow
                           PiecesChain piecesChain= new PiecesChain();
                           piecesChain.addPiece(piece1);
                           piecesChain.addPiece(piece2);
                           ChainList.add(piecesChain);
                    }}
                       else{ // jesli lista lancuchow jest pusta to dodaj do niej nowy lancuch z tych 2 pionkow
                           PiecesChain piecesChain= new PiecesChain();
                           piecesChain.addPiece(piece1);
                           piecesChain.addPiece(piece2);
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
    }
    public void PlayWithBot(){
        bot=true;
    }
    public void PlayWithHuman(){
        bot=false;
    }

    public void CalculatePieces(){
        for(Piece piece: PieceList){
            if(piece.getColor()==white){
                WhiteTerritory++;}
            else {
            BlackTerritory++;}
        }
    }

    public void WhiteTerritory() {
        CalculatePieces();
        WhiteTerritory+=WhitePrisoners;
        for(int i=0;i<13;i++){
            for(int j=0;j<13;j++){

            }}
    }

    public void BlackTerrirtory(){
        BlackTerritory+=BlackPrisoners;
    }
}