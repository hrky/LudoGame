/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

/**
 *
 * @author Alastairs
 */
public class PathSegment {
    private Square square;
    private PathSegment next;
    
    public PathSegment(Square square) {
        this.square = square;
    }
    /**
	 * Sets the next path segment
	 * @param ps the next path segment
	 */
    public void setNext(PathSegment ps) {
        this.next = ps;
    }
    /**
	 * Returns the next path segment
	 * @return the next path segment
	 */
    public PathSegment getNext() {
        return this.next; 
    }
    /**
	 * Returns the square that this path segment contains
	 * @return the square that this path segment contains
	 */
    public Square getSquare() {
        return this.square;
    }
}
