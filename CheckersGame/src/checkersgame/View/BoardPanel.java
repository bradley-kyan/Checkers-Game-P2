/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author bradl
 */
public class BoardPanel extends JPanel {

    private int size;

    /**
     * The dimensions of each individual checker square
     */
    public static Dimension squareSize;

    /**
     * Creates a new board panel which pieces are displayed upon.
     * @param size Dimensions of the board
     */
    public BoardPanel(int size)
    {
        this.size = size;
        this.setVisible(true);
        this.setDoubleBuffered(true);
        this.setAlignmentX(CENTER_ALIGNMENT);
        //resize();
    }

    /**
     * Resizes the board in relation to the parent's size - in this case the window.
     * Keeps the panel the size of a square
     * frame.
     */
    private void resize()
    {
        if (this.getParent() == null)
        {
            return;
        }
        Dimension parent = this.getParent().getSize();
        
        //Keeps the panel a square, i.e. a 1:1 ratio
        if (parent.height > parent.width)
        {
            Dimension d = new Dimension(parent.width, parent.width);
            this.setMaximumSize(d);
            this.setPreferredSize(d);
            this.setMinimumSize(d);
        }
        else
        {
            Dimension d = new Dimension(parent.height, parent.height);
            this.setMaximumSize(d);
            this.setPreferredSize(d);
            this.setMinimumSize(d);
        }
        //Update the size of each square
        squareSize = new Dimension(this.getSize().width / size, this.getSize().height / size);
    }

    /**
     * Paints the current panel. Paints the checkerboard pattern to the background
     * according to the size.
     * Should not be called on its own
     * @param g Graphics
     * @see JPanel
     * @see Graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        resize();

        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                int posx = x * squareSize.width;
                int posy = y * squareSize.height;

                //Set the colour of the square
                if ((y % 2) == (x % 2))
                {
                    g.setColor(Color.decode("#eeeed2"));
                }
                else
                {
                    g.setColor(Color.decode("#769656"));
                }
                //Create the sqaure
                g.fillRect(posx, posy, squareSize.width, squareSize.height);
            }
        }
    }
}
