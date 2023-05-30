/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Model.Colour;
import checkersgame.Model.Piece;
import checkersgame.Model.PiecesArray;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author bradl
 */
public class PieceComponent extends JComponent{

    private PiecesArray pa;
    
    public PieceComponent(PiecesArray pa)
    {
        this.pa = pa;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if(pa == null)
            return;
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        for(Piece piece : pa.getPieces())
        {
            if(piece.getColour() == Colour.RED)
                g2d.setColor(Color.RED);
            else if(piece.getColour() == Colour.BLACK)
                g2d.setColor(Color.BLACK);
            
            g2d.fillOval(piece.position.x, piece.position.y, 30, 30);
        }
        
    }
}
