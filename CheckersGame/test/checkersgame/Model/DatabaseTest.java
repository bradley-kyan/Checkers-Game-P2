/*
 * Copyright 2023 bradl.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package checkersgame.Model;

import checkersgame.Model.PieceComponents.Colour;
import checkersgame.Model.PieceComponents.Move;
import checkersgame.Model.PieceComponents.Piece;
import checkersgame.Model.PieceComponents.Rank;
import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bradl
 */
public class DatabaseTest {
    
    private Database instance;
    
    public DatabaseTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
        if(instance != null)
            instance.disconnect();
        instance = null;
    }

    /**
     * Test of establishDBConn method, of class Database.
     */
    @Test
    public void testEstablishDBConn()
    {
        System.out.println("Test connection to database");
        instance = new Database(true);
        instance.establishDBConn();
    }

    /**
     * Test of createTables method, of class Database.
     */
    @Test
    public void testCreateTables()
    {
        System.out.println("Test table creation");
        instance = new Database(true);
        instance.establishDBConn();
        instance.createTables();     
    }

    /**
     * Test of query method, of class Database.
     */
    @Test
    public void testQuery()
    {
        System.out.println("Test Query ability");
        String query = "";
        instance = new Database();

        ResultSet rs = instance.query("Select 'DATA' as Test from Replays");
        
        String expected = "DATA";
        String actual = "";
        
        if(rs == null)
            fail("No results returned");
        
        try
        {
            while(rs.next())            
            {
                actual = rs.getString("Test");
            }
        }
        catch (SQLException ex)
        {
            fail("Error reading data");
        }
        
        Assert.assertEquals(expected, actual);   
    }  
    
    /**
     * Test if replays can be gotten from the database
     */
    @Test
    public void getReplay()
    {
        System.out.println("Get Replay list:");
        SaveManager sm = new SaveManager();
        ResultSet rs = sm.getReplaysList();
        
        try
        {
            while(rs.next())            
            {
                System.out.println("   > " + rs.getString("Title"));
            }
        }
        catch (SQLException ex)
        {
            fail(ex.toString());
        }
        sm.disconnect();
    }
    
    /**
     * Test if null titles are removed from the database successfully on new connection
     */
    @Test
    public void testDbRemoveNullTitle()
    {
        System.out.println("Test DB removal of null titles");
        SaveManager sm = new SaveManager();
        Move move = new Move(new Piece(Colour.RED, Rank.PAWN, new Point(), 1), new Point());
        
        move.title = null;
        sm.save(move);
        
        ResultSet rs = sm.query("Select * From Replays where title = 'null'");
        try
        {
            if(!rs.next())
            {
                fail("Failed");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sm.disconnect();
        
        sm = new SaveManager();
        
        rs = sm.query("Select * From Replays where title = 'null'");
        try
        {
            if(rs.next()){     
                sm.disconnect();
                fail("Null title detected");
            }
        }
        catch (SQLException ex)
        {
            fail(ex.toString());
        }
        
        System.out.println("   > No null titles found");
    }
}
