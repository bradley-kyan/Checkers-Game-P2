/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author bradl
 */
public class MovesQueue{
    
    private Queue<Move> queue;
    
    public MovesQueue()
    {
        queue = new ConcurrentLinkedQueue<Move>();
    } 
    public void add(Move move)
    {
        queue.add(move);
    }
    public Move poll()
    {
        return queue.poll();
    }
    public Move peek()
    {
        return queue.peek();
    }
    public int size()
    {
        return queue.size();
    }
}
