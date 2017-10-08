package Model;

import java.util.ArrayList;

public abstract class Piece {

	protected Check position;
	protected AI owner;
	protected String color;

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
        
        abstract ArrayList<Check> getPossibleMoves();

	abstract void move();

}