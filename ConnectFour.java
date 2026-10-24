import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFour {

    static final int ROWS = 6;
    static final int COLS = 7;
    static char[][] board = new char[ROWS][COLS];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int xWins = 0, oWins = 0, draws = 0;
        String playAgain = "y";

        System.out.println("=================================");
        System.out.println("         CONNECT FOUR GAME       ");
        System.out.println("=================================");
        System.out.println("Drop your piece into a column (1-7).");
        System.out.println("Get 4 in a row (across, up/down or diagonal) to win!");

        do {
            resetBoard();
            char current = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                printBoard();
                System.out.print("Player " + current + ", choose a column (1-7): ");
                int col = readInt(sc);

                if (col < 1 || col > COLS) {
                    System.out.println("Invalid column! Choose a number from 1 to " + COLS + ".");
                    continue;
                }

                int row = dropPiece(col - 1, current);
                if (row == -1) {
                    System.out.println("That column is full! Try another one.");
                    continue;
                }

                if (checkWin(current)) {
                    printBoard();
                    System.out.println("*** Player " + current + " WINS with 4 in a row! ***");
                    if (current == 'X') {
                        xWins++;
                    } else {
                        oWins++;
                    }
                    gameOver = true;
                } else if (isFull()) {
                    printBoard();
                    System.out.println("*** The board is full. It's a DRAW! ***");
                    draws++;
                    gameOver = true;
                } else {
                    current = (current == 'X') ? 'O' : 'X';
                }
            }

            System.out.println("\n--- Scoreboard ---");
            System.out.println("X wins: " + xWins + " | O wins: " + oWins + " | Draws: " + draws);

            System.out.print("\nPlay again? (y/n): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("\nThanks for playing! Goodbye.");
        sc.close();
    }

    static void resetBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = '.';
            }
        }
    }

    static void printBoard() {
        System.out.println();
        System.out.println("  1   2   3   4   5   6   7");
        System.out.println("+---+---+---+---+---+---+---+");
        for (int r = 0; r < ROWS; r++) {
            StringBuilder line = new StringBuilder("|");
            for (int c = 0; c < COLS; c++) {
                line.append(" ").append(board[r][c]).append(" |");
            }
            System.out.println(line);
            System.out.println("+---+---+---+---+---+---+---+");
        }
        System.out.println();
    }

    static int dropPiece(int col, char player) {
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == '.') {
                board[r][col] = player;
                return r;
            }
        }
        return -1;
    }

    static boolean checkWin(char p) {
        int[][] directions = {
            {0, 1},
            {1, 0},
            {1, 1},
            {-1, 1}
        };

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                for (int[] d : directions) {
                    int endRow = r + 3 * d[0];
                    int endCol = c + 3 * d[1];

                    if (endRow < 0 || endRow >= ROWS || endCol < 0 || endCol >= COLS) {
                        continue;
                    }

                    int count = 0;
                    for (int i = 0; i < 4; i++) {
                        if (board[r + i * d[0]][c + i * d[1]] == p) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean isFull() {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c] == '.') {
                return false;
            }
        }
        return true;
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.print("That's not a number. Try again: ");
            }
        }
    }
}