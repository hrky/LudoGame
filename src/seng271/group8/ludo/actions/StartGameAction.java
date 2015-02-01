/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.actions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import seng271.group8.ludo.strategies.Strategy;
import seng271.group8.ludo.ui.GameContainer;
import seng271.group8.ludo.ui.PlayerSelectPanel;

/**
 *
 * @author Alastairs
 */
public class StartGameAction extends AbstractAction {
    private GameContainer game;
    private PlayerSelectPanel[] options;
    
    public StartGameAction(Container game, PlayerSelectPanel[] options) {
        this.game = (GameContainer)game;
        this.options = options;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Strategy[] strategies = new Strategy[4];
        
        int i = 0;
        
        for(PlayerSelectPanel p : options ) {
           strategies[i++] = p.getStrategy();
        }
        
        this.game.showGameView(strategies);
    }
    
}
