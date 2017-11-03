/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alexi
 */
public class MainWindowOtherContent extends JPanel {
    
    public MainWindowOtherContent() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel player = new JLabel("Alexis");
        JLabel infoColor = new JLabel("You are playing with white pieces");
        JLabel infoPion = new JLabel("You have 20 pieces left");
        JButton save = new JButton("Save the game");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JButton undo = new JButton("Undo last move");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JButton redo = new JButton("Redo last move");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        this.add(player);
        this.add(infoColor);
        this.add(infoPion);
        this.add(save);
        this.add(undo);
        this.add(redo);
    }
    
    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int totalLenght = 1000;
        int subLenght = totalLenght / 8;
        // Paint the board

        g.setColor(Color.WHITE);
        g.fillRect(100, 100, totalLenght, totalLenght);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(100, 100, totalLenght, totalLenght);
    }*/
    
}
