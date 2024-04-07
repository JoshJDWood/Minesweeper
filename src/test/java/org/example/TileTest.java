package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    Tile minedTile = new Tile();
    Tile emptyTile = new Tile();

    @Test
    public void getHasMineReturnsTrueForMinedTile(){
        minedTile.setHasMine(true);
        Assertions.assertTrue(minedTile.getHasMine(), "Get has mine returned false for a mined tile");
    }

    @Test
    public void getHasMineReturnsFalseForEmptyTile(){
        Assertions.assertFalse(emptyTile.getHasMine(), "Get has mine returned true for a empty tile");
    }
}
