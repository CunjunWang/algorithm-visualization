package fractal.fractal_tree;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class TreeData {

    private int depth;

    private double splitAngle;

    public TreeData(int depth, double splitAngle) {
        this.depth = depth;
        this.splitAngle = splitAngle;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getSplitAngle() {
        return splitAngle;
    }

    public void setSplitAngle(double splitAngle) {
        this.splitAngle = splitAngle;
    }
}
