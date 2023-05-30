/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkersgame;

import checkersgame.Model.Colour;
import checkersgame.Controller.DrawBoard;
import checkersgame.Model.Piece;
import checkersgame.Model.Piece;
import checkersgame.Model.Player;
import checkersgame.Model.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author kishanyugendran
 */
public class CheckersGame {

    private static DrawBoard board;
    private static Scanner scan;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        runGame();
    }
    
    /**
     * This method runs the checkers game by calling functions from multiple classes. This also displays some generic messages
     * such as requests for username and board size.
     */
    public static void runGame()
    {
        
        Player.displayLeaderboard();
        
        System.out.println("Please enter red's name: ");
        String nameRed  = scan.nextLine();
        
        System.out.println("Please enter blacks's name: ");
        String nameBlack  = scan.nextLine();
        
        
        System.out.println(getTitle(nameRed, nameBlack));
        System.out.println("Press 'Enter' to start.");
        String anyKey  = scan.nextLine();
        int size  =  0;
        boolean input = false;
        
        while (!input) {
            System.out.println("Please enter an integer to set board size(Must be greater than 6): ");
            try {
                size = scan.nextInt();
                if(size > 6)
                {
                input = true;
                }
                else{
                    System.out.println("Invalid input! Please enter an integer greater than 6.");
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scan.next();
            }
        }

        if (size % 2 != 0)
            size++; //Makes sure that he size is an even number to prevent errors.
        
        board = new DrawBoard(size);
        
        Player playerRed = new Player(nameRed, Colour.RED);
        Player playerBlack = new Player(nameBlack, Colour.BLACK);
        
        while(true)
        {
            int currentRed = board.remainingPieces(Colour.RED);
            int currentBlack = board.remainingPieces(Colour.BLACK);

            if(currentRed == 0 || currentBlack == 0)
                break;

            while(!playTurn(playerRed));
            if(currentBlack != board.remainingPieces(Colour.BLACK))
                playerRed.capture();
            if(board.remainingPieces(Colour.BLACK) == 0 || board.remainingPieces(Colour.RED) == 0)
                break;
            
            while(!playTurn(playerBlack));
            if(currentRed != board.remainingPieces(Colour.RED))
                playerBlack.capture();
            if(board.remainingPieces(Colour.BLACK) == 0 || board.remainingPieces(Colour.RED) == 0)
                break;
        }       
        
        if(board.remainingPieces(Colour.BLACK) > board.remainingPieces(Colour.RED))
            playerBlack.win();
        else
            playerRed.win();
        
        Player.updateFile();
        System.out.println(nameRed + " has " + playerRed.getWinLossString());
        System.out.println(nameBlack + " has " + playerBlack.getWinLossString());
        
        
    }

    /**
     * The method allows the player to take their turn and ends their turn when they move their piece.
     * @param currentPlayer represents the current player which is taking a turn
     * @return true if the player made a valid move and their turn ended, false if the player
     * made an invalid move or decided to go back to selecting a different piece, and the turn has not been completed. 
     */
    private static boolean playTurn(Player currentPlayer)
    {
        
    }
}
