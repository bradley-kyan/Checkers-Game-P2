/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.LinkedPoint;
import checkersgame.Model.Piece;
import checkersgame.Model.PiecesArray;
import checkersgame.View.HintButton;
import checkersgame.View.PieceComponent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author bradl
 */
public class PieceController{
    
    private static ArrayList<Component> components;
    
    public PieceController()
    {
        components = new ArrayList<Component>();
        if(PiecesArray.pieces == null)
            return;
        updatePieces();
    }
    public ArrayList<Component> getComponents()
    {
        updatePieces();
        return this.components;
    }

    public static void updatePieces()
    {
        BoardController.updatePieces();

        for(Piece piece : PiecesArray.getPieces())
        {
            if(piece == null)
                continue;
        }
        if(components == null)
            return;
        
        components = new ArrayList<Component>();
        
        for(Piece piece : PiecesArray.getPieces())
        {
            if(piece == null)
                continue;
            PieceComponent pc = new PieceComponent(piece);
            
            components.add(pc);
        }
    }
}
