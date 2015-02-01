package seng271.group8.ludo.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import seng271.group8.ludo.strategies.Strategy;

/**
 *
 * @author Alastairs
 * 
 * Generates and instance of the board based
 * on the constants defined in the BoardConfigClass
 * 
 */
public class Board {
    
    private Square[][] squares;
    private List<Square> squareList;
    private List<Pawn> pawnList;
    private List<Player> playerList;
    private List<Path> paths;
    private List<List<Pawn>> pawns;
    private BoardMessage message;
    
    public Board(Strategy[] strategies) {
        buildBoard();
        buildPlayers(strategies);
    }
    
    public void buildBoard() {
        message = new BoardMessage("Game Started!");
        squareList = new ArrayList<Square>();
        squares = new Square[BoardConfig.WIDTH][BoardConfig.HEIGHT];
        for( int i = 0; i < BoardConfig.HEIGHT; i ++) {
            for( int j = 0; j < BoardConfig.WIDTH; j++){
                // row*column loop (j,i) opposite to what might be expected
                // System.out.println("I: " + i + " J: " + j);
                createSquare(BoardConfig.MAP[i][j], new Point(j,i));
            }
        }
    }
    
    public Path buildPath(Player player) {
        
        List<Square> homeSquares = new ArrayList<Square>();
        
        for(Pawn pw : player.getPawns()) {
            homeSquares.add(pw.getSquare());
        }
        
        Path path = new Path(homeSquares);
        
        int playerId = player.getId();
        Square s = getSquareAt(BoardConfig.START_SQUARES[playerId].x, 
                BoardConfig.START_SQUARES[playerId].y);
        path.add(new PathSegment(s));
        Point[] pathVectors = this.rotatePoints(BoardConfig.PATH, 
                BoardConfig.ROTATION_OFFSET*playerId);

        for(int j = 0; j < pathVectors.length; j++) {
                addToPath(path, pathVectors[j]);
        }
        
        return path;

    }
    
    public BoardMessage getMessage() {
        return this.message;
    }
    
    public void setMessage(String message){
        this.message.setMessage(message);
    }
    
    public List<Pawn> buildPawns(int playerNum, Player p) {
        List<Pawn> playerPawns = new ArrayList<Pawn>();
        for(int i = 0; i < BoardConfig.NUM_PAWNS; i++) {
            int x = BoardConfig.PAWN_HOME[playerNum].x + BoardConfig.PAWN_OFFSETS[i].x; 
            int y = BoardConfig.PAWN_HOME[playerNum].y + BoardConfig.PAWN_OFFSETS[i].y;
            Pawn pawn = new Pawn(p,getSquareAt(x,y));
            playerPawns.add(pawn);
            pawnList.add(pawn);
        }
        return playerPawns;
    }

    public void buildPlayers(Strategy[] strategies) {
        this.playerList = new ArrayList<Player>();
        this.pawnList = new ArrayList<Pawn>();
        
        for(int i = 0; i < BoardConfig.NUM_PLAYERS; i++) {
           Player p = new Player(i);
           p.setStrategy(strategies[i]);
           this.playerList.add(p);
           p.setPawns(buildPawns(i,p)); 
           p.setPath(buildPath(p));
        }
    }
    
    public void createSquare(Grid g, Point position) {
        Square s;
        
        switch(g) {
            case P1_HOM:
            case P2_HOM:
            case P3_HOM:
            case P4_HOM:
                s = new Home(g, position);
                break;
            case P1_STA:
            case P2_STA:
            case P3_STA:
            case P4_STA:
                 s = new Entry(g, position);
                break;
            case P1_GOA:
            case P2_GOA:
            case P3_GOA:
            case P4_GOA:
                 s = new Goal(g, position);
                break;
            case EMP_SQ:
            case SQUARE:
            case CENTER:
            case P1_END:
            case P2_END:
            case P3_END:
            case P4_END:
            default:
                s = new Square(g, position);          
        }
        this.squareList.add(s);
        this.squares[position.x][position.y] = s;
    }
        
    /****
     * Walks a path from a starting point given a list of vectors
     * Adds these vectors to the specified path
     * 
     * @param path
     * @param vector 
     */
    public void addToPath(Path path, Point vector) {
        int xMag = vector.x;
        int yMag = vector.y;
        int xInc = 0;
        int yInc = 0;
        
        if(xMag != 0)
            xInc = xMag/Math.abs(xMag);
        if(yMag != 0)
            yInc = -yMag/Math.abs(yMag);
 
        PathSegment last = path.getLast();
       
        while(yMag != 0 || xMag != 0) {
            int xtemp = last.getSquare().getPosition().x +xInc;
            int ytem = last.getSquare().getPosition().y +yInc;
            Square s = this.getSquareAt(xtemp, ytem);
           //System.out.println("x:" + (last.getPosition().x + xInc) +" y:" + (last.getPosition().y + yInc));
            yMag += yInc;
            xMag -= xInc;
      
            if(s != null) {
               PathSegment next = new PathSegment(s);
               last.setNext(next);
               path.add(next);
               last = next;
            }
            else {
                // TODO: Throw invalid path exception
                break;
            }
        }
    }
    
    public Point[] rotatePoints(Point[] path,double rads) {
        Point[] rPath = new Point[path.length];
        //System.out.println(rads);
        for(int i = 0; i < path.length; i++) {
            rPath[i] = rotatePoint(path[i], rads);
        }
        
        return rPath;
    }
    
    
    public Point rotatePoint(Point p, double rads) {
        int x,y;
        x = p.x*(int)Math.cos(rads) - p.y*(int)Math.sin(rads);
        y = p.x*(int)Math.sin(rads) + p.y*(int)Math.cos(rads);
        //System.out.println("Old: (" + path[i].x + "," + path[i].y + ") New: (" + x + "," +  y +")");
        return new Point(x,y);
    }
    
    public Point getCentrePoint() {
    	return new Point(BoardConfig.HEIGHT/2, BoardConfig.WIDTH/2);
    }
    
    public Square getSquareAt(int row, int column) {
        Square s = null;
        try{
            s = this.squares[row][column];
        } catch (Exception e) {
            System.out.println("Attempted to get square that does not exist error: " +  e.getMessage());
        }
        return s;
    }
    
    public List<Square> getSquareList() {
        return this.squareList;
    }
    
    public List<Pawn> getPawnList() {
        return this.pawnList;
    }
    
    public List<Path> getPaths() {
        return this.paths;
    }
    
    public List<Player> getPlayers() {
        return this.playerList;
    }
    
    /**
     *
     * @param int num: 1-n 
     */
    public Player getPlayer(int num) {
        return this.playerList.get(num-1);
    }
   
}
    