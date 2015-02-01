/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.handlers;

import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.events.KickPawnEvent;

/**
 *
 * @author Alastairs
 */
public class KickPawnEventHandler extends BaseHandler<KickPawnEvent> {

    public KickPawnEventHandler(GameLogic gamelogic) {
        super(gamelogic);
    }
    
    @Override
    public void handle(KickPawnEvent evt) {
        game.makeKickMove(evt.getKickMove());
    }
}
