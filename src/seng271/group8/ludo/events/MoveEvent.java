/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.events;

import seng271.group8.ludo.model.Move;

/**
 *
 * @author alastair
 */
public class MoveEvent extends GameEvent {
    private Move move;
    
    public MoveEvent(Move m) {
        move = m;
    }
    
    public Move getMove() {
        return this.move;
    }
}
