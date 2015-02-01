/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import seng271.group8.ludo.GameController;
import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.events.RollEvent;

/**
 *
 * @author alastair
 */
public class DiceRollAction extends AbstractAction {
    private GameLogic game;
    
    public DiceRollAction(GameLogic game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(game.getCurrentPlayer().isHuman()
            && !game.getCurrentPlayer().getHasRolled()) {
        GameController.publish(new RollEvent(-1));
        }
    }
    
}
