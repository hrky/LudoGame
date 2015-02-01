/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.handlers;

import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.events.GameEvent;

/**
 *
 * @author alastair
 */
public class BaseHandler<T> implements Handler<T> {
    protected GameLogic game;
    
    public BaseHandler() {
        
    }
    
    public BaseHandler(GameLogic game) {
        this.game = game;
    }

    @Override
    public void handle(T evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
