/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import javax.swing.Action;

/**
 *
 * @author alastair
 */
public interface IAnimatable {
    public Boolean tic(long gt);
    //public void repeats();
    //public void reverse();
    public void start();  
    public void cancelRepeats();
   // public void onComplete(Action done);
     
}
