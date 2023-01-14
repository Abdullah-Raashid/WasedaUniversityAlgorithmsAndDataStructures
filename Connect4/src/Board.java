import java.util.Objects;

public class Board {
    // our board has 3 states, empty, filled and what it is filled with.
    private static final int rows = 6;
    private static final int columns = 7;

    // board will be the size of Pieces, say 6 pieces of colomns
    Piece[][] ourBoard = new Piece[rows][columns];

    // constructor
    public Board() {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++) ourBoard[row][col] = null;
    }

    public static int getColumns() {
        return columns;
    }

    /*
    public static int getRows() {
        return rows;
    }
    */

    public boolean checkWinner(int col, String winningColor) {
        boolean someoneWon = false;

        // figuring out where the last piece is
        for (int row = 0; row < rows; row++) {
            if (ourBoard[row][col] != null) {
                // If this reaches 0 we have won
                int winningStreak = 3;

                //Exercise 1:Judgement of winner (vertical/horizontal)
                //check downwards / vertical
                for (int winRow = row + 1; winRow < rows; winRow++) {
                    if (Objects.equals(ourBoard[winRow][col].getColor(), winningColor)) {
                        winningStreak--;
                        if (winningStreak == 0) someoneWon = true;
                    } else {
                        winningStreak = 3;
                    }
                }
                // for any other type of check it needs to be 4, because left to right
                winningStreak = 4;


                // look at the horizontal
                for (int winCol = col - 3; winCol <= col + 3; winCol++) {
                    if (winCol < 0) continue;
                    if (winCol >= columns) break;
                    if (ourBoard[row][winCol] != null && Objects.equals(ourBoard[row][winCol].getColor(), winningColor)) {
                        winningStreak--;
                        if (winningStreak == 0) someoneWon = true;

                    } else {
                        winningStreak = 4;
                    }
                }
                winningStreak = 4;

                //Exercise 2:Judgement of winner (diagonal)
                //Checking the left diagonal
                for (int winRow = row - 3, winCol = col - 3; winRow <= row + 3 && winCol <= col + 3; winRow++, winCol++) {
                    if (winRow < 0 || winCol < 0) continue;
                    if (winRow >= rows || winCol >= columns) break;
                    if (ourBoard[winRow][winCol] != null && Objects.equals(ourBoard[winRow][winCol].getColor(), winningColor)) {
                        winningStreak--;
                        if (winningStreak == 0) someoneWon = true;

                    } else {
                        winningStreak = 4;
                    }
                }
                winningStreak = 4;
                //Checking the right diagonal
                for (int winRow = row - 3, winCol = col + 3; winRow <= row + 3 && winCol >= col - 3; winRow++, winCol--) {
                    if (winRow < 0 || winCol >= columns) continue; // because we are decreasing
                    if (winRow >= rows || winCol < 0) break; // because we are decreasing
                    if (ourBoard[winRow][winCol] != null && Objects.equals(ourBoard[winRow][winCol].getColor(), winningColor)) {
                        if (--winningStreak == 0) someoneWon = true;
                    } else {
                        winningStreak = 4;
                    }
                }
                winningStreak = 4;
                break;
            }
        }

        return someoneWon;
    }

    // Exercise 3: Functionality of placing a piece
    // handling if the piece being dropped has succeeded or not
    public boolean addPiece(int colToAdd, String color) {
        // Validity check
        if (colToAdd > columns || colToAdd < 0) {
            System.err.print("Please enter a valid row\n");
            return false;
        } else {
            // We can add
            if (ourBoard[0][colToAdd] == null) {
                boolean addedThePiece = false;
                // Checking from up to down: this makes sure that
                // they cannot place the pieces against gravity
                for (int row = rows - 1; row >= 0; row--) {
                    if (ourBoard[row][colToAdd] == null) {
                        ourBoard[row][colToAdd] = new Piece();
                        ourBoard[row][colToAdd].setColor(color);
                        addedThePiece = true;
                        break;
                    }
                }
                return addedThePiece;

            } else {
                // we say that this column is full
                System.err.print("This column is full\n");
                return false;

            }

        }

    }

    public void printBoard() {
        /*
            for (int col = 0; col < columns + 8; col++) {
                System.out.print("-");
            }
         */
        System.out.println();
        System.out.println(" 1 2 3 4 5 6 7");
        for (int row = 0; row < rows; row++) {
            System.out.print("|");
            for (int col = 0; col < columns; col++) {
                if (ourBoard[row][col] == null) System.out.print(" ");
                else System.out.print(ourBoard[row][col].getColor());
                System.out.print("|");
            }
            System.out.println();
        }
    }
}