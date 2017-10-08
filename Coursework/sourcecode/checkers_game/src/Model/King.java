package Model;

import java.util.ArrayList;

public class King extends Piece {

    public King(Check position, String color) {
        super(position, color);
    }
    
    @Override
    public ArrayList<Check> getPossibleMoves() {
     return null;
    }        

    @Override
    public void move() {
        
    }
}