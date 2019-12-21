package sortings.heap_sort;

/**
 * Created by CunjunWang on 2019-12-21.
 */
public class HeapSortData {

    public int[] numbers;

    // arr[heapIndex...N) is sorted
    public int heapIndex;

    public HeapSortData(int N, int randomBound) {
        numbers = new int[N];
        heapIndex = N;

        for (int i = 0; i < N; i++)
            numbers[i] = (int) (Math.random() * randomBound) + 1;
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
