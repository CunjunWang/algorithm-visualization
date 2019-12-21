package sortings.insertion_sort;

/**
 * Created by CunjunWang on 2019-12-20.
 */
public class InsertionSortData {

    private int[] numbers;

    // [0...sortedIndex) is sorted
    public int sortedIndex = -1;

    public int currentIndex = -1;

    public InsertionSortData(int N, int randomBound) {
        numbers = new int[N];

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
