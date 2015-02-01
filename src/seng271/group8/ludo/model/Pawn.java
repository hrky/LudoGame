package seng271.group8.ludo.model;

import java.awt.Color;
import java.awt.Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bill
 */
public class Pawn extends GameEntity {
    private Player owner;
    private Square square;
    private Move move;
    public int id = 0;
    public static final String MOVE = "MOVE";
    public Boolean onGoal = false;
    
    public Pawn(Player player, Square pos){
        this.owner = player;
        this.square  = pos;
        pos.setPawn(this);
    }
    
    /** Sets the Pawn's Owner
     * 
     * @param owner	The Player whom the pawn belongs to
     */
    public void setOwner(Player owner){
            this.owner = owner;
    }
    
    /** Returns the pawn's owner
     * 
     * @return the pawn's owner
     */
    public Player getOwner(){
        return this.owner;
    }
    
    /** Returns the pawn's square
    * 
    * @return the square that the pawn is on
    */
    public Square getSquare(){
        return this.square;
    }
    
    public void setMove(Move m) {
        Move old = this.move;
        this.move = m;
        this.setPosition(m.getSquares().getLast(), m.wasKicked());
        pcs.firePropertyChange(MOVE, old, move);
    }

    public Move getMove() {
        return this.move;
    }
    
    public void setPosition(Square position, Boolean wasKicked){
        if(this.square != null && !wasKicked)
            this.square.setPawn(null);
        
        this.square = position;
        
        if(position.getClass().equals(Goal.class))
            this.onGoal = true;
            
        this.square.setPawn(this);
    }
    
    @Override
    public Point getPosition() {
        return this.square.getPosition();
    }
    /**
	 * 
	 * @return True if the pawn is on the goal
	 */
    public Boolean isOnGoal() {
        return this.onGoal;
    }
}
