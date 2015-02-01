/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.actions;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import seng271.group8.ludo.ui.LudoWindow;


/**
 *
 * @author Hiroki
 */
public class QuitGameAction extends AbstractAction {
    
    private LudoWindow game;
    JDialog jd = new JDialog();

    public QuitGameAction(Container game) {
        this.game = (LudoWindow)game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        // Construct a game quit confirmation dialog
        JLabel jlab = new JLabel("Are you sure you want to quit the game?");
        JPanel jpanel = new JPanel();
       
        jd.setModal(true);
        jd.setSize(new Dimension(250, 100));
        jd.setResizable(false);
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jpanel.setLayout(new FlowLayout());      
        jpanel.add(jlab);
        
        JButton jbtnYes = new JButton("Yes");
        JButton jbtnNo = new JButton("No");
        jpanel.add(jbtnYes);
        jpanel.add(jbtnNo);
        jbtnYes.addActionListener(new CloseAction(game, jd));
        jbtnNo.addActionListener(new CloseAction(game, jd));
        
        // Set to default game window location. This is not relative to the game window. 
        jd.setLocationRelativeTo(this.game);
            
        jd.add(jpanel);
        jd.setVisible(true);
    }
}
