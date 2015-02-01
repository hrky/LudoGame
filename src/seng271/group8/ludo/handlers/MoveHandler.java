/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.handlers;

import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.events.MoveEvent;
import seng271.group8.ludo.graphics.Renderer2D;

/**
 *
 * @author alastair
 */
public class MoveHandler implements Handler<MoveEvent> {

    private GameLogic game;
    
    public MoveHandler(GameLogic game) {
        this.game = game;
    }
    
    @Override
    public void handle(MoveEvent evt) {
        game.makeMakeMove(evt.getMove());
    }
    
}
