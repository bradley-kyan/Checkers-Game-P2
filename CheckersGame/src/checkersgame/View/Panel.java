/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.BoardController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author bradl
 */
public class Panel extends JPanel{
    
    private int size;
    public static Dimension squareSize;
    
    public Panel(int size)
    {
        this.size = size;
        this.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        squareSize = new Dimension(this.getSize().width/size, this.getSize().height/size);
        
        for(int x = 0; x < size; x++)
        {       
            for(int y = 0; y < size; y++)
            {
                int posx = x * squareSize.width;
                int posy = y * squareSize.height;
                
                if((y % 2) == (x % 2))
                    g.setColor(Color.decode("#eeeed2"));
                else
                    g.setColor(Color.decode("#769656"));     
                
                g.fillRect(posx, posy, squareSize.width, squareSize.height);
            }
        } 
        
        for(Component comp : this.getComponents())
            comp.paint(g);

        repaint();
    }
}
