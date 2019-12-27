package fractal.fractal_tree;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class TreeVisualizer {

    private TreeData data;
    private TreeFrame frame;

    private static final int DELAY = 40;

    public TreeVisualizer(int sceneWidth, int sceneHeight, int maxDepth, double splitAngle) {
        if (maxDepth > 9)
            throw new IllegalArgumentException("Recursion too deep");

        data = new TreeData(maxDepth, splitAngle);

        EventQueue.invokeLater(() -> {
            frame = new TreeFrame("Tree Fractal", sceneWidth, sceneHeight);
            frame.addKeyListener(new TreeVisualizer.TreeKeyListener());
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

    private class TreeKeyListener extends KeyAdapter {

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
        double splitAngle = 60.0;
        int sceneWidth = 800;
        int sceneHeight = 800;
        new TreeVisualizer(sceneWidth, sceneHeight, maxDepth, splitAngle);
    }

}
