package Model;

import java.util.*;

public abstract class Player {
    
    protected ArrayList<Piece> pieces;
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    
    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    public void deletePiece(Piece piece){
        int index = this.getPieces().indexOf(piece);
        if(index!=-1){
           this.getPieces().remove(index);
        }
    }

	
	

}