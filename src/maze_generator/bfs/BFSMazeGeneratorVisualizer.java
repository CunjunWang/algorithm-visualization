package maze_generator.bfs;

import gui_frame.AlgorithmVisualizationHelper;
import maze_generator.RandomQueue;
import maze_generator.data.MazeData;
import maze_generator.data.MazeGeneratorFrame;
import maze_generator.data.Point;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class BFSMazeGeneratorVisualizer {

    private static int blockSide = 8;
    private static int DELAY = 5;

    private MazeData data;
    private MazeGeneratorFrame frame;

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public BFSMazeGeneratorVisualizer(int N, int M) {
        // initialize maze data
        data = new MazeData(N, M);

        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeGeneratorFrame("Recursive DFS Random Maze Generator", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData(-1, -1);

        RandomQueue<Point> queue = new RandomQueue<>();
        Point first = new Point(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.remove();

            for (int d = 0; d < 4; d++) {
                int nextX = cur.getX() + dir[d][0] * 2;
                int nextY = cur.getY() + dir[d][1] * 2;

                if (data.inArea(nextX, nextY) && !data.visited[nextX][nextY]) {
                    queue.add(new Point(nextX, nextY));
                    data.visited[nextX][nextY] = true;
                    setData(cur.getX() + dir[d][0], cur.getY() + dir[d][1]);
                }
            }
        }

        setData(-1, -1);
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
        new BFSMazeGeneratorVisualizer(N, M);
    }

}
