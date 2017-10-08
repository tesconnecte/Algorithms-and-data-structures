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
        mainGameboard.drawGameboard();
        
        /*Piece test1 = mainGameboard.getCheckByLineColomn(3, 0).getcheckPiece();
        Piece test2 = mainGameboard.getCheckByLineColomn(6, 1).getcheckPiece();
        
        if(test1 instanceof Man){
            ArrayList<Check> test1moves=test1.getPossibleMoves();
            for(Check currentMove : test1moves){
                System.out.println(test1.);
            }
            
        }*/
        //ArrayList<Check> test1Checks = test1.getP
    }
    
}
