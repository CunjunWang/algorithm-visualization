package random_simulation.monte_carlo;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-19.
 */
public class FindPIVisualizer {

    private static int DELAY = 40;

    private MonteCarloPIData data;
    private FindPIFrame frame;
    private int N;


    public FindPIVisualizer(int sceneWidth, int sceneHeight, int N) {

        if (sceneHeight != sceneWidth)
            throw new IllegalArgumentException("This demo requires scene width equals to scene height");

        // initialize
        this.N = N;
        Circle circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneHeight / 2);
        data = new MonteCarloPIData(circle);

        EventQueue.invokeLater(() -> {
            frame = new FindPIFrame("Get PI with Monte Carlo", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        for (int i = 0; i < N; i++) {
            if (i % 100 == 0) {
                frame.render(data);
                AlgorithmVisualizationHelper.pause(DELAY);
                System.out.println("Pi: " + data.estimatePI());
            }

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100000;
        new FindPIVisualizer(sceneWidth, sceneHeight, N);
    }

}
