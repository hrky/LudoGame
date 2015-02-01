package seng271.group8.ludo.strategies;

import java.util.List;
import seng271.group8.ludo.Die;
import seng271.group8.ludo.model.Move;

/**Human Strategy
 * The human strategy doesn't actually calculate anything, as the human needs to 
 * choose the move they want to perform on another level.
 * @author bill
 */
public class HumanStrategy extends AbstractStrategy {

	public HumanStrategy() {
		this.name = "Human";
	}
    
	@Override
	public Move getMove(List<Move> moves) {
            // No move is returned. The move is selected through UI events
            // by the human player.
            Move m = null;
            if(moves.size() > 0) {
                m = new Move();
                m.setHuman(true); 
            }
            return m;
        }
        
        @Override
        public int getRoll(Die d) {
            return -1;
        }
}
