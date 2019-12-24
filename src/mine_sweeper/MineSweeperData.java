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

    private int N, M;

    private boolean[][] mines;

    private int[][] numbers;

    public boolean[][] open;

    public boolean[][] flags;

    public MineSweeperData(int N, int M, int mineNumber) {
        if (N <= 0 || M <= 0)
            throw new IllegalArgumentException("Invalid size of game");

        if (mineNumber < 0 || mineNumber > N * M)
            throw new IllegalArgumentException("Invalid number of mines");

        this.N = N;
        this.M = M;
        mines = new boolean[N][M];
        open = new boolean[N][M];
        flags = new boolean[N][M];
        numbers = new int[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                mines[i][j] = false;
                open[i][j] = false;
                flags[i][j] = false;
                numbers[i][j] = 0;
            }

        generateMines(mineNumber);
        calculateNumbers();
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getNumber(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Invalid position");

        return numbers[x][y];
    }

    public static String numberImgUrl(int num) {
        if (num < 0 || num > 8)
            throw new IllegalArgumentException("Invalid mine number");

        return URL_PREFIX + num + URL_SUFFIX;
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

        for (int i = N * M - 1; i >= 0; i--) {
            int iX = i / M;
            int iY = i % M;

            int randNumber = (int) (Math.random() * (i + 1));
            int randX = randNumber / M;
            int randY = randNumber % M;

            swap(iX, iY, randX, randY);
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    private void calculateNumbers() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if (mines[i][j])
                    numbers[i][j] = -1;

                numbers[i][j] = 0;
                for (int ii = i - 1; ii <= i + 1; ii++)
                    for (int jj = j - 1; jj <= j + 1; jj++) {
                        if (inArea(ii, jj) && mines[ii][jj])
                            numbers[i][j]++;
                    }
            }
    }
}
