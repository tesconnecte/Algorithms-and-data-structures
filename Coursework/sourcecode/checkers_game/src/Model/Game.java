/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.rmi.activation.ActivationGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author alexi_000
 */
public class Game {
    private Gameboard gameboard;
    private Player playerOne;
    private Player playerTwo;
    private LinkedList<Player> currentPlayer;
    private LinkedList<HashMap> gameHistory;
    boolean gameIsOver;

    public Game(Gameboard gameboard, Player playerOne, Player playerTwo) {
        this.gameboard = gameboard;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;        
        currentPlayer = new LinkedList<Player>();
        gameHistory = new LinkedList<HashMap>();
        this.gameIsOver = false;
        
        ArrayList<Piece> whitePieces = gameboard.getPiecesByColor("white");
        ArrayList<Piece> blackPieces = gameboard.getPiecesByColor("black");
        
        for(Piece currentPiece: blackPieces){
            currentPiece.setOwner(playerOne);
            playerOne.addPiece(currentPiece);
        }
        
        for(Piece currentPiece: whitePieces){
            currentPiece.setOwner(playerTwo);
            playerTwo.addPiece(currentPiece);
        }
        
        currentPlayer.add((Player)DeepCopy.copy(playerOne));
        HashMap<Gameboard,Player> initialisationState = new HashMap<Gameboard,Player>();
        initialisationState.put((Gameboard)DeepCopy.copy(gameboard), null);
        gameHistory.add(initialisationState);
    }

    public Gameboard getGameboard() {
        return gameboard;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    private void setGameboard(Gameboard gameboard) {
        this.gameboard = gameboard;
    }

    private void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    private void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }   
    

    public Player getCurrentPlayer() {
        return currentPlayer.getFirst();
    }

    public HashMap<Gameboard,Player> getCurrentGameboardHistory() {
        return gameHistory.getLast();
    }
    
    public void nextPlayer(){
        if(this.getCurrentPlayer().getName()==playerOne.getName()){
            currentPlayer.add(playerTwo);
        } else {
            currentPlayer.add(playerOne);
        }
    }
    
    public void addGameboardHistory(){
        Gameboard copyGameboard = (Gameboard)DeepCopy.copy(this.getGameboard());
        Player copyPlayer = (Player)DeepCopy.copy(this.getCurrentPlayer());
        HashMap<Gameboard,Player> newGameHistory = new HashMap<Gameboard,Player>();
        newGameHistory.put(copyGameboard, copyPlayer);
        gameHistory.add(newGameHistory);
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }
    
    
    
    
    
    
    
    
}
