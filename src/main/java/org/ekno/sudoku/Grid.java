package org.ekno.sudoku;

import java.util.Random;

/**
 * org.ekno.sudoku
 * User: ekno
 * Date: 12/18/2014
 * Time: 7:15 PM
 */
public class Grid {
    protected long seed;
    protected Random r = null;
    protected int[] grid = new int[81];
    //int[c]cell number
    protected static final int[][] columns = {
            {0,  9, 18, 27, 36, 45, 54, 63, 72},
            {1, 10, 19, 28, 37, 46, 55, 64, 73},
            {2, 11, 20, 29, 38, 47, 56, 65, 74},
            {3, 12, 21, 30, 39, 48, 57, 66, 75},
            {4, 13, 22, 31, 40, 49, 58, 67, 76},
            {5, 14, 23, 32, 41, 50, 59, 68, 77},
            {6, 15, 24, 33, 42, 51, 60, 69, 78},
            {7, 16, 25, 34, 43, 52, 61, 70, 79},
            {8, 17, 26, 35, 44, 53, 62, 71, 80}};
    //int[r]cell number
    protected static final int[][] rows = {
            { 0,  1,  2,  3,  4,  5,  6,  7,  8},
            { 9, 10, 11, 12, 13, 14, 15, 16, 17},
            {18, 19, 20, 21, 22, 23, 24, 25, 26},
            {27, 28, 29, 30, 31, 32, 33, 34, 35},
            {36, 37, 38, 39, 40, 41, 42, 43, 44},
            {45, 46, 47, 48, 49, 50, 51, 52, 53},
            {54, 55, 56, 57, 58, 59, 60, 61, 62},
            {63, 64, 65, 66, 67, 68, 69, 70, 71},
            {72, 73, 74, 75, 76, 77, 78, 79, 80}};
    //int[b]cell number
    protected static final int[][] blocks = {
            {0,  1,  2,   9, 10, 11, 18, 19, 20},
            {3,  4,  5,  12, 13, 14, 21, 22, 23},
            {6,  7,  8,  15, 16, 17, 24, 25, 26},
            {27, 28, 29, 36, 37, 38, 45, 46, 47},
            {30, 31, 32, 39, 40, 41, 48, 49, 50},
            {33, 34, 35, 42, 43, 44, 51, 52, 53},
            {54, 55, 56, 63, 64, 65, 72, 73, 74},
            {57, 58, 59, 66, 67, 68, 75, 76, 77},
            {60, 61, 62, 69, 70, 71, 78, 79, 80}};
    //int[cell number][c,r,b]
    protected static final int[][] cellMap = {
            {0, 0, 0}, {1, 0, 0}, {2, 0, 0}, {3, 0, 1}, {4, 0, 1}, {5, 0, 1}, {6, 0, 2}, {7, 0, 2}, {8, 0, 2},
            {0, 1, 0}, {1, 1, 0}, {2, 1, 0}, {3, 1, 1}, {4, 1, 1}, {5, 1, 1}, {6, 1, 2}, {7, 1, 2}, {8, 1, 2},
            {0, 2, 0}, {1, 2, 0}, {2, 2, 0}, {3, 2, 0}, {4, 2, 1}, {5, 2, 1}, {6, 2, 2}, {7, 2, 2}, {8, 2, 2},
            {0, 3, 3}, {1, 3, 3}, {2, 3, 3}, {3, 3, 4}, {4, 3, 4}, {5, 3, 4}, {6, 3, 5}, {7, 3, 5}, {8, 3, 5},
            {0, 4, 3}, {1, 4, 3}, {2, 4, 3}, {3, 4, 4}, {4, 4, 4}, {5, 4, 4}, {6, 4 ,5}, {7, 4, 5}, {8, 4, 5},
            {0, 5, 3}, {1, 5, 3}, {2, 5, 3}, {3, 5, 4}, {4, 5, 4}, {5, 5, 4}, {6, 5, 5}, {7, 5, 5}, {8, 5, 5},
            {0, 6, 6}, {1, 6, 6}, {2, 6, 6}, {3, 6, 7}, {4, 6, 7}, {5, 6, 7}, {6, 6 ,8}, {7, 6, 8}, {8, 6, 8},
            {0, 7, 6}, {1, 7, 6}, {2, 7, 6}, {3, 7, 7}, {4, 7, 7}, {5, 7, 7}, {6, 7, 8}, {7, 7, 8}, {8, 7, 8},
            {0, 8, 6}, {1, 8, 6}, {2, 8, 6}, {3, 8, 7}, {4, 8, 7}, {5, 6, 7}, {6, 8, 8}, {7, 8, 8}, {8, 8, 8}};

    public Grid(){
        seed = 0;
        r = new Random(seed);
    }

    public Grid(long l){
        seed = l;
        r = new Random(seed);
    }

    public static int getCellColumn(int c){
        return cellMap[c][0];
    }

    public static int getCellRow(int c){
        return cellMap[c][1];
    }

    public static int getCellBlock(int c){
        return cellMap[c][2];
    }

    public static int[] getCellsInColumn(int c){
        return columns[c];
    }

    public static int[] getCellsInRow(int r){
        return rows[r];
    }

    public static int[] getCellsInBlock(int b){
        return blocks[b];
    }

    public static int[] getCellCRB(int c){
        int crb[] = new int[3];
        crb[0] = getCellColumn(c);
        crb[1] = getCellRow(c);
        crb[2] = getCellBlock(c);
        return crb;
    }

    public void setCell(int c, int v){
        grid[c] = v;
    }

    public boolean valueInColumn(int v, int c){
        int column[] = getCellsInColumn(c);
        for(int count : column){
            if(grid[count] == v){
                return true;
            }
        }
        return false;
    }

    public boolean valueInRow(int v, int r){
        int row[] = getCellsInRow(r);
        for(int c : row){
            if(grid[c] == v){
                return true;
            }
        }
        return false;
    }

    public boolean valueInBlock(int v, int b){
        int block[] = getCellsInBlock(b);
        for(int c : block){
            if(grid[c] == v){
                return true;
            }
        }
        return false;
    }

    public boolean valueIsInCellCRB(int v, int c){
        int crb[] = getCellCRB(c);
        if(valueInColumn(v, crb[0])){
            return true;
        }
        if(valueInRow(v, crb[1])){
            return true;
        }
        if(valueInBlock(v, crb[2])){
            return true;
        }
        return false;
    }

    public void deleteCell(int c){
        grid[c] = 0;
    }

    public void randomize(){
        for(int c = 0; c < grid.length - 1; c++){
            boolean terminator = true;
            while(terminator){
                int newCell = r.nextInt(9) + 1;
                if(valueIsInCellCRB(newCell, c) != true){
                    grid[c] = newCell;
                    terminator = false;
                }
            }
        }
    }
}
