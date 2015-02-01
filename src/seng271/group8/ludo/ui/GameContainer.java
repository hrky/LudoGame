package seng271.group8.ludo.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import seng271.group8.ludo.strategies.Strategy;

public class GameContainer extends JPanel {
    
    private SetupView setupView = null;
    private GameView gameView = null;
    private JPanel currentView = null;
    private Container window = null;
    
	public GameContainer(Container window) {
		//this.setSize(800, 800);
		this.window = window;
		this.setLayout(new BorderLayout());
		this.setup();
	}
	
	private void setup() {
        this.showSetupView();
    }
    
    /**
     * TODO: Alastair Refactor switching views
     */
    public void showSetupView() {
        this.removeCurrentView();
        
        if(setupView == null)
            setupView = new SetupView(this);
        
        currentView = setupView;
        this.add(currentView);
        this.revalidate();
    }
    
    /**
     * TODO: Alastair Refactor switching views
     */
    public void showGameView(Strategy[] strategies) {
        removeCurrentView();
        
        if(gameView == null) {
            gameView = new GameView(this, strategies);
            gameView.start();
        }
        
        currentView = gameView;
        this.add(currentView);
        this.revalidate();
    }
    
    /**
     * TODO: Alastair Refactor switching views
     * 
     * I think this will be another panel shown
     * over top of the GameView
     * 
     * TODO: remove
     */
    public void showGameOverView() {
        
    }
    
    public void close() {
    	if(window != null) {
    		LudoWindow w = (LudoWindow)window;
    		w.dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING));
    	}
    }
    
    public void removeCurrentView() {
       if(currentView != null)
           this.remove(currentView);
    }
}
