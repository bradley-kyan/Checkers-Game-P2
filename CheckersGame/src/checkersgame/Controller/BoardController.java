package checkersgame.Controller;

import checkersgame.Model.PieceComponents.Piece;
import checkersgame.Model.PieceComponents.Colour;
import checkersgame.Model.*;
import checkersgame.View.*;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author bradl
 */
public class BoardController extends MoveController {

    protected static String instanceName;
    public static BoardPanel panel;
    public static PieceButton pieceComponent;
    public static PiecesArray pieceArray;
    public static BoardController pieceController;
    public static SidePanel sidePanelFrame;
    public static Colour playerTurn;
    private static ArrayList<Component> components;

    /**
     *  Initializes a new Board Controller. This is used for controlling the
     *  representation of the game of the frame. It handles pieces that are
     *  displayed on the screen.
     * @see MoveController
     */
    public BoardController()
    {
        super(instanceName);
        components = new ArrayList<Component>();
        if (PiecesArray.pieces == null)
        {
            return;
        }

        sidePanelFrame = new SidePanel(this);
        updatePieces();
    }

    /**
     * Gets an arrayList of all the current components (pieces) currently on the
     * board.
     * @return ArrayList of Components
     * @see Component
     */
    public ArrayList<Component> getComponents()
    {
        updatePieces();
        return components;
    }

    /**
     * Updates the board's components to have the correct location as defined by
     * the pieces in the PiecesArray model class which handles all piece data.
     */
    public static void updatePieces()
    {
        if (components == null)
        {
            return;
        }

        components = new ArrayList<Component>();

        for (Piece piece : PiecesArray.getPieces())
        {
            if (piece == null)
            {
                continue;
            }
            PieceButton pc = new PieceButton(piece);

            components.add(pc);
        }
    }

    /**
     * Resets the board by removing all components and re-adding components to
     * the display panel.
     */
    public static void addPieces()
    {
        pieceController = new BoardController();
        panel.removeAll();
        for (Component component : pieceController.getComponents())
        {
            component.setVisible(true);
            panel.add(component);
        }
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Moves a piece from one location to another. If the piece trying to be moved
     * is not of the same colour as the player turn, it will not allow the move.
     * Will pass the move information to the PiecesArray model for further move
     * handling.
     * @param piece The piece to be moved
     * @param location The location of the move as defined by the piece's move hint.
     */
    public static void movePiece(Piece piece, Point location)
    {
        if (piece.getColour() != playerTurn)
        {
            return;
        }

        inverseColour();

        if (pieceArray.movePiece(piece, location))
        {
            addMove(piece, location);
        }

        updatePieces();
    }

    /**
     * Switches the current player to move to the other.
     */
    public static void inverseColour()
    {
        if (playerTurn == Colour.RED)
        {
            playerTurn = Colour.BLACK;
        }
        else
        {
            playerTurn = Colour.RED;
        }
    }
}
