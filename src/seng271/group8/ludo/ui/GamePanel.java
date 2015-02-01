/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

import seng271.group8.ludo.Die;
import seng271.group8.ludo.graphics.AnimationBuilder;
import seng271.group8.ludo.graphics.Animator;
import seng271.group8.ludo.graphics.BoardGraphic;
import seng271.group8.ludo.graphics.DieGraphic;
import seng271.group8.ludo.graphics.Layer;
import seng271.group8.ludo.graphics.LudoGraphic;
import seng271.group8.ludo.graphics.MessageGraphic;
import seng271.group8.ludo.graphics.MoveBuilder;
import seng271.group8.ludo.graphics.PawnGraphic;
import seng271.group8.ludo.graphics.PulseBuilder;
import seng271.group8.ludo.graphics.Renderer2D;
import seng271.group8.ludo.graphics.SquareGraphic;
import seng271.group8.ludo.model.Board;
import seng271.group8.ludo.model.BoardMessage;
import seng271.group8.ludo.model.DieChangeListener;
import seng271.group8.ludo.model.MessageChangeListener;
import seng271.group8.ludo.model.Pawn;
import seng271.group8.ludo.model.PawnChangeListener;
import seng271.group8.ludo.model.Player;
import seng271.group8.ludo.model.PlayerChangeListener;
import seng271.group8.ludo.model.Square;
import seng271.group8.ludo.model.SquareChangeListener;

/**
 *
 * @author Alastairs
 */
public class GamePanel extends JComponent implements ComponentListener, 
        FocusListener {
    
    private Board board;
    private Renderer2D renderer;
    private Animator animationThread;
    private Map<Class<? extends AnimationBuilder>, AnimationBuilder> animationBuilders;
    private BufferedImage background;
    
    public GamePanel(Board b, Die d) {
        /*
         * Todo: A lot of code is ending up here for setup. Refactor eventually...
         * Low priority
         */
        this.setOpaque(true);
        this.setBackground(new Color(51,0,0));
        this.board = b;
        this.renderer = new Renderer2D();
        
        this.animationThread = new Animator(this);
        // make sure the thread stops when the JFrame is closed
        this.animationThread.setDaemon(true);
        this.animationThread.start();
        
        animationBuilders = new HashMap<Class<? extends AnimationBuilder>, 
                            AnimationBuilder>();
        animationBuilders.put(MoveBuilder.class, new MoveBuilder());
        animationBuilders.put(PulseBuilder.class, new PulseBuilder());
        
        for(Player p : board.getPlayers()) {
            p.addPropertyChangeListener(new PlayerChangeListener(animationThread, 
                    animationBuilders));
        }
        
        Layer backgroundLayer = new Layer();

        backgroundLayer.add(new BoardGraphic(new Point(0,0)));
        
        renderer.addLayer(backgroundLayer);
        
        Layer squareLayer = new Layer();
        
        for(Square s : board.getSquareList()) {
            s.setRendering(new SquareGraphic(s));
            s.addPropertyChangeListener(new SquareChangeListener(animationThread,
                    animationBuilders));
            squareLayer.add(s.getRendering());
            //squareLayer.add(s.getRendering(), s.getPosition().x, s.getPosition().y);
        }
        
        renderer.addLayer(squareLayer);
        Layer pawnLayer = new Layer();
        
        for(Pawn pw : board.getPawnList()) {
            pw.setRendering(new PawnGraphic(pw));
            pawnLayer.add(pw.getRendering());
            pw.addPropertyChangeListener(new PawnChangeListener(
                    animationThread,animationBuilders));
        }

        d.setRendering(new DieGraphic(board.getCentrePoint()));
        d.addPropertyChangeListener(new DieChangeListener(
                    animationThread,animationBuilders));
        pawnLayer.add(d.getRendering());
        
        renderer.addLayer(pawnLayer);
        
        Layer uiLayer = new Layer();
        LudoGraphic mes = new MessageGraphic(board.getCentrePoint(), "Game started");
        BoardMessage bmes = board.getMessage();
        bmes.setRendering(mes);
        bmes.addPropertyChangeListener(new MessageChangeListener(animationThread,
                animationBuilders));
       
        uiLayer.add(mes);
        
        renderer.addLayer(uiLayer);
        
                
        try{
            background = ImageIO.read(Renderer2D.class.getResource("diagonal.png"));
        } catch (Exception e) {
            System.out.println("Board background didn't load: " + e.getMessage());
        }
        
        
        GameMouseListener gl = new GameMouseListener(this);
        this.addMouseListener(gl);
        this.addMouseMotionListener(gl);
        this.addComponentListener(this);
    }
    
    private void resize() {
        renderer.resize(this.getSize());
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
       
       super.paintComponent(g);      
       Graphics2D g2 = (Graphics2D) g;
       g.clearRect(0, 0, this.getWidth(), this.getHeight());
       g.setColor(new Color(64,42,13));
       g.fillRect(0, 0, this.getWidth(), this.getHeight());
       float opacity = 0.1f;
       g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        for(int x = 0; x < this.getWidth(); x+=background.getWidth()) {
           for(int y = 0; y < this.getHeight(); y+=background.getHeight()) {
               g2.drawImage(background, x, y, null);
           }
       }
       g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
       //System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
       //long start = System.currentTimeMillis();
       
       renderer.paint(g);
       //System.out.println(System.currentTimeMillis()-start);
    }
    
    public void highlightSquare(Point p) {
        
    }
    
    public Renderer2D getRenderer2D() {
        return this.renderer;
    }
    
    public Board getBoard() {
        return this.board;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.resize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        this.resize();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.resize();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.resize();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
