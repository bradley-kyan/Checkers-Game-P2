/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.Database;
import checkersgame.Model.Move;
import checkersgame.Model.MovesQueue;
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
public abstract class SaveController extends Database {

    public SaveController()
    {
        super();
    }

    protected void save(Move move)
    {
        String query;
        String title = move.title;
        int pieceID = move.pieceID;
        int moveX = move.moveLocation.x;
        int moveY = move.moveLocation.y;
        int moveOrder = move.moveOrder;

        query = "SELECT * FROM REPLAYS WHERE TITLE = '" + title + "'";

        ResultSet rs = this.query(query);
        try
        {
            if (!rs.first())
            {
                query = "INSERT INTO REPLAYS (ID, Title)"
                        + "VALUES(NEXT VALUE FOR seq_move, '" + title + "')";

                this.query(query);

            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SaveController.class.getName()).log(Level.SEVERE, null, ex);
        }

        query = "INSERT INTO ReplayData VALUES(\n"
                + "(select ID from Replays Where title = '" + title + "'), \n"
                + moveOrder + ", \n"
                + pieceID + ", \n"
                + moveX + ", \n"
                + moveY + ")";
        this.query(query);

    }

    protected ResultSet getReplaysList()
    {
        String query = "SELECT * FROM Replays";
        return this.query(query);
    }

    protected ResultSet getReplay(int replayID)
    {
        String query = "SELECT * FROM ReplayData WHERE ID = " + replayID
                + " ORDER BY ReplayOrder ASC";
        return this.query(query);
    }

    protected MovesQueue getMoves(int replayID)
    {

        PiecesArray pa = new PiecesArray(8);
        MovesQueue queue = new MovesQueue();

        ResultSet rs = getReplay(replayID);
        try
        {
            while (rs.next())
            {
                Move move = new Move();
                Point point = new Point();

                move.pieceID = rs.getInt("Piece");
                point.x = rs.getInt("NewPositionX");
                point.y = rs.getInt("NewPositionY");
                move.moveOrder = rs.getInt("ReplayOrder");
                move.moveLocation = point;
                queue.add(move);

            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }

        return queue;
    }
}
