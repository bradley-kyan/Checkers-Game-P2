/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View.UI;

import checkersgame.View.PieceButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author bradl
 */
public class PieceUI extends BasicButtonUI {

    private Color color;

    /**
     * Creates a new UI to be associated with a PieceButton
     * @param colour The colour of the piece
     * @see PieceButton
     * @see Color
     */
    public PieceUI(Color colour)
    {
        this.color = colour;
    }

    /**
     * Paints the following to the bounds of the calling component. Sets the colour
     * of the button the same a the piece.
     * @param g Graphics component
     * @param c Component associated with the UI
     * @see Graphics
     * @see JComponent
     */
    @Override
    public void paint(Graphics g, JComponent c)
    {
        int width = c.getWidth();
        int height = c.getHeight();
        Graphics2D g2d = (Graphics2D) g;

        Color outerColour;

        //The piece will have a border slightly ligher than the center
        if (color.equals(Color.RED))
        {
            outerColour = new Color(255, 88, 88);
        }
        else
        {
            outerColour = new Color(44, 44, 44);
        }

        g2d.setColor(outerColour);
        g2d.fillOval(10, 10, width - 20, height - 20);
        
        g2d.setColor(color);
        g2d.fillOval(20, 20, width - 40, height - 40);
    }
}
