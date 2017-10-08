package Model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Man extends Piece {

	/**
	 * 2 directions possible :
	 *  - 0 : From top of the gameboard to the bottom
	 *  - 1 : From bottom of the gameboard to the top
	 */
	private int destination;

        public Man(Check position, String color, int destination) {
            super(position, color);
            this.destination=destination;
        }

	public int getDestination() {
		return this.destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

    @Override
    public ArrayList<Check> getPossibleMoves() {
     int positionLine = this.getPosition().getLineNumber();
     int positionColomn = this.getPosition().getColomnNumber();
     Gameboard gameboard = this.getPosition().getGameboard();
     int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
     int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
     int dest = this.getDestination();
     Check currentOption;
     ArrayList<Check> possibleMoves = new ArrayList<Check>();
     ArrayList<Check> occupiedChecks = new ArrayList<Check>();
     
     if(dest==0){//Case 1 : top to bottom
         if(positionLine<nbGameboardLines){
             if(positionColomn>0){
                     currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn-1));
                     if(!currentOption.isOccupied()){
                         possibleMoves.add(currentOption);
                     } else {
                         occupiedChecks.add(currentOption);
                     }
             }
             
             if(positionColomn<nbGameboardColomns){
                     currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                     if(!currentOption.isOccupied()){
                         possibleMoves.add(currentOption);
                     } else {
                         occupiedChecks.add(currentOption);
                     }
             }
         }
     } else {//Case 2 : bottom to top
         if(positionLine>0){
             if(positionColomn>0){
                     currentOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn-1));
                     if(!currentOption.isOccupied()){
                         possibleMoves.add(currentOption);
                     } else {
                         occupiedChecks.add(currentOption);
                     }
             }
             
             if(positionColomn<nbGameboardColomns){
                     currentOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                     if(!currentOption.isOccupied()){
                         possibleMoves.add(currentOption);
                     } else {
                         occupiedChecks.add(currentOption);
                     }
             }
         }
     }
     
     return possibleMoves;
    }        

    @Override
    public void move() {
        
    }
        
        

}