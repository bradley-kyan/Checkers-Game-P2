/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author bradl
 */
public class Database {

    private static Connection conn;
    private final String url = "jdbc:derby:CheckersDB; create=true";

    /**
     * Create a new database connection and create the necessary tables to store
     * replays if they do not exist.
     */
    public Database()
    {
        establishDBConn();
        createTables();
    }
    public Database(boolean test)
    {
        
    }

    /**
     * Establish the database connection.
     */
    public void establishDBConn()
    {
        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) 
        {
            /**
             * Exception will be thrown when another instance of the program is running
             * since there can only be one connection to the database at a time. Creates 
             * a new dialogue box with the exception and quits the program.
             */
            System.out.println(e);
            JFrame errFrame = new JFrame();
            JOptionPane.showMessageDialog(errFrame, "Cannot Establish database Connection.\n\nPlease close all instances "
                    + "of the game before running!", "Fatal Game Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public Connection getConnection()
    {
        return conn;
    }
    
    /**
     * Closes the current database connection
     */
    public void disconnect()
    {
        try
        {
            conn.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        conn = null;
    }

    /**
     * Creates the necessary tables in the database if they do not exist by checking
     * if the replay table is present.
     */
    public void createTables()
    {
        ResultSet rs = this.query("SELECT TABLENAME \n"
                + "FROM SYS.SYSTABLES \n"
                + "WHERE TABLENAME = 'REPLAYS'");
        try
        {
            if (rs == null || !rs.first())
            {
                //create squence for incrementing primary keys
                this.query("CREATE SEQUENCE seq_move\n"
                        + "MINVALUE 1\n"
                        + "START WITH 1\n"
                        + "INCREMENT BY 1");
                //Create replay table to store game replays
                this.query("CREATE TABLE Replays(\n"
                        + "    ID int NOT NULL PRIMARY KEY,\n"
                        + "    Title varchar(255) NOT NULL\n"
                        + ")");
                //Create replayData table storing moves
                this.query("CREATE TABLE ReplayData(\n"
                        + "    ID int NOT NULL,\n"
                        + "    ReplayOrder int NOT NULL,\n"
                        + "    Piece int NOT NULL,\n"
                        + "    NewPositionX int NOT NULL,\n"
                        + "    NewPositionY int NOT NULL,\n"
                        + "    CONSTRAINT FK_ReplayID FOREIGN KEY (ID)\n"
                        //If a replay is deleted in the replay table, delete the accociated data in this table
                        + "    REFERENCES Replays(ID) ON DELETE CASCADE\n" 
                        + ")");
            }
            /**
             * Delete all replays that have a title of null in the case of 
             * accidental writing of moves to the database. This can be caused
             * if for some reason a piece is moved during a replay.
             */
            this.query("DELETE FROM Replays WHERE Title = 'null'");
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Run the query to the database regardless of the type. Uses execute rather 
     * than executeUpdate or executeQuery. If a resultSet can be returned from
     * the query it will be returned. ResultSet only are returned from a select
     * query, else null is returned from the getResultSet() method.
     * @param query Query to be executed
     * @return ResultSet if applicable, else null.
     */
    public ResultSet query(String query)
    {
        try
        {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.execute(query);
            return statement.getResultSet();
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
