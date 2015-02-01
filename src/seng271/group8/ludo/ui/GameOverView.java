/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import seng271.group8.ludo.actions.CloseAction;
import seng271.group8.ludo.model.Player;

/**
 *
 * @author Alastairs
 */
public class GameOverView extends JPanel { 
    
    private static final int GAME_OVER_WIDTH = 500;
    private static final int GAME_OVER_HEIGHT = 300;
    List<Player> winners;
    JDialog jd = new JDialog();
    private GameContainer container;
    
    // Constructor
    public GameOverView(List<Player> winners, GameContainer l) {
        this.winners = winners;
        this.container = l;
        this.setup();
    }
    
    private void setup() {
        
        JPanel titlePanel = new JPanel();
        JPanel listPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        jd.setLocationRelativeTo(container);
        jd.setModal(true);
        jd.setSize(new Dimension(GAME_OVER_WIDTH, GAME_OVER_HEIGHT));
        jd.setResizable(false);
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);        
        
        // Setup game over title
        JLabel gameOverTitle = new JLabel("Game Over");
        Font currFont = gameOverTitle.getFont();
        titlePanel.add(gameOverTitle);
        gameOverTitle.setFont(new Font(currFont.getFontName(), currFont.getStyle(), 50));
        
        // Setup winner list
        JLabel firstPlayer = new JLabel("Winner: " + "Player " + (winners.get(0).getId() + 1));
        JLabel secondPlayer = new JLabel("2nd: " + "Player " + (winners.get(1).getId() + 1));
        JLabel thirdPlayer = new JLabel("3rd: " + "Player " + (winners.get(2).getId() + 1));
        JLabel fourthPlayer = new JLabel("4th: " + "Player " + (winners.get(3).getId() + 1));
        
        firstPlayer.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        secondPlayer.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        thirdPlayer.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        fourthPlayer.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        firstPlayer.setFont(new Font(currFont.getFontName(), currFont.getStyle(), 20));
        secondPlayer.setFont(new Font(currFont.getFontName(), currFont.getStyle(), 20));
        thirdPlayer.setFont(new Font(currFont.getFontName(), currFont.getStyle(), 20));
        fourthPlayer.setFont(new Font(currFont.getFontName(), currFont.getStyle(), 20));
        
        listPanel.add(firstPlayer);
        listPanel.add(secondPlayer);
        listPanel.add(thirdPlayer);
        listPanel.add(fourthPlayer);
        
        // Setup buttons on the bottom
        JButton quitButton = new JButton("Quit Game");
        buttonPanel.add(quitButton);
        quitButton.addActionListener(new CloseAction(container, jd));
        
        jd.add(titlePanel);
        jd.add(buttonPanel);
        
        jd.add(titlePanel, BorderLayout.NORTH);
        jd.add(listPanel, BorderLayout.CENTER);
        jd.add(buttonPanel, BorderLayout.SOUTH);
        jd.setVisible(true);
        
    }

}
