package sortings.merge_sort;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by CunjunWang on 2019-12-20.
 */
public class MergeSortVisualizer {

    private static int DELAY = 100;
    private MergeSortData data;
    private MergeSortFrame frame;

    public MergeSortVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new MergeSortData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            frame = new MergeSortFrame("Merge Sort", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {

        setData(-1, -1, -1);

        // merge sort
        mergeSort(0, data.N() - 1);

        setData(0, data.N() - 1, data.N() - 1);
    }

    private void mergeSort(int l, int r) {
        if (l >= r)
            return;

        setData(l, r, -1);

        int mid = l + (r - l) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);

        int i = l, j = mid + 1;

        for (int k = l; k <= r; k++) {
            if (i > mid) {
                data.numbers[k] = aux[j - l];
                j++;
            } else if (j > r) {
                data.numbers[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                data.numbers[k] = aux[i - l];
                i++;
            } else {
                data.numbers[k] = aux[j - l];
                j++;
            }

            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex) {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        new MergeSortVisualizer(sceneWidth, sceneHeight, N);
    }
}
