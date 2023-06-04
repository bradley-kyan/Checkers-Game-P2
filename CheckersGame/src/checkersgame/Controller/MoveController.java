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

/**
 *
 * @author bradl
 */
public abstract class MoveController extends SaveController{
    
    private static String title;
    private static MovesQueue movesQueue;
    public MoveController(String title)
    {
        if(movesQueue == null)
            movesQueue = new MovesQueue();
        MoveController.title = title;
    }
    
    public static void addMove(Piece piece, Point moveLoaction)
    {
        Move currentMove = new Move(piece, moveLoaction);
        currentMove.title = title;
        
        movesQueue.add(currentMove);
        MoveController mc = new MoveController(title) {};
        mc.saveToDB();
        
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
