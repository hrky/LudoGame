/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import seng271.group8.ludo.model.GameEntity;

/**
 *
 * @author alastair
 */
public class PulseBuilder implements AnimationBuilder {

    @Override
    public IAnimatable build(GameEntity g, long postDelay, long preDelay) {
        // Loop forever
        Animation2DSeries series = new Animation2DSeries(-1);
        LudoGraphic graphic = g.getRendering();
        series.add(new ScaleAnimation(graphic, 1.45f,1000));
        series.add(new ScaleAnimation(graphic, 1.0f,1000));
        return series;
    }
    
}
