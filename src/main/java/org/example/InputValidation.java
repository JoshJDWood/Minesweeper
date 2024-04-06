package org.example;

import static org.example.Main.reader;

public class InputValidation {
    public static int checkValidInt(int min, int max){
        int input;
        boolean valid = false;

        do{
            while (!reader.hasNextInt()){
                System.out.println("That is not an integer");
                reader.next();
            }
            input = reader.nextInt();
            if (input < min || input > max){
                System.out.println("Please enter value in the range " + min + "-" + max);
            }
            else{
                valid = true;
            }
        } while (!valid);

        return input;
    }
}
