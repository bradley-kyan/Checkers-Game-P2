/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author bradl
 */
public class HintUI extends BasicButtonUI{
    
    @Override
    public void paint(Graphics g, JComponent c) 
    {   
        if(!c.isVisible())
            return;
        int w = c.getWidth();
        int h = c.getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.lightGray);
        g2d.fillOval(30, 30, w-60, h -60);
        g2d.setColor(Color.gray);
        g2d.fillOval(36, 36, w-72, h-72);
    }
}
