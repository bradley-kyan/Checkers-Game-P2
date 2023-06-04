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
public class MenuController implements ActionListener{
    private PlayController board;
    public static Frame frame;
    private MenuPanel menuPanel;

    public MenuController()
    {
        frame = new Frame();
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        menuPanel = new MenuPanel(this);
        frame.add(menuPanel);
        
        
        frame.validate();
        frame.repaint();
        //board = new PlayController(8, frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton o = (JButton)e.getSource();
        
        if(o.getName() == "Play")
        {
            menuPanel.setEnabled(false);
            menuPanel.setVisible(false);
            board = new PlayController(8);
            frame.revalidate();
            frame.repaint();
        }    
        if(o.getName() == "Replays")
        {
            menuPanel.setEnabled(false);
            menuPanel.setVisible(false);
            frame.revalidate();
            frame.repaint();
        }
    }
}
