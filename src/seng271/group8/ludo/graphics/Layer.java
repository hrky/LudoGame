/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alastairs
 */
public class Layer {
    private List<LudoGraphic> graphics;
    private BufferedImage layer;
    private Graphics2D g2d;    
    
    public Layer() {
        graphics = new ArrayList<LudoGraphic>();
        create(100);
    }
    
    public void create(int viewSize) {
        layer = new BufferedImage(viewSize, viewSize, BufferedImage.TYPE_INT_ARGB);
        g2d = layer.createGraphics();
        g2d.setBackground(new Color(0,0,0,0));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }
    
    public void add(LudoGraphic g) {
        graphics.add(g);
    }
    
    public BufferedImage getImage() {
        return layer;
    }
    
    public Graphics2D getContext() {
        return g2d;
    }
    
    public List<LudoGraphic> getGraphics() {
        return graphics;
    }
}
