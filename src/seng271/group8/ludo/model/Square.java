/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Alastairs
 */
public class Square extends GameEntity {
	protected Grid type;
	protected Pawn pawn = null;
	protected Boolean selected = false;
	protected Color c; 
	public static final String PAWNLANDED = "PAWNLANDED";
	
	public Square(Grid type, Point position) {
		this.type = type;
		this.c = this.type.getColor();
		this.position = position;
	}
	/**
	 * Removes the old pawn on this square (if any), sets a new pawn, 
	 * and fires a property change so that the animations repaint this square.
	 * @param p the new pawn that is about to enter this square
	 */
	public void setPawn(Pawn p) {
		Pawn oldPawn = this.pawn;
		this.pawn = p;
		this.pcs.firePropertyChange(PAWNLANDED, oldPawn, this.pawn);
	}
	
	public Grid getType() {
		return this.type;
	}
	/**
	 * returns the pawn that is on this square
	 * @return the pawn that is currently occupying this square
	 */
	public Pawn getPawn() {
		return this.pawn;
	}
	
	public Color getColor() {
		return c;
	}
	
	public Color getDefaultColor() {
		return this.type.getColor();
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	/**
	 * 
	 * @return whether this square is selected by the player or not
	 */
	public Boolean isSlected() {
		return this.selected;
	}
	/**
	 * Returns whether a pawn can pass over this square. In our implementation, 
	 * you can always pass other pawns.
	 * @param pw the pawn to pass this square.
	 * @return true if it can pass, false if it can't.
	 */
	public Boolean canPass(Pawn pw) {
		return true;
	}
	
	public Boolean canOccupy(Pawn pw) {
		
		if (this.pawn != null && this.pawn.getOwner().equals(pw.getOwner())) {
			return false;
		}
		return true;
	}
}
