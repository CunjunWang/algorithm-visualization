package maze_solver.data;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-22.
 */
public class MazeSolverVisualizer {

    private static int blockSide = 8;

    private static int DELAY = 20;
    private MazeData data;
    private MazeSolverFrame frame;

    public MazeSolverVisualizer(String mazeFile) {
        // initialize maze data
        data = new MazeData(mazeFile);

        int sceneHeight = data.getR() * blockSide;
        int sceneWidth = data.getC() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeSolverFrame("Maze Solver", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData();
    }

    private void setData() {
        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "./src/maze_solver/maze.txt";
        new MazeSolverVisualizer(mazeFile);
    }

}
