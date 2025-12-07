import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class board { //class fields
    private int rows;
    private int cols;
    private char[][] grid;
    private char[][] guessBoard;
    private List<creature> creaturesOnBoard = new ArrayList<>();
    
    
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



    public void displayGuessBoard() { //displays the guess board
    System.out.print("    ");
    for (int c = 0; c < cols; c++) {
        System.out.printf("%3d", c);
    }
    System.out.println();

    for (int r = 0; r < rows; r++) { //rows
        System.out.printf("%2d: ", r );
        for (int c = 0; c < cols; c++) { //columns
            System.out.printf("%3c", guessBoard[r][c]);
        }
        System.out.println();
    }
}

public int guess(int row, int col) { //processes a guess at the specified coordinates
    if (guessBoard[row][col] != '?') {
        return -1; // already guessed
    }

    char cell = grid[row][col];

    if (cell == '.') { //miss
        guessBoard[row][col] = 'M';
        return 0;
    }

    guessBoard[row][col] = 'H'; //hit
    grid[row][col] = 'H';

    for (creature c : creaturesOnBoard) { //find the creature that was hit
        if (c.getSymbol() == cell) {
            c.addHit();
            if (c.isDestroyed()) {
                System.out.println("You destroyed the " + c.getName() + "!");
                return 10;
            }
            return 5;
        }
    }
    return 0;
}








    private void initialiseBoard() { //initialises the board with empty cells
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

    public char[][] getGrid() { //returns the grid
        return grid;
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public boolean placeCreatureShape( //places a creature shape on the board
        int startRow,
        int startCol,
        creaturesShape shape,
        char symbol,
        creature c) {

    int[][] offs = shape.getOffsets();

    
    for (int[] o : offs) { // bounds + collision
        int r = startRow + o[0];
        int col = startCol + o[1];

        if (r < 0 || r >= rows || col < 0 || col >= cols) return false;
        if (grid[r][col] != '.') return false;
    }

   
    for (int[] o : offs) {  // place symbols
        int r = startRow + o[0];
        int col = startCol + o[1];
        grid[r][col] = symbol;
    }

    c.setTotalParts(offs.length); // set total parts of the creature
    creaturesOnBoard.add(c);

    return true;
}



    public boolean placeCreatureShapeRandom(creaturesShape shape, char symbol, creature c) { //places a creature shape randomly on the board
        java.util.Random rand = new java.util.Random();

        for (int attempts = 0; attempts < 100; attempts++) { //try up to 100 times

            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);

            if (placeCreatureShape(row, col, shape, symbol, c)) {
                return true;
            }
        }

        return false;
    }

public boolean allCreaturesDestroyed() { //checks if all creatures on the board are destroyed
    for (creature c : creaturesOnBoard) {
        if (!c.isDestroyed()) return false;
    }
    return true;
}




public void addCreatureToBoard(creature c) { //adds a creature to the board's creature list
    creaturesOnBoard.add(c);
}


public void save(PrintWriter out) { //saves the board state to a PrintWriter

    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            out.print(grid[r][c]);
        }
        out.println();
    }

    for (int r = 0; r < rows; r++) { // guess board
        for (int c = 0; c < cols; c++) {
            out.print(guessBoard[r][c]);
        }
        out.println();
    }

    out.println(creaturesOnBoard.size());

    for (creature c : creaturesOnBoard) { // save each creature's state
        out.println(c.getName());
        out.println(c.getSymbol());
        out.println(c.getHitCount());
        out.println(c.getTotalParts());
    }
}



public void load(java.util.Scanner in) { //loads the board state from a Scanner

    // grid
    for (int r = 0; r < rows; r++) {
        String line = in.nextLine();
        for (int c = 0; c < cols; c++) {
            grid[r][c] = line.charAt(c);
        }
    }

    // guess board
    for (int r = 0; r < rows; r++) {
        String line = in.nextLine();
        for (int c = 0; c < cols; c++) {
            guessBoard[r][c] = line.charAt(c);
        }
    }

    int count = Integer.parseInt(in.nextLine());
    creaturesOnBoard.clear();

    for (int i = 0; i < count; i++) { // load each creature's state
        String name = in.nextLine();
        char symbol = in.nextLine().charAt(0);
        int hits = Integer.parseInt(in.nextLine());
        int totalParts = Integer.parseInt(in.nextLine());

        creature cr = new creature(name, symbol);
        cr.setHitCount(hits);
        cr.setTotalParts(totalParts);
        creaturesOnBoard.add(cr);
    }
}

public boolean alreadyGuessed(int row, int col) { //checks if a cell has already been guessed
    return guessBoard[row][col] != '?';
}


public boolean hasCreatureWithSymbol(char symbol) { //checks if a creature with the given symbol exists on the board
    for (creature c : creaturesOnBoard) {
        if (c.getSymbol() == symbol) 
        return true;
    }
    return false;
}

}



