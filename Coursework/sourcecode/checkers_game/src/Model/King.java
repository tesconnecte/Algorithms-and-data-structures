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
    public void move(Check arrival) {
        
    }
    
    @Override
        public ArrayList<Check> getRifleMove(){
            
            return null;
        }
    
     @Override
    public void die(){
        this.getPosition().setcheckPiece(null);
        this.getOwner().deletePiece(this);
        this.setOwner(null);
        this.setPosition(null);
    }
}