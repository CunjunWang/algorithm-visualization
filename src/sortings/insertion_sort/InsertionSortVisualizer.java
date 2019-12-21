package sortings.insertion_sort;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-20.
 */
public class InsertionSortVisualizer {

    private static int DELAY = 20;
    private InsertionSortData data;
    private InsertionSortFrame frame;

    public InsertionSortVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new InsertionSortData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            frame = new InsertionSortFrame("Insertion Sort", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {

        // render before sorting
        setData(0, -1);

        // insertion sort
        for (int i = 0; i < data.N(); i++) {
            // render when current index changed
            setData(i, i);

            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);

                // render when sorted index changed
                setData(i + 1, j - 1);
            }
        }

        // render after sorting
        setData(data.N(), -1);
    }

    private void setData(int sortedIndex, int currentIndex) {
        data.sortedIndex = sortedIndex;
        data.currentIndex = currentIndex;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        new InsertionSortVisualizer(sceneWidth, sceneHeight, N);

    }
}
