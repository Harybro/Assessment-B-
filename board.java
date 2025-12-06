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

    public boolean guess(int row, int col) { // player makes a guess

        // player hit
        if (grid[row][col] != '.') {
            guessBoard[row][col] = 'X';
            return true;
        }

        // player miss
        guessBoard[row][col] = 'O';
        return false;
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


    public boolean placeCreatureShape(int startRow, int startCol, creaturesShape shape, char symbol) {

        int[][] offs = shape.getOffsets();

        // check bounds + collisions
        for (int[] o : offs) {
            int r = startRow + o[0];
            int c = startCol + o[1];

            if (r < 0 || r >= rows || c < 0 || c >= cols) return false;
            if (grid[r][c] != '.') return false;
        }

        // place symbols
        for (int[] o : offs) {
            int r = startRow + o[0];
            int c = startCol + o[1];
            grid[r][c] = symbol;
        }

        return true;
    }

    public boolean placeCreatureShapeRandom(creaturesShape shape, char symbol) {
        java.util.Random rand = new java.util.Random();

        for (int attempts = 0; attempts < 100; attempts++) {

            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);

            if (placeCreatureShape(row, col, shape, symbol)) {
                return true;
            }
        }

        return false;
    }

}
