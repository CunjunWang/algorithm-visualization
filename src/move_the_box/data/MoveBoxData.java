package move_the_box.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by CunjunWang on 2019-12-25.
 */
public class MoveBoxData {

    private int maxTurn;

    private Board initialBoard;

    private Board currentBoard;

    private int N, M;

    private static int dir[][] = {{1, 0}, {0, 1}, {0, -1}};

    public MoveBoxData(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("Filename cannot be null");

        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                throw new IllegalArgumentException("File does not exist");

            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis));

            String turnLine = scanner.nextLine();
            maxTurn = Integer.parseInt(turnLine);

            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            initialBoard = new Board(lines.toArray(new String[lines.size()]));

            this.N = initialBoard.getN();
            this.M = initialBoard.getM();

            initialBoard.print();

            currentBoard = new Board(initialBoard);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public boolean solve() {
        if (maxTurn < 0)
            return false;

        return solve(initialBoard, maxTurn);
    }

    private boolean solve(Board board, int turn) {
        if (board == null || turn < 0)
            throw new IllegalArgumentException("Invalid input");

        if (turn == 0)
            return board.win();

        if (board.win())
            return true;

        // search all available moves
        for (int x = 0; x < N; x++)
            for (int y = 0; y < M; y++)
                if (board.getData(x, y) != Board.EMPTY) {
                    for (int d = 0; d < dir.length; d++) {
                        int nextX = x + dir[d][0];
                        int nextY = y + dir[d][1];
                        if (inArea(nextX, nextY)) {
                            Board nextBoard = new Board(board);
                            nextBoard.swap(x, y, nextX, nextY);
                            nextBoard.run();
                            if (solve(nextBoard, turn - 1))
                                return true;
                        }
                    }
                }

        return false;
    }

}
