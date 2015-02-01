/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.awt.Point;
import java.awt.geom.Point2D;
import seng271.group8.ludo.model.GameEntity;
import seng271.group8.ludo.model.Pawn;
import seng271.group8.ludo.model.Square;

/**
 *
 * @author Alastairs
 */
public class MoveBuilder implements AnimationBuilder {
    
    public IAnimatable build(GameEntity g, long preDelay, long postDelay) {
        IAnimatable animation = null;
        Pawn pw = (Pawn) g; 
        Point modelPosition = pw.getPosition();
        Point2D graphicPosition = g.getRendering().getPosition();
        
        if(modelPosition.x != graphicPosition.getX() ||
                modelPosition.y != graphicPosition.getY()) {
            // Build a move animation
            Animation2DSeries series = new Animation2DSeries();
            int time = 500;
            
            if(preDelay > 0)
                series.add(new NOPAnimation(g.getRendering(), preDelay));
            
            for(Square s : pw.getMove().getSquares()) {
                if(s.getPawn() == null || s.getPawn().equals(pw)) {    
                    Animation2DSeries scale = new Animation2DSeries();
                    scale.add(new ScaleAnimation(g.getRendering(),1.5f,time/2));
                    scale.add(new ScaleAnimation(g.getRendering(),1.0f,time/2));
                    Animation2DGroup group = new Animation2DGroup();
                    group.add(scale);
                    group.add(new TranslateAnimation(g.getRendering(),s.getPosition(),time));

                    series.add(group);
                }
            }
            
            if(postDelay > 0)
                series.add(new NOPAnimation(g.getRendering(), postDelay));
            animation = series;
        }
        
        return animation;
    }
}
    
