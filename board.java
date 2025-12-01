public class board {

    private int rows;
    private int cols;
    private char[][] grid;

    public board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        initialiseBoard();
    }

    private void initialiseBoard() {
        for (int r = 0; r <rows;r++) {
            for (int c = 0; c < cols; c++){
                grid[r][c] = '.';
            }
        }
    }

    private void display() {
        System.out.print("    ");
        for (int c = 0; c < cols; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        
    }
}
