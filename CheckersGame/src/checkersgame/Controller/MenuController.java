/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.View.Frame;
import checkersgame.View.MenuPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 *
 * @author bradl
 */
public class MenuController implements ActionListener {

    private PlayableGameController board;
    public static Frame frame;
    private MenuPanel menuPanel;
    private ReplayMenuController replayMenu;

    /**
     * Main entry point for starting the program. Will generate a new display frame
     * for the program, and creates a menu for starting a new game, or reviewing previous 
     * game replays.
     * @see ActionListener
     */
    public MenuController()
    {
        frame = new Frame();
        frame.setTitle("Checkers?!?");
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        menuPanel = new MenuPanel(this); //Create a new menu panel
        replayMenu = new ReplayMenuController(this); //Creates a new panel for displaying replays
        frame.add(menuPanel);

        frame.validate();
        frame.repaint();
    }

    /**
     * Generic Action Listener for getting menu button inputs. Inputs are determined
     * by the name of the button that created the action event.
     * Will either create a new game, or display a list of previous replays.
     * @param e Action Event from a button
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton o = (JButton) e.getSource();

        if (o.getName() == "Play") //Starts a new checkers game.
        {
            menuPanel.setEnabled(false);
            menuPanel.setVisible(false);
            this.board = new PlayableGameController(8);

            frame.revalidate();
            frame.repaint();
        }
        if (o.getName() == "Replays")
        {
            //Hides the current menu
            menuPanel.setEnabled(false);
            menuPanel.setVisible(false);

            //Adds the replay panel to the frame for viewing replays.
            frame.add(replayMenu.replayPanel);
            replayMenu.replayPanel.setVisible(true);
            replayMenu.replayPanel.setEnabled(true);

            frame.revalidate();
            frame.repaint();
        }
    }
}
