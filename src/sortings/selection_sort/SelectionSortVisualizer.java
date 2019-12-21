package sortings.selection_sort;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * MVC - Controller
 * <p>
 * Created by CunjunWang on 2019-12-15.
 */
public class SelectionSortVisualizer {

    private static int DELAY = 20;
    private SelectionSortData data;
    private SelectionSortFrame frame;

    public SelectionSortVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new SelectionSortData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            frame = new SelectionSortFrame("Selection Sort", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {

        // render before sorting
        setData(0, -1, -1);

        // selection sort
        for (int i = 0; i < data.N(); i++) {
            // find the minimum from [i,n)
            int minIndex = i;

            // render when minIndex changed
            setData(i, -1, minIndex);

            for (int j = i + 1; j < data.N(); j++) {
                // render when current compare changed
                setData(i, j, minIndex);

                if (data.get(j) < data.get(minIndex)) {
                    // render when minIndex changed
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }

            data.swap(i, minIndex);

            // render after data changed
            setData(i + 1, -1, -1);
        }

        // render after sorting
        setData(data.N(), -1, -1);
    }

    private void setData(int sortedIndex, int currentCompareIndex, int currentMinIndex) {
        data.sortedIndex = sortedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        new SelectionSortVisualizer(sceneWidth, sceneHeight, N);

    }

}
