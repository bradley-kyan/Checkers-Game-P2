/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Model.Colour;
import checkersgame.Model.LinkedPoint;
import checkersgame.Model.Piece;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author bradl
 */
public class HintComponent extends JComponent{
    
    private Piece piece;
    
    public HintComponent(Piece p)
    {
        piece = p;
    }

    @Override
    public void paint(Graphics g)
    {
        if(piece == null)
            return;      
         
        for(LinkedPoint lp : piece.moves)
        {       
            Dimension square = Panel.squareSize;
        
            int posx = lp.toMove.x * square.width;
            int posy = lp.toMove.y * square.height;
            int width = square.width;
            int height = square.height;

            this.setBounds(posx, posy, width, height);
            
            g.setColor(Color.GRAY);
            g.fillOval(posx + 10, posy + 10, width/2, height/2);
        }      
    }
}
