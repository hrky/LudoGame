package seng271.group8.ludo;


import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import seng271.group8.ludo.model.GameEntity;

/** Dice Used with Ludo game. This one isn't rigged, I promise.
 * 
 * @author bill
 */
public class Die extends GameEntity {
    private List<Integer> log;
    private int result;
    private Random generator;
    public static final String ROLL = "ROLL";
	private boolean cheat = false;
	private List<Integer> rig;
    
    public Die(){
        generator = new Random();
        log = new ArrayList<Integer>();
		rig = new ArrayList<Integer>();
		if(cheat){
			System.out.println("We're using the cheater die. Don't forget to disable this in production code!");
			//add the results you want the rigged dice to return.
			//blue
			rig.add(6);
			rig.add(6);rig.add(6);
			rig.add(3);
			//yellow
			rig.add(6);
			rig.add(1);
			//green
			rig.add(6);
			rig.add(1);
			//red
			rig.add(6);
			rig.add(1);
			//blue again
			rig.add(6);
			rig.add(2);
		}
		
		
    }
	/** Rolls the dice and returns the result. 
	 * Also adds the result to the dice log.
	 * If this.debug is set, the dice instead returns the first 
	 * 
	 * @return			An integer between 1 and 6 (as the result of the dice roll.)
	 */
    public int roll(){
		int oldResult = this.result;
		if(this.cheat){
			if(rig.isEmpty()){
				this.result = generator.nextInt(6)+1;
				System.out.println("Cheater list empty. RNG'd result:"+this.result);
			}else{
				this.result = rig.get(0);
				rig.remove(0);
			}					
		}else{
			this.result = generator.nextInt(6)+1;
		}
		log.add(new Integer(this.result));
		this.pcs.firePropertyChange(ROLL, oldResult, this.result);
		return this.result; 
    }
	/** returns a log of all of the dice results.
	 * 
	 * @return			ArrayList<Integer> of all of the results of the die
	 */
    public List<Integer> getResults(){
        return this.log;
    }
}
