/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.awt.Point;

/**
 *
 * @author Alastairs
 */
public class Goal extends Square {
    public Goal(Grid type, Point position) {
            super(type, position);
    }
    
    @Override
    public Boolean canPass(Pawn pw) {
        Boolean success = true;
        if(pawn != null)
            success = false;
        return success;
    }   
}
