/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import checkersgame.Controller.ReplayController;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author bradl
 */
public class SidePanel extends JPanel{
    
    private ReplayController controller;
    
    public SidePanel(ReplayController controller)
    {
        this.controller = controller;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        
    }
}
