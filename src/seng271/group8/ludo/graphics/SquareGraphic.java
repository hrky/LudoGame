/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import seng271.group8.ludo.model.Grid;
import seng271.group8.ludo.model.Square;

/**
 *
 * @author Alastairs
 */
public class SquareGraphic extends LudoGraphic {
    
    private Square square;
    private float outerXPercent = 1f, outerYPercent = 1f;
    
    public SquareGraphic(Square square) {
        super(square.getPosition());
        this.gameEntity = this.square = square;
        this.xPercent = this.yPercent = 0.65f;
    }
    
    @Override
    public void paint(Graphics g, Dimension squareSize) {
        Graphics2D g2 = (Graphics2D)g;
        
        // Temporary hack to avoid drawing the blank squares
        // These draw methods are all pretty ugly right now
        // Experimenting with a few different things...
        if(square.getType() == Grid.EMP_SQ)
            return;
        Point2D pos = getDrawPosition(squareSize);
        Point2D size = getDrawSize(squareSize);

        Rectangle2D bounds = new Rectangle2D.Double(
                squareSize.width*position.getX(),squareSize.height*position.getY(),
                squareSize.width, squareSize.height);
        g2.setColor(new Color(139,137,137));
        //g2.fill(bounds);
        
        Ellipse2D shape = new Ellipse2D.Double(pos.getX(), pos.getY(), size.getX(), size.getY());
        //g2.setColor(new Color(224,195,108));
        //g2.fill(bounds);
        float[] rgb = new float[3]; // TODO: get rid of this sort of code
        square.getColor().getColorComponents(rgb); // and this
        g2.setColor(new Color(rgb[0], rgb[1], rgb[2], 0.7f)); // and this
        g2.fill(shape);
        g2.setColor(new Color(139,137,137));
        g2.setStroke( new BasicStroke (2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        
        //drawDebugIndexes(g2, x ,y);
        
    }
    
    private void drawDebugIndexes(Graphics2D  g2, int x, int y) {
        String xyCoords = Integer.toString(square.getPosition().x) + "," + Integer.toString(square.getPosition().y);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawChars(xyCoords.toCharArray(), 0, xyCoords.length(), x+20, y+40);
    }
}
