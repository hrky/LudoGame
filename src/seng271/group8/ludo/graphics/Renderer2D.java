/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import seng271.group8.ludo.model.BoardConfig;

/**
 *
 * @author Alastairs
 */
public class Renderer2D {
    
    private List<Layer> layers;
    private List<LudoGraphic> graphics;
    private Dimension panelSize = new Dimension(100,100);
    private Dimension squareSize = new Dimension(100,100);
    private Boolean repaintAll = true;
    private BufferedImage scene;
    private Graphics2D g2d;
    private int viewSize;
    private int side;
    
    public Renderer2D () {
        this.layers = new ArrayList<Layer>();
        this.graphics = new ArrayList<LudoGraphic>();
        
    }
    
    public void add(LudoGraphic g) {
       graphics.add(g);
    }
    
    public void addLayer(Layer l) {
        layers.add(l);
    }
    
    public void resize( Dimension panelSize) {
        this.panelSize = panelSize;
        this.computeSquareSize(panelSize);
        this.repaintAll = true;
    }
      
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();


        // If the view size has changed need to recompute the entire view
        // All drawing is done to a buffered image which is then copied to the 
        // Panel area
         viewSize = (int)Math.min(panelSize.getWidth()-30,panelSize.getHeight()-30);
         if(repaintAll) {
             for(Layer l : layers) {
                 l.create(viewSize);
             }
        }
         // Center board in panel
         g2.translate(computeOffsetX(), computeOffsetY());
         int i = 0;
         for(Layer l : layers) {
            Graphics2D g2l = l.getContext();
            List<Rectangle.Double> regions = computeDirtyRegions(l.getGraphics());
            if(!repaintAll){
                // Clear all dirty areas
               for (Rectangle.Double ar : regions) {
                    g2l.clearRect((int)ar.x, (int)ar.y, (int)( ar.width), (int)( ar.height));
               }
            } else {
                // Clear entire board (window as resized)
                g2l.clearRect(0, 0, squareSize.width*11, squareSize.width*11);
            }

            // Now redraw all dirty graphics
            for(LudoGraphic gr : l.getGraphics()) {
                if(!repaintAll){
                    if(gr.getDirty()) {
                         gr.paint(g2l, squareSize);
                         gr.setDirty(false);
                         i++;
                     }
                }
                else {
                    // otherwise redraw entire board
                    gr.paint(g2l, squareSize);
                }
            }
            
            // Some Debug drawing code (Shows regions which are being repainted)
//            for (Rectangle.Double ar : regions) {
//              g2.drawRect((int)ar.x, (int)ar.y, (int)( ar.width), (int)( ar.height));
//            }
            // Draw the buffered image to the panel
            g2.drawImage(l.getImage(), g2l.getTransform(), null);
        }
        repaintAll = false;
    }
    
    public List<Rectangle.Double> computeDirtyRegions(List<LudoGraphic> layerGraphics) {
        List<LudoGraphic> dirty = new ArrayList<LudoGraphic>();
        List<Rectangle.Double> regions = new ArrayList<Rectangle.Double>();

        // Find all dirty graphics
        for(LudoGraphic gr : layerGraphics) {
          if(gr.getDirty()) {
             dirty.add(gr);
          }  
        }
        
        // Compute any other graphics that may overlap with them
        // They will need to be redrawn as well
        findRedrawAreas(dirty, layerGraphics, regions);          
        return regions;
    }
    
    public void findRedrawAreas(List<LudoGraphic> dirty, List<LudoGraphic> layerGraphics, List<Rectangle.Double> dirtyRegions) {
        Point2D size = new Point2D.Double(squareSize.width,squareSize.height);
            
        // For each Dirty graphic check its last and new position
        // Find other graphics whose bounds overlap un the same layer
        // These regions need to be redrawn
        for(LudoGraphic dr : dirty) {
            Point2D paCur = dr.getBounds(squareSize);
            Point2D paOld = dr.getLastBounds(squareSize);              
            dirtyRegions.add(new Rectangle.Double(paCur.getX(),paCur.getY(), size.getX(), size.getY()));
            dirtyRegions.add(new Rectangle.Double(paOld.getX(),paOld.getY(), size.getX(), size.getY()));
    
            for(LudoGraphic ov : layerGraphics) {
                Point2D pbCur = ov.getBounds(squareSize);            

                if(checkForOverlap(paCur, pbCur, size, size)
                   || checkForOverlap(paOld, pbCur, size, size)) {
                    ov.setDirty(true);
                    dirtyRegions.add(new Rectangle.Double(pbCur.getX(), pbCur.getY(),
                    size.getX(), size.getY()));
                }
                
            }
        }

    }
    
    public Boolean checkForOverlap(Point2D pa, Point2D pb, Point2D sa, Point2D sb) {
        Boolean overlap = false;
        
        // Condition for overlap of two rectangles
        if(pa.getX() < (pb.getX()+sb.getX())
           && pa.getX() + sa.getX() > pb.getX()
           && pa.getY() < (pb.getY()+ sb.getY())
           && pa.getY()+sa.getY() > pb.getY()) {
                overlap = true;
            }
        return overlap;
    }
    
    public Point graphicToGridCoords(double x, double y) {
        Point p = new Point((int)(x - computeOffsetX())/squareSize.width, (int)(y - computeOffsetY())/squareSize.height);
        return p;
    }
    
    private int computeOffsetX(){
        return (int)(panelSize.width-viewSize)/2;
    }
    
    private int computeOffsetY() {
        return (int)(panelSize.height-viewSize)/2;
    }
    
    private void computeSquareSize(Dimension panelSize) {
        //System.out.println(Math.min(panelSize.width, panelSize.height));
        side = Math.min(panelSize.width-30, panelSize.height-30)/BoardConfig.WIDTH;
        this.squareSize.setSize(side, side);
    }
}
