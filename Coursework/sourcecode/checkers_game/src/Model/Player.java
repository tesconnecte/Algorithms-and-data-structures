package Model;

import java.util.*;
import java.io.Serializable;

public abstract class Player implements Serializable{
    
    protected ArrayList<Piece> pieces;
    protected String name;

    public Player(String name) {
        this.name = name;
        this.pieces = new ArrayList<Piece>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    
    public void addPiece(Piece piece){
        this.pieces.add(piece);
    }

    public void deletePiece(Piece piece){
        int index = this.getPieces().indexOf(piece);
        if(index!=-1){
           this.getPieces().remove(index);
        }
    }
    
    public abstract ArrayList<Check> chooseNextMove(HashMap<ArrayList,Integer> possibleMoves);
    
    public abstract Piece choosePieceToMove();
    
    public void playOnce(){
        System.out.println(this.getName()+"'s turn to play !");
        Piece pieceToMove = this.choosePieceToMove();
        if(pieceToMove!=null){
            ArrayList<Check> arrival = this.chooseNextMove(pieceToMove.getPossibleMoves());
            if(arrival.size()>1){
                pieceToMove.riffleMove(arrival);
            } else {
                pieceToMove.move(arrival.get(0));    
            }                    
        } else {
            System.out.println(this.getName()+" cannot play !");
        }
    }


	
	

}