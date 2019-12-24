package mine_sweeper;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            frame.addMouseListener(new MineSweeperMouseListener());
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        setData(false, -1, -1);
    }

    public void setData(boolean isLeftClicked, int x, int y) {
        if (data.inArea(x, y)) {
            if (isLeftClicked)
                data.open[x][y] = true;
            else
                data.flags[x][y] = !data.flags[x][y];
        }

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    private class MineSweeperMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent event) {
            event.translatePoint(
                    -(frame.getBounds().width - frame.getCanvasWidth()),
                    -(frame.getBounds().height - frame.getCanvasHeight())
            );

            Point pos = event.getPoint();

            int w = frame.getCanvasWidth() / data.getM();
            int h = frame.getCanvasHeight() / data.getN();

            int x = pos.y / h;
            int y = pos.x / w;

            setData(SwingUtilities.isLeftMouseButton(event), x, y);
        }
    }

    public static void main(String[] args) {
        int N = 20;
        int M = 20;
        int mineNumber = 20;
        new MineSweeperVisualizer(N, M, mineNumber);
    }

}
