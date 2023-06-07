/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.BoardController;
import checkersgame.Controller.ReplayGameController;
import checkersgame.Model.PieceComponents.Colour;
import static checkersgame.Model.PieceComponents.Colour.BLACK;
import static checkersgame.Model.PieceComponents.Colour.RED;
import checkersgame.Model.PieceComponents.Piece;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
    private BoardController controller;
    private static JPanel panel;

    /**
     * Creates a new frame which will be used for displaying current piece count on the
     * board, the current player's move, and handle replay controls i.e. display the
     * next move
     * @param controller - The board controller controlling the panel
     * @see BoardController
     */
    public SidePanel(BoardController controller)
    {
        this.controller = controller;
        if (panel != null)
        {
            return;
        }

        panel = new JPanel();
        
        panel.setLayout(new GridLayout(0, 1, 0, 10)); //Display components in a single coloum

        this.setVisible(true);
        panel.setVisible(true);
        
        //Add contents to the panel
        panel.add(new CurrentMover(BLACK));
        panel.add(new JSeparator());
        panel.add(new CountLabel(BLACK));
        panel.add(new CountLabel(RED));
        
        //This panel shouldnt be closed, only closed when the game is exited
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.add(panel);
        resize();
    }

    /**
     * Adds a button for controlling a next move. This is used for replays when
     * wanting to see the next move. Sets the action listener to be the replay
     * controller responsible for replays.
     * @param rgc ReplyGameController controlling the current replay
     * @see ReplayGameController 
     * @see ActionListener
     */
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

    /**
     * Resize the current frames according to the contents in the frame.
     */
    private void resize()
    {
        this.pack();
        Dimension current = this.getSize();
        
        //Add some padding
        current.width += 50;
        current.height += 100;

        panel.setMinimumSize(current);
        this.setSize(current);

        //Refresh the contents
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }
    
    /**
     * Object defining the label that will display the current player's turn.
     */
    public class CurrentMover extends JLabel
    {

        /**
         * Creates a new label from the current player to move
         * @param mover The colour of the player mover
         * @see setMover
         * @see Colour
         */
        public CurrentMover(Colour mover)
        {
            super();
            this.setMover(mover);
        }

        /**
         * Paints the label
         * @param g Graphics component
         * @see Graphics
         */
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if(BoardController.playerTurn == null)
                return;
            
            this.setMover(BoardController.playerTurn);
        }

        /**
         * Updates the text of the label according to the input colour.
         * @param mover The colour of the current players turn
         * @see Colour
         */
        public void setMover(Colour mover)
        {
            if(mover == RED)
            {
                this.setText("<html><font color='red'>Red's</font> turn to move!</html>");
            }
            else
            {
                this.setText("<html>Black's turn to move!</html>");
            }
            this.setFont(font);
            this.repaint();
        }
    }

    /**
     *  Object for displaying the remaining pieces on the board
     */
    public class CountLabel extends JLabel {

        /**
         * Creates a new label showing the current number of pieces for the defined
         * piece colour
         * @param pieceColour The colour of pieces to be displayed
         * @see Piece
         * @see Colour
         */
        public CountLabel(Colour pieceColour)
        {
            super();
            this.setValues(pieceColour);
        }

        /**
         * Paints the label
         * @param g Graphics component
         * @see Graphics
         */
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

        /**
         * Sets the text of the label according to the colour associated, and the
         * number of remaining pieces. 
         * @param pieceColour Colour of pieces
         * @see getPieceCount
         */
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

        /**
         * Get the total amount of remaining pieces for the input colour.
         * @param pieceColour Colour of pieces
         * @return int - number of pieces remaining.
         * @see BoardController
         * @see PiecesArray
         */
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
