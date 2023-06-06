/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.View.UI.PieceUI;
import checkersgame.Controller.PlayableGameController;
import checkersgame.Model.PieceComponents.Colour;
import checkersgame.Model.PieceComponents.Piece;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author bradl
 */
public class PieceButton extends JButton implements ActionListener {

    public Piece piece;
    private Dimension square;

    public PieceButton(Piece piece)
    {
        this.piece = piece;
        this.setVisible(true);
        this.addActionListener(this);
        this.setDoubleBuffered(false);
        if (piece.getColour() == Colour.RED)
        {
            this.setUI(new PieceUI(Color.RED));
        }
        else
        {
            this.setUI(new PieceUI(Color.BLACK));
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        square = BoardPanel.squareSize;

        int posx = piece.position.x * square.width;
        int posy = piece.position.y * square.height;
        int width = square.width;
        int height = square.height;

        this.setLocation(posx, posy);
        this.setSize(width, height);
        this.setContentAreaFilled(false);

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        PlayableGameController.addPieces();
        PlayableGameController.showHint(piece);
        System.out.println("Press!");
    }
}
