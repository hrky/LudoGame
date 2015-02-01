/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import seng271.group8.ludo.GameController;
import seng271.group8.ludo.events.BoardClickEvent;
import seng271.group8.ludo.graphics.Renderer2D;

/**
 *
 * @author Alastairs
 */
public class GameMouseListener implements MouseListener, MouseMotionListener {
    
    private GamePanel game;
    private Renderer2D playArea;
    
    public GameMouseListener(GamePanel game){
        this.game = game;
        this.playArea = game.getRenderer2D();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = playArea.graphicToGridCoords(e.getX(), e.getY());
        BoardClickEvent bc = new BoardClickEvent(p);         
        GameController.publish(bc);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = playArea.graphicToGridCoords(e.getX(), e.getY());
       // System.out.println(p.x);
    }
}
