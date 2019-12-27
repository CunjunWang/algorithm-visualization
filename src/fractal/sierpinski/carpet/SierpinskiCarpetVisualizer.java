package fractal.sierpinski.carpet;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class SierpinskiCarpetVisualizer {

    private SierpinskiCarpetData data;
    private SierpinskiCarpetFrame frame;

    private static final int DELAY = 40;

    public SierpinskiCarpetVisualizer(int maxDepth) {
        if (maxDepth > 7)
            throw new IllegalArgumentException("Recursion too deep");
        data = new SierpinskiCarpetData(maxDepth);
        int sceneWidth = (int) Math.pow(3, maxDepth);
        int sceneHeight = (int) Math.pow(3, maxDepth);

        EventQueue.invokeLater(() -> {
            frame = new SierpinskiCarpetFrame("Sierpinski Carpet Fractal", sceneWidth, sceneHeight);
            frame.addKeyListener(new SierpinskiKeyListener());
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

    private class SierpinskiKeyListener extends KeyAdapter {

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
        new SierpinskiCarpetVisualizer(maxDepth);
    }

}
