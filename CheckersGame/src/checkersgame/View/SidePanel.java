/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.BoardController;
import checkersgame.Controller.ReplayGameController;
import checkersgame.Model.Colour;
import static checkersgame.Model.Colour.BLACK;
import static checkersgame.Model.Colour.RED;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import static javax.swing.SwingConstants.EAST;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 * @author bradl
 */
public class SidePanel extends JFrame {

    private final static Font font = new Font("Arial", Font.PLAIN, 40);
    ;
    private BoardController controller;
    private static JPanel panel;

    public SidePanel(BoardController controller)
    {
        this.controller = controller;
        if (panel != null)
        {
            return;
        }

        panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1, 0, 10));

        this.setVisible(true);
        panel.setVisible(true);

        panel.add(new CountLabel(BLACK));
        panel.add(new JSeparator());
        panel.add(new CountLabel(RED));
        this.add(panel);
        resize();
    }

    public void addReplayFunction(ReplayGameController rgc)
    {
        JLabel nextText = new JLabel("Next Move");
        nextText.setFont(font.deriveFont(Font.ITALIC));

        BasicArrowButton button = new BasicArrowButton(EAST);
        button.addActionListener(rgc);
        button.setMinimumSize(new Dimension(50, 100));

        panel.add(new JSeparator());
        panel.add(nextText);
        panel.add(button);

        this.resize();
    }

    private void resize()
    {
        this.pack();
        Dimension current = this.getSize();
        current.width += 50;
        current.height += 100;

        panel.setMinimumSize(current);
        this.setSize(current);

        this.getContentPane().validate();
        this.getContentPane().repaint();
    }

    private class CountLabel extends JLabel {

        public CountLabel(Colour pieceColour)
        {
            super();
            this.setValues(pieceColour);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Colour pieceColour = BLACK;
            if (this.getName() == "RED")
            {
                pieceColour = RED;
            }

            this.setValues(pieceColour);
        }

        private void setValues(Colour pieceColour)
        {
            String colour = "";
            if (pieceColour == BLACK)
            {
                colour = "<html><font color='black'>Black</font> Remaining Pieces: ";
                this.setName("BLACK");
            }
            else
            {
                colour = "<html><font color='red'>Red</font> Remaining Pieces: ";
                this.setName("RED");
            }

            int count = getPieceCount(pieceColour);
            colour += count + "</html>";
            this.setText(colour);
            this.setFont(font);
            this.repaint();
        }

        private int getPieceCount(Colour pieceColour)
        {
            if (BoardController.pieceArray == null)
            {
                return 0;
            }

            return BoardController.pieceArray.remainingPieces(pieceColour);
        }
    }
}
