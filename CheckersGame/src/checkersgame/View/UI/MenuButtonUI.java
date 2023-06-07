/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View.UI;

import checkersgame.View.MenuPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @deprecated 
 * @author bradl
 */
public class MenuButtonUI extends BasicButtonUI {

    /**
     * @deprecated 
     * Creates a new UI to be associated with a Menu's buttons
     * @see MenuPanel
     */
    public MenuButtonUI(){};
    
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
        int width = c.getWidth();
        int height = c.getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.fill3DRect(0, 0, width, height, true);

    }
}
