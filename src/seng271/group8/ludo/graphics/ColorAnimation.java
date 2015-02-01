/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

/**
 *
 * @author Alastairs
 */
public class ColorAnimation extends Animation2D {
    
    public ColorAnimation(LudoGraphic g, long dur, int alpha) {
        super(g,dur,"linear");
    }
    
    @Override
    public Boolean tic(long dt) {
        Boolean done = super.tic(dt);
        return done;
    }
}
