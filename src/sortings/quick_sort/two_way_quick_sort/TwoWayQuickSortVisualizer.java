package sortings.quick_sort.two_way_quick_sort;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-21.
 */
public class TwoWayQuickSortVisualizer {

    private static int DELAY = 20;
    private TwoWayQuickSortData data;
    private TwoWayQuickSortFrame frame;

    public TwoWayQuickSortVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new TwoWayQuickSortData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            frame = new TwoWayQuickSortFrame("Two Way Quick Sort", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    /**
     * Animation
     */
    private void run() {

        // render before sorting
        setData(-1, -1, -1, -1, -1, -1);

        // two way quick sort
        twoWayQuickSort(0, data.N() - 1);

        // render after sorting
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void twoWayQuickSort(int l, int r) {
        if (l > r)
            return;

        if (l == r) {
            // if only one element, paint the pivot
            setData(l, r, l, -1, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1, -1);

        int p = partition(l, r);
        twoWayQuickSort(l, p - 1);
        twoWayQuickSort(p + 1, r);
    }

    private int partition(int l, int r) {

        // random pivot
        int p = (int) (Math.random() * (r - l + 1)) + l;

        // paint after find the pivot
        setData(l, r, -1, p, -1, -1);

        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l + 1, j = r;
        setData(l, r, -1, l, i, j);

        while (true) {
            while (i <= r && data.get(i) < v) {
                i++;
                // update curL
                setData(l, r, -1, l, i, j);
            }

            while (j >= l + 1 && data.get(j) > v) {
                j--;
                // update curR
                setData(l, r, -1, l, i, j);
            }

            if (i > j)
                break;

            data.swap(i, j);
            i++;
            j--;
            setData(l, r, -1, l, i, j);
        }

        data.swap(l, j);

        // render after data changed
        setData(l, r, j, -1, -1, -1);

        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR) {
        data.l = l;
        data.r = r;

        if (fixedPivot != -1)
            data.fixedPivot[fixedPivot] = true;
        data.currentPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        new TwoWayQuickSortVisualizer(sceneWidth, sceneHeight, N);
    }

}
