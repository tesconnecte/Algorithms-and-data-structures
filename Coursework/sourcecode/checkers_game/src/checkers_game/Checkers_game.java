/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers_game;
import Model.*;
import UI.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        Human playerOne = new Human("Alexis");
        AI playerTwo = new AI();
        Game game = new Game(mainGameboard,playerOne,playerTwo);
        JFrame mainWindow = new JFrame();
        mainWindow.add(new MainWindowAllContent(game));
        mainWindow.pack();
        mainWindow.setVisible(true);
        
        Player currentPlayer;
        
        while(!game.isGameIsOver()){            
            mainGameboard.drawGameboard();
            currentPlayer = game.getCurrentPlayer();                        
            currentPlayer.playOnce();
            game.addGameboardHistory();
            game.nextPlayer();
            
            
            
            if((playerOne.getPieces().isEmpty())||(playerTwo.getPieces().isEmpty())){
                game.setGameIsOver(true);
                System.out.println("END OF THE GAME");                
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
