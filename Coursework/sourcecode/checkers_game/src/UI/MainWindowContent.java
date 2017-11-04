/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Check;
import Model.Game;
import Model.King;
import Model.Piece;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.w3c.dom.events.MouseEvent;
import sun.print.resources.serviceui;

/**
 *
 * @author alexi
 */
public class MainWindowContent extends JPanel implements MouseListener{
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;
    private Rectangle2D[][] graphicalChecks;
    int size = 900;
    int offset = 15;
    int subLenght = size / 10;

    public MainWindowContent(Game game) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        graphicalChecks = new Rectangle2D[10][10];
        blackPieces = game.getPlayerOne().getPieces();
        whitePieces = game.getPlayerTwo().getPieces();
        addMouseListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size + offset * 2, size + offset * 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int totalLenght = size;
        
        // Paint the board

        //g.setColor(Color.BLUE);
        //g.fillRect(offset, offset, (totalLenght+50), (totalLenght+50));
       // g.setColor(Color.DARK_GRAY);
        Rectangle2D check;
        Graphics2D g2d = (Graphics2D) g;
        //g.drawRect(offset, offset, totalLenght, totalLenght);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                int ope = x + (y & 1);
                if ((ope & 1) != 0) {
                    g.setColor(Color.DARK_GRAY);
                    check = new Rectangle2D.Double(x * subLenght + offset, y * subLenght + offset, subLenght, subLenght);
                    //g.fillRect(x * subLenght + offset, y * subLenght + offset, subLenght, subLenght);
                    
                    graphicalChecks[y][x]=(check);
                    g2d.fill(check);
                }else{
                    g.setColor(Color.WHITE);
                    check = new Rectangle2D.Double(x * subLenght + offset, y * subLenght + offset, subLenght, subLenght);
                    
                    graphicalChecks[y][x]=(check);
                    g2d.fill(check);
                    
                }
                //g.drawRect(x * subLenght + offset, y * subLenght + offset, subLenght, subLenght);
                
            }
        }

        for (int i=0;i<blackPieces.size();i++) {
            g.setColor(Color.BLACK);
            g.fillOval(blackPieces.get(i).getPosition().getColomnNumber() * subLenght + (offset*2) , blackPieces.get(i).getPosition().getLineNumber() * subLenght + (offset*2) , 60, 60);
            
            if (blackPieces.get(i) instanceof King) {
                g.setColor(Color.ORANGE);
                g.fillOval(blackPieces.get(i).getPosition().getColomnNumber() * subLenght + offset , blackPieces.get(i).getPosition().getLineNumber() * subLenght + offset * 4, subLenght - offset * 6, subLenght - offset * 6);
            }
        }
        
        for (int i=0;i<whitePieces.size();i++) {
            g.setColor(Color.WHITE);
            g.fillOval(whitePieces.get(i).getPosition().getColomnNumber() * subLenght + (offset*2) , whitePieces.get(i).getPosition().getLineNumber() * subLenght + (offset*2) , 60, 60);

            if (whitePieces.get(i) instanceof King) {
                g.setColor(Color.ORANGE);
                g.fillOval((whitePieces.get(i).getPosition().getColomnNumber()+1) * subLenght + offset * 4, whitePieces.get(i).getPosition().getLineNumber() * subLenght + offset * 4, subLenght - offset * 6, subLenght - offset * 6);
            }
        }
    }
    
    public void yellowOriginalColorFlash(Rectangle2D rectangle, int lineNumber, int colomnNumber){
        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;        
        g.setColor(Color.YELLOW);
        rectangle.setRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        g2d.fill(rectangle);
        g.setColor(Color.BLACK);
        g.fillOval(colomnNumber * subLenght + (offset*2) , lineNumber * subLenght + (offset*2) , 60, 60);
        
    }
    
    public void showSelectableCheck(ArrayList<Check> possibilities){
        Check currentCheck;
        int currentLine;
        int currentColomn;
        Rectangle2D currentRec ;
        for(int i=0;i<possibilities.size();i++){
            currentCheck=possibilities.get(i);
            currentLine=currentCheck.getLineNumber();
            currentColomn=currentCheck.getColomnNumber();
            currentRec=graphicalChecks[currentLine][currentColomn];
            this.yellowOriginalColorFlash(currentRec,currentLine,currentColomn);            
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

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getButton() == 1) {
            int checkLine = -1;
            int checkColomn = -1;
            Rectangle2D currentRectangle;
            for(int i = 0; i<graphicalChecks.length;i++){
                for(int j=0; j<graphicalChecks.length;j++){
                    currentRectangle = graphicalChecks[i][j];
                    if(currentRectangle.contains(e.getX(), e.getY())){
                    checkLine=i;
                    checkColomn= j;    
                    }
                }              
            }
            if((checkLine!=-1)&&(checkColomn!=-1)){
                JOptionPane.showMessageDialog(null,"Clicked check line "+(checkLine+1)+ " colomn "+(checkColomn+1)); 
            }
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
}
