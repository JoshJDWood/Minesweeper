package org.example;

public class Board {
    private final int width;
    private final int height;

    private final int mineCount;
    private final int[][] mineLoc;
    private Tile[][] tiles;

    private final int revealsToWin;
    private int curReveals = 0;

    public Board(int width, int height, int mineCount){
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        revealsToWin = width * height - mineCount;

        tiles = new Tile[width][height];
        mineLoc = new int[2][mineCount];

        initBoard();
    }

    public void drawBoard(){
        String xLine = "     ";
        for (int x = 0; x < width; x++){
            if (x < 9) {
                xLine += x + "   ";
            }
            else{
                xLine += x + "  ";
            }
        }
        System.out.println(xLine);
        for (int y = 0; y < height; y++) {
            String curLine;
            if (y < 10) {
                curLine = " " + y + " | ";
            }
            else{
                curLine = y + " | ";
            }
            for (int x = 0; x < width; x++) {
                if (tiles[x][y].getIsFlagged()){
                    curLine += "P | ";
                }
                else if (tiles[x][y].getIsHidden()){
                    curLine += "H | ";
                }
                else if (tiles[x][y].getHasMine()){
                    curLine += "X | ";
                }
                else if (tiles[x][y].getAdjMines() == 0){
                    curLine += "  | ";
                }
                else{
                    curLine += tiles[x][y].getAdjMines() + " | ";
                }
            }
            curLine += y;
            System.out.println(curLine);
        }
        System.out.println(xLine);
    }

//    public enum TileState{
//        mined(-1), flagged(-2), revealed(-3)
//
//        private final int state;
//        TileState(int state){this.state = state;}
//        public getState(){return state;}
//    }

    public int revealTile(int x, int y){
        if (!tiles[x][y].getIsHidden()){
            return -3;
        }
        else if (tiles[x][y].getIsFlagged()){
            return -2;
        }
        else if (tiles[x][y].getHasMine()){
            tiles[x][y].reveal();
            return -1;
        }
        else if (tiles[x][y].getAdjMines() == 0){
            tiles[x][y].reveal();
            chainRevealTiles(x, y);
            curReveals++;

            return tiles[x][y].getAdjMines();
        }
        else{
            tiles[x][y].reveal();
            curReveals++;
            return tiles[x][y].getAdjMines();
        }
    }

    public boolean checkWin(){
        return curReveals == revealsToWin;
    }

    private void chainRevealTiles(int x, int y){
        int[] safe = getSafeTiles(x, y);
        for (int a = safe[0]; a <= safe[1]; a++) {
            for (int b = safe[2]; b <= safe[3]; b++) {
                if (tiles[a][b].getIsHidden()) {
                    tiles[a][b].reveal();
                    curReveals++;
                    if (tiles[a][b].getAdjMines() == 0) {
                        chainRevealTiles(a, b);
                    }
                }
            }
        }
    }

    public boolean flagTile(int x, int y){
        if (tiles[x][y].getIsHidden()){
            tiles[x][y].toggleIsFlagged();
            return true;
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void initBoard(){
        initMines();

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                tiles[x][y] = new Tile();
            }
        }

        for (int i = 0; i < mineCount; i++){
            int x = mineLoc[0][i];
            int y = mineLoc[1][i];
            tiles[x][y].setHasMine(true);
            incrementAdjTiles(x, y);
        }
    }

    private void incrementAdjTiles(int x, int y){
        int[] safe = getSafeTiles(x, y);
        for (int a = safe[0]; a <= safe[1]; a++) {
            for (int b = safe[2]; b <= safe[3]; b++) {
                tiles[a][b].incrementAdjMines();
            }
        }
    }

    private int[] getSafeTiles(int x, int y){
        int amin = x - 1;
        int amax = x + 1;
        int bmin = y - 1;
        int bmax = y + 1;
        if (x == 0){
            amin = x;
        }
        else if (x == width - 1){
            amax = x;
        }
        if (y == 0){
            bmin = y;
        }
        else if (y == height - 1){
            bmax = y;
        }
        return new int[]{amin, amax, bmin, bmax};
    }

    private void initMines(){
        for (int i = 0; i < mineCount; i++){
            int x;
            int y;
            do{
                x = (int)Math.floor(Math.random() * width);
                y = (int)Math.floor(Math.random() * height);
            } while (matchOtherMines(x, y, i));
            mineLoc[0][i] = x;
            mineLoc[1][i] = y;
        }
    }

    private boolean matchOtherMines(int x, int y, int i){
        for (int j = 0; j < i; j++){
            if (mineLoc[0][j] == x && mineLoc[1][j] == y){
                return true;
            }
        }
        return false;
    }
}
