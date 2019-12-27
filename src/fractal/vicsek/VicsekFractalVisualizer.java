package fractal.vicsek;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class VicsekFractalVisualizer {

    private VicsekFractalData data;
    private VicsekFractalFrame frame;

    private static final int DELAY = 40;

    public VicsekFractalVisualizer(int maxDepth) {
        if (maxDepth > 7)
            throw new IllegalArgumentException("Recursion too deep");
        data = new VicsekFractalData(maxDepth);
        int sceneWidth = (int) Math.pow(3, maxDepth);
        int sceneHeight = (int) Math.pow(3, maxDepth);

        EventQueue.invokeLater(() -> {
            frame = new VicsekFractalFrame("Vicsek Fractal", sceneWidth, sceneHeight);
            frame.addKeyListener(new VicsekKeyListener());
            new Thread(this::run).start();
        });
    }

    private void run() {
        setData(data.getDepth());
    }

    private void setData(int depth) {
        data.setDepth(depth);
        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    private class VicsekKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9') {
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {
        int maxDepth = 6;
        new VicsekFractalVisualizer(maxDepth);
    }

}
