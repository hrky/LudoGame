/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.awt.Point;

/**
 *
 * @author Alastairs
 * 
 * Defines the board area and square types.
 */
public class BoardConfig {
    
    public static final int WIDTH = 11;
    public static final int HEIGHT = 11;
    public static final int NUM_PLAYERS = 4;
    public static final int NUM_PAWNS = 4;
    
    /* Defines the layout of the board. Note that this array is indexed in
     * [row][column] order and is transposed in the Board class to [x][y] cords
     * 
     *   Y 
     * x | -------------------------- +
     *   |(0,0)
     *   |     (1,1)
     *   |
     *   |
     *   |
     *   |
     *   |
     *   +
     * 
     ***/ 
    
    public static final Grid[][] MAP = {
        {Grid.P1_HOM,Grid.P1_HOM,Grid.EMP_SQ,Grid.EMP_SQ,Grid.SQUARE,Grid.P2_END,Grid.P2_STA,Grid.EMP_SQ,Grid.EMP_SQ,Grid.P2_HOM,Grid.P2_HOM},
        {Grid.P1_HOM,Grid.P1_HOM,Grid.EMP_SQ,Grid.EMP_SQ,Grid.SQUARE,Grid.P2_GOA,Grid.SQUARE,Grid.EMP_SQ,Grid.EMP_SQ,Grid.P2_HOM,Grid.P2_HOM},
        {Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.SQUARE,Grid.P2_GOA,Grid.SQUARE,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ},
        {Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.SQUARE,Grid.P2_GOA,Grid.SQUARE,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ},
        {Grid.P1_STA,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.P2_GOA,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE},
        {Grid.P1_END,Grid.P1_GOA,Grid.P1_GOA,Grid.P1_GOA,Grid.P1_GOA,Grid.CENTER,Grid.P3_GOA,Grid.P3_GOA,Grid.P3_GOA,Grid.P3_GOA,Grid.P3_END},
        {Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.P4_GOA,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.SQUARE,Grid.P3_STA},
        {Grid.EMP_SQ,Grid.		EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.SQUARE,Grid.P4_GOA,Grid.SQUARE,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ},
        {Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.SQUARE,Grid.P4_GOA,Grid.SQUARE,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ,Grid.EMP_SQ},
        {Grid.P4_HOM,Grid.P4_HOM,Grid.EMP_SQ,Grid.EMP_SQ,Grid.SQUARE,Grid.P4_GOA,Grid.SQUARE,Grid.EMP_SQ,Grid.EMP_SQ,Grid.P3_HOM,Grid.P3_HOM},
        {Grid.P4_HOM,Grid.P4_HOM,Grid.EMP_SQ,Grid.EMP_SQ,Grid.P4_STA,Grid.P4_END,Grid.SQUARE,Grid.EMP_SQ,Grid.EMP_SQ,Grid.P3_HOM,Grid.P3_HOM}
    };
   
    // Shape of path around board as seen from player 1's perspective (Top-left)
    // Stored as vector [x-dir, y-dir], eg: [4,0]==[4 squares right, zero left]
    public static final Point[] PATH = {new Point(4,0),new Point(0,4),new Point(2,0),new Point(0,-4),
                                        new Point(4,0),new Point(0,-2),new Point(-4,0),new Point(0,-4),
                                        new Point(-2,0),new Point(0,4),new Point(-4,0),new Point(0,1),
                                        new Point(4,0)};
    // Starting grid position for each player in [x,y] coordinates
    public static final Point[] START_SQUARES = {new Point(0,4),new Point(6,0),new Point(10,6),new Point(4,10)};
    // #degrees (in radians) each players path is offset from player 1
    // starting at player one (top-right) and rotating clockwise
    public static final double ROTATION_OFFSET = -Math.PI/2;
    // Location of top left home square for each	 player
    public static final Point[] PAWN_HOME = {new Point(0,0), new Point(WIDTH-2,0), new Point(WIDTH-2, HEIGHT-2), new Point(0, HEIGHT-2)};
    // Pawns offset
    public static final Point[] PAWN_OFFSETS =  {new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1)};
            
    /*
     *  Thought about doing something like this but the path method 
     *  allows the board to be changed pretty quickly
    public static final int[] PATH = {
         0, 0, 0, 0, 9,10,11, 0, 0, 0, 0,
         0, 0, 0, 0, 8, 0,12, 0, 0, 0, 0,
         0, 0, 0, 0, 7, 0,13, 0, 0, 0, 0,
         0, 0, 0, 0, 6, 0,14, 0, 0, 0, 0,
         1, 2, 3, 4, 5, 0,15,16,17,18,19,
        40, 0, 0, 0, 0, 0, 0, 0, 0, 0,20,
        39,38,37,36,35, 0,25,24,23,22,21,
         0, 0, 0, 0,34, 0,26, 0, 0, 0, 0,
         0, 0, 0, 0,33, 0,27, 0, 0, 0, 0,
         0, 0, 0, 0,32, 0,28, 0, 0, 0, 0,
         0, 0, 0, 0,31,30,29, 0, 0, 0 ,0
    };*/
}
