package Model;

public class Man extends Piece {

	/**
	 * 2 directions possible :
	 *  - 0 : From top of the gameboard to the bottom
	 *  - 1 : From bottom of the gameboard to the top
	 */
	private int destination;

	public int getDestination() {
		return this.destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

}