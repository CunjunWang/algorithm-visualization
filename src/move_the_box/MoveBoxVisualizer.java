package move_the_box;

import gui_frame.AlgorithmVisualizationHelper;
import move_the_box.data.MoveBoxData;
import move_the_box.data.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            frame.addMouseListener(new MoveBoxMouseListener());
            new Thread(this::run).start();
        });
    }

    public void run() {
        setData(-1, -1);

        if (data.solve())
            System.out.println("There is a solution for the board");
        else
            System.out.println("There is no solution for this board");

        setData(-1, -1);
    }

    private void setData(int x, int y) {
        data.clickX = x;
        data.clickY = y;

        frame.render(data);
        AlgorithmVisualizationHelper.pause(DELAY);
    }

    private Position clickPos1 = null;
    private Position clickPos2 = null;

    private class MoveBoxMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent event) {
            event.translatePoint(
                    -(int) (frame.getBounds().width - frame.getCanvasWidth()),
                    -(int) (frame.getBounds().height - frame.getCanvasHeight())
            );

            Point clickPosition = event.getPoint();

            int w = frame.getCanvasWidth() / data.getM();
            int h = frame.getCanvasHeight() / data.getN();

            int x = clickPosition.y / h;
            int y = clickPosition.x / w;

            if (SwingUtilities.isLeftMouseButton(event)) {
                if (data.inArea(x, y)) {
                    setData(x, y);
                    if (clickPos1 == null) {
                        clickPos1 = new Position(x, y);
                    } else {
                        clickPos2 = new Position(x, y);
                        if (clickPos2.nextTo(clickPos1)) {
                            data.getCurrentBoard().swap(clickPos1.getX(), clickPos1.getY(),
                                    clickPos2.getX(), clickPos2.getY());
                            data.getCurrentBoard().run();
                        }
                        clickPos1 = null;
                        clickPos2 = null;
                        setData(-1, -1);
                    }
                } else {
                    setData(-1, -1);
                    clickPos1 = null;
                    clickPos2 = null;
                }
            }
        }

    }

    public static void main(String[] args) {
        String filename = "./src/move_the_box/levels/boston_16.txt";
        new MoveBoxVisualizer(filename);
    }
}
