import java.util.*;
public class Game {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ConnectFour game = new ConnectFour();
        int column;
        while(!game.getWin()) {
            System.out.println(game);
            System.out.println("---------------------------");
            System.out.print("Drop a red disk at column(0 - 6): ");
            boolean continueInput = true;
            while(continueInput) {
                try {
                    column = reader.nextInt();
                    try {
                        if(game.insert('R', column)) {
                            continueInput = false;
                        }
                        else {
                            System.out.println("Column is full. Try another column.");
                        }
                    }catch(IndexOutOfBoundsException ex) {
                        System.out.println("Enter an integer between 0 and 6.");
                        continueInput = true;
                        reader.nextLine();
                    }
                }catch(InputMismatchException ex) {
                    System.out.println("Enter an integer, please try again.");
                    reader.nextLine();
                }
            }
            game.isDraw();
            if(game.getWin()) {
                break;
            }
            System.out.println(game);
            System.out.println("---------------------------");
            System.out.print("Drop a yellow disk at column(0 - 6): ");
            continueInput = true;
            while(continueInput) {
                try {
                    column = reader.nextInt();
                    try {
                        if(game.insert('Y', column)) {
                            continueInput = false;
                        }
                        else {
                            System.out.println("Column is full. Try another column.");
                        }
                    }catch(IndexOutOfBoundsException ex) {
                        System.out.println("Enter an integer between 0 and 6.");
                        continueInput = true;
                        reader.nextLine();
                    }
                }catch(InputMismatchException ex) {
                    System.out.println("Enter an integer, please try again.");
                    reader.nextLine();
                }
            }
            game.isDraw();
        }
        System.out.println(game);
        System.out.println("---------------------------");
        if(game.getWinner() == 'R') {
            System.out.println("The red player is the winner.");
        }
        else if(game.getWinner() == 'Y') {
            System.out.println("The yellow player is the winner.");
        }
        else {
            System.out.println("The game is a draw.");
        }
        reader.close();
    }
}
