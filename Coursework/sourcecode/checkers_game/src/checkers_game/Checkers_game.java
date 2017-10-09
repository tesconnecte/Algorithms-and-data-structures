/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers_game;
import Model.*;
import java.util.ArrayList;

/**
 *
 * @author alexi_000
 */
public class Checkers_game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(" Checkers Game / Le jeu de dames\n_________________________________\n");
        Gameboard mainGameboard = new Gameboard();
        Human me = new Human("Alexis");
        AI computer = new AI();
        mainGameboard.drawGameboard();
        ArrayList<Piece> whitePieces = mainGameboard.getPiecesByColor("white");
        ArrayList<Piece> blackPieces = mainGameboard.getPiecesByColor("black");
        
        for(Piece currentPiece: blackPieces){
            currentPiece.setOwner(computer);
            computer.addPiece(currentPiece);
        }
        
        for(Piece currentPiece: whitePieces){
            currentPiece.setOwner(me);
            me.addPiece(currentPiece);
        }
        
        Piece test1 = mainGameboard.getCheckByLineColomn(6, 1).getcheckPiece();
        Piece test2 = mainGameboard.getCheckByLineColomn(7, 2).getcheckPiece();
        
        if(test1 instanceof Man){
            ArrayList<Check> test1moves=test1.getPossibleMoves();
            
            
            test1.move(test1moves.get(0));
            mainGameboard.drawGameboard();
            
            test1moves=test2.getPossibleMoves();
            for(Check currentMove : test1moves){
                System.out.println(" L : "+(currentMove.getLineNumber()+1)+" | C : "+(currentMove.getColomnNumber()+1));
            }
            
            /*
            test1moves=test1.getPossibleMoves();
            for(Check currentMove : test1moves){
                System.out.println(" L : "+(currentMove.getLineNumber()+1)+" | C : "+(currentMove.getColomnNumber()+1));
            }
            
            test1.move(test1moves.get(0));
            mainGameboard.drawGameboard();*/
            
        }
        
        
    }
    
}
