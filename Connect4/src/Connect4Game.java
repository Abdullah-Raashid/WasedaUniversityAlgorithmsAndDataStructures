import java.util.Random;
import java.util.Scanner;

public class Connect4Game {
    private final Board board;
    private final String color1;
    private final String color2;

    //true if 1 is playing, false if 2
    private boolean isXPlaying;

    public Connect4Game(String color1, String color2) {
        this.board = new Board();
        this.color1 = color1;
        this.color2 = color2;

        isXPlaying = (new Random()).nextBoolean();
    }

    public boolean checkWinner(int column) {
        /* high level syntax: string is storing either color1 or
         color2 depending on weather isXplaying is true or not*/
        String winningColor = isXPlaying ? color1 : color2;
        return board.checkWinner(column, winningColor);
    }

    public void startGame() {
        boolean running = true;

        //turns of play
        while (running) {
            board.printBoard();
            String color;
            if (isXPlaying) {
                color = color1;
                System.out.println(color1 + " is playing");
            } else {
                color = color2;
                System.out.println(color2 + " is playing");
            }
            System.out.println("Please select the column you want to put the piece in.");
            System.out.print("Select the column between 1 and " + Board.getColumns() + ":");
            Scanner sc = new Scanner(System.in);
            int column = sc.nextInt() - 1;

            boolean success = board.addPiece(column, color);
            if (success) {
                if (checkWinner(column)) {
                    board.printBoard();
                    running = false;
                    if (isXPlaying) {
                        System.out.println(color1 + " won");
                    } else {
                        System.out.println(color2 + " won");

                    }
                }
                isXPlaying = !isXPlaying;
            }
        }
    }
}
