package seng271.group8.ludo.strategies;

import java.util.List;
import java.util.Random;
import seng271.group8.ludo.model.Move;
import seng271.group8.ludo.model.Pawn;

/** A derpy strategy that returns a random move, always.
 * TODO: Bill actually make this work.
 * @author bill
 */
public class RandomMoveStrategy extends AbstractStrategy{
	private Random generator;

	public RandomMoveStrategy() {
		this.name = "Random Move";
		generator = new Random();
	}
		
	@Override
	public Move getMove(List<Move> moves) {
		if(moves.isEmpty()){
			return null;
		}else if(moves.size() == 1){
			return moves.get(0);
		}else{
			if(moves.get(0).getRoll() == 6 && !moves.get(0).getPlayer().getPawnsAtHome().isEmpty()){
				List<Pawn> pawnsAtHome = moves.get(0).getPlayer().getPawnsAtHome();
				for(Move m : moves){
					if(pawnsAtHome.contains(m.getPawn())){
						return m;
					}
				}
			}
		}
		return(moves.get(generator.nextInt(moves.size())));
	}

}
