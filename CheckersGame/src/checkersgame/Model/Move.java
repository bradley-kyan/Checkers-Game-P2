/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model;

import java.awt.Point;

/**
 *
 * @author bradl
 */
public class Move {
    
    private static Integer moveNumber;
    public int moveOrder;
    public Piece piece;
    public Point moveLocation;
    public String title;
    
    public Move(Piece piece, Point moveLocation)
    {
        if(moveNumber == null)
            moveNumber = 0;
        
        this.piece = piece;
        this.moveLocation = moveLocation;
        this.moveOrder = moveNumber++;
    }
    public Move()
    {
        
    }
}
