/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Model.Colour;
import checkersgame.Model.LinkedPoint;
import checkersgame.Model.Piece;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author bradl
 */
public class HintComponent extends JComponent{
    
    private static Piece piece;
    
    public HintComponent(Piece p)
    {
        piece = p;
    }
    
    public static void clearPiece()
    {
        piece = null;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if(piece == null)
            return;
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        for(LinkedPoint lp : piece.moves)
        {
            g2d.setColor(Color.GRAY);
            g2d.fillOval(lp.toMove.x, lp.toMove.y, 10, 10);
        }      
    }
}
