package mine_sweeper;

/**
 * Created by CunjunWang on 2019-12-24.
 */
public class MineSweeperData {

    private static final String URL_PREFIX = "src/mine_sweeper/resources/";

    private static final String URL_SUFFIX = ".png";

    public static final String BLOCK_IMG_URL = URL_PREFIX + "block" + URL_SUFFIX;

    public static final String FLAG_IMG_URL = URL_PREFIX + "flag" + URL_SUFFIX;

    public static final String MINE_IMG_URL = URL_PREFIX + "mine" + URL_SUFFIX;

    public static String numberImgUrl(int num) {
        if (num < 0 || num > 8)
            throw new IllegalArgumentException("Invalid mine number");

        return URL_PREFIX + num + URL_SUFFIX;
    }

    private int N, M;

    private boolean[][] mines;

    public MineSweeperData(int N, int M, int mineNumber) {
        if (N <= 0 || M <= 0)
            throw new IllegalArgumentException("Invalid size of game");

        if (mineNumber < 0 || mineNumber > N * M)
            throw new IllegalArgumentException("Invalid number of mines");

        this.N = N;
        this.M = M;
        mines = new boolean[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                mines[i][j] = false;
            }

        generateMines(mineNumber);
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public boolean isMine(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Invalid position");

        return mines[x][y];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private void generateMines(int mineNumber) {
        for (int i = 0; i < mineNumber; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }

        for (int i = 0; i < mineNumber; i++) {
            int x1 = i / M;
            int y1 = i % M;

            int x2 = (int) (Math.random() * N);
            int y2 = (int) (Math.random() * M);

            swap(x1, y1, x2, y2);
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }
}
