/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.handlers;

import seng271.group8.ludo.events.GameEvent;

/**
 *
 * @author alastair
 */
public class NetworkHandler implements Handler<GameEvent> {

    public void handle(GameEvent evt) {
        System.out.println("hey");
    }
    
    /*@Override
    public Boolean handle(GameEvent evt) {
       if(super.handle(evt)) {
           
       }
       System.out.println("Mor reasonable");
    }*/
    
}
