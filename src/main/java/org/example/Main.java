package org.example;

import java.util.Scanner;

public class Main {
    public static Scanner reader = new Scanner(System.in);
    private static boolean quit = false;


    public static void main(String[] args){
        System.out.println("MINESWEEPER!");
        while (!quit) {
            System.out.println("Select Difficulty");
            System.out.println("1. Easy (10 x 8, 10 mines)");
            System.out.println("2. Medium (18 x 14, 40 mines)");
            System.out.println("3. Hard (24 x 20, 100 mines)");
            System.out.println("4. Quit");
            String input = reader.next();
            int level = Integer.parseInt(input);

            if (level == 4){
                quit = true;
            }
            else{
                LevelManager.runLevel(level);
            }
        }

    }
}