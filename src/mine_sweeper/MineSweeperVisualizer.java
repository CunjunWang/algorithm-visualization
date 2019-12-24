package mine_sweeper;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-24.
 */
public class MineSweeperVisualizer {

    private static int BLOCK_SIDE_LENGTH = 32;
    private static int DELAY = 5;

    private MineSweeperData data;
    private MineSweeperFrame frame;

    public MineSweeperVisualizer(int N, int M, int mineNumber) {
        // initialize maze data
        data = new MineSweeperData(N, M, mineNumber);

        int sceneHeight = data.getN() * BLOCK_SIDE_LENGTH;
        int sceneWidth = data.getM() * BLOCK_SIDE_LENGTH;

        EventQueue.invokeLater(() -> {
            frame = new MineSweeperFrame("Mine Sweeper", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData();
    }

    public void setData() {
        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int N = 20;
        int M = 20;
        int mineNumber = 50;
        new MineSweeperVisualizer(N, M, mineNumber);
    }

}
