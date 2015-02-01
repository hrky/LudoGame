/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.handlers;

import java.util.List;
import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.events.GameOverEvent;
import seng271.group8.ludo.model.Player;
import seng271.group8.ludo.ui.GameContainer;
import seng271.group8.ludo.ui.GameOverView;
import seng271.group8.ludo.ui.LudoWindow;

/**
 *
 * @author Alastairs
 */
public class GameOverEventHandler extends BaseHandler<GameOverEvent> {
    
    private GameContainer container;
    
    // Constructor could include a reference to the panel to show
    public GameOverEventHandler(GameLogic game, GameContainer l) {
        super(game);
        this.container = l;
    }
        
        /**
     *
     * @param evt
     */
    public void handle(GameOverEvent evt) {
        // Get the game results
        List<Player> finishPositions = game.getGameResults();
        //Show the game over panel here.
        GameOverView overView = new GameOverView(finishPositions, this.container);
    }
}
