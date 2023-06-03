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

/**
 *
 * @author bradl
 */
public class Database {
    
    public static Connection conn;
    public final String url = "jdbc:derby:Checkers; create=true";
    public final String username = "checkers";
    public final String password = "checkers123";
    
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
        ResultSet rs = this.query("SELECT * FROM ");
    }
    
    public ResultSet query(String query)
    {
        try {      
            Statement statement = conn.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
