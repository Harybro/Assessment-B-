
public class board {
    private int rows;
    private int cols;
    private char[][] grid;

    public board(int rows, int cols) { // Constructor 
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        initialiseBoard();
    }

    private void initialiseBoard() { // Fill the board with default character '.'
        for (int r = 0; r <rows;r++) {
            for (int c = 0; c < cols; c++){
                grid[r][c] = '.';
                }
            }
        }


    public void display() { // Display the board

        System.out.print("    ");
        for (int c = 0; c < cols; c++) { // Column headers
            System.out.print(c + " ");
        }
        System.out.println();

        for (int r = 0; r < rows; r++) { // Rows with row headers
            System.out.print(r + ": ");
            for (int c = 0; c < cols; c++) { // Print each cell
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }

    }
    public char[][] getGrid() { // Getter for grid
        return grid;
    }

    public boolean placeCreature(int startRow, int startCol, int length, char symbol, boolean horizontal) {  //places a creature
        if (horizontal) { //checks if the creature fits on the board
            if (startCol + length > cols) {
                return false;
            } 
        } else {
            if (startRow + length > rows) {
                return false;
            }
        }
        
        for (int i = 0; i < length; i++) {
            int r = startRow + (horizontal ? 0 : i);
            int c = startCol + (horizontal ? i : 0);
            if (grid[r][c] != '.') { //checks if the space is already occupied
                return false;
        }


    }

            for (int i = 0; i < length; i++) {
            int r = startRow + (horizontal ? 0 : i);
            int c = startCol + (horizontal ? i : 0);
            grid[r][c] = symbol;
        }

        return true;
    }


    public boolean placeCreatureShape(int startRow, int startCol, creatures shape) {

    for (int[] offset : shape.offsets) {  // 1. Check bounds + collisions
        int r = startRow + offset[0];
        int c = startCol + offset[1];
        

        if (r < 0 || r >= rows || c < 0 || c >= cols) // Board boundary check
            return false;

        if (grid[r][c] != '.') // Collision check
            return false;
    }

    
    for (int[] offset : shape.offsets) { // 2. Place all segments
        int r = startRow + offset[0];
        int c = startCol + offset[1];
        grid[r][c] = shape.symbol;
    }

    return true;
}
    public boolean placeCreatureShapeRandom(creatures shape) {
    java.util.Random rand = new java.util.Random();

    for (int attempts = 0; attempts < 100; attempts++) {

        int row = rand.nextInt(rows);
        int col = rand.nextInt(cols);

        if (placeCreatureShape(row, col, shape)) {
            return true; // success
        }
    }
    
    return false; // failed after many tries
}


}
