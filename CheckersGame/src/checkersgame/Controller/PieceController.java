/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.Piece;
import checkersgame.Model.PiecesArray;
import checkersgame.View.HintComponent;
import checkersgame.View.PieceComponent;
import java.util.ArrayList;

/**
 *
 * @author bradl
 */
public class PieceController{
    
    private ArrayList<PieceComponent> components;
    
    public PieceController()
    {
        components = new ArrayList<PieceComponent>();
        if(PiecesArray.pieces == null)
            return;
        
        for(Piece piece : PiecesArray.getPieces())
        {
            PieceComponent pc = new PieceComponent(piece);
            this.components.add(pc);
        }
    }
    public ArrayList<PieceComponent> getComponents()
    {
        return this.components;
    }
}
