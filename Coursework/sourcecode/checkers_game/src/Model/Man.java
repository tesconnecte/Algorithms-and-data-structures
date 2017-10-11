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
     
     switch (dest){
         case 0://Case 0 : From gameboard's top to bottom
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
              break;
         
         case 1://Case 1 : From gameboard's bottom to top
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
                break;         
             
     }

     if(!occupiedChecks.isEmpty()){
         int currentOccupiedCheckLine;
         int currentOccupiedCheckColomn;
         for(Check currentOccupiedCheck : occupiedChecks){
             currentOccupiedCheckLine = currentOccupiedCheck.getLineNumber();
             currentOccupiedCheckColomn = currentOccupiedCheck.getColomnNumber();
            switch (dest) {
                case 0 :
                    if((currentOccupiedCheck.getcheckPiece().getColor()!=this.getColor())&&(currentOccupiedCheckLine<nbGameboardLines)){//Occupied Check not same color + not out of gameboard range 
                       int rightOrLeft = currentOccupiedCheckColomn-positionColomn;//Indicate wether the occupied check is right or left diagonal
                       if((rightOrLeft==-1)&&(currentOccupiedCheckColomn>0)){//Left case
                               currentOption = gameboard.getCheckByLineColomn((currentOccupiedCheckLine+1),(currentOccupiedCheckColomn-1));
                               if(!currentOption.isOccupied()){
                                   possibleMoves.add(currentOption);
                               }
                       }             
                       if((rightOrLeft==1)&&(currentOccupiedCheckColomn<nbGameboardColomns)){//Right case
                               currentOption = gameboard.getCheckByLineColomn((currentOccupiedCheckLine+1),(currentOccupiedCheckColomn+1));
                               if(!currentOption.isOccupied()){
                                   possibleMoves.add(currentOption);
                               }
                       }
                   }
                   break;
                case 1 :
                    if((currentOccupiedCheck.getcheckPiece().getColor()!=this.getColor())&&(currentOccupiedCheckLine>0)){//Occupied Check not same color + not out of gameboard range 
                       int rightOrLeft = currentOccupiedCheckColomn-positionColomn;//Indicate wether the occupied check is right or left diagonal
                       if((rightOrLeft==-1)&&(currentOccupiedCheckColomn>0)){//Left case
                               currentOption = gameboard.getCheckByLineColomn((currentOccupiedCheckLine-1),(currentOccupiedCheckColomn-1));
                               if(!currentOption.isOccupied()){
                                   possibleMoves.add(currentOption);
                               }
                       }             
                       if((rightOrLeft==1)&&(currentOccupiedCheckColomn<nbGameboardColomns)){//Right case
                               currentOption = gameboard.getCheckByLineColomn((currentOccupiedCheckLine-1),(currentOccupiedCheckColomn+1));
                               if(!currentOption.isOccupied()){
                                   possibleMoves.add(currentOption);
                               }
                       }
                   }
                    break;
            }
         }
     }
     return possibleMoves;
    }        

    @Override
    public void move(Check arrival) {
        int departureLine = this.getPosition().getLineNumber();
        int departureColomn = this.getPosition().getColomnNumber();
        int arrivalLine = arrival.getLineNumber();
        int arrivalColomn = arrival.getColomnNumber();
        Gameboard gameboard = this.getPosition().getGameboard();
        int dest = this.getDestination();
        
        if(departureLine-arrivalLine>1){//case if White takes an adversary piece 
            int rightOrLeft  = ((arrivalColomn-departureColomn)/2);//Result will be 1 or -1 depending if it's right or left
            if((rightOrLeft==-1)||(rightOrLeft==1)){
                Check adversaryCheck = gameboard.getCheckByLineColomn((departureLine-1),(departureColomn+rightOrLeft));
                Piece adversaryPiece = adversaryCheck.getcheckPiece();
                adversaryPiece.die();
                adversaryPiece = null;
            }
        }
        
        if(departureLine-arrivalLine<-1){//case if Blacks takes an adversary piece 
            int rightOrLeft  = ((arrivalColomn-departureColomn)/2);//Result will be 1 or -1 depending if it's right or left
            if((rightOrLeft==-1)||(rightOrLeft==1)){
                Check adversaryCheck = gameboard.getCheckByLineColomn((departureLine+1),(departureColomn+rightOrLeft));
                Piece adversaryPiece = adversaryCheck.getcheckPiece();
                adversaryPiece.die();
                adversaryPiece = null;
            }            
        }
        
        if((dest==0)&&(arrivalLine==(gameboard.getNbLines()-1))){
            this.toKing(arrival);
        } else if((dest==1)&&(arrivalLine==0)){
            this.toKing(arrival);
        } else {
            this.getPosition().setcheckPiece(null);
            this.setPosition(arrival);
            arrival.setcheckPiece(this);
        }
    }
    
    @Override
    public void die(){
        this.getOwner().deletePiece(this);
        this.setOwner(null);
        this.getPosition().setcheckPiece(null);        
        this.setPosition(null);
    }
    
    public void toKing(Check arrivalCheck){
        King upgradedMan = new King(arrivalCheck,this.getColor());
        upgradedMan.setOwner(this.getOwner());
        this.die();
        arrivalCheck.setcheckPiece(upgradedMan);
        
    }
        
        

}