package maze_solver.data;

/**
 * Created by CunjunWang on 2019-12-23.
 */
public class Point {

    public int r, c;

    // prev point on the path
    private Point prev;

    public Point(int r, int c, Point prev) {
        this.r = r;
        this.c = c;
        this.prev = prev;
    }

    public Point(int r, int c) {
        this(r, c, null);
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public Point getPrev() {
        return prev;
    }
}
