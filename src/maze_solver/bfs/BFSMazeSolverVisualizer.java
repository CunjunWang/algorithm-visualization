package maze_solver.bfs;

import gui_frame.AlgorithmVisualizationHelper;
import maze_solver.data.MazeData;
import maze_solver.data.MazeSolverFrame;
import maze_solver.dfs.iterative.Point;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class BFSMazeSolverVisualizer {

    private static int blockSide = 8;

    private static int DELAY = 1;
    private MazeData data;
    private MazeSolverFrame frame;

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public BFSMazeSolverVisualizer(String mazeFile) {
        // initialize maze data
        data = new MazeData(mazeFile);

        int sceneHeight = data.getR() * blockSide;
        int sceneWidth = data.getC() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeSolverFrame("BFS Maze Solver", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData(-1, -1, false);

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(data.getEntranceR(), data.getEntranceC()));
        data.visited[data.getEntranceR()][data.getEntranceC()] = true;

        boolean solved = false;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            setData(cur.getR(), cur.getC(), true);

            if (cur.getR() == data.getExitR() && cur.getC() == data.getExitC()) {
                solved = true;
                findPath(cur);
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nextR = cur.getR() + dir[d][0];
                int nextC = cur.getC() + dir[d][1];

                if (data.inArea(nextR, nextC) && !data.visited[nextR][nextC]
                        && (data.getMaze(nextR, nextC) == MazeData.ROAD || data.getMaze(nextR, nextC) == MazeData.END)) {
                    queue.add(new Point(nextR, nextC, cur));
                    data.visited[nextR][nextC] = true;
                }
            }
        }

        if (!solved)
            System.out.println("This maze has no solution");

        setData(-1, -1, false);
    }

    private void findPath(Point des) {
        Point cur = des;
        while (cur != null) {
            data.result[cur.getR()][cur.getC()] = true;
            cur = cur.getPrev();
        }
    }

    private void setData(int r, int c, boolean onPath) {
        if (data.inArea(r, c)) {
            data.onPath[r][c] = onPath;
        }

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "./src/maze_solver/data/maze.txt";
        new BFSMazeSolverVisualizer(mazeFile);
    }

}
