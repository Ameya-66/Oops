public class SudokuSolver {
    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("🧩 Original Sudoku:");
        printBoard(board);

        if (solve(board)) {
            System.out.println("\n✅ Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("❌ No solution found.");
        }
    }

    public static boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) return true;
                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false; // trigger backtracking
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Row
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == num) return false;

        // Column
        for (int i = 0; i < SIZE; i++)
            if (board[i][col] == num) return false;

        // 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++)
            for (int j = startCol; j < startCol + 3; j++)
                if (board[i][j] == num) return false;

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0 && row != 0)
                System.out.println("------+-------+------");

            for (int col = 0; col < SIZE; col++) {
                if (col % 3 == 0 && col != 0)
                    System.out.print("| ");

                System.out.print(board[row][col] == 0 ? ". " : board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
