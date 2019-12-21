package sortings.quick_sort.three_way_quick_sort;

import gui_frame.AlgorithmVisualizationHelper;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-21.
 */
public class ThreeWayQuickSortVisualizer {

    private static int DELAY = 100;
    private ThreeWayQuickSortData data;
    private ThreeWayQuickSortFrame frame;

    public ThreeWayQuickSortVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new ThreeWayQuickSortData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            frame = new ThreeWayQuickSortFrame("Three Way Quick Sort", sceneWidth, sceneHeight);
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
        threeWayQuickSort(0, data.N() - 1);

        // render after sorting
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void threeWayQuickSort(int l, int r) {
        if (l > r)
            return;

        if (l == r) {
            // if only one element, paint the pivot
            setData(l, r, l, -1, -1, -1);
            return;
        }

        // partition
        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1, -1);

        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        // arr[l+1...lt] < v
        int lt = l;
        // arr[gt...r] > v
        int gt = r + 1;
        // arr[lt+1...i] == v
        int i = l + 1;

        setData(l, r, -1, l, lt, gt);

        while (i < gt) {
            if (data.get(i) < v) {
                data.swap(i, lt + 1);
                i ++;
                lt ++;
            } else if (data.get(i) > v) {
                data.swap(i, gt - 1);
                gt--;
            } else {
                i++;
            }

            setData(l, r, -1, l, i, gt);
        }

        data.swap(l, lt);
        setData(l, r, lt, -1, -1, -1);

        threeWayQuickSort(l, lt-1);
        threeWayQuickSort(gt, r);
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR) {
        data.l = l;
        data.r = r;

        if (fixedPivot != -1) {
            data.fixedPivot[fixedPivot] = true;
            int i = fixedPivot;
            while (i < data.N() && data.get(i) == data.get(fixedPivot))
                data.fixedPivot[i++] = true;
        }
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

        new ThreeWayQuickSortVisualizer(sceneWidth, sceneHeight, N);
    }

}
