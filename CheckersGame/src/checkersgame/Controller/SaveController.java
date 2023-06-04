/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.Database;
import checkersgame.Model.Move;
import checkersgame.Model.MovesQueue;
import java.sql.ResultSet;

/**
 *
 * @author bradl
 */
public abstract class SaveController extends Database{
   
    public SaveController()
    {
        super();
    }
    
    protected void save(Move move)
    {
        String query;
        String title = move.title;
        int pieceID = move.piece.getID();
        int moveX = move.moveLocation.x;
        int moveY = move.moveLocation.y;
        int moveOrder = move.moveOrder;
        
        query = "SELECT * FROM REPLAYS WHERE TITLE = '" + title + "'";
        
        ResultSet rs = this.query(query);
        if(rs == null)
        {
            query = "INSERT INTO REPLAYS (ID, Title)"
                    + "VALUES(seq_move.nextval, " + title + ")";

            this.query(query);          
        } 
        
        query = "INSERT INTO ReplayData VALUES("
                + "(select ID from Replays Where title = " + title + ")," + moveOrder + ", "
                + pieceID + ", " + moveX + ", " + moveY + ")";
        
    }
    protected ResultSet getReplaysList()
    {
        String query = "SELECT * FROM Replays";
        return this.query(query);
    }
    protected ResultSet getReplay(int replayID)
    {
        String query = "SELECT * FROM ReplayData WHERE ID = " + replayID +
                " ORDER BY ReplayOrder ASC";
        return this.query(query);
    }
    protected abstract MovesQueue getMoves(int replayID);
}
