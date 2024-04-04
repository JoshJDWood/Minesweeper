package org.example;

import java.util.Scanner;

public class Main {
    private static boolean gameOver = false;
    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args){

        Board board = new Board(15,12, 10);
        board.drawBoard();
        while (!gameOver) {
            //get player move
            System.out.println("x?");
            String input = reader.next();
            int x = Integer.parseInt(input);
            System.out.println("y?");
            input = reader.next();
            int y = Integer.parseInt(input);

            int guessResult = board.revealTile(x, y);
            System.out.println(guessResult);

            if (guessResult == -1){
                gameOver = true;
            }
            board.drawBoard();
        }
    }
}