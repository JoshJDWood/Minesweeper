package org.example;

public class Tile {
    private int x;
    private int y;
    private boolean hasMine = false;
    private boolean isFlagged = false;
    private boolean isHidden = true;
    private int adjMines = 0;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
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
}
