package random_simulation.monte_carlo;

/**
 * Created by CunjunWang on 2019-12-19.
 */
public class BinomialSimulation {

    private double prob;
    private int time;
    private int N;

    public BinomialSimulation(double prob, int time, int N) {
        if (prob < 0 || prob > 1)
            throw new IllegalArgumentException("Invalid probability");

        if (time <= 0 || N <= 0)
            throw new IllegalArgumentException("Invalid experiment times");

        this.prob = prob;
        this.time = time;
        this.N = N;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i++)
            if (play())
                wins++;

        System.out.println("win rate: " + (double) wins / N);
    }

    private boolean play() {
        for (int i = 0; i < time; i++)
            if (Math.random() < prob)
                return true;
        return false;
    }

    public static void main(String[] args) {
        double prob = 0.2;
        int times = 5;
        int N = 1000000;

        BinomialSimulation binomialSimulation = new BinomialSimulation(prob, times, N);
        binomialSimulation.run();
    }
}
