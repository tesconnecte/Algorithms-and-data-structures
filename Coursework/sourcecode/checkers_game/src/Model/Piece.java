package Model;

import java.util.ArrayList;

public abstract class Piece {

	protected Check position;
	protected Player owner;
	protected String color;

        public Player getOwner() {
            return owner;
        }

        public void setOwner(Player owner) {
            this.owner = owner;
        }     
        

        public Piece(Check position, String color) {
            this.position = position;
            this.color = color;
        }

        public Check getPosition() {
            return position;
        }

        public void setPosition(Check position) {
            this.position = position;
        }  

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}
        
        public abstract ArrayList<Check> getFrontMove();
        
        public abstract ArrayList<Check> getBackCaptureMove();
        
        public abstract ArrayList<Check> getPossibleMoves();
        
        public abstract Tree<Check> getRifleMove(Tree<Check> possibilities);

	public abstract void move(Check arrival);
        
        public abstract void die();

}