package checkersgame.Controller;

import checkersgame.Model.PieceComponents.PieceMoves;
import static checkersgame.Controller.MenuController.frame;
import checkersgame.Model.PieceComponents.Piece;
import checkersgame.Model.PieceComponents.Colour;
import checkersgame.Model.*;
import checkersgame.View.*;
import java.awt.Component;
import java.util.Date;

/**
 * @author bradl
 */
public class PlayableGameController extends BoardController {

    public static boolean showHint;

    /**
     * Initializes a new checkers board, and populates each side's pieces. 
     * Handles piece hints when a piece is selected.
     * @param size Number of squares the board will have in both an x and y axis
     * @see BoardController
     */
    public PlayableGameController(int size)
    {
        super();
        instanceName = "Game: " + new Date();
        showHint = true;
        frame = MenuController.frame;

        panel = new BoardPanel(size);

        panel.setSize(frame.getSize());
        playerTurn = Colour.BLACK;

        pieceArray = new PiecesArray(size);
        pieceArray.populateBoard(size);
        pieceArray.updateMoves();

        addPieces();

        panel.setVisible(true);
        frame.add(panel);

        frame.validate();
        frame.repaint();
    }

    /**
     * Adds a piece's hints to the panel when a user selects a piece.
     * @param piece The selected piece
     */
    public static void showHint(Piece piece)
    {
        if(!showHint)
            return;
        
        for (Component component : pieceController.getComponents())
        {
            if (((PieceButton) component).piece.equals(piece))
            {
                for (PieceMoves lp : piece.moves)
                {
                    HintButton hb = new HintButton(lp);
                    panel.add(hb);
                }
            }
        }
        panel.revalidate();
        panel.repaint();
    }
}
