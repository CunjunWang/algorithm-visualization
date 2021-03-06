package maze_solver.dfs.iterative;

import gui_frame.AlgorithmVisualizationHelper;
import maze_solver.data.MazeData;
import maze_solver.data.MazeSolverFrame;
import maze_solver.data.Point;

import java.awt.*;
import java.util.Stack;

/**
 * Created by CunjunWang on 2019-12-22.
 */
public class IterativeDFSMazeSolverVisualizer {

    private static int blockSide = 8;

    private static int DELAY = 1;
    private MazeData data;
    private MazeSolverFrame frame;

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public IterativeDFSMazeSolverVisualizer(String mazeFile) {
        // initialize maze data
        data = new MazeData(mazeFile);

        int sceneHeight = data.getR() * blockSide;
        int sceneWidth = data.getC() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeSolverFrame("Iterative DFS Maze Solver", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData(-1, -1, false);

        Stack<maze_solver.data.Point> stack = new Stack<maze_solver.data.Point>();
        stack.push(new maze_solver.data.Point(data.getEntranceR(), data.getEntranceC()));
        data.visited[data.getEntranceR()][data.getEntranceC()] = true;

        boolean solved = false;

        while (!stack.empty()) {
            maze_solver.data.Point cur = stack.pop();
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
                    stack.push(new maze_solver.data.Point(nextR, nextC, cur));
                    data.visited[nextR][nextC] = true;
                }
            }
        }

        if (!solved)
            System.out.println("This maze has no solution");

        setData(-1, -1, false);
    }

    private void findPath(maze_solver.data.Point des) {
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
        new IterativeDFSMazeSolverVisualizer(mazeFile);
    }

}
