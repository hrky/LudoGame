/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import seng271.group8.ludo.model.Board;
import seng271.group8.ludo.model.Move;
import seng271.group8.ludo.model.Path;
import seng271.group8.ludo.model.PathSegment;
import seng271.group8.ludo.model.Pawn;
import seng271.group8.ludo.model.Player;
import seng271.group8.ludo.model.Square;

/**
 *
 * @author alastair
 */
public class GameLogic {
   private Board model;
   private List<Player> players, rankings;
   private int turn = 0; // Player 1 always starts
   private int roll = 6; // Hardcoded for testing
   private Die dice;
   private String stateMessage;
   private Boolean shouldBlock;

   public GameLogic(Board b, Die d) {
       this.model = b;
       this.dice = d;
       this.players = b.getPlayers();
       this.rankings = new LinkedList<Player>();
   }
   
   public Player getCurrentPlayer() {
       return players.get(turn);
   }
   
   public void makeMakeMove(Move m) {
       this.getCurrentPlayer().setHasRolled(false);
       m.getPawn().setMove(m);
   }
   
   public Boolean checkForWin() {
       Boolean gameOver = false;

       for(Player p : this.players) {
           if(p.checkIsFinished() && !rankings.contains(p)) {
               rankings.add(p);
           }      
       }
       
       if(rankings.size() == 3) {
           gameOver = true;
           
           // TODO: this is ugly fix it.
           for(Player p : this.players) {
               if(!rankings.contains(p))
                   rankings.add(p);
           }
           
       }
       
       return gameOver;
   }
   
   /**
    * 
    * @return The player rankings from 1st-4th place 
    */
   public List<Player> getGameResults() {
       return this.rankings;
   }
   
   public void makeKickMove(Move m) {
       m.getPawn().setMove(m);
   }
   
   public void advanceTurn() {
       Player last = players.get(turn);
       last.setSelected(Boolean.FALSE);
       turn = (turn + 1) % players.size();
       if(this.getCurrentPlayer().getIsFinished()) {
           this.advanceTurn();
       } else {
            Player cur = players.get(turn);
            cur.setSelected(Boolean.TRUE);
       }
   }
   
   public void setModel(Board b) {
    model = b;
   }
   
   /***
    * Can be used by the strategies.
    * Will probably move these function into the
    * AbstractStrategy class
    * 
    * @param player
    * @return 
    */
   public LinkedList<Move> getValidMoves (Player player) {
	   
       LinkedList<Move> moves = new LinkedList<Move>();
       boolean mustMoveFromHome = false;
       
       //Loop through the player's pawns and get their moves
       for (Pawn pw : player.getPawns()) {
           Move m = getValidMove(pw);
           
           if (m != null) {
        	   
        	   //If the player can move a pawn out of their home, they must do it
               if (m.isMovingFromHome()) {
            	   mustMoveFromHome = true;
               }
               
               moves.add(m);
           }
       }

       //If mustMoveFromHome is set, remove any moves that don't move pawns out of the home
       if (mustMoveFromHome) {
    	   Iterator<Move> iterator = moves.iterator();
    	   while (iterator.hasNext()) {
    		   Move move = iterator.next();
    		   if (!move.isMovingFromHome()) {
    	    	   iterator.remove();
    		   }
    	   }
       }
       
       return moves;
   }
   
   public Move getValidMoveForPawn(Pawn pw) {
	   List<Move> moves = this.getValidMoves(this.getCurrentPlayer());
	   Move valid = null;
	   for (Move m : moves) {
		   if (pw.equals(m.getPawn())) {
			   valid = m;
			   break;
		   }
	   }
	   
	   return valid;
   }
   
   private Move getValidMove(Pawn pw) {
       return getValidMove(pw, pw.getOwner());
   }
   
   public Move getValidMove(Pawn pw, Player player) {
        Move move = null;
        LinkedList<Square> squares = new LinkedList<Square>();
        Path path = player.getPath();
        PathSegment cur;
        int steps = 0;
        cur = path.getSegment(pw.getSquare());
        
        //Pawn is not on the path (therefore must be on a home square)
        if (cur == null) {
        	
	        //Player must roll a 6 to enter board
	        if (roll == 6) {
	
	            Square startSquare = path.getFirst().getSquare();
	            
	            //Gets the pawn's home square if it's sitting on it (null otherwise)
	            Square homeSquare = path.getHomeSquare(pw);
	            
	            //Player must not have a pawn on the start square (and selected pawn is on the home square)
	            if (homeSquare != null && startSquare.canOccupy(pw)) {
					squares.add(startSquare);
					move = new Move(pw, squares, roll);
					move.setMovingFromHome(true);
					
					//Kick any opponent players on that square
					checkForKick(startSquare, move);
	            }
	        }
        }
        else {
            // Clicked pawn is on the path
            //cur = cur.getNext();

            while(cur.getNext() != null) {
                /**
                 * Logic for determining valid moves!
                 * 
                 * Each square decides if the pawn can pass or occupy
                 * the square.
                 */
                PathSegment next = cur.getNext();
                squares.add(next.getSquare());
                steps++;
                
                if(steps > roll)
                    break;
                
                if(roll == steps && 
                        next.getSquare().canOccupy(pw)) {
                   move = new Move(pw, squares, roll);
                   checkForKick(next.getSquare(),move);
                   break;
                }      
                
                cur = next;
            }
        } 
       return move;
   }
   
   private void checkForKick(Square s, Move move) {
	   Pawn pw = s.getPawn();
        
        if(pw != null) {
        	LinkedList<Square> squares = new LinkedList<Square>();
        	for(Square sq : pw.getOwner().getPath().getHomeSquares()) {
        		if(sq.getPawn() == null) {
        			squares.add(sq);
        			//pw.setPosition(sq);
        			break;
        		} 
        	}
                Move m = new Move(pw, squares);
                m.setWasKicked(true);
        	move.setKickMove(m);
        }
   }
   
   public void setRoll(int i) {
       
   }
   
   public void setBlockInput(Boolean block) {
       this.shouldBlock = block;
   }
   
   public Boolean shouldBlockInput() {
       return false;
   }
   
   public int getRoll() {
       return this.roll;
   }
   
   public int generateRoll() {
       if(roll == -1) {
           this.roll = dice.roll();
           setMessage(getCurrentPlayer(),roll);
       }
       return this.roll;
   }
   
   public int makeRoll() {
       Player p = getCurrentPlayer();
       roll = p.getRoll(dice);
       if(roll != -1) {
           this.setMessage(p,roll );
       }
       return roll;
   }
   
   private void setMessage(Player p, int roll) {
           p.setHasRolled(true);
           model.setMessage("Player " + (p.getId()+1) + " rolled " + roll +"!");
   }
   
   public Move getNextMove() {    
       Player p = getCurrentPlayer();
       return p.getMove(getValidMoves(p));
   }
}
