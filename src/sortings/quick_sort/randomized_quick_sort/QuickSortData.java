package sortings.quick_sort.randomized_quick_sort;

/**
 * Created by CunjunWang on 2019-12-21.
 */
public class QuickSortData {

    private int[] numbers;

    public int l, r;

    public int currentPivot;

    public int currentElement;

    public boolean[] fixedPivot;

    public QuickSortData(int N, int randomBound) {
        numbers = new int[N];
        fixedPivot = new boolean[N];


        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
            fixedPivot[i] = false;
        }
    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid Index");

        return numbers[index];
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid Index");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

}
