package sortings.selection_sort;

/**
 * Created by CunjunWang on 2019-12-20.
 */
public class SelectionSortData {

    private int[] numbers;

    // [0...sortedIndex) is sorted
    public int sortedIndex = -1;

    // index of minimum in current iteration
    public int currentMinIndex = -1;

    // index of current comparing index
    public int currentCompareIndex = -1;

    public SelectionSortData(int N, int randomBound) {
        numbers = new int[N];

        for (int i = 0; i < N; i++)
            numbers[i] = (int) (Math.random() * randomBound) + 1;
    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index");
        return numbers[index];
    }

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
