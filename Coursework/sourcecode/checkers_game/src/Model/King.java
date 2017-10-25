package Model;

import java.util.ArrayList;

public class King extends Piece {

    public King(Check position, String color) {
        super(position, color);
    }
    
    public ArrayList<Check> getKingCapture(){
        int positionLine = this.getPosition().getLineNumber();
        int positionColomn = this.getPosition().getColomnNumber();
        Gameboard gameboard = this.getPosition().getGameboard();
        int nbGameboardLines = (gameboard.getNbLines()-1);//Minus 1 to match array storage which starts at 0
        int nbGameboardColomns = (gameboard.getNbColomns()-1);//Minus 1 to match array storage which starts at 0
        Check currentOption;
        ArrayList<Check> possibleCaptures = new ArrayList<Check>();
        boolean isFree;

        /*Diagonal 1*/
        while(((positionLine-1)>=0)&&((positionColomn-1)>=0)){
            currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn-1));
            if(currentOption.isOccupied()){
                positionLine--;
                positionColomn--;
                if(currentOption.getcheckPiece().getColor()!=this.getColor()){
                    isFree  = true;
                    while((isFree)&&((positionLine-1)>=0)&&((positionColomn-1)>=0)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn-1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.add(currentOption);
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
                positionLine--;
                positionColomn++;
                if(currentOption.getcheckPiece().getColor()!=this.getColor()){
                    isFree  = true;                    
                    while((isFree)&&((positionLine-1)>=0)&&((positionColomn+1)<=nbGameboardColomns)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine-1),(positionColomn+1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.add(currentOption);
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
                positionLine++;
                positionColomn--;
                if(currentOption.getcheckPiece().getColor()!=this.getColor()){
                    isFree  = true;                    
                    while((isFree)&&((positionLine+1)<=nbGameboardLines)&&((positionColomn-1)>=0)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn-1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.add(currentOption);
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
                positionLine++;
                positionColomn++;
                if(currentOption.getcheckPiece().getColor()!=this.getColor()){
                    isFree  = true;                   
                    while((isFree)&&((positionLine+1)<=nbGameboardLines)&&((positionColomn+1)<=nbGameboardColomns)){
                        currentOption = gameboard.getCheckByLineColomn((positionLine+1),(positionColomn+1));
                        if(currentOption.isOccupied()){
                            isFree=false;
                        }else{
                            possibleCaptures.add(currentOption);
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
        riffle = this.getRifleMove(rootCurrentPosition);
        riffle.drawTree();
        ArrayList<Check> longestRiffle = riffle.getLongestTreePath();
        System.out.println("Longest riffle possible : ");
        int checkNumber = 1;
        for(Check currentCheck : longestRiffle){
            System.out.println("Check "+checkNumber+" on line "+(currentCheck.getLineNumber()+1)+" and colomn "+ (currentCheck.getColomnNumber()+1));
            checkNumber++;

        }
        System.out.print(this.getPosition());
        this.getPosition().getGameboard().drawGameboard();
     
       /* ArrayList<Check> possibleMoves = new ArrayList<Check>();
        possibleMoves.addAll(this.getKingMove());*/

        return null;
    }        

    @Override
    public void move(Check arrival) {
        
    }
    
    @Override
    public Tree<Check> getRifleMove(Tree<Check> possibilities){
       // System.out.println("\nROOT of check line "+(possibilities.getData().getLineNumber()+1)+" and colomn "+(possibilities.getData().getColomnNumber()+1));
            King fakeKingForResult = new King(possibilities.getData(),this.getColor());
            ArrayList<Check> nextPossibleMoves = new ArrayList<Check>();
            ArrayList<Check> possiblePreviousPosition = new ArrayList<>();
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
            }

            for(Check currentCheck : possiblePreviousPosition){
               // System.err.println("Line "+(currentCheck.getLineNumber()+1)+" colomn "+ (currentCheck.getColomnNumber()+1));
            }
            nextPossibleMoves.addAll(fakeKingForResult.getKingCapture());
            fakeKingForResult = null;
            Tree<Check> newChildTree;
            
            for(Check currentCheck : nextPossibleMoves){
                //System.out.println("CHILD of check line "+(currentCheck.getLineNumber()+1)+" and colomn "+(currentCheck.getColomnNumber()+1));

                if((possiblePreviousPosition.isEmpty())||(!possiblePreviousPosition.contains(currentCheck))){
                    newChildTree = new Tree<Check>(currentCheck);
                    possibilities.addChild(newChildTree);
                }           
            }
            for(Tree currentTree : possibilities.getChildren()){
                getRifleMove(currentTree);
            }
            
            return possibilities;
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
            case 2:
                while(((currentLine-1)>=limitLine)&&((currentColomn+1)<=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine-1), (currentColomn+1));
                    result.add(currentOption);
                    currentLine--;
                    currentColomn++;
                }
            case 3:
                while(((currentLine+1)<=limitLine)&&((currentColomn-1)>=limitColomn)){
                    currentOption = gameboard.getCheckByLineColomn((currentLine+1), (currentColomn-1));
                    result.add(currentOption);
                    currentLine++;
                    currentColomn--;
                }
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
    
   /* @Override
    public void die(){
        this.getPosition().setcheckPiece(null);
        this.getOwner().deletePiece(this);
        this.setOwner(null);
        this.setPosition(null);
    }*/
}