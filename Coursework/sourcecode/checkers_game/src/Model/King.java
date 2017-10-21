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
            currentOption = gameboard.getCheckByLineColomn((positionLine-1), (positionColomn-1));
            if(currentOption.isOccupied()){
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
                if(currentOption.getcheckPiece().getColor()!=this.getColor()){
                    isFree  = true;
                    while((isFree)&&((positionLine+1)>=0)&&((positionColomn-1)<=nbGameboardColomns)){
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
                if(currentOption.getcheckPiece().getColor()!=this.getColor()){
                    isFree  = true;
                    while((isFree)&&((positionLine+1)>=0)&&((positionColomn+1)<=nbGameboardColomns)){
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
     return null;
    }        

    @Override
    public void move(Check arrival) {
        
    }
    
    @Override
    public Tree<Check> getRifleMove(Tree<Check> possibilities){
            
        return null;
    }
    
    @Override
    public void die(){
        this.getPosition().setcheckPiece(null);
        this.getOwner().deletePiece(this);
        this.setOwner(null);
        this.setPosition(null);
    }
}