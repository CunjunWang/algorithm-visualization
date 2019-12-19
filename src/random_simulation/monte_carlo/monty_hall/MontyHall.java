package random_simulation.monte_carlo.monty_hall;

/**
 * Created by CunjunWang on 2019-12-19.
 * <p>
 * Three Gates Problem: https://en.wikipedia.org/wiki/Monty_Hall_problem
 */
public class MontyHall {

    private int N;

    public MontyHall(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("Invalid Argument");

        this.N = N;
    }

    public void run(boolean changeGate) {
        int wins = 0;
        for (int i = 0; i < N; i++)
            if (play(changeGate))
                wins++;

        System.out.println(changeGate ? "Change" : "Not change");
        System.out.println("win rate: " + (double) wins / N);
    }

    /**
     * Player get prize or not
     *
     * @param changeGate player change gate or not
     * @return result
     */
    public boolean play(boolean changeGate) {
        // Gates: 0 1 2
        int prizeGate = (int) (Math.random() * 3);
        int playerChoice = (int) (Math.random() * 3);

        if (playerChoice == prizeGate)
            return !changeGate;
        else
            return changeGate;
    }

    public static void main(String[] args) {
        int N = 100000;
        MontyHall exp = new MontyHall(N);
        exp.run(true);
        System.out.println();
        exp.run(false);
    }
}
