/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model;

import checkersgame.Model.PieceComponents.Move;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author bradl
 */
public class MovesQueue {

    private Queue<Move> queue;

    /**
     * Creates a new queue for storing piece moves in order. Uses a ConcurrentLinkedQueue
     * @see Move
     * @see ConcurrentLinkedQueue
     * @see Queue
     */
    public MovesQueue()
    {
        queue = new ConcurrentLinkedQueue<Move>();
    }

    /**
     * Add a move to the queue
     * @param move Move to be added
     * @see Move
     */
    public void add(Move move)
    {
        queue.add(move);
    }

    /**
     * Remove the head element of the queue and return the move
     * @return Move
     * @see Move head element
     */
    public Move poll()
    {
        return queue.poll();
    }

    /**
     * Check the head element of the queue without removing it.
     * @return Move
     * @see Move head element
     * @see Queue
     */
    public Move peek()
    {
        return queue.peek();
    }

    /**
     * Gets the number of elements stored in the queue
     * @return int
     * @see Queue
     */
    public int size()
    {
        return queue.size();
    }
}
