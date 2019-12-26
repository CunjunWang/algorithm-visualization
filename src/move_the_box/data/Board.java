package move_the_box.data;

/**
 * Created by CunjunWang on 2019-12-25.
 */
public class Board {

    private int N, M;

    private char[][] data;

    public static char EMPTY = '.';

    private int dir[][] = {{0, 1}, {1, 0}};

    public Board(String[] lines) {
        if (lines == null)
            throw new IllegalArgumentException("Lines cannot be null");

        N = lines.length;
        if (N == 0)
            throw new IllegalArgumentException("Lines cannot be empty");

        M = lines[0].length();

        data = new char[N][M];

        for (int i = 0; i < N; i++) {
            if (lines[i].length() != M)
                throw new IllegalArgumentException("Invalid input data");

            for (int j = 0; j < M; j++)
                data[i][j] = lines[i].charAt(j);
        }
    }

    public Board(Board board) {
        if (board == null)
            throw new IllegalArgumentException("Invalid input data");

        this.N = board.getN();
        this.M = board.getM();
        this.data = new char[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                this.data[i][j] = board.data[i][j];
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public char getData(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Invalid input position");

        return data[x][y];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print() {
        for (int i = 0; i < N; i++)
            System.out.println(String.valueOf(data[i]));
    }

    public boolean win() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (data[i][j] != EMPTY)
                    return false;
        return true;
    }

    public void swap(int x1, int y1, int x2, int y2) {
        if (!inArea(x1, y1) || !inArea(x2, y2))
            throw new IllegalArgumentException("Invalid input position");

        char t = data[x1][y1];
        data[x1][y1] = data[x2][y2];
        data[x2][y2] = t;
    }

    public void run() {
        do {
            // drop - simulate the gravity
            drop();

            // match - eliminate 3 or more continues boxes with same color
        } while (match());
    }

    private void drop() {
        for (int j = 0; j < M; j++) {
            int cur = N - 1;
            for (int i = N - 1; i >= 0; i--)
                if (data[i][j] != EMPTY) {
                    swap(i, j, cur, j);
                    cur--;
                }
        }
    }

    private boolean match() {
        boolean matched = false;
        boolean[][] tag = new boolean[N][M];

        for (int x = 0; x < N; x++)
            for (int y = 0; y < M; y++)
                if (data[x][y] != EMPTY) {
                    for (int i = 0; i < dir.length; i++) {
                        int nextX1 = x + dir[i][0];
                        int nextY1 = y + dir[i][1];

                        int nextX2 = nextX1 + dir[i][0];
                        int nextY2 = nextY1 + dir[i][1];

                        if (inArea(nextX1, nextY1) && inArea(nextX2, nextY2) &&
                                data[nextX1][nextY1] == data[x][y] &&
                                data[nextX2][nextY2] == data[x][y]) {
                            tag[x][y] = true;
                            tag[nextX1][nextY1] = true;
                            tag[nextX2][nextY2] = true;
                            matched = true;
                        }
                    }
                }

        for (int x = 0; x < N; x++)
            for (int y = 0; y < M; y++)
                if (tag[x][y])
                    data[x][y] = EMPTY;

        return matched;
    }

}
