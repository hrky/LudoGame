package seng271.group8.ludo.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import seng271.group8.ludo.model.Move;
import seng271.group8.ludo.model.Pawn;
import seng271.group8.ludo.model.Square;

/** Give preference to a move to a target field where the pawn cannot be kicked.
 * This strategy picks the move that moves the player's pawns as far away from 
 * other player's pawns as possible.
 * 
 * In other words, I am a coward and I make my pawns 
 * run as far away from others as possible.
 * 
 * @author bill
 */
public class DefensiveStrategy extends AbstractStrategy {
	public DefensiveStrategy() {
            this.name = "Defensive";
        }

	public Move getMove(List<Move> moves) {
		if(moves.isEmpty()){
			return null;
		}else if(moves.size() == 1){
			return moves.get(0);
		}else{
			if(moves.get(0).getRoll() == 6 && !moves.get(0).getPlayer().getPawnsAtHome().isEmpty()){
				// get one of them and move it
				List<Pawn> leftAtHome = moves.get(0).getPlayer().getPawnsAtHome();
				for(Move m : moves){
					if(leftAtHome.contains(m.getPawn())){
						return m;
					}
				}
			}else{
				//look ahead
				int roll = moves.get(0).getRoll();
				List<Integer> firstPawnAhead = new ArrayList<Integer>();
				
				List<Square> currentSquares;
				
				List<Move> clear = new ArrayList<Move>();
				List<Move> blocked = new ArrayList<Move>();
				
				boolean block = false;
				for(Move m : moves){
					if(m.pathContainsEnemyPawn()){
						blocked.add(m);
					}else{
						clear.add(m);
					}
				}
				//System.out.println("#Blocked:"+blocked.size()+" #Clear:"+clear.size());
				if(!clear.isEmpty()){
					//System.out.println("Returning clear move!");
					return clear.get(0);
				}else if(!blocked.isEmpty()){
					//System.out.println("Returning Blocked Move!");
					return blocked.get(0);
				}
			}
			Random rng = new Random();
			return moves.get(rng.nextInt(moves.size()));
		}
	}
}
