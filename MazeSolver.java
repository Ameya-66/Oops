public class MazeSolver {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char VISITED = '·';

    private static final int[][] DIRECTIONS = {
        {-1, 0}, // up
        {1, 0},  // down
        {0, -1}, // left
        {0, 1}   // right
    };

    public static void main(String[] args) {
        char[][] maze = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', 'S', ' ', ' ', '#', 'E', '#'},
            {'#', '#', '#', ' ', '#', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'},
        };

        int[] start = findStart(maze);

        if (solveMaze(maze, start[0], start[1])) {
            System.out.println("Maze Solved:\n");
        } else {
            System.out.println("No solution found.\n");
        }

        printMaze(maze);
    }

    private static boolean solveMaze(char[][] maze, int row, int col) {
        if (!isValid(maze, row, col)) return false;
        if (maze[row][col] == END) return true;

        if (maze[row][col] != START) {
            maze[row][col] = VISITED;
        }

        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (solveMaze(maze, nextRow, nextCol)) return true;
        }

        if (maze[row][col] != START) {
            maze[row][col] = PATH;
        }

        return false;
    }

    private static boolean isValid(char[][] maze, int row, int col) {
        return row >= 0 && row < maze.length &&
               col >= 0 && col < maze[0].length &&
               (maze[row][col] == PATH || maze[row][col] == END || maze[row][col] == START);
    }

    private static int[] findStart(char[][] maze) {
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++)
                if (maze[i][j] == START)
                    return new int[]{i, j};
        return new int[]{-1, -1}; // Not found
    }

    private static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
