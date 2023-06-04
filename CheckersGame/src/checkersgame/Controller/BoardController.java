package checkersgame.Controller;

import checkersgame.Model.*;
import checkersgame.View.*;
import java.awt.Component;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * @author bradl
 */
public class BoardController extends PieceController
{    
    private static String instanceName;
    private static Frame frame;
    /**
     * Initializes a new checkers board, and populates each side's pieces. 
     * @param size Number of squares the board will have in both an x and y axis
     */
    public BoardController(int size)
    {                   
        instanceName = "Game: " + new Date();
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
    
    public BoardController(int size, String replayName)
    {
        instanceName = replayName;
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
        
    public static void showHint(Piece piece)
    {
        for(Component component : pieceController.getComponents())
        {            
            if(((PieceComponent)component).piece.equals(piece))
            {
                for(LinkedPoint lp : piece.moves)
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
