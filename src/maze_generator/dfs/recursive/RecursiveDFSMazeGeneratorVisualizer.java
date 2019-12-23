package maze_generator.dfs.recursive;

import gui_frame.AlgorithmVisualizationHelper;
import maze_generator.data.MazeData;
import maze_generator.data.MazeGeneratorFrame;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class RecursiveDFSMazeGeneratorVisualizer {

    private static int blockSide = 8;
    private static int DELAY = 10;

    private MazeData data;
    private MazeGeneratorFrame frame;

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public RecursiveDFSMazeGeneratorVisualizer(int N, int M) {
        // initialize maze data
        data = new MazeData(N, M);

        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeGeneratorFrame("Recursive DFS Maze Generator", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData(-1, -1);

        go(data.getEntranceX(), data.getEntranceY() + 1);

        setData(-1, -1);
    }

    private void go(int x, int y) {
        if (!data.inArea(x, y))
            throw new IllegalArgumentException("Point is out of bound.");

        data.visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nextX = x + dir[d][0] * 2;
            int nextY = y + dir[d][1] * 2;
            if (data.inArea(nextX, nextY) && !data.visited[nextX][nextY]) {
                setData(x + dir[d][0], y + dir[d][1]);
                go(nextX, nextY);
            }
        }
    }

    private void setData(int x, int y) {
        if (data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;
        new RecursiveDFSMazeGeneratorVisualizer(N, M);
    }

}
