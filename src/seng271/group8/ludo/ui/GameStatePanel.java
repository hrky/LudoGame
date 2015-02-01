/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import seng271.group8.ludo.GameLogic;
import seng271.group8.ludo.TimingConfig;

import seng271.group8.ludo.actions.DiceRollAction;
/*
import seng271.group8.ludo.actions.GameOverAction;
import seng271.group8.ludo.actions.QuitGameAction;
import seng271.group8.ludo.actions.OptionsAction;
import seng271.group8.ludo.actions.RestartGameAction;*/

/**
 *
 * @author Hiroki
 */
public class GameStatePanel extends JPanel implements ChangeListener {

    private JButton diceRoll;
    /*private JButton newGame;
    private JButton restart;
    private JButton options;
    private JButton quit;*/
    private JSpinner gameSpeed;
    private GameLogic game;
    private GameContainer ludo;

    public GameStatePanel(GameContainer ludo, GameLogic game) {
        this.game = game;
        this.ludo = ludo;
        this.setup();
    }

    public void setup() {


    	this.setLayout(new GridLayout(1,3));
        JPanel contentPaneCenter = new JPanel();
        JPanel contentPaneLeft = new JPanel();
        JPanel contentPaneRight = new JPanel();

        contentPaneCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    	//Dimension buttonSize = new Dimension(80, 30);

        // Locate "Roll" button onto the center
    	gbc.gridx = 1;
        gbc.gridy = 1;

        diceRoll = new JButton("Roll");
        diceRoll.setPreferredSize(new Dimension(80, 60));
        diceRoll.addActionListener(new DiceRollAction(game));
        contentPaneCenter.add(diceRoll);
        contentPaneCenter.add(diceRoll, gbc);

        /*// Additional buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        newGame = new JButton("New");
        newGame.setPreferredSize(buttonSize);
        contentPaneCenter.add(newGame, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        restart = new JButton("Restart");
        restart.setPreferredSize(buttonSize);
        contentPaneCenter.add(restart, gbc);
        restart.addActionListener(new RestartGameAction());

        gbc.gridx = 0;
        gbc.gridy = 2;
        options = new JButton("Options");
        options.setPreferredSize(buttonSize);
        contentPaneCenter.add(options, gbc);
        options.addActionListener(new OptionsAction());

        gbc.gridx = 2;
        gbc.gridy = 2;
        quit = new JButton("Quit");
        quit.setPreferredSize(buttonSize);
        contentPaneCenter.add(quit, gbc);
        quit.addActionListener(new QuitGameAction(ludo));*/
        
        
        // Locate "Roll" button onto the center
    	JLabel speed = new JLabel("Speed: ");    

        gameSpeed = new JSpinner();
        SpinnerNumberModel gameSpeedRange =  new SpinnerNumberModel(1,1,10000,1);
        gameSpeed.setModel(gameSpeedRange);
        gameSpeed.addChangeListener(this);

        contentPaneRight.add(speed);
        contentPaneRight.add(gameSpeed);

        // Add three main components of game state panel
        this.add(contentPaneLeft);
        this.add(contentPaneCenter);
        this.add(contentPaneRight);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        SpinnerModel speedModel = gameSpeed.getModel();
        int speed = (Integer)speedModel.getValue();
        TimingConfig.setGameSpeed(speed);
    }

}