/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.events;

/**
 *
 * @author alastair
 */
public class TurnEvent extends GameEvent {
    public TurnEvent() {
        
    }
    
    public TurnEvent(long defer) {
        super(defer);
    }
}
