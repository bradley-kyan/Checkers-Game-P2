/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.View.UI.HintUI;
import checkersgame.Controller.PlayableGameController;
import checkersgame.Model.PieceMoves;
import checkersgame.Model.PieceComponents.Piece;
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
public class HintButton extends JButton implements ActionListener {

    /**
     * The piece the hint is associated with.
     */
    public Piece piece;
    private Point moveLocation;

    /**
     * Create a new HintButton for selecting where a piece should move to
     * @param pieceMoves The moves associated with a piece
     * @see Piece
     * @see PieceMoves
     */
    public HintButton(PieceMoves pieceMoves)
    {
        this.piece = pieceMoves.origin;
        this.moveLocation = pieceMoves.toMove;
        this.addActionListener(this);
        this.setVisible(true);
        this.setUI(new HintUI());
    }

    /**
     * Readjusts the size of the bounds of the button according to the size of the
     * checkerboard squares.
     * @param g Graphics component
     * @see JButton
     * @see Graphics
     */
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

    /**
     * Action Listener for all hint buttons which will move the piece to the location
     * of the button
     * @param e HintButton's press action event
     * @see ActionListener
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        PlayableGameController.movePiece(piece, moveLocation);
        PlayableGameController.addPieces();
    }
}
