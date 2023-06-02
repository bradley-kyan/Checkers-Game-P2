package checkersgame.Controller;

import checkersgame.Model.LinkedPoint;
import checkersgame.Model.Piece;
import checkersgame.Model.PiecesArray;
import checkersgame.View.Frame;
import checkersgame.View.HintButton;
import checkersgame.View.Panel;
import checkersgame.View.PieceComponent;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BoxLayout;

/**
 * @author bradl
 */
public class BoardController
{    
    
    public static Frame frame;
    public static Panel panel;
    public static PieceComponent pieceComponent;
    public static PiecesArray pieceArray;
    public static PieceController pieceController;
    /**
     * Initializes a new checkers board, and populates each side's pieces. 
     * @param size Number of squares the board will have in both an x and y axis
     */
    public BoardController(int size)
    {       
        frame = new Frame();
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        panel = new Panel(size);

        panel.setSize(frame.getSize());       
        
        pieceArray = new PiecesArray(size);
        pieceArray.populateBoard(size);
        pieceArray.updateMoves();
        
        addPieces();
        
        panel.setVisible(true);
        frame.add(panel);
        
        frame.validate();
        frame.repaint();
    }  
    
    public static void addPieces()
    {
        pieceController = new PieceController();
        panel.removeAll();
        for(Component component : pieceController.getComponents())
        {
            component.setVisible(true);
            panel.add(component);
        }
        panel.revalidate();
        panel.repaint();
    }
    
    public static void updatePieces()
    {
        pieceArray.updateMoves();
    }
    
    public static void movePiece(Piece piece, Point location)
    {
        pieceArray.movePiece(piece, location);
        updatePieces();
        PieceController.updatePieces();
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
