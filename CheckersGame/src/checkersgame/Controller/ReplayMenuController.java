/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.SaveManager;
import checkersgame.Model.MovesQueue;
import checkersgame.View.ReplayMenuPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;

/**
 *
 * @author bradl
 */
public class ReplayMenuController extends SaveManager implements ActionListener {

    public ReplayMenuPanel replayPanel;
    private MenuController menu;
    private ReplayGameController board;
    private MovesQueue moves;

    /**
     * Creates a new panel for displaying all game replays stored in the database.
     * @param menu Menu controller
     * @see SaveManager
     */
    public ReplayMenuController(MenuController menu)
    {
        super();
        replayPanel = new ReplayMenuPanel(this);
        addButtons();
        this.menu = menu;
    }
    
    /**
     * Adds all buttons to the replay list which correspond to a replay in the 
     * database.
     */
    private void addButtons()
    {
        ResultSet replays = this.getReplaysList();
        try
        {
            while (replays.next())
            {
                String title = replays.getString("Title");
                int ID = replays.getInt("ID");
                replayPanel.addButton(title, ID);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Action listener for replay buttons for selecting a new replay to watch.
     * @param e Button press action event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        /**
         * Checks if the button's name is valid. Button names correspond to a 
         * database entry
         */
        String name = ((JButton) e.getSource()).getName();
        Integer ID = null;
        try
        {
            ID = Integer.valueOf(name);
        }
        catch (NumberFormatException ex)
        {
            //There was an error, this under normal conditions will never be run
            System.out.println(ex);
            ex.printStackTrace();
        }
        
        //Create a new MoveQueue and fill it with the moves from the replay.
        MovesQueue mq = null;
        if (ID != null)
        {
            mq = this.getMoves(ID);
        }
        if (mq == null)
        {
            return;
        }

        moves = mq;
        this.startReplay();
    }

    /***
     * Hides the current replay list and creates a new replay game instance.
     */
    private void startReplay()
    {
        replayPanel.setVisible(false);
        replayPanel.setEnabled(false);

        board = new ReplayGameController(moves, 8);
    }
}
