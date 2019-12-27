package fractal.circle;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class CircleVisualizer {

    private CircleData data;
    private CircleFrame frame;

    private static final int DELAY = 40;

    public CircleVisualizer(int sceneWidth, int sceneHeight) {
        int R = Math.min(sceneWidth, sceneHeight) / 2 - 2;
        data = new CircleData(sceneWidth / 2, sceneHeight / 2, R, R / 2, 2);

        EventQueue.invokeLater(() -> {
            frame = new CircleFrame("Circle Fractal", sceneWidth, sceneHeight);

            new Thread(this::run).start();
        });
    }

    private void run() {
        setData();
    }

    private void setData() {
        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        new CircleVisualizer(sceneWidth, sceneHeight);
    }
}
