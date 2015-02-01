/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

/**
 *
 * @author Alastairs
 */
public class ScaleAnimation extends Animation2D {
   
    float targetScale;
    float startScale;
    
    public ScaleAnimation(LudoGraphic g, float scale, long dur) {
        this(g,scale,dur,"easeInOut");
       // System.out.println("Target Scale is:" + scale + " Start is : " + this.startScale);
    }
    
    public ScaleAnimation(LudoGraphic g, float scale, long dur, String easing) {
        super(g, dur, easing);
        this.targetScale = scale;
    }
    
    @Override
    public void start() {
        super.start();
        this.startScale = graphic.getScale();
    }
    
    @Override
    public Boolean tic(long dt) {
        Boolean done = super.tic(dt);
        this.graphic.setScale(ease.tic(elapsed, startScale, 
               targetScale - startScale, dur));

        return done;
    }
}
 