package Model;

import java.util.*;

public abstract class Player {
    
    protected ArrayList<Piece> pieces;
    protected String name;

    public Player(String name) {
        this.name = name;
        this.pieces = new ArrayList<Piece>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    
    public void addPiece(Piece piece){
        this.pieces.add(piece);
    }

    public void deletePiece(Piece piece){
        int index = this.getPieces().indexOf(piece);
        if(index!=-1){
           this.getPieces().remove(index);
        }
    }
    
    public Check chooseNextMove(ArrayList<Check> possibleMoves){
        Scanner keyboardUSER = new Scanner(System.in);
        int choice=0;
        int count=1;
        boolean rightChoice=false;
        System.out.println("Choose the next Check :");
        for(Check currentCheck : possibleMoves){
            System.out.println(count+") Line: "+currentCheck.getLineNumber()+ " | Colomn: "+currentCheck.getColomnNumber());
            count++;
        }
        while(!rightChoice){
            System.out.println("Select an option above from 1 to "+count);
            choice=(keyboardUSER.nextInt()-1);
            if((choice>=1)&&(choice<count)){
                rightChoice=true;
            } else {
                System.out.println("Wrong choice try again between 1 and "+count);
            }
        }
        
        return possibleMoves.get(choice);
        
    }

	
	

}