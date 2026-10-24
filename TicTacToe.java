import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    static char currentPlayer = 'X';

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            printBoard();

            System.out.println("Player " + currentPlayer + ", enter a position (1-9): ");

            int position = sc.nextInt();

            if (!placeMark(position)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            if (checkWinner()) {
                printBoard();
                System.out.println("🎉 Player " + currentPlayer + " Wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("🤝 Match Draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        sc.close();
    }

    static void printBoard() {
        System.out.println();
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("--+---+--");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("--+---+--");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println();
    }

    static boolean placeMark(int position) {

        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (position < 1 || position > 9)
            return false;

        if (board[row][col] == 'X' || board[row][col] == 'O')
            return false;

        board[row][col] = currentPlayer;
        return true;
    }

    static boolean checkWinner() {

        for (int i = 0; i < 3; i++) {

            if (board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return true;

            if (board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return true;
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return true;

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return true;

        return false;
    }

    static boolean isBoardFull() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] != 'X' && board[i][j] != 'O')
                    return false;
            }
        }

        return true;
    }
}