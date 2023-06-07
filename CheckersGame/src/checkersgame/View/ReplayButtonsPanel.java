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

    /**
     * Create a new panel which will hold a button for moving to the next move
     * during a game replay.
     * @param controller The controller that will handle the components ActionEvents.
     * @see ReplayGameController
     */
    public ReplayButtonsPanel(ReplayGameController controller)
    {
        b = new BasicArrowButton(EAST);
        b.addActionListener(controller);

        this.add(b);
    }

    /**
     * Paints the panel
     * @param g Graphics component
     * @see Graphics
     * @see JPanel
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}
