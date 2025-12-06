import java.awt.RadialGradientPaint;
import java.util.List; // Import List interface
import java.util.ArrayList; // Import ArrayList class
import java.util.Random;    // Import Random class
import java.util.Scanner;


public class game { //class fields 
    private board player1Board; //game board where creatures are placed
    private board player2Board;
    private List<creature> creatureList; //list of creatures available in the game
    
   
    public game() { //constructor 
        player1Board = new board(16, 16); //creates a 10x10 game board
        player2Board = new board(16, 16);


        creatureList = new ArrayList<>(); //creates empty list to hold creatures

        // Add some creatures to the list
        creatureList.add(new creature("Fish", 'F', ShapeLibrary.vShape())); 
        creatureList.add(new creature("Shark", 'S', ShapeLibrary.circle()));
        creatureList.add(new creature("Crab", 'C', ShapeLibrary.line()));

    }



    private static final String[] SHAPE_TYPES = { //array of shape type names 
        "Vshape",
        "crcle",
        "line",
        "horizontalLine"
    };


    public void placeRandomCreature(board b) { //chooses and places a random creature
        Random rand = new Random(); //random number generator
        String type = SHAPE_TYPES[rand.nextInt(SHAPE_TYPES.length)];

        creature randomCreature = creatureList.get(rand.nextInt(creatureList.size())); //this picks a random creature for the list


        //randomly choose a shape for the creature

        creaturesShape shape = switch (type) { //switch expression to select shape
            case "vshape" -> ShapeLibrary.vShape();
            case "circle" -> ShapeLibrary.circle();
            case "line" -> ShapeLibrary.line();
            case "horizontalLine" -> ShapeLibrary.horizontalLine();
            default -> ShapeLibrary.circle(); // fallback
        };

        for (int attempts = 0; attempts < 100; attempts++)  { //tries to place the creature up to 100 times
            int row = rand.nextInt(b.getRows());  
            int col = rand.nextInt(b.getCols());

            if (b.placeCreatureShape(row, col, shape, randomCreature.getSymbol())) { //tries to place the creature
                System.out.println("Placed " + randomCreature.getName() + " at (" + row + "," + col + ")");
                return; //successful placement, exit the method
            }
        }
         System.out.println("Failed to place creature after many tries.");
    }

    public void setupBoards() {
        for (int i = 0; i < 5; i++) {
            placeRandomCreature(player1Board);
            placeRandomCreature(player2Board);
        }
    }  


    public void runGame() {
        setupBoards(); // fills both boards
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // --- PLAYER 1 TURN ---
            System.out.println("Player 1, choose a target:");
            player2Board.displayGuessBoard();
            
            System.out.print("Row: ");
            int row = scanner.nextInt();
            System.out.print("Col: ");
            int col = scanner.nextInt();

            if (player2Board.guess(row, col)) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }

        // --- PLAYER 2 TURN ---
            System.out.println("\nPlayer 2, choose a target:");
            player1Board.displayGuessBoard();

            System.out.print("Row: ");
            row = scanner.nextInt();
            System.out.print("Col: ");
            col = scanner.nextInt();

            if (player1Board.guess(row, col)) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }
    }
}

}
