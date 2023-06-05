/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.BoardController;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author bradl
 */
public class SidePanel extends JPanel{
        
    private Font font;
    
    public SidePanel()
    {
        this.setLayout(new GridLayout(0, 1, 40, 40));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        font = new Font("Arial", Font.PLAIN, 20);
        
        this.setVisible(true);
        this.setDoubleBuffered(true);
        this.setAlignmentX(CENTER_ALIGNMENT);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        
    }
}
