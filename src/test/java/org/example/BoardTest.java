package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    Board testBoard = new Board(10,8,10);

    //How do you write tests when the output is random, and you can't access private variables

    @Test
    public void checkWinReturnsFalseOnNewBoard(){
        Assertions.assertFalse(testBoard.checkWin(), "board returns win condition has been met for a new board");
    }
}
