/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alastairs
 */
public class TimingConfig {
    
    public static String TURN = "TURN";
    public static String ROLL = "ROLL";
    public static String KICK = "KICK";
    
   /* All Times in milliseconds */
    private static float GAMESPEED = 1;
    private static int BETWEEN_TURN;
    private static Map<String,Integer> times = new HashMap<String,Integer>();
    
    static {
        times.put(TURN, 500);
        times.put(ROLL, 1000);
        times.put(KICK, 50);
    }
    
    public static void setGameSpeed(float f) {
       TimingConfig.GAMESPEED = f;
    }
    
    public static float getGameSpeed() {
        return TimingConfig.GAMESPEED;
    }
    
    public static Integer get(String timing) {
        Integer n = times.get(timing);
        int time = 1;
        if(n!= null)
            time = (int)(n/TimingConfig.getGameSpeed());
        return time;
    }
}
