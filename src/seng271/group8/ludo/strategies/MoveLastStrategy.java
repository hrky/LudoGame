package seng271.group8.ludo.strategies;

import java.util.ArrayList;
import java.util.List;
import seng271.group8.ludo.model.Move;
import seng271.group8.ludo.model.PathSegment;
import seng271.group8.ludo.model.Pawn;

/** Give preference to moving the hindmost pawn.
 *
 * @author bill
 */
public class MoveLastStrategy extends AbstractStrategy {
	public MoveLastStrategy() {
            this.name = "Move Last";
        }

		public Move getMove(List<Move> moves) {
		if(moves.isEmpty()){
			return null;
		}else if(moves.size() == 1){
			return moves.get(0);
		}else{
			//iterate through the board and move my pawn that is furthest ahead			
			List<Pawn> placement = new ArrayList<Pawn>();
			
			PathSegment path = moves.get(0).getPlayer().getPath().getFirst();
			while(path != null){
				//if pawn is not null and not in goal, add to list
				if(path.getSquare().getPawn() != null && !path.getSquare().getPawn().isOnGoal()){
					placement.add(path.getSquare().getPawn());
				}
				path = path.getNext();
			}
			
			if((moves.get(0).getRoll() == 6  && !moves.get(0).getPlayer().getPawnsAtHome().isEmpty())|| placement.isEmpty()){
				//I rolled 6 and I should move out.
				List<Pawn> pawnsAtHome = moves.get(0).getPlayer().getPawnsAtHome();
				for(Move m : moves){
					if(pawnsAtHome.contains(m.getPawn())){
						return m;
					}
				}
			}else{
				//I didn't roll 6 or I shouldn't move out
				for(int i=0;i<moves.size();i++){
					if(placement.get(0) == moves.get(i).getPawn()){
						return moves.get(i);
					}
				}
			}
			return moves.get(0);
		}
	}
}
