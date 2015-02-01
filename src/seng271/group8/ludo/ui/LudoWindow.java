/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import seng271.group8.ludo.strategies.Strategy;
//import seng271.group8.ludo.BufferStrategyDemo;

/**
 *
 * @author Alastairs
 */
public class LudoWindow extends JFrame {
    
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 800;
    private static final String TITLE = "Group 8 Ludo";
    
    
    public LudoWindow () {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { }

        this.setIconImage(new ImageIcon("res/icon.png").getImage());
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        GameContainer gc = new GameContainer(this);
        this.add(gc);
        this.revalidate();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
               LudoWindow game = new LudoWindow();
            }
        });
    }
}
