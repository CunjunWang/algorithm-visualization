package random_simulation.fisher_yates;

/**
 * Created by CunjunWang on 2019-12-24.
 */
public class FisherYates {

    private int N;
    private int n, m;

    public FisherYates(int N, int n, int m) {
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
        // way 1:
        // pick a random element in [i...n)
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * (n - i)) + i;
            swap(arr, i, x);
        }

        // way 2:
        // pick a random element in [0...i+1)
//        for (int i = n - 1; i >= 0; i--) {
//            int x = (int) (Math.random() * (i + 1));
//            swap(arr, i, x);
//        }
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
        FisherYates fisherYates = new FisherYates(N, n, m);
        fisherYates.run();
    }
}
