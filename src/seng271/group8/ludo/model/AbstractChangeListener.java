/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.beans.PropertyChangeListener;
import java.util.Map;
import seng271.group8.ludo.graphics.AnimationBuilder;
import seng271.group8.ludo.graphics.Animator;

/**
 *
 * @author alastair
 */
public abstract class AbstractChangeListener implements PropertyChangeListener {
    Map<Class<? extends AnimationBuilder>,AnimationBuilder> builders;
    Animator animator;
    
    public AbstractChangeListener(Animator animator, 
            Map<Class<? extends AnimationBuilder>,AnimationBuilder> builders) {
        this.animator = animator;
        this.builders = builders;
    }
}
