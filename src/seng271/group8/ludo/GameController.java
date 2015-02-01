/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import seng271.group8.ludo.events.GameEvent;
import seng271.group8.ludo.handlers.Handler;
import seng271.group8.ludo.model.*;

/**
 *
 * @author Alastairs
 * 
 * Might rename this to notification center or something closer to what 
 * it actually does.
 * 
 * I don't have any experience coding something like this, let me know if it 
 * seems nuts.
 */
public class GameController implements Runnable {
    
    private static BlockingQueue<GameEvent> gameEvents = new LinkedBlockingQueue<GameEvent>();
    private Boolean isRunning = true;
    
    private static final Map<Class,List<Handler>> map = 
            new HashMap<Class,List<Handler>>();
    
    private List<Move> history;
    
    public GameController() {
       
    }

    @Override
    public void run() {
       while(isRunning) {
           try {
               GameEvent ge = gameEvents.take();
               dispatch(ge);
           } catch (InterruptedException ex) {
               Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
    
    public static synchronized <T> void register(Class<? extends T> evtClass, Handler<T> h) {
        List<Handler> handlers = map.get(evtClass);
        if(handlers == null) {
            handlers = new ArrayList<Handler>();
            map.put(evtClass, handlers);
        }
        handlers.add(h);
    }
    
    private void dispatch(GameEvent event) {
        List<Handler> handlers = map.get(event.getClass());
        if(handlers != null)
            for(Handler hd : handlers) {
                hd.handle(event);
            }
        else
            System.out.println("No handlers registered for event"  + event.getClass());
    }
    
    public static void publish(GameEvent e) {
        if(e.getDefer() > 5) {
            GameController.publish(e, e.getDefer());
        } else {
            GameController.put(e);
        }
            
    }
    
    private static void put(GameEvent e) {
        try {
            GameController.gameEvents.put(e);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void publish(final GameEvent e, long delay) {
        new Timer().schedule(new TimerTask() {          
            @Override
            public void run() {
                GameController.put(e);
             }
        }, delay);
    }
}
