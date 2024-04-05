package org.example;

import static org.example.Main.reader;

public class LevelManager {

    private static Board board;

    //easy 10x8 10mines
    //medium 18x14 40mines
    //hard 24x20 100mines

    public static void runLevel(int difficulty){
        boolean gameOver = false;
        switch (difficulty){
            case 1:
                board = new Board(10,8,10);
                break;
            case 2:
                board = new Board(18, 14, 40);
                break;
            case 3:
                board = new Board(24,20,100);
                break;
        }
        board.drawBoard();

        while (!gameOver) {
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

            if (gameOver){
                System.out.println("Oh no! you hit a mine.");
                System.out.println("GAME OVER");
            }
            else if (board.checkWin()){
                gameOver = true;
                System.out.println("Congratulations, you've beaten the game!!");
            }
        }
    }

}
