package sortings.heap_sort;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-21.
 */
public class HeapSortVisualizer {

    private static int DELAY = 100;
    private HeapSortData data;
    private HeapSortFrame frame;

    public HeapSortVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new HeapSortData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            frame = new HeapSortFrame("Heap Sort", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {

        setData(data.N());

        // build max heap
        for (int i = (data.N() - 2) / 2; i >= 0; i--) {
            shiftDown(data.N(), i);
        }

        // heap sort
        for (int i = data.N() - 1; i > 0; i--) {
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i);
        }

        setData(0);
    }

    private void shiftDown(int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && data.get(j + 1) > data.get(j))
                j += 1;

            if (data.get(k) >= data.get(j))
                break;

            data.swap(k, j);
            setData(data.heapIndex);

            k = j;
        }
    }

    private void setData(int heapIndex) {
        data.heapIndex = heapIndex;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        new HeapSortVisualizer(sceneWidth, sceneHeight, N);
    }
}
