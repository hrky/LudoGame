/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.graphics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alastair
 */
public class Animation2DSeries implements IAnimatable{
    
    private List<IAnimatable> series;
    private IAnimatable current;
    private int currentInd = 0;
    private int repetitions = 0;
    protected Boolean cancelRepeats = false;
    
    public Animation2DSeries(int reps) {
        this();
        this.repetitions = reps;
    }
    
    public Animation2DSeries() {
        this.series = new ArrayList<IAnimatable>();
    }
    
    public void add(IAnimatable animation) {
        this.series.add(animation);
        if(this.series.size() == 1)
            this.current = animation;
    }
    
    private IAnimatable getNext() {
        IAnimatable next = null;
        if(++currentInd < series.size())
             next = series.get(currentInd);
        else if(!cancelRepeats) {
             if(repetitions > 0 || repetitions == -1) {
                 if(repetitions > 0)
                     repetitions--;
                 next = this.series.get((currentInd = 0));
             } 
        }
        return next;
    }

    @Override
    public Boolean tic(long dt) {
        Boolean done = false;
        if(this.current.tic(dt)) {
            this.current = getNext();
            if(this.current == null)
                done = true;
            else
                this.current.start();
        }
        return done;
    }

    @Override
    public void start() {
       current.start();
    }

    @Override
    public void cancelRepeats() {
        cancelRepeats = true;
    }
}
