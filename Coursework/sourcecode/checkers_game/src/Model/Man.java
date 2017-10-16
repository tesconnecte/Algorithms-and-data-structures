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
        public Tree<Check> getRifleMove(Tree<Check> possibilities){
            Man fakeManForResult = new Man(possibilities.getData(),"Fk",this.getDestination());
            ArrayList<Check> nextPossibleMoves = new ArrayList<Check>();
            nextPossibleMoves.addAll(fakeManForResult.getFrontCaptureMove());
            nextPossibleMoves.addAll(fakeManForResult.getBackCaptureMove());
            fakeManForResult = null;
            Tree<Check> newChildTree;
            for(Check currentCheck : nextPossibleMoves){
                if(((possibilities.getParent()!=null)&&(currentCheck!=possibilities.getParent().getData()))||(possibilities.getParent()==null)){
                    newChildTree = new Tree<Check>(currentCheck);
                    possibilities.addChild(newChildTree);
                }           
            }
            for(Tree currentTree : possibilities.getChildren()){
                getRifleMove(currentTree);
            }
            
            return possibilities;
            
            /*if((frontPossibleMoves.isEmpty())&&(backPossibleMoves.isEmpty())){
                return possibilities;
            } else {
                Tree<Check> newChildTree;
                for(Check currentCheck : frontPossibleMoves){
                    newChildTree = new Tree<Check>(currentCheck);
                    getRifleMove(newChildTree);
                    possibilities.addChild(newChildTree);
                    
                }
                
                for(Check currentCheck : backPossibleMoves){
                    newChildTree = new Tree<Check>(currentCheck);
                    getRifleMove(newChildTree);
                    possibilities.addChild(newChildTree);
                }
                
                return possibilities;
            }*/
        }
        
        @Override
        public ArrayList<Check> getFrontMove(){
           int positionLine = this.getPosition().getLineNumber();
           int positionColomn = this.getPosition().getColomnNumber();
           Gameboard gameboard = this.getPosition().getGameboard();
           int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
           int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
           int dest = this.getDestination();
           Check currentOption;
           Check backwardCaptureOption;
           ArrayList<Check> possibleMoves = new ArrayList<Check>();

           switch (dest){
               case 0://Case 0 : For pieces from gameboard's top to bottom
                      if(positionLine<nbGameboardLines){
                          if(positionColomn>0){
                                  currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn-1));
                                  if(!currentOption.isOccupied()){
                                      possibleMoves.add(currentOption);
                                  }
                          }             
                          if(positionColomn<nbGameboardColomns){
                                  currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                                  if(!currentOption.isOccupied()){
                                      possibleMoves.add(currentOption);
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
                                  }
                          }

                          if(positionColomn<nbGameboardColomns){
                                  currentOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                                  if(!currentOption.isOccupied()){
                                      possibleMoves.add(currentOption);
                                  }
                          }
                      }
                      break;       

           }
           return possibleMoves;            
        }
        
        public ArrayList<Check> getFrontCaptureMove(){
            int positionLine = this.getPosition().getLineNumber();
            int positionColomn = this.getPosition().getColomnNumber();
            Gameboard gameboard = this.getPosition().getGameboard();
            int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
            int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
            int dest = this.getDestination();
            Check middleOption;
            Check captureOption;
            ArrayList<Check> possibleMoves = new ArrayList<Check>();
            
            switch(dest){
            case 0:
                if(positionLine<(nbGameboardLines-1)){
                    if(positionColomn>1){
                        middleOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn-1));
                        captureOption = gameboard.getCheckByLineColomn((positionLine+2),(positionColomn-2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!captureOption.isOccupied())){
                            possibleMoves.add(captureOption);
                        }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                        middleOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                        captureOption = gameboard.getCheckByLineColomn((positionLine+2),(positionColomn+2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!captureOption.isOccupied())){
                            possibleMoves.add(captureOption);
                        }
                    }
                }
            break;            
            case 1:
                if(positionLine>1){
                    if(positionColomn>1){
                            middleOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn-1));
                            captureOption = gameboard.getCheckByLineColomn((positionLine-2),(positionColomn-2));
                            if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!captureOption.isOccupied())){
                                possibleMoves.add(captureOption);
                            }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                            middleOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                            captureOption = gameboard.getCheckByLineColomn((positionLine-2),(positionColomn+2));
                            if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!captureOption.isOccupied())){
                                possibleMoves.add(captureOption);
                            }
                    }
                }
            break;
            }
        return possibleMoves;
        }
        
        @Override
        public ArrayList<Check> getBackCaptureMove(){
            int positionLine = this.getPosition().getLineNumber();
            int positionColomn = this.getPosition().getColomnNumber();
            Gameboard gameboard = this.getPosition().getGameboard();
            int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
            int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
            int dest = this.getDestination();
            Check middleOption;
            Check backwardCaptureOption;
            ArrayList<Check> possibleMoves = new ArrayList<Check>();
            
            switch(dest){
            case 0:
                if(positionLine>1){
                    if(positionColomn>1){
                        middleOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn-1));
                        backwardCaptureOption = gameboard.getCheckByLineColomn((positionLine-2),(positionColomn-2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!backwardCaptureOption.isOccupied())){
                            possibleMoves.add(backwardCaptureOption);
                        }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                        middleOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                        backwardCaptureOption = gameboard.getCheckByLineColomn((positionLine-2),(positionColomn+2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!backwardCaptureOption.isOccupied())){
                            possibleMoves.add(backwardCaptureOption);
                        }
                    }
                }
            break;            
            case 1:
                if(positionLine<(nbGameboardLines-1)){
                    if(positionColomn>1){
                            middleOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn-1));
                            backwardCaptureOption = gameboard.getCheckByLineColomn((positionLine+2),(positionColomn-2));
                            if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!backwardCaptureOption.isOccupied())){
                                possibleMoves.add(backwardCaptureOption);
                            }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                            middleOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                            backwardCaptureOption = gameboard.getCheckByLineColomn((positionLine+2),(positionColomn+2));
                            if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!backwardCaptureOption.isOccupied())){
                                possibleMoves.add(backwardCaptureOption);
                            }
                    }
                }
            break;
            }
        return possibleMoves;
        }

    @Override
    public ArrayList<Check> getPossibleMoves() {
        Tree<Check> rootCurrentPosition = new Tree<Check>(this.getPosition());
        Tree<Check> riffle;
        riffle = this.getRifleMove(rootCurrentPosition);
        riffle.drawTree();
     
        ArrayList<Check> possibleMoves = new ArrayList<Check>();
        possibleMoves.addAll(this.getFrontMove());
        possibleMoves.addAll(this.getFrontCaptureMove());
        possibleMoves.addAll(this.getBackCaptureMove());

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