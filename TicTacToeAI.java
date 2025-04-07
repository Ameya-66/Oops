import java.util.*;

public class TicTacToeAI {
    static final char HUMAN = 'X';
    static final char AI = 'O';
    static final char EMPTY = ' ';
    static char[][] board = {
        {EMPTY, EMPTY, EMPTY},
        {EMPTY, EMPTY, EMPTY},
        {EMPTY, EMPTY, EMPTY}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🎮 Welcome to Tic Tac Toe!");
        printBoard();

        while (true) {
            // Human move
            System.out.print("Enter row and column (1-3): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (board[row][col] != EMPTY) {
                System.out.println("❌ Cell already taken. Try again.");
                continue;
            }
            board[row][col] = HUMAN;

            if (checkWinner(HUMAN)) {
                printBoard();
                System.out.println("🎉 You win!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("🤝 It's a tie!");
                break;
            }

            // AI move
            System.out.println("🤖 AI is thinking...");
            int[] bestMove = findBestMove();
            board[bestMove[0]][bestMove[1]] = AI;

            if (checkWinner(AI)) {
                printBoard();
                System.out.println("💀 AI wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("🤝 It's a tie!");
                break;
            }

            printBoard();
        }

        scanner.close();
    }

    static void printBoard() {
        System.out.println("\nCurrent board:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---|---|---");
        }
        System.out.println();
    }

    static boolean checkWinner(char player) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
        for (int i = 0; i < 3; i++)
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    static boolean isBoardFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == EMPTY) return false;
        return true;
    }

    static int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] move = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = AI;
                    int score = minimax(false);
                    board[i][j] = EMPTY;

                    if (score > bestScore) {
                        bestScore = score;
                        move = new int[]{i, j};
                    }
                }
            }
        }

        return move;
    }

    static int minimax(boolean isMaximizing) {
        if (checkWinner(AI)) return 1;
        if (checkWinner(HUMAN)) return -1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = isMaximizing ? AI : HUMAN;
                    int score = minimax(!isMaximizing);
                    board[i][j] = EMPTY;

                    if (isMaximizing) {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }

        return bestScore;
    }
}
