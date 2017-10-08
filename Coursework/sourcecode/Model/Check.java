package Model;

public class Check {

	Piece CheckPiece;
	private int lineNumber;
	private int colomnNumber;
	private boolean isOccupied;
	Gameboard gameboard;

	public int getLineNumber() {
		return this.lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColomnNumber() {
		return this.colomnNumber;
	}

	public void setColomnNumber(int colomnNumber) {
		this.colomnNumber = colomnNumber;
	}

	public boolean isIsOccupied() {
		return this.isOccupied;
	}

	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public void freeCheck(){
    this.setIsOccupied(false);
	}

  public void occupyCheck(){
    this.setIsOccupied(true);
  }

}
