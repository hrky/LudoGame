/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.beans.PropertyChangeEvent;
import java.util.Map;
import seng271.group8.ludo.events.GameEvent;
import seng271.group8.ludo.graphics.Animation2DGroup;
import seng271.group8.ludo.graphics.AnimationBuilder;
import seng271.group8.ludo.graphics.Animator;
import seng271.group8.ludo.graphics.IAnimatable;
import seng271.group8.ludo.graphics.ScaleAnimation;

/**
 *
 * @author alastair
 */
public class PlayerChangeListener extends AbstractChangeListener {

    public PlayerChangeListener(Animator animator, 
            Map<Class<? extends AnimationBuilder>,AnimationBuilder> builders) {
        super(animator, builders);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        Player p = (Player)evt.getSource();
        IAnimatable ani = null;
        GameEvent ge = null;
        if (Player.SELECTED.equals(propertyName)) {
            float scale;
 
            if((Boolean)evt.getNewValue())
                scale = 1.25f;
            else
                scale = 1.0f;
                
            Animation2DGroup group = new Animation2DGroup();
            for(Square hs : p.getPath().getHomeSquares()) {
                ScaleAnimation ss = new ScaleAnimation(hs.getRendering(),scale,400l, "bounce");
                group.add(ss);
                if(hs.getPawn() != null) {
                     //ScaleAnimation sp = new ScaleAnimation(hs.getPawn().getRendering(),scale,400l, "linear");
                     //group.add(sp);
                }      
            }
            ani = group;
        }
        if(ani != null && ge != null)
           animator.addAnimation(ani, ge);
        else if(ani != null)
           animator.addAnimation(ani); 
    }
    
}
