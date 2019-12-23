package maze_game;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class MazeGameVisualizer {

    private static int blockSide = 8;
    private static int DELAY = 5;

    private MazeData data;
    private MazeGameFrame frame;

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public MazeGameVisualizer(int N, int M) {
        // initialize maze data
        data = new MazeData(N, M);

        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeGameFrame("Maze Game", sceneWidth, sceneHeight);
            frame.addKeyListener(new MazeGameKeyListener());
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setRoadData(-1, -1);

        RandomQueue<Point> queue = new RandomQueue<>();
        Point first = new Point(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        data.outMist(first.getX(), first.getY());

        while (!queue.isEmpty()) {
            Point cur = queue.remove();

            for (int d = 0; d < 4; d++) {
                int nextX = cur.getX() + dir[d][0] * 2;
                int nextY = cur.getY() + dir[d][1] * 2;

                if (data.inArea(nextX, nextY) && !data.visited[nextX][nextY]) {
                    queue.add(new Point(nextX, nextY));
                    data.visited[nextX][nextY] = true;
                    data.outMist(nextX, nextY);
                    setRoadData(cur.getX() + dir[d][0], cur.getY() + dir[d][1]);
                }
            }
        }

        setRoadData(-1, -1);
    }

    private void setRoadData(int x, int y) {
        if (data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    private void setPathData(int x, int y, boolean onPath) {
        if (data.inArea(x, y))
            data.onPath[x][y] = onPath;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    private boolean go(int x, int y) {
        if (!data.inArea(x, y))
            throw new IllegalArgumentException("Start out of bound");

        data.visited[x][y] = true;
        setPathData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int d = 0; d < 4; d++) {
            int nextX = x + dir[d][0];
            int nextY = y + dir[d][1];
            if (data.inArea(nextX, nextY) && !data.visited[nextX][nextY]
                    && data.maze[nextX][nextY] == MazeData.ROAD)
                if (go(nextX, nextY))
                    return true;
        }

        setPathData(x, y, false);

        return false;
    }

    private class MazeGameKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                for (int i = 0; i < data.getN(); i++)
                    for (int j = 0; j < data.getM(); j++)
                        data.visited[i][j] = false;

                new Thread(() -> {
                    go(data.getEntranceX(), data.getEntranceY());
                }).start();
            }
        }
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;
        new MazeGameVisualizer(N, M);
    }
}
