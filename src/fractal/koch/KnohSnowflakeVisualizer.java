package fractal.koch;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class KnohSnowflakeVisualizer {

    private KnohSnowflakeData data;
    private KnohSnowflakeFrame frame;

    private static final int DELAY = 40;

    public KnohSnowflakeVisualizer(int maxDepth, int side) {
        if (maxDepth > 9)
            throw new IllegalArgumentException("Recursion too deep");

        data = new KnohSnowflakeData(maxDepth);
        int sceneWidth = side;
        int sceneHeight = side / 3;

        EventQueue.invokeLater(() -> {
            frame = new KnohSnowflakeFrame("Koch Snowflake Fractal", sceneWidth, sceneHeight);
            frame.addKeyListener(new KnohSnowflakeVisualizer.KnohSnowflakeKeyListener());
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

    private class KnohSnowflakeKeyListener extends KeyAdapter {

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
        int side = 900;
        new KnohSnowflakeVisualizer(maxDepth, side);
    }

}
