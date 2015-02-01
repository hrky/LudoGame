/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.actions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hiroki
 */
public class RestartGameAction extends AbstractAction {

    JDialog jd = new JDialog();
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Construct a game quit confirmation dialog
        JLabel jlab = new JLabel("Are you sure you want to restart the game?");
        JPanel jpanel = new JPanel();
       
        jd.setModal(true);
        jd.setSize(new Dimension(300, 100));
        jd.setResizable(false);
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jpanel.setLayout(new FlowLayout());
        jpanel.add(jlab);
        
        JButton jbtnYes = new JButton("Yes");
        JButton jbtnNo = new JButton("No");
        jpanel.add(jbtnYes);
        jpanel.add(jbtnNo);
        jbtnNo.addActionListener(new RestartGameActionExit());

        // Set to default game window location. This is not relative to the game window. 
        jd.setLocationRelativeTo(null);
          
        jd.add(jpanel);
        jd.setVisible(true);
    }
    
    class RestartGameActionExit implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("No"))
                jd.dispose();
        }
    }
}
