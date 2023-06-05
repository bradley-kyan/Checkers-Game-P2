/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.View.UI.HintUI;
import checkersgame.Controller.PlayableGameController;
import checkersgame.Model.LinkedPoint;
import checkersgame.Model.Piece;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author bradl
 */
public class HintButton extends JButton implements ActionListener{
    
    public Piece piece;
    private Point moveLocation;
    
    public HintButton(LinkedPoint lp)
    {
        this.piece = lp.origin;
        this.moveLocation = lp.toMove;
        this.addActionListener(this);
        this.setVisible(true);
        this.setUI(new HintUI());
    }

    @Override
    public void paintComponent(Graphics g)
    {
        this.setContentAreaFilled(false);
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        
        Dimension square = BoardPanel.squareSize;
        
        int posx = moveLocation.x * square.width;
        int posy = moveLocation.y * square.height;
        int width = square.width;
        int height = square.height;

        this.setBounds(posx, posy, width, height);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        System.out.println("Hint!");
        PlayableGameController.movePiece(piece, moveLocation);
        PlayableGameController.addPieces();
    }
}
