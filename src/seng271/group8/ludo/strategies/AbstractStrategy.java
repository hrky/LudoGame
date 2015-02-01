package seng271.group8.ludo.strategies;

import seng271.group8.ludo.Die;

/**
 *
 * @author alastair
 */
public abstract class AbstractStrategy implements Strategy {
    protected String name = "Abstract Strategy";
    
    @Override
    public String toString(){
            return this.name;
    }
    
    @Override
    public int getRoll(Die d) {
        return d.roll();
    }
}
