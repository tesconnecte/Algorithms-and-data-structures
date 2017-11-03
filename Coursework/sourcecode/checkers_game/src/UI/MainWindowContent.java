/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Game;
import Model.King;
import Model.Piece;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author alexi
 */
public class MainWindowContent extends JPanel{
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;
    int size = 900;
    int offset = 50;

    public MainWindowContent(Game game) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        blackPieces = game.getPlayerOne().getPieces();
        whitePieces = game.getPlayerTwo().getPieces();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size + offset * 2, size + offset * 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int totalLenght = size;
        int subLenght = totalLenght / 10;
        // Paint the board

        g.setColor(Color.WHITE);
        g.fillRect(offset, offset, totalLenght, totalLenght);
        g.setColor(Color.DARK_GRAY);
        //g.drawRect(offset, offset, totalLenght, totalLenght);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                int ope = x + (y & 1);
                if ((ope & 1) != 0) {
                    g.fillRect(x * subLenght + offset, y * subLenght + offset, subLenght, subLenght);
                }
                g.drawRect(x * subLenght + offset, y * subLenght + offset, subLenght, subLenght);
            }
        }

        for (Piece currentPiece : blackPieces) {
            g.setColor(Color.CYAN);
            System.out.println((currentPiece.getPosition().getLineNumber()+1) * subLenght + offset * 2);
            g.fillOval((currentPiece.getPosition().getLineNumber()+1) * subLenght + offset , currentPiece.getPosition().getColomnNumber() * subLenght + offset * 2, subLenght - offset * 2, subLenght - offset * 2);

            if (currentPiece instanceof King) {
                g.setColor(Color.ORANGE);
                g.fillOval(currentPiece.getPosition().getLineNumber() * subLenght + offset , currentPiece.getPosition().getColomnNumber() * subLenght + offset * 4, subLenght - offset * 6, subLenght - offset * 6);
            }
        }
        
        for (Piece currentPiece : blackPieces) {
            g.setColor(Color.BLACK);
            g.fillOval(currentPiece.getPosition().getLineNumber() * subLenght + offset * 2, currentPiece.getPosition().getColomnNumber() * subLenght + offset * 2, subLenght - offset * 2, subLenght - offset * 2);

            if (currentPiece instanceof King) {
                g.setColor(Color.ORANGE);
                g.fillOval(currentPiece.getPosition().getLineNumber() * subLenght + offset * 4, currentPiece.getPosition().getColomnNumber() * subLenght + offset * 4, subLenght - offset * 6, subLenght - offset * 6);
            }
        }
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public int getOffset() {
        return offset;
    }
    
}
