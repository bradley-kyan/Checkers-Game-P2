/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.MenuController;
import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author bradl
 */
public class MenuPanel extends JPanel {

    private static Font font;
    private MenuController menu;

    /**
     * Creates a new panel for displaying welcome menu contents.
     * @param menu The menu controller for linking ActionListener
     * @see ActionListener
     */
    public MenuPanel(MenuController menu)
    {
        this.menu = menu;

        this.setLayout(new GridLayout(0, 1, 40, 40));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        font = new Font("Arial", Font.PLAIN, 20);

        this.setVisible(true);
        this.setDoubleBuffered(true);
        this.setAlignmentX(CENTER_ALIGNMENT);

        JLabel l = new JLabel("Checkers?!?", SwingConstants.CENTER);
        this.add(l);
        this.add(createButton("Play"));
        this.add(createButton("Replays"));
    }

    /**
     * Create a new button to be displayed on the menu. All press events are
     * sent to the controller (MenuController) to be handled there.
     * @param name The name the button will display
     * @return JButton - the created button
     * @see JButton
     * @see MenuController
     */
    private JButton createButton(String name)
    {
        JButton button = new JButton(name); //Create the button
        button.addActionListener(menu); //Set the action listener to the controller
        button.setName(name);

        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING);

        button.setFont(font);
        button.setVisible(true);

        return button;
    }

    /**
     * Update the panel's size to be relative to the frame's size. Keeps the
     * panel's dimensions to a square, 1:1 ratio.
     * @param g Graphics component
     * @see JPanel
     * @see Graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Dimension parent = this.getParent().getSize();
        Dimension d;
        if (parent.height > parent.width)
        {
            d = new Dimension(parent.width, parent.width);
            this.setMaximumSize(d);
            this.setPreferredSize(d);
            this.setMinimumSize(d);
        }
        else
        {
            d = new Dimension(parent.height, parent.height);
            this.setMaximumSize(d);
            this.setPreferredSize(d);
            this.setMinimumSize(d);
        }

        //readjust all components to have the same font.
        for (Component comp : this.getComponents())
        {
            comp.setFont(font.deriveFont(Font.PLAIN, d.width / 16));
        }
        repaint();
    }
}
