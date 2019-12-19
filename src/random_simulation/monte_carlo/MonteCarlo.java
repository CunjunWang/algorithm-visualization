package random_simulation.monte_carlo;

import random_simulation.monte_carlo.find_pi.Circle;
import random_simulation.monte_carlo.find_pi.MonteCarloPIData;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-19.
 * <p>
 * Find pi with Monte Carlo without visualization
 */
public class MonteCarlo {

    private int squareSide;
    private int N;

    public MonteCarlo(int squareSide, int N) {
        if (squareSide <= 0 || N <= 0)
            throw new IllegalArgumentException("Invalid argument");

        this.squareSide = squareSide;
        this.N = N;
    }

    public void run() {
        Circle circle = new Circle(squareSide / 2, squareSide / 2, squareSide / 2);
        MonteCarloPIData data = new MonteCarloPIData(circle);

        for (int i = 0; i < N; i++) {
            if (i % 10000 == 0)
                System.out.println("PI: " + data.estimatePI());

            int x = (int) (Math.random() * squareSide);
            int y = (int) (Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
        int squareSide = 800;
        int N = 10000000;
        MonteCarlo monteCarlo = new MonteCarlo(squareSide, N);
        monteCarlo.run();
    }
}
