package checkersgame.Model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author bradl
 */
public class Piece {

    private Colour colour;
    private Rank rank;
    private Integer ID;
    public Point position;
    public int direction;
    public ArrayList<LinkedPoint> moves;
    
    public Character hintIdentifier;
    
    /**
     * initializes piece and creates the IDs for the pieces in increase numbers
     * @param colour the colour of the piece
     * @param rank the rank of the piece (king,pawn)
     * @param pos the initial position of the piece
     */
    public Piece(Colour colour, Rank rank, Point pos, int ID)
    {
        this.colour = colour;
        this.rank = rank;
        
        if(colour == Colour.RED)
            direction = 1;
        else
            direction = -1;

        this.ID = ID;
        
        this.position = pos;
    }
    
    public boolean equals(Piece p)
    {
        if(p.ID.equals(this.ID))
            return true;
        return false;
    }
    
    public Piece(char c)
    {
        hintIdentifier = c;
    }
    
    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public Point getPos()
    {
        return this.position;
    }
    
    public void setPos(Point pos)
    {
        this.position = pos;
    }   
}
