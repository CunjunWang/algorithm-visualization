package maze_generator;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class MazeGeneratorVisualizer {

    private static int blockSide = 8;
    private static int DELAY = 10;

    private MazeData data;
    private MazeGeneratorFrame frame;

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public MazeGeneratorVisualizer(int N, int M) {
        // initialize maze data
        data = new MazeData(N, M);

        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new MazeGeneratorFrame("Random Maze Generator", sceneWidth, sceneHeight);
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
        int N = 101;
        int M = 101;
        new MazeGeneratorVisualizer(N, M);
    }

}
