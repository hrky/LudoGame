/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import seng271.group8.ludo.model.GameEntity;

/**
 *
 * @author Alastairs
 */
public interface AnimationBuilder {
    public IAnimatable build(GameEntity g, long preDelay, long postDelay);
}
