package fractal.vicsek;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class VicsekFractalVisualizer {

    private VicsekFractalData data;
    private VicsekFractalFrame frame;

    private static final int DELAY = 40;

    public VicsekFractalVisualizer(int depth) {
        data = new VicsekFractalData(depth);
        int sceneWidth = (int) Math.pow(3, depth);
        int sceneHeight = (int) Math.pow(3, depth);

        EventQueue.invokeLater(() -> {
            frame = new VicsekFractalFrame("Vicsek Fractal", sceneWidth, sceneHeight);

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
        int depth = 6;
        new VicsekFractalVisualizer(depth);
    }

}
