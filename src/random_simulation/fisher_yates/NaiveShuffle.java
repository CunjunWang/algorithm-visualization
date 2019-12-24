package random_simulation.fisher_yates;

/**
 * Created by CunjunWang on 2019-12-24.
 */
public class NaiveShuffle {

    private int N;
    private int n, m;

    public NaiveShuffle(int N, int n, int m) {
        if (N <= 0 || n < m)
            throw new IllegalArgumentException("Invalid arguments");

        this.N = N;
        this.n = n;
        this.m = m;
    }

    public void run() {
        int[] arr = new int[n];
        int[] freq = new int[n];

        for (int i = 0; i < N; i++) {
            reset(arr);
            shuffle(arr);
            for (int j = 0; j < n; j++)
                freq[j] += arr[j];
        }

        for (int i = 0; i < n; i++)
            System.out.println(i + " : " + (double) freq[i] / N);
    }

    private void shuffle(int[] arr) {
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * n);
            swap(arr, i, x);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private void reset(int[] arr) {
        for (int i = 0; i < m; i++)
            arr[i] = 1;

        for (int i = m; i < n; i++)
            arr[i] = 0;
    }

    public static void main(String[] args) {
        int N = 10000000;
        int n = 10;
        int m = 5;
        NaiveShuffle exp = new NaiveShuffle(N, n, m);
        exp.run();
    }
}
