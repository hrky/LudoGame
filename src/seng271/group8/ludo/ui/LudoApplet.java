package seng271.group8.ludo.ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.UIManager;

import seng271.group8.ludo.strategies.Strategy;

public class LudoApplet extends JApplet {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 800;
    
    
    public LudoApplet () {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { }

        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setVisible(true);
        
        GameContainer gc = new GameContainer(null);
        this.add(gc);
        this.revalidate();
    }
	
    public void init() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
               LudoApplet game = new LudoApplet();
            }
        });
    }
    
    @Override
    public void destroy() {
    	System.exit(0);
    }
}