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

    public boolean placeCreature(int startRow, int startCol, int length, char symbol, boolean horizontal) {
        if (horizontal) {
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
        }




        return true;
    }
}
