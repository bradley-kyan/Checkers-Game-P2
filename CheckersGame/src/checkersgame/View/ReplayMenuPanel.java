/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.ReplayGameController;
import checkersgame.Controller.ReplayMenuController;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author bradl
 */
public class ReplayMenuPanel extends JPanel
{    
    private static Font font;
    private ReplayMenuController rmc;
    public ReplayMenuPanel(ReplayMenuController rmc)
    {
        this.rmc = rmc;
        this.setLayout(new GridLayout(0, 1, 40, 40));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        font = new Font("Arial", Font.PLAIN, 20);
    }
    
    public void addButton(String title, int ID)
    {
        JButton button = new JButton(title);
        button.setName(""+ID);
        button.addActionListener(rmc);
        
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING);
        
        button.setFont(font);
        button.setVisible(true);      
        
        this.add(button);
        this.revalidate();
        this.repaint();
    }
}
