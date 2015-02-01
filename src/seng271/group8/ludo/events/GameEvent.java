/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.events;

/**
 *
 * @author alastair
 */
public abstract class GameEvent {
    
    private long defer = 0;
    
    public GameEvent() {
        
    }
    
    public GameEvent(long defer) {
        this.defer = defer;
    }
    
    public long getDefer() {
        return this.defer;
    }
    
    public String getMessage() {
        return "Game Event of Type " + this.getClass() + " was handled";
    }
}
