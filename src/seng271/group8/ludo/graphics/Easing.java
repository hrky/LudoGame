/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alastair
 */
public class Easing {
    
    private static Map<String,IEasing> easing = new HashMap<String,IEasing>();
     
    
    static {
        Easing.put("linear", new Linear());
        Easing.put("easeInOut", new EaseInEaseOut());
        Easing.put("sinEaseInOut", new SinEaseInOut());
        Easing.put("elastic", new ElasticEaseInOut());
        Easing.put("bounce", new Bounce());
    }
    
    private static void put(String key, IEasing value) {
        Easing.easing.put(key, value);
    }
    
    public static IEasing get(String name) {
        return easing.get(name);
    }
   
    /***
     *  For info on these functions see: 
     *  https://github.com/jesusgollonet/processing-penner-easing
     */
    private static class Linear implements IEasing {
        @Override
        public float tic(float t, float b, float c, float d) {
            return c*t/d + b;
        }
    }
   
    private static class EaseInEaseOut implements IEasing {
        @Override
        public float tic(float t, float b, float c, float d) {
            if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
            return -c/2 * ((t-=2)*t*t*t - 2) + b;   
        }
    }
    
    private static class SinEaseInOut implements IEasing{
      @Override
      public float tic(float t, float b, float c, float d) {
          return -c/2 * ((float)Math.cos(Math.PI*t/d) - 1) + b;
      }
    }
    
    private static class ElasticEaseInOut implements IEasing{
      @Override
      public float tic(float t, float b, float c, float d) {
		if (t==0) return b;  if ((t/=d/2)==2) return b+c; 
                float p=d*(.3f*1.5f);
		float a=c; 
		float s=p/4;
		if (t < 1) return -.5f*(a*(float)Math.pow(2,10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )) + b;
		return a*(float)Math.pow(2,-10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )*.5f + c + b;
	}
    }
    
    private static class Bounce implements IEasing{
        @Override
        public float tic(float t, float b, float c, float d) {
            if ((t/=d) < (1/2.75f)) {
                        return c*(7.5625f*t*t) + b;
                } else if (t < (2/2.75f)) {
                        return c*(7.5625f*(t-=(1.5f/2.75f))*t + .75f) + b;
                } else if (t < (2.5/2.75)) {
                        return c*(7.5625f*(t-=(2.25f/2.75f))*t + .9375f) + b;
                } else {
                        return c*(7.5625f*(t-=(2.625f/2.75f))*t + .984375f) + b;
                }
            }
    }
}
