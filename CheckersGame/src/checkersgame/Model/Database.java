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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author bradl
 */
public class Database {

    private static Connection conn;
    private final String url = "jdbc:derby:CheckersDB; create=true";

    public Database()
    {
        establishDBConn();
        createTables();
    }

    public void establishDBConn()
    {
        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            System.out.println(e);
            JFrame errFrame = new JFrame();
            JOptionPane.showMessageDialog(errFrame, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void createTables()
    {
        ResultSet rs = this.query("SELECT TABLENAME \n"
                + "FROM SYS.SYSTABLES \n"
                + "WHERE TABLENAME = 'REPLAYS'");
        try
        {
            if (rs == null || !rs.first())
            {
                this.query("CREATE SEQUENCE seq_move\n"
                        + "MINVALUE 1\n"
                        + "START WITH 1\n"
                        + "INCREMENT BY 1");

                this.query("CREATE TABLE Replays(\n"
                        + "    ID int NOT NULL PRIMARY KEY,\n"
                        + "    Title varchar(255) NOT NULL\n"
                        + ")");

                this.query("CREATE TABLE ReplayData(\n"
                        + "    ID int NOT NULL,\n"
                        + "    ReplayOrder int NOT NULL,\n"
                        + "    Piece int NOT NULL,\n"
                        + "    NewPositionX int NOT NULL,\n"
                        + "    NewPositionY int NOT NULL,\n"
                        + "    CONSTRAINT FK_ReplayID FOREIGN KEY (ID)\n"
                        + "    REFERENCES Replays(ID)\n"
                        + ")");
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

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
