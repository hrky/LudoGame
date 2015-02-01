/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.beans.PropertyChangeEvent;
import java.util.Map;
import seng271.group8.ludo.events.GameEvent;
import seng271.group8.ludo.events.KickPawnEvent;
import seng271.group8.ludo.events.TurnEvent;
import seng271.group8.ludo.graphics.AnimationBuilder;
import seng271.group8.ludo.graphics.Animator;
import seng271.group8.ludo.graphics.IAnimatable;
import seng271.group8.ludo.graphics.MoveBuilder;
import seng271.group8.ludo.graphics.PulseBuilder;

/**
 *
 * @author alastair
 */
public class SquareChangeListener extends AbstractChangeListener {
    private IAnimatable active = null;
    
    public SquareChangeListener(Animator animator, 
            Map<Class<? extends AnimationBuilder>,AnimationBuilder> builders) {
        super(animator, builders);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        IAnimatable ani = null;
        GameEvent ge = null;
        Square s = (Square)evt.getSource();

        if (Square.SELECTED.equals(propertyName)) {
            Boolean selected = (Boolean)evt.getNewValue();
            if(selected) {
                ani = builders.get(PulseBuilder.class).build(s,0,0);
            }
            else if(active != null) {
                active.cancelRepeats();
            }
        } else if(Square.PAWNLANDED.equals(propertyName)) {
            
        }
        
        active = ani;
        
        if(ani != null && ge != null)
           animator.addAnimation(ani, ge);
        else if(ani != null)
           animator.addAnimation(ani); 
    }
}

