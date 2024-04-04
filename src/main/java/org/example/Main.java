package org.example;

import java.util.Scanner;

public class Main {
    private static boolean gameOver = false;
    private static boolean gameWon = false;
    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args){

        Board board = new Board(10,8, 10);
        board.drawBoard();
        while (!gameOver && !gameWon) {
            //get player move
            System.out.println("Target x: ");
            String input = reader.next();
            int x = Integer.parseInt(input);
            System.out.println("Target y: ");
            input = reader.next();
            int y = Integer.parseInt(input);
            System.out.println("1. To Dig");
            System.out.println("2. To Flag / Remove Flag: ");
            input = reader.next();
            boolean dig = Integer.parseInt(input) == 1;
            boolean flag = Integer.parseInt(input) == 2;

            if (dig) {
                int guessResult = board.revealTile(x, y);

                if (guessResult == -1) {
                    System.out.println("Oh no! you hit a mine.");
                    System.out.println("GAME OVER");
                    gameOver = true;
                }
                else if (guessResult == -2){
                    System.out.println("You can't Dig on a Flagged square.");
                }
                else if (guessResult == -3){
                    System.out.println("This square has already been revealed.");
                }
                else{
                    System.out.println("There are " + guessResult + " adjacent mines");
                }
            }
            else if (flag){
                boolean successfulFlag = board.flagTile(x, y);
                if(successfulFlag){
                    System.out.println("You Flagged the square");
                }
                else{
                    System.out.println("You can't Flag a revealed square");
                }
            }

            board.drawBoard();

            if (board.checkWin()){
                gameWon = true;
                System.out.println("Congratulations, you've beaten the game!!");
            }
        }
    }
}