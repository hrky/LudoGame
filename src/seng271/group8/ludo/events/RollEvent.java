/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.events;

/**
 *
 * @author alastair
 */
public class RollEvent extends GameEvent {
    private Boolean deferredRoll = false;
    private int roll;
    
    public RollEvent() {
        
    }
    
    public RollEvent(int roll) {
        this.roll = roll;
    }
    
    public RollEvent(int roll, long defer) {
        super(defer);
        this.roll = roll;
    }
    
    public int getRoll() {
        return this.roll;
    }
    
    public Boolean isDeferred() {
        return this.deferredRoll;
    }
}
