/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.BoardController;
import checkersgame.Model.Colour;
import checkersgame.Model.LinkedPoint;
import checkersgame.Model.Piece;
import checkersgame.Model.PiecesArray;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author bradl
 */
public class PieceComponent extends JButton implements ActionListener{

    private Piece piece;
    private Dimension square;
    private static boolean hint;
    
    public PieceComponent(Piece piece)
    {
        this.piece = piece;  
        this.setVisible(true); 
        this.addActionListener(this);
    }
    
    @Override
    public void paint(Graphics g)
    {   
        square = Panel.squareSize;
        
        int posx = piece.position.x * square.width;
        int posy = piece.position.y * square.height;
        int width = square.width;
        int height = square.height;
        
        this.setBounds(posx, posy, width, height);       
        
        if(piece.getColour() == Colour.RED)
            g.setColor(Color.red);
        else
            g.setColor(Color.black);
        
        g.fillOval(posx + 10, posy + 10, width - 20, height - 20);
       
        if(hint)
        {
            for(Component comp : this.getComponents())
                comp.paint(g);         
        }
        else
        {
            this.removeAll();
            this.repaint();
            hint = false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        BoardController.updatePieces();

        if(hint == true)
        {
            hint = false;
        }
        else
        {
            hint = true;
            for(LinkedPoint lp : piece.moves)
            {
                this.add(new HintComponent(piece));
            }
            System.out.println("Press!");           
        }
    }
}
