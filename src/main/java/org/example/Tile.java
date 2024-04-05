package org.example;

public class Tile {
    private boolean hasMine = false;
    private boolean isFlagged = false;
    private boolean isHidden = true;
    private int adjMines = 0;

    public Tile(){
    }


    public boolean getIsHidden(){
        return isHidden;
    }

    public void reveal(){
        isHidden = false;
    }

    public boolean getHasMine(){
        return hasMine;
    }

    public void setHasMine(boolean hasMine){
        this.hasMine = hasMine;
    }

    public int getAdjMines(){
        return adjMines;
    }

    public void incrementAdjMines(){
        adjMines++;
    }

    public boolean getIsFlagged(){
        return isFlagged;
    }

    public void toggleIsFlagged(){
        isFlagged = !isFlagged;
    }
}
