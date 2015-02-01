/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Greg Richardson
 */
public class DieGraphic extends LudoGraphic {
	
	private final int dotMatrices[][][] = {
		{
			{0, 0, 0},
			{0, 1, 0},
			{0, 0, 0}
		},
		{
			{1, 0, 0},
			{0, 0, 0},
			{0, 0, 1}
		},
		{
			{1, 0, 0},
			{0, 1, 0},
			{0, 0, 1}
		},
		{
			{1, 0, 1},
			{0, 0, 0},
			{1, 0, 1}
		},
		{
			{1, 0, 1},
			{0, 1, 0},
			{1, 0, 1}
		},
		{
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1}
		}
	};
	
	private int roll;
	
    public DieGraphic(Point p) {
        super(p);
        this.roll = 6;
    }
    
    public int getRoll() {
    	return this.roll;
    }
    
    public void setRoll(int roll) {
    	this.roll = roll;
    }
    
    @Override
    public void paint(Graphics g, Dimension squareSize) {
        
        Graphics2D g2 = (Graphics2D)g;
        
        Point2D pos = getDrawPosition(squareSize);
        Point2D size = getDrawSize(squareSize);

        //Draw the die
        RoundRectangle2D shape = new RoundRectangle2D.Double(
        		pos.getX(), pos.getY(), size.getX(), size.getY(), size.getX()/4, size.getY()/4);
        g2.setColor(new Color(255,255,255));
        g2.fill(shape);
        
        //Draw the border
        g2.setStroke( new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        
        //Create a sub-container centered within the rounded die to draw the dots in
        Point2D containerSize = new Point2D.Double(size.getX() * 0.8, size.getY() * 0.8);
        Point2D containerPos = new Point2D.Double(
        		pos.getX() + size.getX()/2 - containerSize.getX()/2,
        		pos.getY() + size.getY()/2 - containerSize.getY()/2);
        
        drawSpots(g2, containerPos, containerSize, this.roll);
    }
    
    private void drawSpot(Graphics2D g2, Point2D pos, double diameter) {
    	Ellipse2D shape = new Ellipse2D.Double(
                pos.getX() - diameter/2, pos.getY() - diameter/2, diameter, diameter);
        g2.setColor(new Color(0,0,0));
        g2.fill(shape);
    }
    
    private void drawSpots(Graphics2D g2, Point2D containerPos, Point2D containerSize, int num) {
    	if (num < 1 || num > 6) {
    		throw new IndexOutOfBoundsException("Invalid die number");
    	}
    	
    	//Get the dot matrix for the passed number
    	int[][] dotMatrix = dotMatrices[num-1];
    	
    	//Dimensions of one 'cell' (ie. 1/9 of the container's area)
    	double cellWidth = containerSize.getX()/3;
    	double cellHeight = containerSize.getY()/3;
    	
    	//Diameter of a spot
    	double diameter = containerSize.getX()/4;
    	
    	//Draw the spots
    	for (int i = 0; i < dotMatrix.length; i++) {
    		for (int j = 0; j < dotMatrix[i].length; j++) {
    			if (dotMatrix[i][j] == 1) {
    				drawSpot(g2, new Point2D.Double(containerPos.getX() + j*cellWidth + cellWidth/2, containerPos.getY() + i*cellHeight + cellHeight/2), diameter);
    			}
    		}
    	}
    }
}
