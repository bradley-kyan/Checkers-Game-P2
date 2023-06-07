/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.SaveManager;
import checkersgame.Model.PieceComponents.Move;
import checkersgame.Model.MovesQueue;
import checkersgame.Model.PieceComponents.Piece;
import java.awt.Point;

/**
 *
 * @author bradl
 */
public abstract class MoveController extends SaveManager {

    private static String title;
    private static MovesQueue movesQueue;

    /**
     * Creates a new MoveController for piece movement history. Creates a new empty
     * queue for saving piece movements. Extends save controller.
     * @param title The title of the current game
     * @see SaveManager
     */
    public MoveController(String title)
    {
        if (movesQueue == null)
        {
            movesQueue = new MovesQueue();
        }
        MoveController.title = title;
    }

    /**
     * Adds a pieces to the movement queue. Used during gameplay.
     * @param piece The moved piece
     * @param moveLoaction The piece's new move location
     */
    public static void addMove(Piece piece, Point moveLoaction)
    {
        Move currentMove = new Move(piece, moveLoaction);
        currentMove.title = title;

        movesQueue.add(currentMove);
        MoveController mc = new MoveController(title) {
        };
        mc.saveToDB();
    }

    /**
     * Empties the queue and saves the moves to the database.
     * @see MovesQueue
     */
    public void saveToDB()
    {
        for (int i = 0; i < movesQueue.size(); i++)
        {
            save(movesQueue.poll());
        }
    }
}
