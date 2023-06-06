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

    private JButton createButton(String name)
    {
        JButton button = new JButton(name);
        button.addActionListener(menu);
        button.setName(name);

        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING);

        button.setFont(font);
        button.setVisible(true);
        //button.setUI(new MenuButtonUI());

        return button;
    }

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

        for (Component comp : this.getComponents())
        {
            comp.setFont(font.deriveFont(Font.PLAIN, d.width / 16));
        }
        repaint();
    }
}
