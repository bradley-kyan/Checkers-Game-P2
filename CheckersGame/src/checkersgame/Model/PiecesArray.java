/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model;

import checkersgame.Model.PieceComponents.Rank;
import checkersgame.Model.PieceComponents.Piece;
import checkersgame.Model.PieceComponents.Colour;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author bradl
 */
public class PiecesArray {

    private static int dimension;

    /**
     * ArrayList of all the current pieces on the board
     * @see Piece
     */
    public static ArrayList<Piece> pieces;

    /**
     * Creates a new board of pieces with the correct placement.
     * @param size Board's dimensions
     */
    public PiecesArray(int size)
    {
        this.dimension = size;
        this.populateBoard(size);
    }

    /**
     * Populates the board with the correct placement and number of pieces.
     * Pieces are added to the arrayList.
     * @param dimension Size of the board
     * @see Piece
     */
    public void populateBoard(int dimension)
    {
        this.pieces = new ArrayList<Piece>();
        int pieceNum = 0;
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < dimension; x++)
            {
                if (y == 0 || y == 2)
                {
                    pieces.add(new Piece(Colour.RED, Rank.PAWN,
                            new Point(x++, y), pieceNum++));
                }
                else
                {
                    if (x++ >= dimension)
                    {
                        continue;
                    }

                    pieces.add(new Piece(Colour.RED, Rank.PAWN,
                            new Point(x, y), pieceNum++));
                }
            }
        }

        for (int y = dimension - 1; y > dimension - 4; y--)
        {
            for (int x = 0; x < dimension; x++)
            {
                if (y == dimension - 2)
                {
                    pieces.add(new Piece(Colour.BLACK, Rank.PAWN,
                            new Point(x++, y), pieceNum++));
                }
                else
                {
                    if (x++ >= dimension)
                    {
                        continue;
                    }

                    pieces.add(new Piece(Colour.BLACK, Rank.PAWN,
                            new Point(x, y), pieceNum++));
                }
            }
        }
    }

    /**
     * Update the moves that all the board's pieces can make
     * @see getMoves
     */
    public void updateMoves()
    {
        for (Piece p : pieces)
        {
            p.moves = this.getMoves(p.position);
        }
    }

    /**
     * Update the moves of a single piece
     * @param p Piece to be updated
     * @see getMoves
     */
    public void updateMoves(Piece p)
    {
        p.moves = this.getMoves(p.position);
    }

    /**
     * Finds a piece that is on the board which has the input ID
     *
     * @param ID ID of a piece
     * @return Piece object or null if no matching ID
     */
    public Piece getPiece(int ID)
    {
        Iterator it = this.pieces.iterator();

        while (it.hasNext())
        {
            Piece p = (Piece) it.next();

            if (p.getID() == ID)
            {
                return p;
            }
        }
        return null;
    }

    /**
     * Checks piece list and gets the Piece that is in the same Point location.
     *
     * @param point Board location
     * @return Piece object or null if no piece is found at specified location
     */
    public Piece getPiece(Point point)
    {
        for (Piece p : pieces)
        {
            if (p.position.equals(point))
            {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets the ArrayList of pieces.
     *
     * @return ArrayList-Piece of all alive pieces
     */
    public static ArrayList<Piece> getPieces()
    {
        return pieces;
    }

    /**
     * Moves a specified piece to a new location.Location must be valid. Valid
     *  moves are defined in piece's move list (LinkedPoint). If valid move, will
     *  update piece position and remove all pre-calculated to be removed pieces.
     *
     * @param piece Piece to be moved
     * @param location New location of piece
     * @return True if successfully moved
     * @see PieceMoves
     */
    public boolean movePiece(Piece piece, Point location)
    {
        piece = this.getPiece(piece.getPos());

        ArrayList<PieceMoves> points = piece.moves;
        boolean success = false;

        for (PieceMoves lp : points)
        {
            if (lp.toMove.equals(location))
            {
                for (Point p : lp.toBeRemoved)
                {
                    this.pieces.remove(this.getPiece(p));
                }
                piece.position.setLocation(lp.toMove);
                success = true;
            }
        }
        this.updateMoves();
        return success;
    }

    /**
     * Filters out the potential moves from the inputted moves. Filtered moves
     * are moved which a piece can move to. It calculates moved based on if
     * there is a piece in its move location, or if it can capture the
     * opponent's piece
     *
     * @param directionalMoves Pre-calculated potential moves on the diagonal
     * axis.
     * @param origin Piece which movements are for.
     * @return ArrayList-LinkedPoint containing all valid moves a piece can
     * move.
     * @see getMoves
     */
    public ArrayList<PieceMoves> filterMoves(ArrayList<ArrayList<Point>> directionalMoves, Piece origin)
    {
        ArrayList<PieceMoves> filtered = new ArrayList<PieceMoves>();

        for (ArrayList<Point> dimension : directionalMoves)
        {
            PieceMoves lp = new PieceMoves();
            Point lastPoint = null;

            for (Point p : dimension)
            {
                //There is no piece at the place we want to move to thus we can move
                if (this.getPiece(p) == null && this.getPiece(lastPoint) == null)
                {
                    lp.toMove = new Point(p);
                    lp.origin = origin;
                    filtered.add(lp);
                    break;
                }

                //There is a piece to jump
                if (this.getPiece(lastPoint) != null)
                {
                    //Can we move to the next square
                    if (this.getPiece(p) != null)
                    {
                        break; //We cannot move
                    }
                    else
                    {
                        //Check if the piece to be jumped is the same colour
                        if (this.getPiece(lastPoint).getColour().equals(origin.getColour()))
                        {
                            break;
                        }

                        lp.toMove = new Point(p);
                        lp.toBeRemoved.add(lastPoint);
                        lp.origin = origin;

                        //Add these new positions to the moveset
                        filtered.add(lp);

                        break;
                    }
                }
                lastPoint = p;
            }
        }
        return filtered;
    }

    /**
     * Gets the total number of pieces a colour has on the board.
     *
     * @param colour Colour to check
     * @return int of remaining pieces
     */
    public int remainingPieces(Colour colour)
    {
        int count = 0;

        Iterator it = pieces.iterator();
        while (it.hasNext())
        {
            Piece p = (Piece) it.next();
            if (p.getColour() == colour)
            {
                count++;
            }
        }

        return count;
    }

    /**
     * Gets all the moves which a piece can legally move to.
     *
     * @param origin Piece which movements are for.
     * @return ArrayList<LinkedPoint> containing moves a piece can move to.
     */
    private ArrayList<PieceMoves> getMoves(Point origin)
    {
        return this.filterMoves(this.potentialMoves(origin), this.getPiece(origin));
    }

    /**
     * Gets all points that are diagonal to input point.
     *
     * @param p Point on the board
     * @return Multidimensional ArrayList of points in both negative, and
     * positive diagonal directions to given point.
     * @see getMoves
     */
    private ArrayList<ArrayList<Point>> potentialMoves(Point p)
    {
        ArrayList<ArrayList<Point>> directionalMoves = new ArrayList<ArrayList<Point>>();

        ArrayList<Point> moves = new ArrayList<Point>();

        Point tempPos = new Point(p);

        while (++tempPos.x < dimension && tempPos.x >= 0)
        {
            if (++tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        }
        directionalMoves.add(moves);

        moves = new ArrayList<Point>();

        tempPos.setLocation(p);
        while (++tempPos.x < dimension && tempPos.x >= 0)
        {
            if (--tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        }
        directionalMoves.add(moves);

        moves = new ArrayList<Point>();

        tempPos.setLocation(p);
        while (--tempPos.x < dimension && tempPos.x >= 0)
        {
            if (--tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        }
        directionalMoves.add(moves);

        moves = new ArrayList<Point>();

        tempPos.setLocation(p);
        while (--tempPos.x < dimension && tempPos.x >= 0)
        {
            if (++tempPos.y < dimension && tempPos.y >= 0)
            {
                moves.add(new Point(tempPos));
            }
        }
        directionalMoves.add(moves);

        return directionalMoves;
    }
}
