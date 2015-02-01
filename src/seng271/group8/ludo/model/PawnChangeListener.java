/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.beans.PropertyChangeEvent;
import java.util.Map;
import seng271.group8.ludo.TimingConfig;
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
 * @author Alastairs
 */
public class PawnChangeListener extends AbstractChangeListener {
    private IAnimatable active = null;
    
    
    public PawnChangeListener(Animator animator, 
            Map<Class<? extends AnimationBuilder>,AnimationBuilder> builders) {
        super(animator, builders);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        IAnimatable ani = null;
        GameEvent ge = null;
        if(Pawn.MOVE.equals(propertyName)) {
            Pawn p = (Pawn)evt.getSource();
            if(p.getMove().doesKick()) {
                ge = new KickPawnEvent(p.getMove().getKickMove(),TimingConfig.get(TimingConfig.KICK));
                ani = builders.get(MoveBuilder.class).build(p,0,0);
            }
            else {
                ge = new TurnEvent((int)(TimingConfig.get(TimingConfig.TURN)*0.4f));
                ani = builders.get(MoveBuilder.class).build(p,0,0);
            }
        } else if (Pawn.SELECTED.equals(propertyName)) {
            Boolean selected = (Boolean) evt.getNewValue();
            if(selected) {
               active = ani = builders.get(PulseBuilder.class).build((GameEntity)evt.getSource(),0,0);
            } else if(active != null) {
               active.cancelRepeats();
            }
        }
         if(ani != null && ge != null)
            animator.addAnimation(ani, ge);
         else if(ani != null)
            animator.addAnimation(ani);
    }
    
}
