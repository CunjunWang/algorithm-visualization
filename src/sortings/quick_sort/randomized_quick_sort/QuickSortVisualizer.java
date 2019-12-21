package sortings.quick_sort.randomized_quick_sort;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-21.
 */
public class QuickSortVisualizer {

    private static int DELAY = 20;
    private QuickSortData data;
    private QuickSortFrame frame;

    public QuickSortVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new QuickSortData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            frame = new QuickSortFrame("Quick Sort", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {

        // render before sorting
        setData(-1, -1, -1, -1, -1);

        // quick sort
        quickSort(0, data.N() - 1);

        // render after sorting
        setData(-1, -1, -1, -1, -1);
    }

    private void quickSort(int l, int r) {
        if (l > r)
            return;

        if (l == r) {
            // if only one element, paint the pivot
            setData(l, r, l, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1);

        int p = partition(l, r);
        quickSort(l, p - 1);
        quickSort(p + 1, r);
    }

    private int partition(int l, int r) {

        // random pivot
        int p = (int) (Math.random() * (r - l + 1)) + l;

        // paint after find the pivot
        setData(l, r, -1, p, -1);

        data.swap(l, p);

        int v = data.get(l);

        // paint after find the pivot
        setData(l, r, -1, l, -1);

        // loop invariant:
        // arr[l+1...j] < v; arr[j+1...i] > v
        int j = l;
        for (int i = l + 1; i <= r; i++) {

            // render for current element
            setData(l, r, -1, l, i);

            if (data.get(i) < v) {
                j++;
                data.swap(j, i);

                // render after data changed
                setData(l, r, -1, l, i);
            }
        }

        data.swap(l, j);

        // render after data changed
        setData(l, r, j, -1, -1);

        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElement) {
        data.l = l;
        data.r = r;

        if (fixedPivot != -1)
            data.fixedPivot[fixedPivot] = true;
        data.currentPivot = curPivot;
        data.currentElement = curElement;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        new QuickSortVisualizer(sceneWidth, sceneHeight, N);
    }

}
