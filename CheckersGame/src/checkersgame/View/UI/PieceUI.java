/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author bradl
 */
public class PieceUI extends BasicButtonUI{
     
    private Color color;
    
    public PieceUI(Color colour)
    {
        this.color = colour;
    }
    @Override
    public void paint(Graphics g, JComponent c) 
    {
        int width = c.getWidth();
        int height = c.getHeight();
        Graphics2D g2d = (Graphics2D) g;
        
        Color internal;
        
        if(color.equals(Color.RED))
            internal = new Color(255, 88, 88);
        else
            internal = new Color(44, 44, 44);
        
        g2d.setColor(internal);
        g2d.fillOval(10, 10, width-20, height -20);
        g2d.setColor(color);
        g2d.fillOval(20, 20, width-40, height-40);
    }
}
