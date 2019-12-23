package maze_generator.data;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;
    private int entranceX, entranceY;
    private int exitX, exitY;

    public char[][] maze;
    public boolean[][] visited;

    public MazeData(int N, int M) {
        if (N % 2 == 0 || M % 2 == 0)
            throw new IllegalArgumentException("Maze should have odd number size");

        this.N = N;
        this.M = M;

        maze = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if (i % 2 == 1 && j % 2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;
            }

        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;

        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
