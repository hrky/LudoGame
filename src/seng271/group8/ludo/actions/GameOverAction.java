/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.actions;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.model.Player;
import seng271.group8.ludo.ui.GameContainer;
import seng271.group8.ludo.ui.GameOverView;
import seng271.group8.ludo.ui.LudoWindow;

/**
 *
 * @author Hiroki
 */
public class GameOverAction extends AbstractAction {
    private GameLogic game;
    private GameOverView gameoverview = null;
    private GameContainer container;
    
    public GameOverAction(GameLogic game) {
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         // Get the game results
        List<Player> finishPositions = game.getGameResults();
        //Show the game over panel here.
        gameoverview = new GameOverView(finishPositions, container);
    }
}
