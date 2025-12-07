import java.util.Scanner; // Import Scanner class

public class main {
    public static void main(String[] args) { 
        Scanner s = new Scanner(System.in); // Scanner for user input
        game g = new game(); // Create a new game instance

        while (true) { // Main menu loop
            System.out.println("1. Play");
            System.out.println("2. Load");
            System.out.println("3. help");
            System.out.println("4. Exit");
        
            if (!s.hasNextInt()) { // Input Validation
                s.next();
                System.out.println("Invalid input.");
                continue;
            }

            int choice = s.nextInt(); // Get user choice
        
            switch (choice) {
                case 1: // Start a new game
                    g.setupBoards(); // Setup game boards
                    g.runGame(); // Start the game loop
                    break;
                case 2: // Load a saved game
                    System.out.print("Filename: (save.txt)"); // Ask for filename
                    g.loadGame(s.next()); // Load the game
                    break;
                case 3: // Display help information
                    System.out.println("Welcome to the Creature Battle Game!");
                    System.out.println("Players take turns guessing the locations of each other's creatures on a grid.");
                    System.out.println("Each creature has a specific shape and size, represented by different symbols.");
                    System.out.println("The goal is to find and destroy all your creatures before the opponent does.");
                    System.out.println("Good luck and have fun!");
                    break;
                case 4: // Exit the game
                     System. exit(0);
                default: // Invalid choice handling
                    System.out.println("Invalid choice. Please try again.");
            }
}
    }
}
