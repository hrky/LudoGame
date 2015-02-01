package seng271.group8.ludo.strategies;

import java.util.List;
import seng271.group8.ludo.model.Move;

/** An All-out, merciless, completely heuristic strategy to crush opponents. Shame it doesn't work yet
 *
 * @author bill
 */
public class BrutalStrategy extends AbstractStrategy {

        public BrutalStrategy() {
            this.name = "Brutal";
        }
    
	public Move getMove(List<Move> moves) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
