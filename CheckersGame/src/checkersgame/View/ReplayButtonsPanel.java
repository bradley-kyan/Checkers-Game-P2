/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.ReplayGameController;
import java.awt.Graphics;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.*;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 * @author bradl
 */
public class ReplayButtonsPanel extends JPanel {

    private BasicArrowButton b;

    public ReplayButtonsPanel(ReplayGameController controller)
    {
        b = new BasicArrowButton(EAST);
        b.addActionListener(controller);

        this.add(b);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        b.setSize(100, 100);
    }
}
