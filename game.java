import java.awt.RadialGradientPaint;
import java.io.File;
import java.io.PrintWriter;
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
    private String player1Name;
    private String player2Name;


    
   
    public game() { //constructor 
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Player 1 name: ");
        player1Name = scanner.nextLine();

        System.out.print("Enter Player 2 name: ");
        player2Name = scanner.nextLine();

        player1Board = new board(10, 10); //creates a 10x10 game board
        player2Board = new board(10, 10);


        creatureList = new ArrayList<>(); //creates empty list to hold creatures

        // Add some creatures to the list
        creatureList.add(new creature("Fish", 'F' ));
        creatureList.add(new creature("Shark", 'S'));
        creatureList.add(new creature("Crab", 'C'));

    }

    //public game(boolean load) {
    //creatureList = createCreatureList();
//
    //if (load) {
    //    loadGame("save.txt");
    //}
    //}
//

    private static final String[] SHAPE_TYPES = { //array of shape type names 
        "Vshape",
        "crcle",
        "line",
        "horizontalLine"
    };


    public void placeRandomCreature(board b) { //chooses and places a random creature
        Random rand = new Random(); //random number generator
        String type = SHAPE_TYPES[rand.nextInt(SHAPE_TYPES.length)];

        creature template = creatureList.get(rand.nextInt(creatureList.size()));
        if (b.hasCreatureWithSymbol(template.getSymbol())) {
        return; // skip placing this creature
        }
        creature randomCreature = new creature(
            template.getName(),
            template.getSymbol()
        );


        //randomly choose a shape for the creature

        creaturesShape shape = switch (type) { //switch expression to select shape
            case "vshape" -> ShapeLibrary.vShape();
            case "circle" -> ShapeLibrary.circle();
            case "line" -> ShapeLibrary.line();
           // case "horizontalLine" -> ShapeLibrary.horizontalLine();
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

    public void setupBoards() { //sets up both players' boards with random creatures
        for (int i = 0; i < 7; i++) {
            placeRandomCreature(player1Board);
            placeRandomCreature(player2Board);
        }
    }  


public void runGame() { //main game loop
    Scanner scanner = new Scanner(System.in);

    while (true) {

        // ------------------------------
        // PLAYER 1 TURN
        // ------------------------------
        System.out.println("\n=====" + player1Name + "====="); 
        player2Board.displayGuessBoard();  // what P1 sees
        int row, col;
        while (true) { //input loop for valid guess
           row = getValidCoordinate(scanner, "Enter row: ", player2Board.getRows());
           col = getValidCoordinate(scanner, "Enter column: ", player2Board.getCols());
        
           if (player2Board.alreadyGuessed(row, col)) { //check if already guessed
               System.out.println("You already guessed that position. Try again.");
           } else {
               break;
           }
        }

        int points = player2Board.guess(row, col); //process the guess and get points

        if (points > 0) { //hit case
            System.out.println("Hit! +" + points + " points");
            player1Score += points;
        } else { //miss case
            System.out.println("Miss!");
        }
        System.out.println("Player 1 Score: " + player1Score); //display score

        if (player2Board.allCreaturesDestroyed()) { //check for win condition
            System.out.println(player1Name + " WINS!");
            break;
        }

        System.out.println("Save game? (y/n)"); //ask to save game
        if (scanner.next().equalsIgnoreCase("y")) {
            saveGame("save.txt");
        }

        scanner.nextLine(); // clear input

        System.out.println("Exit? (y/n)"); //ask to exit game
        if (scanner.next().equalsIgnoreCase("y")) {
            System. exit(0);
        }


        pause(scanner); // wait before switching


        // ------------------------------
        // PLAYER 2 TURN
        // ------------------------------
        System.out.println("\n=====" + player2Name + "=====");
        System.out.println("Your Guess Board:");
        player1Board.displayGuessBoard();   // what P2 sees

        while (true) { //input loop for valid guess
           row = getValidCoordinate(scanner, "Enter row: ", player2Board.getRows());
           col = getValidCoordinate(scanner, "Enter column: ", player2Board.getCols());
        
           if (player2Board.alreadyGuessed(row, col)) { //check if already guessed
               System.out.println("You already guessed that position. Try again.");
           } else {
               break;
           }
        }

        if (player1Board.allCreaturesDestroyed()) { //check for win condition
            System.out.println(player2Name + "WINS!");
            break;
    }

        if (player1Board.guess(row, col) != 0) { //process the guess
            System.out.println("Hit!");
        } else {
            System.out.println("Miss!");
        }

        if (player2Board.allCreaturesDestroyed()) { //check for win condition
            System.out.println("PLAYER 1 WINS!");
            break;
    }

        System.out.println("Save game? (y/n)"); //ask to save game
        if (scanner.next().equalsIgnoreCase("y")) {
            saveGame("save.txt");
        }
        scanner.nextLine(); // clear input

        System.out.println("Exit? (y/n)"); //ask to exit game
        if (scanner.next().equalsIgnoreCase("y")) {
            System. exit(0);
        }

        pause(scanner);
    }
}


    private void pause(Scanner scanner) { //pauses the game until user presses ENTER
        System.out.println("Press ENTER to continue...");
        scanner.nextLine();  // consumes leftover input
        scanner.nextLine();  // waits for actual ENTER
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear console
        System.out.flush(); // flush the console
    }

    public void saveGame(String file) { //saves the current game state to a file
    try (PrintWriter out = new PrintWriter(file)) { 
        out.println(player1Score);
        out.println(player2Score);
        player1Board.save(out);
        player2Board.save(out);
    } catch (Exception e) { //handle exceptions
        System.out.println("Save failed: " + e.getMessage());
    }
}


public void loadGame(String file) { //loads a game state from a file
    try (Scanner in = new Scanner(new File(file))) { 
        player1Score = Integer.parseInt(in.nextLine());
        player2Score = Integer.parseInt(in.nextLine());
        player1Board.load(in);
        player2Board.load(in);
        runGame();
    } catch (Exception e) { //handle exceptions
        System.out.println("Load failed: " + e.getMessage());
    }
}

public void askForNames() { //asks players for their names
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Player 1 name: ");
    player1Name = scanner.nextLine();

    System.out.print("Enter Player 2 name: ");
    player2Name = scanner.nextLine();
}

private int getValidInt(Scanner scanner, String prompt) { //gets a valid integer from user input
    while (true) {
        System.out.print(prompt);
        if (scanner.hasNextInt()) { //check if input is an integer
            int value = scanner.nextInt();
            return value;
        } else { //invalid input case
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // discard invalid input
        }
    }
}

private int getValidCoordinate(Scanner scanner, String prompt, int max) { //gets a valid coordinate within bounds
    while (true) {
        int value = getValidInt(scanner, prompt);
        if (value >= 0 && value < max) {
            return value;
        } else {
            System.out.println("Please enter a value between 0 and " + (max - 1));
        }
    }
}



}
