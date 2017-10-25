/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers_game;
import Model.*;
import java.util.ArrayList;
import java.util.Scanner;

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
        final Gameboard mainGameboard = new Gameboard();
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
        
        boolean hasWon = false;
        Scanner keyboard = new Scanner(System.in);
        int line;
        int colomn;
        Piece currentPiece;
        ArrayList<Check> possiblemoves;
        Check destination;
        
        while(!hasWon){
            System.out.println(computer.getName()+"'s turn. Select the piece you wan to move");
            System.out.print("Piece's line : ");
            line = (keyboard.nextInt()-1);
            System.out.print("Piece's colomn : ");
            colomn = (keyboard.nextInt()-1);
            currentPiece=mainGameboard.getCheckByLineColomn(line, colomn).getcheckPiece();
            possiblemoves=currentPiece.getPossibleMoves();
            destination = computer.chooseNextMove(possiblemoves);
            currentPiece.move(destination);
            
            mainGameboard.drawGameboard();
            
            System.out.println(me.getName()+"'s turn. Select the piece you wan to move");
            System.out.print("Piece's line : ");
            line = (keyboard.nextInt()-1);
            System.out.print("Piece's colomn : ");
            colomn = (keyboard.nextInt()-1);
            currentPiece=mainGameboard.getCheckByLineColomn(line, colomn).getcheckPiece();
            possiblemoves=currentPiece.getPossibleMoves();
            destination = computer.chooseNextMove(possiblemoves);
            currentPiece.move(destination);
            
            mainGameboard.drawGameboard();
            /*System.out.println(computer.getName()+"'s turn. Select the piece you wan to move");
            System.out.print("Piece's line : ");
            line = (keyboard.nextInt()-1);
            System.out.print("Piece's colomn : ");
            colomn = (keyboard.nextInt()-1);
            currentPiece=mainGameboard.getCheckByLineColomn(line, colomn).getcheckPiece();
            possiblemoves=currentPiece.getPossibleMoves();
            System.out.println("Possible moves for this piece :");
            for(Check possibleCheck : possiblemoves){
                System.out.println("Line : "+(possibleCheck.getLineNumber()+1)+" | Colomn : "+(possibleCheck.getColomnNumber()+1));
            }
            System.out.print("New Check's line : ");
            line = (keyboard.nextInt()-1);
            System.out.print("New Check's colomn : ");
            colomn = (keyboard.nextInt()-1);
            destination = mainGameboard.getCheckByLineColomn(line, colomn);
            currentPiece.move(destination);
            mainGameboard.drawGameboard();
            /*
            Player Change
            *//*
            System.out.println(me.getName()+"'s turn. Select the piece you wan to move");
            System.out.print("Piece's line : ");
            line = (keyboard.nextInt()-1);
            System.out.print("Piece's colomn : ");
            colomn = (keyboard.nextInt()-1);
            currentPiece=mainGameboard.getCheckByLineColomn(line, colomn).getcheckPiece();
            possiblemoves=currentPiece.getPossibleMoves();
            System.out.println("Possible moves for this piece :");
            for(Check possibleCheck : possiblemoves){
                System.out.println("Line : "+(possibleCheck.getLineNumber()+1)+" | Colomn : "+(possibleCheck.getColomnNumber()+1));
            }
            System.out.print("New Check's line : ");
            line = (keyboard.nextInt()-1);
            System.out.print("New Check's colomn : ");
            colomn = (keyboard.nextInt()-1);
            destination = mainGameboard.getCheckByLineColomn(line, colomn);
            currentPiece.move(destination);
            mainGameboard.drawGameboard();*/
            
            
            if((computer.getPieces().isEmpty())||(me.getPieces().isEmpty())){
                System.out.println("END OF THE GAME");
                hasWon=true;
            }
                   
            
        }
        
       /* Piece test1 = mainGameboard.getCheckByLineColomn(6, 1).getcheckPiece();
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
            mainGameboard.drawGameboard();
            
        }*/
        
        
    }
    
}
