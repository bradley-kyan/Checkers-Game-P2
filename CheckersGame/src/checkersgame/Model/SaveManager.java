/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model;

import checkersgame.Model.Database;
import checkersgame.Model.PieceComponents.Move;
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
public abstract class SaveManager extends Database {

    /**
     * Creates a new SaveController for communicating with the database.
     * Establishes a new connection with the database
     * @see Database
     */
    public SaveManager()
    {
        super();
    }

    /**
     * Saves the current move to the database. If there is no replay associated
     * with the move, a new replay will be created.
     * @param move The move to be saved.
     */
    protected void save(Move move)
    {
        String query;
        String title = move.title;
        int pieceID = move.pieceID;
        int moveX = move.moveLocation.x;
        int moveY = move.moveLocation.y;
        int moveOrder = move.moveOrder;

        query = "SELECT * FROM REPLAYS WHERE TITLE = '" + title + "'";

        //Checks if the replay exists
        ResultSet rs = this.query(query);
        try
        {
            if (!rs.first()) //Create a new entry in the replay table with the title of the game
            {
                query = "INSERT INTO REPLAYS (ID, Title)"
                        + "VALUES(NEXT VALUE FOR seq_move, '" + title + "')";

                this.query(query);

            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex); //Should never be called unless the database stops for some reason
        }

        //Insert into the database the move information accordingly
        query = "INSERT INTO ReplayData VALUES(\n"
                + "(select ID from Replays Where title = '" + title + "'), \n"
                + moveOrder + ", \n"
                + pieceID + ", \n"
                + moveX + ", \n"
                + moveY + ")";
        this.query(query);

    }

    /**
     * Gets the list of all stored replays.
     * @return ResultSet of replays
     */
    protected ResultSet getReplaysList()
    {
        String query = "SELECT * FROM Replays";
        return this.query(query);
    }

    /**
     * Return the move replay data from the given replay ID in a raw ResultSet format
     * @param replayID ID of the replay in the replay table
     * @return ResultSet of moves for the replay
     */
    protected ResultSet getReplay(int replayID)
    {
        String query = "SELECT * FROM ReplayData WHERE ID = " + replayID
                + " ORDER BY ReplayOrder ASC";
        return this.query(query);
    }

    /**
     * Get a moves queue from from the database contain all the moves for the
     * specified replay. Converts the resultSet from getReplay to a MovesQueue.
     * @param replayID ID of the replay in the replay table
     * @return MovesQueue queue
     * @see MovesQueue
     */
    protected MovesQueue getMoves(int replayID)
    {

        PiecesArray pa = new PiecesArray(8);
        MovesQueue queue = new MovesQueue();

        ResultSet rs = getReplay(replayID);
        try
        {
            while (rs.next())
            {
                //Create a new move with the data from the current result.
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
