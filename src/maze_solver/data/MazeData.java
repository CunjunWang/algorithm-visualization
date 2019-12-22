package maze_solver.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Read Maze data and build a maze
 * <p>
 * Created by CunjunWang on 2019-12-22.
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int R, C;
    private char[][] maze;

    public MazeData(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("Filename cannot be empty");

        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                throw new IllegalArgumentException("File does not exist");

            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis));

            String sizeLine = scanner.nextLine();
            String[] size = sizeLine.trim().split("\\s+");
            R = Integer.parseInt(size[0]);
            C = Integer.parseInt(size[1]);

            maze = new char[R][C];
            for (int i = 0; i < R; i++) {
                // System.out.println("Processing line " + (i + 1));
                String line = scanner.nextLine();

                 if (line.length() != C)
                     throw new IllegalArgumentException("Invalid line of data");

                for (int j = 0; j < C; j++)
                    maze[i][j] = line.charAt(j);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
    }

    public int getR() {
        return R;
    }

    public int getC() {
        return C;
    }

    public char getMaze(int i, int j) {
        if (!inArea(i, j))
            throw new IllegalArgumentException("Out of bound!");
        return maze[i][j];
    }

    public boolean inArea(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public void print() {
        System.out.println("Size: " + R + " rows, " + C + " columns.");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
    }

}
