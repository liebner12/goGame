package com.michalliebner.sebastianmaraszek.team.gui_swing.controller;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.BlackPiece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Piece;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.PiecesChain;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.Player;
import com.michalliebner.sebastianmaraszek.team.gui_swing.ui.BoardPanel.WhitePiece;
import com.sun.net.httpserver.Filter.Chain;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class VirtualBoard{
    Player BlackPlayer;
    Player WhitePlayer;


    Boolean turn=true;
    Piece immortal;
    Piece toRemember;
    List<Piece> PieceList;// lista pionkow na planszy
    List<PiecesChain> ChainList; // lista wszystkich lancuchow na planszy

    Piece LastPiece;

    public VirtualBoard() {
        ChainList=new ArrayList<PiecesChain>();
        PieceList=new ArrayList<Piece>();

        BlackPlayer=new Player();
        WhitePlayer=new Player();

        BlackPlayer.setColor(BLACK);
        WhitePlayer.setColor(WHITE);
    }

    public void setPieceList(){
        WhitePlayer.PieceList.clear();
        BlackPlayer.PieceList.clear();
        for(Piece piece : PieceList) {
            if (piece.getColor() == white)
                WhitePlayer.PieceList.add(piece);
            else
                BlackPlayer.PieceList.add(piece);
        }
    }

    public void addPiece(int x, int y){

        if(!WhitePlayer.isBot){ //jesli nie bierze udzialu bot, gramy multiplayera
            Multiplayer(x,y);}
        else{ //jesli bierze udzial bot no to single playera
            try {
                SinglePlayer(x,y);
            }
            catch (Exception e){}

        }}

    private void Multiplayer(int x, int y) {
        if (checkFree(x,y)){
            changeTurn();
            if (!turn)
                PlayBlackPiece(x, y);
            else
                PlayWhitePiece(x, y);
        }
    }

    public void resetKO(Color color){
        if(LastPiece!=null){
            if(LastPiece.getColor()!=color)
                LastPiece=null;}
    }

    public void SinglePlayer(int x, int y){
        TwoInt bot=PlayBotPiece();
        if(!turn){
            if(!checkFree(x,y)){
                changeTurn();
            }
            if(checkFree(x,y))//ruch uzytkownika taki sam jak w multiplayerze
            PlayBlackPiece(x,y);
        }
        else{
            PlayWhitePiece(bot.getX(),bot.getY());//ruch bota
        }
    }

    /**  zwracanie listy pionkow i ich pozycji*/
    public List<Piece> getGameBoard(){
        killCheck();
        handleChains(black);
        handleChains(white);

        CalculatePieces();
        WhiteTerritory();
        BlackTerritory();

        return PieceList;
    }

    /** funkcja zwracajaca TRUE jesli miejsce jest wolne, lub FALSE jesli miejsce jest niedostepne
     * Miejsce jest niedostepne, jesli stoi na nim inny pionek, badz postawienie na nim pionka bedzie wiazalo sie z samobojstwem*/
    private boolean checkFree(int x, int y){
        Piece piece2;
        boolean possiblekill=false;

        if(!turn){
            piece2=new WhitePiece();
            piece2.setX(x);
            piece2.setY(y);
            piece2.fullBreathNumber();}
        else {
            piece2 = new BlackPiece();
            piece2.setX(x);
            piece2.setY(y);
            piece2.fullBreathNumber();
        }

        if(KamikazePiece(piece2)){
            return true;
        }
        if(!SuicideChain(piece2)){
            return false;
        }

        for(Piece piece : PieceList){
            if(piece.getX()==x && piece.getY()==y){
                return false;}
            else if (neighbourPieces(piece, piece2)){
                if (piece.getColor() != piece2.getColor()) {
                    if(piece.getBreathNumber()==1){
                        possiblekill=true;
                        immortal=piece2;
                    }
                    piece2.setBreathNumber(piece2.getBreathNumber()-1);//za kazdym razem jak jest kolo pionka pionek innego koloru dodaj 1, jesli bedziesz mial 4 znaczy ze nie mozesz postawic
                }
            }}
        if(piece2.getBreathNumber()<=0){
            System.out.println(possiblekill);
            if(!possiblekill){
                return false;
            }
            if (LastPiece != null && neighbourPieces(LastPiece, piece2) && LastPiece.getColor() != piece2.getColor()){
                return false;}
            else{
                LastPiece = piece2;
            }
        }

        return true;

    }
    public boolean KamikazePiece(Piece piece){
        int willtakebreaths;
        Color color=piece.getColor();
        if(color==white){
            for(PiecesChain chain : BlackPlayer.ChainList){
                willtakebreaths=0;
                for(Piece piece1 : chain.getChain()){
                    if(neighbourPieces(piece1,piece)){
                        willtakebreaths++;
                    }}
                if(chain.BreathsNumber()==willtakebreaths){
                    immortal=piece;
                    return true;}
            }
        }
        else {
            for(PiecesChain chain : WhitePlayer.ChainList){
                willtakebreaths=0;
                for(Piece piece1 : chain.getChain()){
                    if(neighbourPieces(piece1,piece)){
                        willtakebreaths++;
                    }}
                if(chain.BreathsNumber()==willtakebreaths){
                    immortal=piece;
                    return true;}
            }}
        return false;
    }

    public boolean SuicideChain(Piece piece){
        Color color=piece.getColor();
        Piece piece2=null;
        int counter=0;
        int counter2=0;
        int chainBreaths=0;
        piece.fullBreathNumber();
        if(color==black) {
            for (PiecesChain chain : BlackPlayer.ChainList) {
                for (Piece piece1 : chain.getChain()) {
                    if (neighbourPieces(piece1, piece)) {
                        chainBreaths = chain.BreathsNumber();
                        counter2++;
                        piece2 = piece;
                    }
                }
            }

            for (Piece piece3 : WhitePlayer.PieceList) {
                if (neighbourPieces(piece, piece3)) {//inny kolor
                    counter++;
                }
            }

            if (counter == piece.getBreathNumber() - counter2 && piece2 != null
                    && chainBreaths <= counter2) {
                return false;
            } else {
                return true;
            }

        }
        else {
            for(PiecesChain chain : WhitePlayer.ChainList) {
                for (Piece piece1 : chain.getChain()) {
                    if (neighbourPieces(piece1, piece)){
                        chainBreaths=chain.BreathsNumber();
                        piece2=piece;
                        counter2++;}
                }
            }
        }
        for (Piece piece3 : BlackPlayer.PieceList){
            if(neighbourPieces(piece,piece3)){//inny kolor
                counter++;
            }
        }

        if(counter==piece.getBreathNumber()-counter2 && piece2!=null && chainBreaths<=counter2){
            return false;
        }
        else{
            return willJoinAndKillBrothers(piece);}

    }

    public boolean willJoinAndKillBrothers(Piece piece){
        Color color=piece.getColor();
        boolean toReturn=true;
        List<Piece> closeToPiece=new ArrayList<>();
        List<PiecesChain> chainsInNeighbourHood=new ArrayList<>();
        List<Piece> notInChain=new ArrayList<>();
        int TotalBreaths=0;
        int counterAlly=0;
        int counterEnemy=0;

        if(color==black) {
            for (Piece piece3 : BlackPlayer.PieceList) {
                if (neighbourPieces(piece3, piece)){
                    closeToPiece.add(piece3);
                    notInChain.add(piece3);
                }
            }
        }

        else {
            for (Piece piece3 : WhitePlayer.PieceList) {
                if (neighbourPieces(piece3, piece)){
                    closeToPiece.add(piece3);
                    notInChain.add(piece3);
                }
            }}
        for(Piece piece1 : PieceList){
            if(neighbourPieces(piece1,piece) && piece.getColor()!=piece1.getColor()){
                counterEnemy++;
            }
        }

        for(Piece piece1 : closeToPiece){
            if(piece1.getBreathNumber()==1 && counterEnemy>piece.getBreathNumber()){
                toReturn=false;
            }

        }

        return toReturn;}
    /** Sprawdzamy co na calej planszy ma 0 zyc i usuwamy z tego, po usunieciu przywracamy oddech tym obok niego(no bo znikł ten co zabierał)*/
    public void killCheck(){
        List <Piece> toIngonre = new ArrayList<Piece>();
        List<Piece> toRemove = new ArrayList<Piece>();
        for(Piece piece : PieceList){
            if(immortal!=null && immortal.getY()==piece.getY() && immortal.getX()==piece.getX()){
                toIngonre.add(piece);
                immortal=null;
            }
            if(BlackPlayer.ChainContainsPiece(piece) || WhitePlayer.ChainContainsPiece(piece)){
                toIngonre.add(piece);
            }
        }
        for (Piece piece : PieceList) {
            if (piece.getBreathNumber() == 0 && !toIngonre.contains(piece)) {
                if(LastPiece!=null && piece.getX()==LastPiece.getX() && piece.getY()==LastPiece.getY()){
                    LastPiece.setBreathNumber(0);
                    piece=LastPiece;
                }
                if (!BlackPlayer.ChainContainsPiece(piece) && !WhitePlayer.ChainContainsPiece(piece)) {
                    if(piece.getColor()==Color.black){
                        BlackPlayer.addPrisoner();
                    }
                    else{
                        WhitePlayer.addPrisoner();
                    }
                    toRemove.add(piece);}

                else {

                    if(LastPiece!=null && neighbourPieces(LastPiece,piece)){
                        toRemove.remove(LastPiece);
                    }
                    if(piece.getColor()==Color.black){
                        BlackPlayer.addPrisoner();
                    }
                    else{
                        WhitePlayer.addPrisoner();
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
        BlackPlayer.setChainList();
        WhitePlayer.setChainList();

    }
    /** Bardzo wazna i rozbudowana funkcja, okreslajace zachowanie pionka kiedy stoi obok innego pionka
     * Jesli znajdzie sie obok pionka innego koloru, zabiera obu oddech, a jesli stoi obok tego samego koloru
     * dodaje go do lancucha*/

    private void breathCheck(Piece piece1){
        if(piece1.getColor()==BLACK){
            for (Piece piece2 : WhitePlayer.PieceList){
                if(neighbourPieces(piece1,piece2)){//inny kolor
                    piece2.takeBreath(piece1.getX(),piece1.getY());
                    piece1.takeBreath(piece2.getX(),piece2.getY());}}
            BlackPlayer.CheckAndAddToChain(piece1);
            BlackPlayer.scanForJoins();
        }
        else {
            for (Piece piece2 : BlackPlayer.PieceList){
                if(neighbourPieces(piece1,piece2)){//inny kolor
                    piece2.takeBreath(piece1.getX(),piece1.getY());
                    piece1.takeBreath(piece2.getX(),piece2.getY());
                }
            }
            WhitePlayer.CheckAndAddToChain(piece1);
            WhitePlayer.scanForJoins();
        }
    }

    private void handleChains(Color color){
        List <PiecesChain> chainsToRemove=new ArrayList<>();
        if(color==BLACK){
            for(PiecesChain chain : BlackPlayer.ChainList){
                System.out.println(chain.BreathsNumber()+ "--" + chain.getChain().size() + "black" );
                if(chain.BreathsNumber()<=0){
                    chainsToRemove.add(chain);
                }
            }
        }
        else{
            for(PiecesChain chain : WhitePlayer.ChainList){
                System.out.println(chain.BreathsNumber()+ "--" + chain.getChain().size() + "white" );
                if(chain.BreathsNumber()==0){
                    chainsToRemove.add(chain);
                }
            }
        }
        Iterator<PiecesChain> it = chainsToRemove.iterator();
        while (it.hasNext()) {
            removeChain(it.next());
        }
    }

    private void removeChain(PiecesChain chain){
        Color color=chain.getColor();
        List<Piece> toRemove = new ArrayList<Piece>();
        for(Piece piece : chain.getChain()){
            for(Piece piece1 : PieceList){
                toRemove.add(piece);
                if(neighbourPieces(piece1,piece)){
                    if(piece1.getColor()!=color){
                        piece1.addBreath(piece.getX(),piece.getY());
                    }
                }
            }
        }
        if(color==BLACK){
            BlackPlayer.setPrisoners(BlackPlayer.getPrisoners()+chain.getChain().size());
            BlackPlayer.ChainList.remove(chain);
            BlackPlayer.PieceList.removeAll(toRemove);
        }
        else{
            WhitePlayer.setPrisoners(WhitePlayer.getPrisoners()+chain.getChain().size());
            WhitePlayer.PieceList.removeAll(toRemove);
            WhitePlayer.ChainList.remove(chain);
        }

        PieceList.removeAll(toRemove);
        for(PiecesChain pc: BlackPlayer.ChainList){
            pc.setEnemiesPosition();

        }
        for(PiecesChain pc: WhitePlayer.ChainList){
            pc.setEnemiesPosition();
        }

    }



    public void changeTurn(){
        turn=!turn;
    }
    public boolean neighbourPieces(Piece piece1, Piece piece2){
        if((abs(piece1.getX()-piece2.getX())==0 && abs(piece1.getY()-piece2.getY())== 1) || (
                abs(piece1.getX()-piece2.getX())==1 && abs(piece1.getY()-piece2.getY())== 0)){
            return true;
        }
        return false;
    }

    public void PlayWithBot(){
        WhitePlayer.isBot=true;
    }
    public void PlayWithHuman(){
        WhitePlayer.isBot=false;
    }
    public void PlayBlackPiece(int x, int y){
        resetKO(black);
        Piece black=new BlackPiece();
        black.setX(x);
        black.setY(y);
        black.fullBreathNumber();
        PieceList.add(black);
        breathCheck(black);
        setPieceList();
    }
    public void PlayWhitePiece(int x, int y){
        resetKO(white);
        Piece white=new WhitePiece();
        white.setX(x);
        white.setY(y);
        white.fullBreathNumber();
        PieceList.add(white);
        breathCheck(white);
        setPieceList();
    }
    public void CalculatePieces(){
        WhitePlayer.clearTerritory();
        BlackPlayer.clearTerritory();
        for(Piece piece: PieceList){
            if(piece.getColor()==white){
                WhitePlayer.addTerritory();}
            else {
                BlackPlayer.addTerritory();}
        }


    }

    public TwoInt PlayBotPiece(){
        Random random=new Random();
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
        TwoInt twoInt=new TwoInt(white.getX(),white.getY());
        return twoInt;}

    public int WhiteTerritory() {
        return WhitePlayer.setTerritory(WhitePlayer.getTerritory());
    }

    public int BlackTerritory() {
        return BlackPlayer.setTerritory(BlackPlayer.getTerritory());

    }

    public int BlackPrisoners() {
        return BlackPlayer.setPrisoners(BlackPlayer.getPrisoners());
    }

    public int WhitePrisoners() {
        return WhitePlayer.setPrisoners(WhitePlayer.getPrisoners());
    }
    public int whichTurn() {
        if (turn)
            return 1;
        else
            return 0;
    }
    public void setTurn(int newTurn) {
        boolean newT = true;
        if (newTurn == 0)
            newT = false;
        else if (newTurn == 1)
            newT = true;
        turn = newT;
    }

}