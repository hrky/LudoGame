/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.handlers;

import java.util.List;
import seng271.group8.ludo.GameController;
import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.TimingConfig;
import seng271.group8.ludo.events.MoveEvent;
import seng271.group8.ludo.events.RollEvent;
import seng271.group8.ludo.events.TurnEvent;
import seng271.group8.ludo.model.Move;
import seng271.group8.ludo.model.Player;

/**
 *
 * @author alastair
 */
public class RollHandler extends BaseHandler<RollEvent> {
    
    public RollHandler(GameLogic game) {
        super(game);
    }
    
    @Override
    public void handle(RollEvent evt) {
        if(evt.getRoll() == -1) {
            System.out.println("Human rolled " + game.generateRoll());      
            game.getCurrentPlayer().setHasRolled(true);
            GameController.publish(new RollEvent(game.getRoll(),TimingConfig.get(TimingConfig.ROLL)));
            return;
        }
        
        Move m = game.getNextMove();
        
        // If AI has a move do it, otherwise pass on the turn
        // If the current player is human wait for their input
        if(m != null && !m.isHuman())
            GameController.publish(new MoveEvent(m));
        else if (m == null) {
            GameController.publish(new TurnEvent(TimingConfig.get(TimingConfig.TURN)));  
        }
    }
}
