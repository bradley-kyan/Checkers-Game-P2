/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model.PieceComponents;

import checkersgame.Model.PieceComponents.Piece;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author bradl
 */
public class PieceMoves {

    /**
     * Piece's location after move
     */
    public Point toMove;

    /**
     * Piece which this move is for
     */
    public Piece origin;

    /**
     * Pieces to be removed in the process of this move
     */
    public ArrayList<Point> toBeRemoved;

    /**
     * Initializes the object which will contain a Pieces valid moves, and all
     * pieces that will be removed in the process.
     */
    public PieceMoves()
    {
        this.toBeRemoved = new ArrayList<Point>();
    }
}
