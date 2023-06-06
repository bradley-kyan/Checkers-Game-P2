/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

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
public class ReplayMenuController extends SaveController implements ActionListener {

    public ReplayMenuPanel replayPanel;
    private MenuController menu;
    private ReplayGameController board;
    private MovesQueue moves;

    public ReplayMenuController(MenuController menu)
    {
        super();
        replayPanel = new ReplayMenuPanel(this);
        addButtons();
        this.menu = menu;
    }

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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String name = ((JButton) e.getSource()).getName();
        Integer ID = null;
        try
        {
            ID = Integer.valueOf(name);
        }
        catch (NumberFormatException ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
        }

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

    private void startReplay()
    {
        replayPanel.setVisible(false);
        replayPanel.setEnabled(false);

        board = new ReplayGameController(moves, 8);
    }
}
