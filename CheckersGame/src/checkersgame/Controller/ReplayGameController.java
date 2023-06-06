/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import static checkersgame.Controller.BoardController.addPieces;
import static checkersgame.Controller.BoardController.panel;
import static checkersgame.Controller.BoardController.pieceArray;
import static checkersgame.Controller.BoardController.playerTurn;
import checkersgame.Model.Colour;
import checkersgame.Model.Move;
import checkersgame.Model.MovesQueue;
import checkersgame.Model.Piece;
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

    public ReplayGameController(MovesQueue mq, int size)
    {
        super();

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

    public void nextMove()
    {
        Move move = queue.poll();
        if (move == null)
        {
            return;
        }

        pieceArray.updateMoves();
        Piece p = pieceArray.getPiece(move.pieceID);
        pieceArray.movePiece(p, move.moveLocation);

        addPieces();
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        nextMove();
    }
}
