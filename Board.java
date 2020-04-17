import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private final int[][] grid;
    private int n;

    public Board(int[][] tiles) {
        n = tiles.length;
        grid = copyBoardContent(tiles);
    }

    private int[][] copyBoardContent(int[][] original) {

        int[][] destin = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                destin[row][col] = original[row][col];
            }
        }
        return destin;

    }

    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append(n + "\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d", grid[i][j]));
            }
            s.append("\n");
        }

        return s.toString();

    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int hamCount = 0;
        int k = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) break;
                if (grid[i][j] != k) hamCount += 1;
                k += 1;
            }
        }
        return hamCount;
    }

    public int manhattan() {
        int distance = 0;

        for (int row = 0; row < n; row++)
            for (int col = 0; col < n; col++)
                distance += distanceToCorrectPosition(grid[row][col], row, col);

        return distance;
    }

    private int distanceToCorrectPosition(int value, int currentRow, int currentCol) {
        if (value == 0) return 0;

        int correctRow = (value - 1) / n;
        int correctCol = (value - 1) % n;

        int dist = Math.abs(currentRow - correctRow) + Math.abs(currentCol - correctCol);
        return dist;
    }

    public boolean isGoal() {
        boolean goal = false;
        int l = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) break;
                else {
                    if (grid[i][j] == l) goal = true;
                    else goal = false;
                }
                l += 1;
            }
        }
        return goal;
    }

    public boolean equals(Object that) {
        if (that == this) return true;
        if (that == null) return false;

        if (that.getClass() != this.getClass())
            return false;

        Board other = (Board) that;
        return Arrays.deepEquals(this.grid, other.grid);

    }


    public Iterable<Board> neighbors() {
        int blankRow = 0, blankCol = 0;
        boolean blankFound = false;
        ArrayList<Board> neighbors = new ArrayList<Board>();

        for (blankRow = 0; blankRow < n; blankRow++) {
            for (blankCol = 0; blankCol < n; blankCol++)
                if (grid[blankRow][blankCol] == 0) {
                    blankFound = true;
                    break;
                }

            if (blankFound) break;
        }

        // Top neighbor
        if (blankRow > 0)
            neighbors.add(copyFromOriginalAndSwapTiles(blankRow, blankCol, blankRow - 1, blankCol));

        // Left neighbor
        if (blankCol > 0)
            neighbors.add(copyFromOriginalAndSwapTiles(blankRow, blankCol, blankRow, blankCol - 1));

        // Right neighbor
        if (blankCol < (n - 1))
            neighbors.add(copyFromOriginalAndSwapTiles(blankRow, blankCol, blankRow, blankCol + 1));

        // Bottom neighbor
        if (blankRow < (n - 1))
            neighbors.add(copyFromOriginalAndSwapTiles(blankRow, blankCol, blankRow + 1, blankCol));

        return neighbors;
    }

    public Board twin() {
        int swappingRow, swappingCol, twinRow, twinCol;
        swappingRow = swappingCol = 0;
        twinCol = twinRow = n - 1;

        if (grid[swappingRow][swappingCol] == 0)
            swappingCol++;

        if (grid[twinRow][twinCol] == 0)
            twinCol--;

        return copyFromOriginalAndSwapTiles(swappingRow, swappingCol, twinRow, twinCol);
    }

    private Board copyFromOriginalAndSwapTiles(int originalRow, int originalCol, int newRow, int newCol) {
        int[][] boardCopy = copyBoardContent(grid);

        boardCopy[originalRow][originalCol] = grid[newRow][newCol];
        boardCopy[newRow][newCol] = grid[originalRow][originalCol];

        return new Board(boardCopy);
    }

    public static void main(String[] args) {
        // create initial board from file

        int[][] input = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        int[][] oth = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board tBoard = new Board(input);
        Board tbd = new Board(oth);

        System.out.println(tBoard.toString());
        System.out.println(tBoard.hamming());
        System.out.println(tBoard.manhattan());
        System.out.println(tBoard.isGoal());
        System.out.println(tBoard.equals(tbd));
        System.out.println(tBoard.twin());
    }
}


