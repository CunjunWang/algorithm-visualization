package gui_frame;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-15.
 */
public class Main {

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        int N = 10;
        Circle[] circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 11) + R;
            int vy = (int) (Math.random() * 11) + R;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            AlgorithmFrame frame = new AlgorithmFrame("Welcome", sceneWidth, sceneHeight);

            new Thread(() -> {
                while (true) {
                    // paint current data
                    frame.render(circles);

                    // paint interval;
                    AlgorithmVisualizationHelper.pause(100);

                    // update data
                    for (Circle circle : circles)
                        circle.move(0, 0, sceneWidth, sceneHeight);
                }
            }).start();
        });
    }
}
