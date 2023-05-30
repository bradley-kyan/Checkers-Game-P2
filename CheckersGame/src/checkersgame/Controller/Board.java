package checkersgame.Controller;

import checkersgame.Model.Colour;
import checkersgame.Model.LinkedPoint;
import checkersgame.Model.Rank;
import checkersgame.Model.Piece;
import checkersgame.Model.PiecesArray;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author bradl
 */
public class Board
{    
    /**
     * Initializes a new checkers board, and populates each side's pieces. 
     * @param size Number of squares the board will have in both an x and y axis
     */
    public Board(int size)
    {
        PiecesArray piecesArray = new PiecesArray();     
        piecesArray.populateBoard(size);
    }
}
