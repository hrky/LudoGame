    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import seng271.group8.ludo.model.GameEntity;

/**
 *
 * @author Alastairs
 */
public abstract class LudoGraphic {
    protected Point2D position;
    protected Point2D lastPosition;
    protected float scale = 1;
    
    // Indicate the graphic should be
    // scaled to x,y percentage of a grid square
    protected float xPercent = 1;
    protected float yPercent = 1;
    protected Dimension lastSize;
    protected Boolean dirty = true;
    protected GameEntity gameEntity;
    protected Point2D bounds;
     
   public LudoGraphic(Point p) {
        this.lastPosition = this.position = p;
    }
    
    public void setPosition(Point2D p) {
        //System.out.println("X :" + p.getX() + " Y : " + p.getY());
//        this.lastPosition = this.position;
        this.position = p;
    }
    
    public void setLastDrawPosition(Point2D p) {
        this.lastPosition = p;
    }
    
    public Point2D getDrawPosition(Dimension squareSize) {
        return computeDrawPosition(squareSize, this.position);
    }
    
    public Point2D getDrawSize(Dimension squareSize) {
        Point2D d  = new Point2D.Double((this.xPercent*squareSize.width*scale), 
                (this.yPercent*squareSize.height*scale));
        return d;
    }
    
    public Point2D getBounds(Dimension squareSize) {
        return new Point2D.Double(squareSize.width*position.getX(), squareSize.height*position.getY());
    }
    
    public Point2D getLastBounds(Dimension squareSize) {
        return new Point2D.Double(squareSize.width*lastPosition.getX(), squareSize.height*lastPosition.getY());
    }
    
    public Point2D getLastDrawPosition(Dimension squareSize) {
        return this.lastPosition;
    }
    
    public Point2D computeDrawPosition(Dimension squareSize, Point2D position) {
        Point2D p = new Point2D.Double((squareSize.width*(position.getX()+(1-this.xPercent*scale)/2f)), 
                    (squareSize.width*(position.getY()+(1-this.yPercent*scale)/2f)));
        return p;
    }
    
    public void setXPercent(float xPercent) {
        this.xPercent = xPercent;
    }
    
    public void setYPercent(float yPercent) {
        this.yPercent = yPercent;
    }
    
    public float getYPercent() {
        return this.yPercent;
    }
    
    public float getXPercent() {
        return this.xPercent;
    }
    
    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public float getScale() {
        return this.scale;
    } 
    
    public Point2D getPosition() {
        return this.position;
    }
    
    public void setDirty(Boolean dirty) {
        this.dirty = dirty;
    }
    
    public Boolean getDirty() {
        return this.dirty;
    }
    
    public GameEntity getGameEntity() {
        return this.gameEntity;
    }
    
    public abstract void paint(Graphics g, Dimension squareSize);
}
