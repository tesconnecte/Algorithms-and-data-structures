/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.rmi.activation.ActivationGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author alexi_000
 */
public class Game implements Serializable{
    private Gameboard gameboard;
    private Player playerOne;
    private Player playerTwo;
    private LinkedList<Player> currentPlayer;
    private LinkedList<Game> gameUndoHistory;
    private LinkedList<Game> gameRedoHistory;
    boolean gameIsOver;

    public Game(Gameboard gameboard, Player playerOne, Player playerTwo) {
        this.gameboard = gameboard;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;        
        currentPlayer = new LinkedList<Player>();
        gameUndoHistory = new LinkedList<Game>();
        gameRedoHistory = new LinkedList<Game>();
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
        gameUndoHistory.add(null);
        gameRedoHistory.add(null);
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

    public void setGameboard(Gameboard gameboard) {
        this.gameboard = gameboard;
    }

    private void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    private void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }   
    

    public Player getCurrentPlayer() {
        if(this.getPlayerOne().getName().equals(currentPlayer.getLast().getName())){
            return this.getPlayerOne();
        } else {
           return this.getPlayerTwo();
        }
        
    }
    
    public void nextPlayer(){
        if(this.getCurrentPlayer().getName().equals(playerOne.getName())){
            currentPlayer.add(playerTwo);
        } else {
            currentPlayer.add(playerOne);
        }
    }
    
    public void addGameboardHistory(){
        gameUndoHistory.add((Game)DeepCopy.copy(this));
    }
    
    public Game getPreviousGame(){
        if(this.gameUndoHistory.getLast()!=null){
            this.gameRedoHistory.add(this.gameUndoHistory.getLast());
            return this.gameUndoHistory.getLast();
        }else{
            return null;
        }
        
    }
    
    public Game getNextGame(){
        if(this.gameUndoHistory.getLast()!=null){
            this.gameUndoHistory.add(this.gameRedoHistory.getLast());
            return this.gameRedoHistory.getLast();
        }else{
            return null;
        }
    }
    
    public LinkedList<Player> getCurrentListPlayer(){
        return this.currentPlayer;
    }

    public void setCurrentPlayer(LinkedList<Player> currentPlayer) {
        this.currentPlayer = currentPlayer;
    }    
    

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }
    
    
    
    
    
    
    
    
}
