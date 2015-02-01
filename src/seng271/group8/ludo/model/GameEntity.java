/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import seng271.group8.ludo.graphics.LudoGraphic;

/**
 *
 * @author alastair
 */
public class GameEntity {
    protected LudoGraphic rendering;
    protected Point position;
    protected Boolean selected = false;
    public static final String SELECTED = "SELECTED";

    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void setRendering(LudoGraphic g) {
        this.rendering = g;
    }
    
    public LudoGraphic getRendering() {
        return this.rendering;
    }
   
    public Point getPosition() {
        return this.position;
    }
    
    public void setSelected(Boolean selected) {
        Boolean old = this.selected;
        this.selected = selected;
        this.pcs.firePropertyChange(SELECTED, old, selected);
    }
    
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
