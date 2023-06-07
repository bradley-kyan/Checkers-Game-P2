/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import static checkersgame.Controller.BoardController.addPieces;
import static checkersgame.Controller.BoardController.panel;
import static checkersgame.Controller.BoardController.pieceArray;
import static checkersgame.Controller.BoardController.playerTurn;
import checkersgame.Model.PieceComponents.Colour;
import checkersgame.Model.PieceComponents.Move;
import checkersgame.Model.MovesQueue;
import checkersgame.Model.PieceComponents.Piece;
import checkersgame.Model.PiecesArray;
import checkersgame.View.BoardPanel;
import checkersgame.View.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author bradl
 */
public class ReplayGameController extends BoardController implements ActionListener {

    private Frame frame;
    private MovesQueue queue;

    /**
     * Creates a new replay instance, similar to PlayableGameController, but
     * without allowing the user to move, or creating a new database game entry.
     * @param mq MoveQueue with piece movement history
     * @param size Size of the board
     * @see BoardController
     */
    public ReplayGameController(MovesQueue mq, int size)
    {
        super();
        PlayableGameController.showHint = false;
        
        queue = mq;
        panel = new BoardPanel(size);
        frame = MenuController.frame;

        panel.setSize(frame.getSize());
        playerTurn = Colour.BLACK;

        pieceArray = new PiecesArray(size);
        pieceArray.populateBoard(size);
        pieceArray.updateMoves();

        addPieces();

        panel.setVisible(true);
        frame.add(panel);
        sidePanelFrame.addReplayFunction(this);

        frame.validate();
        frame.repaint();
    }

    /**
     * Displays the next move in the replay history
     */
    public void nextMove()
    {
        Move move = queue.poll();
        if (move == null)
        {
            return;
        }

        pieceArray.updateMoves();
        Piece p = pieceArray.getPiece(move.pieceID);
        BoardController.movePiece(p, move.moveLocation);

        addPieces();
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * Action Listener used by buttons to move one step forward in the replay 
     * history.
     * @param e Button action event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        nextMove();
    }
}
