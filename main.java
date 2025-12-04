import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("menu");
            System.out.println("1. play");
            System.out.println("2. load game");
            System.out.println("3. help");
            System.out.println("4. exit");

            int choice = s.nextInt();
            switch(choice) {
                case 1:
                    System.out.print("Starting the game... \n");
                    game g = new game();
                    g.runGame();
                    break;
                case 2:
                    System.out.print("Loading game...");
                    break;
                case 3:
                    System.out.print("Help section...");
                    break;
                case 4:
                    System.out.print("Exiting...");
                    System.exit(0);

            }

        }
    }
}
