/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.MenuController;
import checkersgame.Controller.ReplayMenuController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/**
 *
 * @author bradl
 */
public class ReplayMenuPanel extends JPanel {

    private static Font font;
    private ReplayMenuController rmc;
    private JScrollPane scrollArea;
    private JPanel subPanel;
    
    /**
     *
     * @param rmc
     */
    public ReplayMenuPanel(ReplayMenuController rmc)
    {
        this.rmc = rmc;
        
        subPanel = new JPanel();
        
        subPanel.setLayout(new GridLayout(0, 1, 40, 40));
        scrollArea = new JScrollPane(subPanel);
        scrollArea.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        
        Dimension size = MenuController.frame.getSize();
        size.width *= .9;
        size.height *= .9;
        scrollArea.setPreferredSize(size);
        font = new Font("Arial", Font.PLAIN, 20);

        this.add(scrollArea);    
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Dimension size = MenuController.frame.getSize();
        size.width *= .9;
        size.height *= .9;
        scrollArea.setPreferredSize(size);
    }

    /**
     *
     * @param title
     * @param ID
     */
    public void addButton(String title, int ID)
    {
        JButton button = new JButton(title);
        button.setName("" + ID);
        button.addActionListener(rmc);

        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING);

        button.setFont(font);
        button.setVisible(true);

        subPanel.add(button);
        this.revalidate();
        this.repaint();
    }
}
