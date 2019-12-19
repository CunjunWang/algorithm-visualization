package random_simulation.monte_carlo;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by CunjunWang on 2019-12-19.
 */
public class MonteCarloPIData {
    private Circle circle;
    private LinkedList<Point> points;
    private int insideCircle;

    public MonteCarloPIData(Circle circle) {
        this.circle = circle;
        points = new LinkedList<>();
        insideCircle = 0;
    }

    public Circle getCircle() {
        return circle;
    }

    public Point getPoint(int i) {
        if (i < 0 || i > points.size())
            throw new IllegalArgumentException("Out of bound!");
        return points.get(i);
    }

    public int getNumberOfPoints() {
        return points.size();
    }

    public void addPoint(Point p) {
        points.add(p);
        if (circle.contains(p))
            insideCircle++;
    }

    public double estimatePI() {
        if (points.isEmpty())
            return 0.0;

        int circleArea = insideCircle;
        int squareArea = points.size();
        return 4 * (double) circleArea / squareArea;
    }
}
