package Model;
import java.io.Serializable;
import java.util.*;

public class AI extends Player implements Serializable{

    public AI() {
        super("Computer");
    }

    @Override
    public ArrayList<Check> chooseNextMove(HashMap<ArrayList, Integer> possibleMoves) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Piece choosePieceToMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}