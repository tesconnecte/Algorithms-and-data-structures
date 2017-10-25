package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class King extends Piece implements Serializable{

    public King(Check position, String color) {
        super(position, color);
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
            King currentKing;
            if(possibilities.getParent()!=null){
                previousCheckOnOldGameboard = (Check)possibilities.getParent().getData();
                previousCheckOnNewGameboard = copyGameboard.getCheckByLineColomn(previousCheckOnOldGameboard.getLineNumber(), previousCheckOnOldGameboard.getColomnNumber());
                /*On the new copied gameboard, we copy the King to its new position*/
                previousCheckOnNewGameboard.getcheckPiece().setPosition(currentCheck);
                currentCheck.setcheckPiece(previousCheckOnNewGameboard.getcheckPiece());
                previousCheckOnNewGameboard.setcheckPiece(null);
                currentKing = (King)currentCheck.getcheckPiece();
            }else {//1st case, King does not need to be moved
                currentKing = (King)currentCheck.getcheckPiece();
            }
            
            HashMap<Check,Gameboard> nextPossibleMoves = new HashMap<Check,Gameboard>();
            /*ArrayList<Check> possiblePreviousPosition = new ArrayList<>();
            if(possibilities.getParent()!=null){
                Check parentCheck = (Check)possibilities.getParent().getData();
                Check thisCheck = (Check)possibilities.getData();
                int offsetLine = thisCheck.getLineNumber()-parentCheck.getLineNumber();
                int offsetColomn = thisCheck.getColomnNumber()-parentCheck.getColomnNumber();
                if(offsetLine>0){
                    if(offsetColomn>0){
                        possiblePreviousPosition.addAll(fakeKingForResult.getIntervalCheck(1, parentCheck));
                    }else if (offsetColomn<0){
                        possiblePreviousPosition.addAll(fakeKingForResult.getIntervalCheck(2, parentCheck));
                    }
                } else if (offsetLine<0){
                    if(offsetColomn>0){
                        possiblePreviousPosition.addAll(fakeKingForResult.getIntervalCheck(3, parentCheck));
                    }else if (offsetColomn<0){
                        possiblePreviousPosition.addAll(fakeKingForResult.getIntervalCheck(4, parentCheck));
                    }
                }
            }*/

            /*for(Check currentCheck : possiblePreviousPosition){
               // System.err.println("Line "+(currentCheck.getLineNumber()+1)+" colomn "+ (currentCheck.getColomnNumber()+1));
            }*/
            nextPossibleMoves.putAll(currentKing.getKingCapture(copyGameboard));
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
    
    public HashMap<Check,Gameboard> getKingCapture(Gameboard gameboard){
        int positionLine = this.getPosition().getLineNumber();
        int positionColomn = this.getPosition().getColomnNumber();
        int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
        int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
        Check currentOption;
        Piece adversaryPiece;
        HashMap<Check,Gameboard> possibleCaptures = new HashMap<Check,Gameboard>();
        Gameboard copyGameboard;
        boolean isFree;

        /*Diagonal 1*/
        while(((positionLine-1)>=0)&&((positionColomn-1)>=0)){
            currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn-1));
            if(currentOption.isOccupied()){
                adversaryPiece = currentOption.getcheckPiece();
                positionLine--;
                positionColomn--;
                if(adversaryPiece.getColor()!=this.getColor()){
                    copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                    copyGameboard.getCheckByLineColomn(currentOption.getLineNumber(), currentOption.getColomnNumber()).getcheckPiece().disapear();
                    isFree  = true;
                    while((isFree)&&((positionLine-1)>=0)&&((positionColomn-1)>=0)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn-1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.put(currentOption, copyGameboard);

                        }
                        positionLine--;
                        positionColomn--;
                    }
                    break;
                }
            }
            positionLine--;
            positionColomn--;
        }
        
        positionLine = this.getPosition().getLineNumber();
        positionColomn = this.getPosition().getColomnNumber();
        
        /*Diagonal 2*/
        while(((positionLine-1)>=0)&&((positionColomn+1)<=nbGameboardColomns)){
            currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn+1));
            if(currentOption.isOccupied()){
                adversaryPiece = currentOption.getcheckPiece();                
                positionLine--;
                positionColomn++;
                if(adversaryPiece.getColor()!=this.getColor()){
                    copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                    copyGameboard.getCheckByLineColomn(currentOption.getLineNumber(), currentOption.getColomnNumber()).getcheckPiece().disapear();
                    isFree  = true;                    
                    while((isFree)&&((positionLine-1)>=0)&&((positionColomn+1)<=nbGameboardColomns)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.put(currentOption, copyGameboard);
                        }
                        positionLine--;
                        positionColomn++;
                    }
                    break;
                }
            }
            positionLine--;
            positionColomn++;
        }
        
        positionLine = this.getPosition().getLineNumber();
        positionColomn = this.getPosition().getColomnNumber();
        
        /*Diagonal 3*/
        while(((positionLine+1)<=nbGameboardLines)&&((positionColomn-1)>=0)){
            currentOption = gameboard.getCheckByLineColomn((positionLine+1), (positionColomn-1));
            if(currentOption.isOccupied()){
                adversaryPiece = currentOption.getcheckPiece();              
                positionLine++;
                positionColomn--;
                if(adversaryPiece.getColor()!=this.getColor()){
                    copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                    copyGameboard.getCheckByLineColomn(currentOption.getLineNumber(), currentOption.getColomnNumber()).getcheckPiece().disapear();
                    isFree  = true;                    
                    while((isFree)&&((positionLine+1)<=nbGameboardLines)&&((positionColomn-1)>=0)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn-1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.put(currentOption, copyGameboard);
                        }
                        positionLine++;
                        positionColomn--;
                    }
                    break;
                }
            }
            positionLine++;
            positionColomn--;
        }
        
        isFree = true;
        positionLine = this.getPosition().getLineNumber();
        positionColomn = this.getPosition().getColomnNumber();
        
        /*Diagonal 4*/
        while((isFree)&&((positionLine+1)<=nbGameboardLines)&&((positionColomn+1)<=nbGameboardColomns)){
            currentOption = gameboard.getCheckByLineColomn((positionLine+1), (positionColomn+1));
            if(currentOption.isOccupied()){
                adversaryPiece = currentOption.getcheckPiece();          
                positionLine++;
                positionColomn++;
                if(adversaryPiece.getColor()!=this.getColor()){
                    copyGameboard = (Gameboard)DeepCopy.copy(gameboard);
                    copyGameboard.getCheckByLineColomn(currentOption.getLineNumber(), currentOption.getColomnNumber()).getcheckPiece().disapear();
                    isFree  = true;                   
                    while((isFree)&&((positionLine+1)<=nbGameboardLines)&&((positionColomn+1)<=nbGameboardColomns)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.put(currentOption, copyGameboard);
                        }
                        positionLine++;
                        positionColomn++;
                    }
                    break;
                }
            }
            positionLine++;
            positionColomn++;
        }       

        return possibleCaptures;
    }
    
    public ArrayList<Check> getKingMove(){
        int positionLine = this.getPosition().getLineNumber();
        int positionColomn = this.getPosition().getColomnNumber();
        Gameboard gameboard = this.getPosition().getGameboard();
        int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
        int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
        Check currentOption;
        boolean isFree  = true;
        ArrayList<Check> possibleMoves = new ArrayList<Check>();

        /*Diagonal 1*/
        while((isFree)&&((positionLine-1)>=0)&&((positionColomn-1)>=0)){
            currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn-1));
            if(currentOption.isOccupied()){
                isFree = false;
            } else {
                possibleMoves.add(currentOption);
            }
            positionLine--;
            positionColomn--;
        }
        
        isFree = true;
        positionLine = this.getPosition().getLineNumber();
        positionColomn = this.getPosition().getColomnNumber();
        
        /*Diagonal 2*/
        while((isFree)&&((positionLine-1)>=0)&&((positionColomn+1)<=nbGameboardColomns)){
            currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn+1));
            if(currentOption.isOccupied()){
                isFree = false;
            } else {
                possibleMoves.add(currentOption);
            }
            positionLine--;
            positionColomn++;
        }
        
        isFree = true;
        positionLine = this.getPosition().getLineNumber();
        positionColomn = this.getPosition().getColomnNumber();
        
        /*Diagonal 3*/
        while((isFree)&&((positionLine+1)<=nbGameboardLines)&&((positionColomn-1)>=0)){
            currentOption = gameboard.getCheckByLineColomn((positionLine+1), (positionColomn-1));
            if(currentOption.isOccupied()){
                isFree = false;
            } else {
                possibleMoves.add(currentOption);
            }
            positionLine++;
            positionColomn--;
        }
        
        isFree = true;
        positionLine = this.getPosition().getLineNumber();
        positionColomn = this.getPosition().getColomnNumber();
        
        /*Diagonal 4*/
        while((isFree)&&((positionLine+1)<=nbGameboardLines)&&((positionColomn+1)<=nbGameboardColomns)){
            currentOption = gameboard.getCheckByLineColomn((positionLine+1), (positionColomn+1));
            if(currentOption.isOccupied()){
                isFree = false;
            } else {
                possibleMoves.add(currentOption);
            }
            positionLine++;
            positionColomn++;
        }       

        return possibleMoves;
    }
    
    @Override
    public ArrayList<Check> getPossibleMoves() {
        Tree<Check> rootCurrentPosition = new Tree<Check>(this.getPosition());
        Tree<Check> riffle;
        Gameboard gameboardcopy = new Gameboard();
        gameboardcopy=this.getPosition().getGameboard();
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
        
        this.getPosition().getGameboard().drawGameboard();

        return null;
    }        

    @Override
    public void move(Check arrival) {
        Gameboard gameboard = this.getPosition().getGameboard();
        int currentLine = this.getPosition().getLineNumber();
        int currentColomn = this.getPosition().getColomnNumber();
        int limitLine = arrival.getLineNumber();
        int limitColomn = arrival.getColomnNumber();
        Check currentOption;
        int offsetLine = limitLine-currentLine;
        int offsetColomn = limitColomn-currentColomn;
        int diagonal;
        
        if((offsetLine>0)&&(offsetColomn>0)){
            diagonal = 1;
        } else if((offsetLine>0)&&(offsetColomn<0)){
            diagonal = 2;
        } else if ((offsetLine<0)&&(offsetColomn>0)){
            diagonal = 3;
        } else {
            diagonal = 4;
        }
        
        
        switch(diagonal){
            case 1:
                while(((currentLine-1)>=limitLine)&&((currentColomn-1)>=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine-1), (currentColomn-1));
                    if(currentOption.isOccupied()){
                        currentOption.getcheckPiece().die();
                    }
                    currentLine--;
                    currentColomn--;
                }
            break;   
            
            case 2:
                while(((currentLine-1)>=limitLine)&&((currentColomn+1)<=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine-1), (currentColomn+1));
                    if(currentOption.isOccupied()){
                        currentOption.getcheckPiece().die();
                    }
                    currentLine--;
                    currentColomn++;
                }
            break;
            
            case 3:
                while(((currentLine+1)<=limitLine)&&((currentColomn-1)>=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine+1), (currentColomn-1));
                    if(currentOption.isOccupied()){
                        currentOption.getcheckPiece().die();
                    }
                    currentLine++;
                    currentColomn--;
                }
            break;
            
            case 4:
                while(((currentLine+1)<=limitLine)&&((currentColomn+1)<=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine+1), (currentColomn+1));
                    if(currentOption.isOccupied()){
                        currentOption.getcheckPiece().die();
                    }
                    currentLine++;
                    currentColomn++;
                }    
            break;
        }        
        this.getPosition().setcheckPiece(null);
        this.setPosition(arrival);
        arrival.setcheckPiece(this);
    }
    
    @Override
    public void riffleMove(ArrayList<Check> path) {
        for(Check currentCheck : path){
            this.move(currentCheck);
        }
    }
    
    public ArrayList<Check> getIntervalCheck(int diagonal,Check limit){
        int currentLine = this.getPosition().getLineNumber();
        int currentColomn = this.getPosition().getColomnNumber();
        int limitLine = limit.getLineNumber();
        int limitColomn = limit.getColomnNumber();
        Check currentOption;
        Gameboard gameboard = this.getPosition().getGameboard();
        ArrayList<Check> result = new ArrayList<>();
    
        switch(diagonal){
            case 1:
                while(((currentLine-1)>=limitLine)&&((currentColomn-1)>=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine-1), (currentColomn-1));
                    result.add(currentOption);
                    currentLine--;
                    currentColomn--;
                }
            break;   
            
            case 2:
                while(((currentLine-1)>=limitLine)&&((currentColomn+1)<=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine-1), (currentColomn+1));
                    result.add(currentOption);
                    currentLine--;
                    currentColomn++;
                }
            break;
            
            case 3:
                while(((currentLine+1)<=limitLine)&&((currentColomn-1)>=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine+1), (currentColomn-1));
                    result.add(currentOption);
                    currentLine++;
                    currentColomn--;
                }
            break;
            
            case 4:
                while(((currentLine+1)<=limitLine)&&((currentColomn+1)<=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine+1), (currentColomn+1));
                    result.add(currentOption);
                    currentLine++;
                    currentColomn++;
                }    
            break;
        }
        
        return result;
    }

    
}