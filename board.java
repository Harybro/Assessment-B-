import java.util.ArrayList;
import java.util.List;

public class board {
    private int rows;
    private int cols;
    private char[][] grid;
    private char[][] guessBoard;

    //constructor
    public board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        initialiseBoard();

        guessBoard = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                guessBoard[r][c] = '?';  // unknown to the attacker
           }
        }
    }



    public void displayGuessBoard() {
    System.out.print("    ");
    for (int c = 0; c < cols; c++) {
        System.out.printf("%3d", c);
    }
    System.out.println();

    for (int r = 0; r < rows; r++) {
        System.out.printf("%2d: ", r );
        for (int c = 0; c < cols; c++) {
            System.out.printf("%3c", guessBoard[r][c]);
        }
        System.out.println();
    }
}

    public int guess(int row, int col) {
    char cell = grid[row][col];

    // If already guessed before, no score
    if (cell == 'M' || cell == 'H') {
        return 0;
    }

    // MISS
    if (cell == '.') {
        grid[row][col] = 'M';
        return 0;
    }

    // HIT a creature
    grid[row][col] = 'H';

    // find creature
    for (creature c : creaturesOnBoard) {
        if (c.getSymbol() == cell) {
            c.addHit();

            if (c.isDestroyed()) {
                System.out.println("You destroyed the " + c.getName() + "!");
                return 10; // 5 hit + 5 bonus
            }

            return 5; // normal hit
        }
    }

    return 0;
    }






    private void initialiseBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = '.';
            }
        }
    }

     //displays the board
    public void display() {
        System.out.print("    ");
        for (int c = 0; c < cols; c++) {
            System.out.printf("%2d", c);
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            System.out.printf("%2d: ", r );
            for (int c = 0; c < cols; c++) {
                System.out.printf("%2c", grid[r][c]);
            }
            System.out.println();
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    // ⭐ Add these getters here
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public boolean placeCreatureShape(int startRow, int startCol, creaturesShape shape, char symbol, creature c) {

        int[][] offs = shape.getOffsets();

        // check bounds + collisions
        for (int[] o : offs) {
            int r = startRow + o[0];
            int cCol = startCol + o[1];

            if (r < 0 || r >= rows || cCol < 0 || cCol >= cols) return false;
            if (grid[r][cCol] != '.') return false;
        }

        // place symbols
        for (int[] o : offs) {
            int r = startRow + o[0];
            int cCol = startCol + o[1];
            grid[r][cCol] = symbol;
        }

        c.assignShape(shape);
        addCreatureToBoard(c);

        return true;
    }

    public boolean placeCreatureShapeRandom(creaturesShape shape, char symbol, creature c) {
        java.util.Random rand = new java.util.Random();

        for (int attempts = 0; attempts < 100; attempts++) {

            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);

            if (placeCreatureShape(row, col, shape, symbol, c)) {
                return true;
            }
        }

        return false;
    }

    public boolean allCreaturesFound() {
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            char ch = grid[r][c];
            // If we find any creature letters (not '.', 'M', '*'), game still ongoing
            if (ch != '.' && ch != 'M' && ch != '*') return false;
        }
    }
    return true;
}

public boolean allCreaturesDestroyed() {
    for (creature c : creaturesOnBoard) {
        if (!c.isDestroyed()) return false;
    }
    return true;
}


private List<creature> creaturesOnBoard = new ArrayList<>();

public void addCreatureToBoard(creature c) {
    creaturesOnBoard.add(c);
}



}
