package move_the_box.data;

/**
 * Created by CunjunWang on 2019-12-25.
 */
public class Board {

    private int N, M;

    private char[][] data;

    public static char EMPTY = '.';

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
}
