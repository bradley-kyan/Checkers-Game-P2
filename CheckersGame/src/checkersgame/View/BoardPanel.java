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
    public static Dimension squareSize;

    public BoardPanel(int size)
    {
        this.size = size;
        this.setVisible(true);
        this.setDoubleBuffered(true);
        this.setAlignmentX(CENTER_ALIGNMENT);
        //resize();
    }

    private void resize()
    {
        if (this.getParent() == null)
        {
            return;
        }
        Dimension parent = this.getParent().getSize();

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

        squareSize = new Dimension(this.getSize().width / size, this.getSize().height / size);
    }

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

                if ((y % 2) == (x % 2))
                {
                    g.setColor(Color.decode("#eeeed2"));
                }
                else
                {
                    g.setColor(Color.decode("#769656"));
                }

                g.fillRect(posx, posy, squareSize.width, squareSize.height);
            }
        }
    }
}
