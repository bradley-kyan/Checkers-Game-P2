/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View.UI;

import checkersgame.View.HintButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author bradl
 */
public class HintUI extends BasicButtonUI {

    /**
     * Creates a new UI to be associated with a HintButton
     * @see HintButton
     */
    public HintUI(){}
    
    /**
     * Paints the following to the bounds of the calling component.
     * @param g Graphics component
     * @param c Component associated with the UI
     * @see Graphics
     * @see JComponent
     */
    @Override
    public void paint(Graphics g, JComponent c)
    {
        if (!c.isVisible())
        {
            return;
        }
        int width = c.getWidth();
        int height = c.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.lightGray);
        g2d.fillOval(30, 30, width - 60, height - 60);
        g2d.setColor(Color.gray);
        g2d.fillOval(36, 36, width - 72, height - 72);
    }
}
