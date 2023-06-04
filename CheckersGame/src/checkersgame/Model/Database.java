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

/**
 *
 * @author bradl
 */
public class Database {
    
    private static Connection conn;
    private final String url = "jdbc:derby:Checkers; create=true";
    private final String username = "checkers";
    private final String password = "checkers123";
    
    public Database()
    {
        establishDBConn();
        createTables();
    }
    
    public void establishDBConn()
    {
        try
        {
            conn = DriverManager.getConnection(url, url, password);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public void createTables()
    {
        ResultSet rs = this.query("SELECT * FROM REPLAYS");
        try {
            if(rs.wasNull())
                this.query("CREATE SEQUENCE seq_move\n" +
                        "MINVALUE 1\n" +
                        "START WITH 1\n" +
                        "INCREMENT BY 1;\n" +
                        "\n" +
                        "CREATE TABLE Replays(\n" +
                        "    ID int NOT NULL PRIMARY KEY,\n" +
                        "    Title varchar(255) NOT NULL\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE ReplayData(\n" +
                        "    ID int NOT NULL,\n" +
                        "    ReplayOrder int NOT NULL,\n" +
                        "    Piece int NOT NULL,\n" +
                        "    NewPositionX int NOT NULL,\n" +
                        "    NewPositionY int NOT NULL,\n" +
                        "    CONSTRAINT FK_ReplayID FOREIGN KEY (ID)\n" +
                        "    REFERENCES Replays(ID)\n" +
                        ")");
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
    }
    
    public ResultSet query(String query)
    {
        try {      
            Statement statement = conn.createStatement();
            return statement.executeQuery(query);
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            return null;
        }
    }
}
