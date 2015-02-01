/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import seng271.group8.ludo.events.GameEvent;
import seng271.group8.ludo.graphics.Animation2DSeries;
import seng271.group8.ludo.graphics.AnimationBuilder;
import seng271.group8.ludo.graphics.Animator;
import seng271.group8.ludo.graphics.IAnimatable;
import seng271.group8.ludo.graphics.MessageGraphic;
import seng271.group8.ludo.graphics.NOPAnimation;
import seng271.group8.ludo.graphics.ScaleAnimation;
import seng271.group8.ludo.graphics.TranslateAnimation;

/**
 *
 * @author alastair
 */
public class MessageChangeListener extends AbstractChangeListener {

    public MessageChangeListener(Animator animator, 
            Map<Class<? extends AnimationBuilder>,AnimationBuilder> builders) {
        super(animator,builders);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        BoardMessage b = (BoardMessage)evt.getSource();
        IAnimatable ani = null;
        GameEvent ge = null;
        if (BoardMessage.MESSAGE.equals(propertyName)) {
            MessageGraphic g = (MessageGraphic)b.getRendering();
            g.setScale(0);
            g.setMessage((String)evt.getNewValue());
            g.setPosition(new Point(5,5));
            Animation2DSeries series = new Animation2DSeries();
            ScaleAnimation sc = new ScaleAnimation(g, 1.5f,400, "easeInOut");
            TranslateAnimation ts = new TranslateAnimation(g,new Point(5,-1),600);
            series.add(sc);
            series.add(new NOPAnimation(g,300));
            series.add(ts);
            ani = series;
        }
        // Todo: Refactor this, seens common to each listener
        if(ani != null && ge != null)
           animator.addAnimation(ani, ge);
        else if(ani != null)
           animator.addAnimation(ani); 
    }
    
}
