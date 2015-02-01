/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.actions;

import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hiroki
 */
public class OptionsAction extends AbstractAction {
    
    JDialog jd = new JDialog();
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        JLabel jlab = new JLabel("Options");
        JPanel jpanel = new JPanel();
        
        jd.setModal(true);
        jd.setSize(new Dimension(200, 400));
        jd.setResizable(false);
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
        jpanel.add(jlab);
        
        JButton jbtnReturn = new JButton("Return to the game");
        jpanel.add(jbtnReturn);
        
        // Set to default game window location. This is not relative to the game window. 
        jd.setLocationRelativeTo(null);
        
        jd.add(jpanel);
        jd.setVisible(true);
    }
}
