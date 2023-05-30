package checkersgame.Controller;

import checkersgame.Model.PiecesArray;
import checkersgame.View.Frame;
import checkersgame.View.Panel;
import checkersgame.View.PieceComponent;

/**
 * @author bradl
 */
public class BoardController
{    
    
    public static Frame frame;
    public static Panel panel;
    public static PieceComponent pieceComponent;
    public static PiecesArray pieceArray;
    /**
     * Initializes a new checkers board, and populates each side's pieces. 
     * @param size Number of squares the board will have in both an x and y axis
     */
    public BoardController(int size)
    {       
        frame = new Frame();
        panel = new Panel(size);
        
        panel.setSize(frame.getSize());       
        
        pieceArray = new PiecesArray(size);
        pieceArray.populateBoard(size);
        pieceArray.updateMoves();
        
        PieceController pieceController = new PieceController();
        for(PieceComponent component : pieceController.getComponents())
        {
            component.setVisible(true);
            panel.add(component);
        }
        
        panel.setVisible(true);
        frame.add(panel);
    }  
    
    public static void updatePieces()
    {
        pieceArray.updateMoves();
    }
}
