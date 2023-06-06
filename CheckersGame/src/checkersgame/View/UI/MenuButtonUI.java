/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View.UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author bradl
 */
public class MenuButtonUI extends BasicButtonUI {

    @Override
    public void paint(Graphics g, JComponent c)
    {
        int width = c.getWidth();
        int height = c.getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.fill3DRect(0, 0, width, height, true);

    }
}
