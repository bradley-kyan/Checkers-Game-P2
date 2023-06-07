/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model.PieceComponents;

import checkersgame.Model.MovesQueue;
import checkersgame.Model.PieceComponents.Piece;
import java.awt.Point;

/**
 *
 * @author bradl
 */
public class Move {

    private static Integer moveNumber; //Increment for movement order

    /**
     * Move's current placement order
     */
    public int moveOrder;

    /**
     * ID of the piece moved
     * @see Piece
     */
    public int pieceID;

    /**
     * The moved location of the piece
     */
    public Point moveLocation;

    /**
     * The title of the current game
     */
    public String title;

    /**
     * Create a new move
     * @param piece Piece to be moved
     * @param moveLocation Piece's moved location
     * @see MovesQueue
     */
    public Move(Piece piece, Point moveLocation)
    {
        if (moveNumber == null)
        {
            moveNumber = 0;
        }

        this.pieceID = piece.getID();
        this.moveLocation = moveLocation;
        this.moveOrder = moveNumber++;
    }

    /**
     * Used for manual setting of values
     */
    public Move()
    {

    }
}
