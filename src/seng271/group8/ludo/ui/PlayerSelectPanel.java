/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.ui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import seng271.group8.ludo.strategies.Strategy;

/**
 *
 * @author Alastairs
 */
public class PlayerSelectPanel extends JPanel {
    
    private JComboBox select;
    
    public PlayerSelectPanel(String name, Strategy[] strategies) {
    	this (name, strategies, strategies[0]);
    }
    
    public PlayerSelectPanel(String name, Strategy[] strategies, Strategy defaultStrategy) {
        Font h1 = new Font("Verdana", Font.BOLD, 16);
        Font h2 = new Font("Verdana", Font.BOLD, 12);
        
        JLabel label = new JLabel(name);
        label.setFont(h1);
        
        select = new JComboBox(strategies);
        select.setFont(h2);
        select.setPreferredSize(new Dimension(140, 30));
        select.setSelectedItem(defaultStrategy);
        
        this.add(label);
        this.add(select);
    }
    
    public Strategy getStrategy() {
        return (Strategy)select.getSelectedItem();
    }
}
