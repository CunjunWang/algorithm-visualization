package random_simulation.wealth_distribution;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * MVC - Controller
 * <p>
 * Created by CunjunWang on 2019-12-15.
 */
public class AlgorithmVisualizer {

    private static int DELAY = 40;
    private static int ROUND = 50;

    private int[] money;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight) {

        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Money Distribution", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {
        while (true) {

            Arrays.sort(money);

            // paint current data
            frame.render(money);

            // paint interval;
            AlgorithmVisualizationHelper.pause(DELAY);

            for (int k = 0; k < ROUND; k++)
                // update data
                for (int i = 0; i < money.length; i++) {
//                    if (money[i] > 0) {
                    int j = (int) (Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
//                    }
                }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 800;

        new AlgorithmVisualizer(sceneWidth, sceneHeight);
    }

}
