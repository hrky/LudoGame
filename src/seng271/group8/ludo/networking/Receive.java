/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Alastairs
 */
public class Receive implements Runnable {

    private DataInputStream recv;
    private DataOutputStream send;
    private Socket recvSoc;
    
    
    public Receive(Socket s) {
        this.recvSoc = s;
        try {
            recv = new DataInputStream(recvSoc.getInputStream());
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
