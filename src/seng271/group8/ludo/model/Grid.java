/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seng271.group8.ludo.model;

import java.awt.Color;

/**
 *
 * @author Alastairs
 */
public enum Grid {
        P1_STA(ColorConfig.P1_SQ_COLOR),
        P2_STA(ColorConfig.P2_SQ_COLOR),
        P3_STA(ColorConfig.P3_SQ_COLOR),
        P4_STA(ColorConfig.P4_SQ_COLOR),
        P1_END(ColorConfig.SQUARE),
        P2_END(ColorConfig.SQUARE),
        P3_END(ColorConfig.SQUARE),
        P4_END(ColorConfig.SQUARE),
        P1_GOA(ColorConfig.P1_SQ_COLOR),
        P2_GOA(ColorConfig.P2_SQ_COLOR),
        P3_GOA(ColorConfig.P3_SQ_COLOR),
        P4_GOA(ColorConfig.P4_SQ_COLOR),
        P1_HOM(ColorConfig.P1_SQ_COLOR),
        P2_HOM(ColorConfig.P2_SQ_COLOR),
        P3_HOM(ColorConfig.P3_SQ_COLOR),
        P4_HOM(ColorConfig.P4_SQ_COLOR),
        SQUARE(ColorConfig.SQUARE),
        EMP_SQ(ColorConfig.EMPTY_SQ),
        CENTER(ColorConfig.EMPTY_SQ);
        
        private Color c;
        
        private Grid(Color c) {
            this.c = c;
        }
        
        public Color getColor() {
            return this.c;
        }
        
    }
