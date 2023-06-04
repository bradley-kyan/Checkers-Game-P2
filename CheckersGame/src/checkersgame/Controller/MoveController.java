/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.Move;
import checkersgame.Model.MovesQueue;
import checkersgame.Model.Piece;
import checkersgame.Model.PiecesArray;
import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bradl
 */
public class MoveController extends SaveController{
    
    private static MovesQueue movesQueue;
    public MoveController()
    {
        super();
        movesQueue = new MovesQueue();
    }
    
    public static void addMove(String title, Piece piece, Point moveLoaction)
    {
        Move currentMove = new Move(piece, moveLoaction);
        currentMove.title = title;
        
        movesQueue.add(currentMove);
    }
    public void saveToDB()
    {
        for(int i = 0; i < movesQueue.size(); i++)
        {
            save(movesQueue.poll());
        }
    }

    @Override
    protected MovesQueue getMoves(int replayID) {
        
        PiecesArray pa = new PiecesArray(8);
        MovesQueue queue = new MovesQueue();
        
        ResultSet rs = super.getReplay(replayID);
        try 
        {
            while(rs.next()) 
            {
                Move move = new Move();
                Point point = new Point();

                move.piece = pa.getPiece(rs.getInt("Piece"));
                point.x = rs.getInt("NewPositionX");
                point.y = rs.getInt("NewPositionY");
                move.moveOrder = rs.getInt("ReplayOrder");
                move.moveLocation = point;
                queue.add(move);

            }
        } catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        
        return queue;
    }
}
