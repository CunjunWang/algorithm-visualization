package gui_frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MVC - Controller
 * <p>
 * Created by CunjunWang on 2019-12-15.
 */
public class AlgorithmVisualizer {

    private Circle[] circles;
    private AlgorithmFrame frame;
    private boolean isRunning = true;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int N) {

        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 11);
            int vy = (int) (Math.random() * 11);
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgorithmKeyListener());
            frame.addMouseListener(new AlgorithmMouseListener());
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        while (true) {
            // paint current data
            frame.render(circles);

            // paint interval;
            AlgorithmVisualizationHelper.pause(100);

            // update data
            if (isRunning)
                for (Circle circle : circles)
                    circle.move(0, 0,
                            frame.getCanvasWidth(), frame.getCanvasHeight());
        }
    }

    private class AlgorithmKeyListener extends KeyAdapter {

        /**
         * Invoked when a key has been released.
         */
        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ')
                isRunning = !isRunning;
        }

    }

    private class AlgorithmMouseListener extends MouseAdapter {
        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(MouseEvent event) {
            event.translatePoint(0,
                    -(frame.getBounds().height - frame.getCanvasHeight()));
            for (Circle circle : circles)
                if (circle.contain(event.getPoint()))
                    circle.isFilled = !circle.isFilled;
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;

        AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight, N);

    }

}
