import java.util.List;
import java.util.ArrayList;

public class Fire {
    /**
     * Returns how long it takes for all vulnerable trees to be set on fire if a
     * match is lit at a given location.
     * 
     * The forest is represented via a rectangular 2d char array where t represents a tree
     * and . represents an empty space.
     * 
     * At time 0, the tree at location [matchR, matchC] is set on fire. At every subsequent
     * time step, any tree that is adjacent (up/down/left/right) to a burning tree is also
     * set on fire. 
     * 
     * 
     * EXAMPLE 
     * forest:
     * 
     * t..tttt.t
     * ..tt....t
     * ..ttttttt
     * tttt.....
     * 
     * matchR: 2
     * matchC: 6
     * 
     * Result: 8
     * 
     * Explanation:
     * At time 0, the tree at (2, 6) is set on fire. At time 1, its adjacent trees also catch on fire
     * At time 2, the trees adjacent to those catch as well. At time 8, the last tree to catch is at
     * (0,6). In this example, there is one tree that never burns, so it is not included in the time calculation.
     * 
     * 
     * @param forest a 2d array where t represents a tree and . represents the ground
     * @param matchR The row the match is lit at
     * @param matchC The column the match is lit at
     * @return the time at which the final tree to be incinerated starts burning
     */
    public static int timeToBurn(char[][] forest, int matchR, int matchC) {
        return recursiveBurn(forest, matchR, matchC, new boolean[forest.length][forest[0].length], 0);
    }

    public static int recursiveBurn(char[][] forest, int row, int col, boolean[][] burned, int distance) {
        int time = distance;
        burned[row][col] = true;
        for (int[] tree : burnable(forest, row, col, burned)) {
            int newTime = recursiveBurn(forest, tree[0], tree[1], burned, distance + 1);
            if (time <= newTime) {
                time = newTime;
            }
        }
        return time;
    }


    /**
     * An explorer helper method to find burnable trees.
     * 
     * @param forest a 2d array, represented by t's (trees) and .'s (the ground)
     * @param r the row of the current flame
     * @param c the column of the current flame
     * @param burned a 2d boolean array, where true spaces are burned, and false spaces are untouched
     * @return a List of integer arrays contaning coordinate pairs of burnable forest
     */
    public static List<int[]> burnable(char[][] forest, int r, int c) {
        List<int[]> burnables = new ArrayList<>();
        int row = r, col = c;

        row = r + 1;
        if (row < forest.length && forest[row][col] == 't') {
            burnables.add(new int[]{row, col});
        }

        row = r - 1;
        if (row >= 0 && forest[row][col] == 't') {
            burnables.add(new int[]{row, col});
        }

        row = r;
        col = c + 1;
        if (col < forest[row].length && forest[row][col] == 't') {
            burnables.add(new int[]{row, col});
        }

        col = c - 1;
        if (col >= 0 && forest[row][col] == 't') {
            burnables.add(new int[]{row, col});
        }

        return burnables;
    }
}