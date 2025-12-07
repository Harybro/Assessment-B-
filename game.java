import java.awt.RadialGradientPaint;
import java.util.List; // Import List interface
import java.util.ArrayList; // Import ArrayList class
import java.util.Random;    // Import Random class
import java.util.Scanner;


public class game { //class fields 
    private board player1Board; //game board where creatures are placed
    private board player2Board;
    private List<creature> creatureList; //list of creatures available in the game
    private int player1Score = 0;
    private int player2Score = 0;

    
   
    public game() { //constructor 
        player1Board = new board(10, 10); //creates a 10x10 game board
        player2Board = new board(10, 10);


        creatureList = new ArrayList<>(); //creates empty list to hold creatures

        // Add some creatures to the list
        creatureList.add(new creature("Fish", 'F')); 
        creatureList.add(new creature("Shark", 'S'));
        creatureList.add(new creature("Crab", 'C'));

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

            if (b.placeCreatureShape(row, col, shape, randomCreature.getSymbol(), randomCreature)) { //tries to place the creature
                System.out.println("Placed " + randomCreature.getName() + " at (" + row + "," + col + ")");
                return; //successful placement, exit the method
            }
        }
         System.out.println("Failed to place creature after many tries.");
    }

    public void setupBoards() {
        for (int i = 0; i < 1; i++) {
            placeRandomCreature(player1Board);
            placeRandomCreature(player2Board);
        }
    }  


public void runGame() {
    setupBoards(); 
    Scanner scanner = new Scanner(System.in);

    while (true) {

        // ------------------------------
        // PLAYER 1 TURN
        // ------------------------------
        player2Board.displayGuessBoard();   // what P1 sees
        player2Board.display();
        System.out.print("Enter row: ");
        int row = scanner.nextInt();
        System.out.print("Enter col: ");
        int col = scanner.nextInt();



        int points = player2Board.guess(row, col);

        if (points > 0) {
            System.out.println("Hit! +" + points + " points");
            player1Score += points;
        } else {
            System.out.println("Miss!");
        }
        System.out.println("Player 1 Score: " + player1Score);

        if (player2Board.allCreaturesDestroyed()) {
            System.out.println("PLAYER 1 WINS!");
            break;
        }


        pause(scanner); // wait before switching


        // ------------------------------
        // PLAYER 2 TURN
        // ------------------------------
        System.out.println("\n===== PLAYER 2 TURN =====");
        System.out.println("Your Guess Board:");
        player1Board.displayGuessBoard();   // what P2 sees

        System.out.print("Enter row: ");
        row = scanner.nextInt();
        System.out.print("Enter col: ");
        col = scanner.nextInt();

        if (player1Board.allCreaturesDestroyed()) {
            System.out.println("PLAYER 2 WINS!");
            break;
    }

        if (player1Board.guess(row, col) != 0) {
            System.out.println("Hit!");
        } else {
            System.out.println("Miss!");
        }

        if (player2Board.allCreaturesFound()) {
            System.out.println("PLAYER 1 WINS!");
            break;
    }

        pause(scanner);
    }
}


    private void pause(Scanner scanner) {
        System.out.println("Press ENTER to continue...");
        scanner.nextLine();  // consumes leftover input
        scanner.nextLine();  // waits for actual ENTER
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear console
        System.out.flush(); // flush the console
    }


    

}
