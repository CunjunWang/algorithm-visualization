package move_the_box;

import gui_frame.AlgorithmVisualizationHelper;
import move_the_box.data.MoveBoxData;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-25.
 */
public class MoveBoxVisualizer {

    private static final int BLOCK_SIDE = 80;

    private static final int DELAY = 10;

    private MoveBoxData data;

    private MoveBoxFrame frame;

    public MoveBoxVisualizer(String filename) {
        data = new MoveBoxData(filename);
        int sceneWidth = data.getM() * BLOCK_SIDE;
        int sceneHeight = data.getN() * BLOCK_SIDE;

        EventQueue.invokeLater(() -> {
            frame = new MoveBoxFrame("Move Box Solver", sceneWidth, sceneHeight);

            new Thread(this::run).start();
        });
    }

    public void run() {
        setData();

        if (data.solve())
            System.out.println("There is a solution for the board");
        else
            System.out.println("There is no solution for this board");

    }

    private void setData() {
        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String filename = "./src/move_the_box/levels/boston_09.txt";
        new MoveBoxVisualizer(filename);
    }
}
