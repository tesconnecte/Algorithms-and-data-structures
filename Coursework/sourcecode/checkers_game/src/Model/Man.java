package Model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
import java.util.HashMap;

public class Man extends Piece implements Serializable{

    /**
    * 2 possible directions :
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
    public Tree<Check> getRifleMove(Tree<Check> possibilities,Gameboard gameboard){
       // System.out.println("\nROOT of check line "+(possibilities.getData().getLineNumber()+1)+" and colomn "+(possibilities.getData().getColomnNumber()+1));
            Gameboard copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
            Gameboard originalGameboard = possibilities.getData().getGameboard();
            Check loopCheck;
            Tree<Check> loopTree = possibilities;
            while(loopTree.getParent()!=null){
                loopCheck = (Check)possibilities.getParent().getData();
                originalGameboard = loopCheck.getGameboard();
                loopTree = loopTree.getParent();
            }
            
            Check previousCheckOnOldGameboard = (Check)possibilities.getData();
            Check previousCheckOnNewGameboard = copyGameboard.getCheckByLineColomn(previousCheckOnOldGameboard.getLineNumber(), previousCheckOnOldGameboard.getColomnNumber());
            Check currentCheck = copyGameboard.getCheckByLineColomn(possibilities.getData().getLineNumber(), possibilities.getData().getColomnNumber());
            Man currentMan;
            if(possibilities.getParent()!=null){
                previousCheckOnOldGameboard = (Check)possibilities.getParent().getData();
                previousCheckOnNewGameboard = copyGameboard.getCheckByLineColomn(previousCheckOnOldGameboard.getLineNumber(), previousCheckOnOldGameboard.getColomnNumber());
                /*On the new copied gameboard, we copy the King to its new position*/
                previousCheckOnNewGameboard.getcheckPiece().setPosition(currentCheck);
                currentCheck.setcheckPiece(previousCheckOnNewGameboard.getcheckPiece());
                previousCheckOnNewGameboard.setcheckPiece(null);
                currentMan = (Man)currentCheck.getcheckPiece();
            }else {//1st case, King does not need to be moved
                currentMan = (Man)currentCheck.getcheckPiece();
            }
            HashMap<Check,Gameboard> nextPossibleMoves = new HashMap<Check,Gameboard>();
            //ArrayList<Check> nextPossibleMoves = new ArrayList<Check>();
     
            nextPossibleMoves.putAll(currentMan.getFrontCaptureMove(copyGameboard));
            nextPossibleMoves.putAll(currentMan.getBackCaptureMove(copyGameboard));
            Tree<Check> newChildTree;
            
            for(Check currentPossibleNextCheck : nextPossibleMoves.keySet()){
                //System.out.println("CHILD of check line "+(currentCheck.getLineNumber()+1)+" and colomn "+(currentCheck.getColomnNumber()+1));

                //if((possiblePreviousPosition.isEmpty())||(!possiblePreviousPosition.contains(currentCheck))){
                    newChildTree = new Tree<Check>(originalGameboard.getCheckByLineColomn(currentPossibleNextCheck.getLineNumber(), currentPossibleNextCheck.getColomnNumber()));
                    possibilities.addChild(newChildTree);
                //}           
            }
            Check keyCheck;
            Check treeCheck;
            for(Tree currentTree : possibilities.getChildren()){
                treeCheck = (Check)currentTree.getData();
                keyCheck = copyGameboard.getCheckByLineColomn(treeCheck.getLineNumber(), treeCheck.getColomnNumber());
                getRifleMove(currentTree,nextPossibleMoves.get(keyCheck));
            }
            
            return possibilities;
    }
        
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
        
    public HashMap<Check,Gameboard> getFrontCaptureMove(Gameboard gameboard){            
            int positionLine = this.getPosition().getLineNumber();
            int positionColomn = this.getPosition().getColomnNumber();
            int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
            int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
            int dest = this.getDestination();
            Check middleOption;
            Check captureOption;
            HashMap<Check,Gameboard> possibleMoves = new HashMap<>();
            Gameboard copyGameboard;
            
            switch(dest){
            case 0:
                if(positionLine<(nbGameboardLines-1)){
                    if(positionColomn>1){
                        middleOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn-1));
                        captureOption = gameboard.getCheckByLineColomn((positionLine+2),(positionColomn-2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!captureOption.isOccupied())){                     
                            copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                            copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                            possibleMoves.put(captureOption, copyGameboard);
                        }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                        middleOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                        captureOption = gameboard.getCheckByLineColomn((positionLine+2),(positionColomn+2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!captureOption.isOccupied())){
                            copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                            copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                            possibleMoves.put(captureOption, copyGameboard);
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
                                copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                                copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                                possibleMoves.put(captureOption, copyGameboard);                                
                            }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                            middleOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                            captureOption = gameboard.getCheckByLineColomn((positionLine-2),(positionColomn+2));
                            if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!captureOption.isOccupied())){
                                copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                                copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                                possibleMoves.put(captureOption, copyGameboard);
                            }
                    }
                }
            break;
            }
        return possibleMoves;
        }
        
    public HashMap<Check,Gameboard> getBackCaptureMove(Gameboard gameboard){
            int positionLine = this.getPosition().getLineNumber();
            int positionColomn = this.getPosition().getColomnNumber();
            int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
            int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
            int dest = this.getDestination();
            Check middleOption;
            Check backwardCaptureOption;
            HashMap<Check,Gameboard> possibleMoves = new HashMap<>();
            Gameboard copyGameboard;
            //ArrayList<Check> possibleMoves = new ArrayList<Check>();
            
            switch(dest){
            case 0:
                if(positionLine>1){
                    if(positionColomn>1){
                        middleOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn-1));
                        backwardCaptureOption = gameboard.getCheckByLineColomn((positionLine-2),(positionColomn-2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!backwardCaptureOption.isOccupied())){
                            copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                            copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                            possibleMoves.put(backwardCaptureOption, copyGameboard);
                        }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                        middleOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                        backwardCaptureOption = gameboard.getCheckByLineColomn((positionLine-2),(positionColomn+2));
                        if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!backwardCaptureOption.isOccupied())){
                            copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                            copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                            possibleMoves.put(backwardCaptureOption, copyGameboard);
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
                                copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                                copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                                possibleMoves.put(backwardCaptureOption, copyGameboard);
                            }
                    }             
                    if(positionColomn<(nbGameboardColomns-1)){
                            middleOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                            backwardCaptureOption = gameboard.getCheckByLineColomn((positionLine+2),(positionColomn+2));
                            if((middleOption.isOccupied())&&(middleOption.getcheckPiece().getColor()!=this.getColor())&&(!backwardCaptureOption.isOccupied())){
                                copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                                copyGameboard.getCheckByLineColomn(middleOption.getLineNumber(), middleOption.getColomnNumber()).getcheckPiece().disapear();
                                possibleMoves.put(backwardCaptureOption, copyGameboard);
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
        riffle = this.getRifleMove(rootCurrentPosition,this.getPosition().getGameboard());
        riffle.drawTree();
        ArrayList<ArrayList> longestRiffle = riffle.getLongestTreePath();
        System.out.println("Longest riffle(s) possible : ");
        int riffleNumber=1;
        int checkNumber;
        for(ArrayList<Check> currentArrayList : longestRiffle){
            System.out.println("\n-------------------------\n\nRiffle number "+riffleNumber);
            checkNumber=1;
            for(Check currentCheck : currentArrayList){
                System.out.println("Check "+checkNumber+" on line "+(currentCheck.getLineNumber()+1)+" and colomn "+ (currentCheck.getColomnNumber()+1));
                checkNumber++;
            }
            riffleNumber++;
        }
        
        Gameboard gameboardCopy = (Gameboard)DeepCopy.copy(this.getPosition().getGameboard());
     
        ArrayList<Check> possibleMoves = new ArrayList<Check>();
        possibleMoves.addAll(this.getFrontMove());
        possibleMoves.addAll(this.getFrontCaptureMove(gameboardCopy).keySet());
        possibleMoves.addAll(this.getBackCaptureMove((gameboardCopy)).keySet());

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
    public void riffleMove(ArrayList<Check> path) {
        for(Check currentCheck : path){
            this.move(currentCheck);
        }
    }
    
    public void toKing(Check arrivalCheck){
        King upgradedMan = new King(arrivalCheck,this.getColor());
        upgradedMan.setOwner(this.getOwner());
        this.die();
        arrivalCheck.setcheckPiece(upgradedMan);
        
    }


        
        

}