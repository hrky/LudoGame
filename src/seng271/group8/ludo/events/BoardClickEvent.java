/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.events;

import java.awt.Point;

/**
 *
 * @author alastair
 */
public class BoardClickEvent extends GameEvent {
    private Point click;

    public BoardClickEvent(Point coords) {
        this.click = coords;
    }
    
    public Point getClick() {
        return this.click;
    }
}
