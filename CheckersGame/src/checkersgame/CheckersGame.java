/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package checkersgame;

import checkersgame.Controller.BoardController;
import java.util.Scanner;

public class CheckersGame {

    private static Scanner scan;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runGame();
    }
    
    /**
     * This method runs the checkers game by calling functions from multiple classes. This also displays some generic messages
     * such as requests for username and board size.
     */
    public static void runGame()
    {    
        BoardController board = new BoardController(8);
    }
}
