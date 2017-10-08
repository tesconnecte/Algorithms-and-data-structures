package Model;

import java.util.*;

public class Gameboard {

	private int nbLines = 10;
	private int nbColomns = 10;
	Collection<Check> gameboardChecks;

	public int getNbLines() {
		return this.nbLines;
	}

	public int getNbColomns() {
		return this.nbColomns;
	}

	public void drawGameboard(){
			System.out.println("------------------------------");
			System.out.println("------------------------------");
	}

}
