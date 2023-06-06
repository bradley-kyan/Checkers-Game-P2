/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkersgame.Controller;

import checkersgame.Model.PieceComponents.Piece;
import checkersgame.Model.PieceComponents.Colour;
import checkersgame.Model.*;
import checkersgame.View.*;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author bradl
 */
public class BoardController extends MoveController {

    protected static String instanceName;
    public static BoardPanel panel;
    public static PieceButton pieceComponent;
    public static PiecesArray pieceArray;
    public static BoardController pieceController;
    public static SidePanel sidePanelFrame;
    public static Colour playerTurn;
    private static ArrayList<Component> components;

    public BoardController()
    {
        super(instanceName);
        components = new ArrayList<Component>();
        if (PiecesArray.pieces == null)
        {
            return;
        }

        sidePanelFrame = new SidePanel(this);
        updatePieces();
    }

    public ArrayList<Component> getComponents()
    {
        updatePieces();
        return this.components;
    }

    public static void updatePieces()
    {
        for (Piece piece : PiecesArray.getPieces())
        {
            if (piece == null)
            {
                continue;
            }
        }
        if (components == null)
        {
            return;
        }

        components = new ArrayList<Component>();

        for (Piece piece : PiecesArray.getPieces())
        {
            if (piece == null)
            {
                continue;
            }
            PieceButton pc = new PieceButton(piece);

            components.add(pc);
        }
    }

    public static void addPieces()
    {
        pieceController = new BoardController();
        panel.removeAll();
        for (Component component : pieceController.getComponents())
        {
            component.setVisible(true);
            panel.add(component);
        }
        panel.revalidate();
        panel.repaint();
    }

    public static void movePiece(Piece piece, Point location)
    {
        if (piece.getColour() != playerTurn)
        {
            return;
        }

        inverseColour();

        if (pieceArray.movePiece(piece, location))
        {
            addMove(piece, location);
        }

        updatePieces();
    }

    public static void inverseColour()
    {
        if (playerTurn == Colour.RED)
        {
            playerTurn = Colour.BLACK;
        }
        else
        {
            playerTurn = Colour.RED;
        }

    }
}
