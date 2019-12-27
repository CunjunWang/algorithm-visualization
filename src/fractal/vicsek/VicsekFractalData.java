package fractal.vicsek;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class VicsekFractalData {

    private int depth;

    public VicsekFractalData(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        if (depth >= 0)
            this.depth = depth;
    }

}
