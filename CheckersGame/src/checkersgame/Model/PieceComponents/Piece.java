package checkersgame.Model.PieceComponents;

import checkersgame.Model.PieceMoves;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author bradl
 */
public class Piece {

    private Colour colour;
    private Rank rank;
    private Integer ID;

    /**
     * The current position on the board
     */
    public Point position;

    /**
     * The direction the piece can move
     */
    public int direction;

    /**
     * The legal moves that the piece can move in the next turn 
     */
    public ArrayList<PieceMoves> moves;

    /**
     * @deprecated 
     * The identifier of the hint for selection eg, a,b,c.
     */
    public Character hintIdentifier;

    /**
     * initializes piece and creates the IDs for the pieces in increase numbers
     *
     * @param colour the colour of the piece
     * @param rank the rank of the piece (king,pawn)
     * @param pos the initial position of the piece
     * @param ID ID if the piece
     */
    public Piece(Colour colour, Rank rank, Point pos, int ID)
    {
        this.colour = colour;
        this.rank = rank;

        if (colour == Colour.RED)
        {
            direction = 1;
        }
        else
        {
            direction = -1;
        }

        this.ID = ID;

        this.position = pos;
    }

    /**
     * Check if the piece is the same as another piece by comparing IDs.
     * @param p Piece to compare against
     * @return Boolean - true if equal
     */
    public boolean equals(Piece p)
    {
        if (p.ID.equals(this.ID))
        {
            return true;
        }
        return false;
    }

    /**
     * Set the hint's character
     * @deprecated 
     * @param c Character associated with the hint
     */
    public Piece(char c)
    {
        hintIdentifier = c;
    }

    /**
     * Get the piece's colour
     * @return Colour
     * @see Colour
     */
    public Colour getColour()
    {
        return colour;
    }

    /**
     * Set the piece's colour
     * @param colour The piece's new colour
     * @see Colour
     */
    public void setColour(Colour colour)
    {
        this.colour = colour;
    }

    /**
     * Get the rank of the piece
     * @return Rank
     * @see Rank
     */
    public Rank getRank()
    {
        return rank;
    }

    /**
     * Set the piece's rank
     * @param rank The rank of the piece
     * @see Rank
     */
    public void setRank(Rank rank)
    {
        this.rank = rank;
    }

    /**
     * Get the ID associated with the piece
     * @return Integer
     */
    public Integer getID()
    {
        return ID;
    }

    /**
     * Set the ID of the piece.
     * @param ID ID of piece
     */
    public void setID(Integer ID)
    {
        this.ID = ID;
    }

    /**
     * Get the position that the piece is currently on the board
     * @return Point - Piece's position
     * @see Point
     */
    public Point getPos()
    {
        return this.position;
    }

    /**
     * Set the position that the piece is on the board
     * @param pos - Pieces new position
     * @see Point
     */
    public void setPos(Point pos)
    {
        this.position = pos;
    }
}
