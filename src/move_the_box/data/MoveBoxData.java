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

    private int N, M;

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

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
