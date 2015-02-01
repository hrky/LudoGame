/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import seng271.group8.ludo.actions.StartGameAction;
import seng271.group8.ludo.strategies.AggressiveStrategy;
import seng271.group8.ludo.strategies.CautiousStrategy;
import seng271.group8.ludo.strategies.DefensiveStrategy;
import seng271.group8.ludo.strategies.HumanStrategy;
import seng271.group8.ludo.strategies.MoveFirstStrategy;
import seng271.group8.ludo.strategies.MoveLastStrategy;
import seng271.group8.ludo.strategies.RandomMoveStrategy;
import seng271.group8.ludo.strategies.Strategy;

/**
 *
 * @author Alastairs
 */
public class SetupView extends JPanel {
    
    private PlayerSelectPanel[] players = new PlayerSelectPanel[4];
    JButton playNow;
    private GameContainer ludo;
 
    public SetupView(GameContainer ludo) {
    	
        Strategy[] strategies = {
            new HumanStrategy(),
			new AggressiveStrategy(),
			new CautiousStrategy(),
			new DefensiveStrategy(),
            new MoveFirstStrategy(),
            new MoveLastStrategy(),
			//new BrutalStrategy(),
			new RandomMoveStrategy(),
        };
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        this.ludo = ludo;
        Font h1 = new Font("Verdana", Font.BOLD, 18);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        players[0] = new PlayerSelectPanel("Player 1", strategies, strategies[0]);
        players[0].setPreferredSize(new Dimension(160, 80));
        this.add(players[0], gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        players[1] = new PlayerSelectPanel("Player 2", strategies, strategies[1]);
        players[1].setPreferredSize(new Dimension(160, 80));
        this.add(players[1], gbc); 
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth =1;
        players[3] = new PlayerSelectPanel("Player 4", strategies, strategies[5]);
        players[3].setPreferredSize(new Dimension(160, 80));
        this.add(players[3], gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        players[2] = new PlayerSelectPanel("Player 3", strategies, strategies[3]);
        players[2].setPreferredSize(new Dimension(160, 80));
        this.add(players[2], gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        playNow = new JButton("Play Now!");
        playNow.setFont(h1);
        playNow.setPreferredSize(new Dimension(200, 60));
        this.add(playNow, gbc);
        
        playNow.addActionListener(new StartGameAction(ludo, players));

    }
}
