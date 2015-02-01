package seng271.group8.ludo.strategies;

import java.util.List;
import seng271.group8.ludo.Die;
import seng271.group8.ludo.model.Move;

/**
 *
 * @author bill
 */
public interface Strategy {
	public Move getMove(List<Move> moves);//getMoves calculates all available moves
        public int getRoll(Die d);
}
