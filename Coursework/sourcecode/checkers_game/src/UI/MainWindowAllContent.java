/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author alexi
 */
public class MainWindowAllContent extends JPanel {
    
    public MainWindowAllContent(Game game) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
        this.add(new MainWindowNorthContent(),BorderLayout.NORTH);
        this.add(new MainWindowContent(game), BorderLayout.CENTER);
        this.add(new MainWindowOtherContent(), BorderLayout.EAST);
    }
    
    public void refreshDisplay(Game game){
        game.getGameboard().drawGameboard();
        this.getParent().getComponent(0).repaint();       
    }
    
    
    
}
