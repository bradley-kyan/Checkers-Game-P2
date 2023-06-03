/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author bradl
 */
public class Frame extends JFrame{
    
    public Frame()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension frameSize = kit.getScreenSize();
        frameSize.height = frameSize.height/2;
        frameSize.width = frameSize.width/2;
        if(frameSize.width < frameSize.width)
            frameSize.height = frameSize.width;
        
        frameSize.width = frameSize.height;
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(frameSize);
        this.setVisible(true);
    }  
}
