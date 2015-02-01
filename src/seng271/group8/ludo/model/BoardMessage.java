/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

/**
 *
 * @author alastair
 */
public class BoardMessage extends GameEntity {
    private String message;
    public static final String MESSAGE = "MESSAGE";
    
    public BoardMessage(String message) {
        this.message = message;
    }
    
    public void setMessage(String message) {
        this.message = message;
        this.pcs.firePropertyChange(MESSAGE, null, message);
    }
}
