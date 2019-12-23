package maze_solver.dfs.recursive;

import gui_frame.AlgorithmVisualizationHelper;
import maze_solver.data.MazeSolverFrame;
import maze_solver.data.MazeData;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-22.
 */
public class RecursiveDFSMazeSolverVisualizer {

    private static int blockSide = 8;

    private static int DELAY = 1;
    private MazeData data;
    private MazeSolverFrame frame;

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public RecursiveDFSMazeSolverVisualizer(String mazeFile) {
        // initialize maze data
        data = new MazeData(mazeFile);

        int sceneHeight = data.getR() * blockSide;
        int sceneWidth = data.getC() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeSolverFrame("Recursive DFS Maze Solver", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData(-1, -1, false);

        if (!go(data.getEntranceR(), data.getEntranceC()))
            System.out.println("This maze has no solution.");

        setData(-1, -1, false);
    }

    /**
     * start search at (r,c)
     * if find a path, return true
     * else return false
     *
     * @param r start row
     * @param c start column
     * @return find a path or not
     */
    private boolean go(int r, int c) {
        if (!data.inArea(r, c))
            throw new IllegalArgumentException("Invalid coordinate");

        data.visited[r][c] = true;
        setData(r, c, true);

        if (r == data.getExitR() && c == data.getExitC())
            return true;

        for (int d = 0; d < 4; d++) {
            int newR = r + dir[d][0];
            int newC = c + dir[d][1];
            if (data.inArea(newR, newC) && !data.visited[newR][newC]
                    && (data.getMaze(newR, newC) == MazeData.ROAD ||
                    data.getMaze(newR, newC) == MazeData.END))
                if (go(newR, newC))
                    return true;
        }

        setData(r, c, false);

        return false;
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
        new RecursiveDFSMazeSolverVisualizer(mazeFile);
    }

}
