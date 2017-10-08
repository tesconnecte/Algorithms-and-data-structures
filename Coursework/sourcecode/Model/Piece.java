package Model;

public abstract class Piece {

	Check Position;
	AI owner;
	private string color;

	public string getColor() {
		return this.color;
	}

	public void setColor(string color) {
		this.color = color;
	}

	public Check getPossibleMoves() {
		// TODO - implement Piece.getPossibleMoves
		throw new UnsupportedOperationException();
	}

	public void move() {
		// TODO - implement Piece.move
		throw new UnsupportedOperationException();
	}

}