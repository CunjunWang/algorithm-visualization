package random_simulation.monte_carlo.find_pi;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-19.
 */
public class Circle {

    private int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean contains(Point p) {
        return Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2) <= r * r;
    }
}
