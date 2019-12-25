package move_the_box;

import gui_frame.AlgorithmVisualizationHelper;
import move_the_box.data.Board;
import move_the_box.data.MoveBoxData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CunjunWang on 2019-12-25.
 */
public class MoveBoxFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public MoveBoxFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        MoveBoxFrame.AlgorithmCanvas canvas = new MoveBoxFrame.AlgorithmCanvas();
        // window size can be different with canvas size;
        this.setContentPane(canvas);
        pack(); // use this canvas to fill the window.

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private MoveBoxData data;

    public void render(MoveBoxData data) {
        this.data = data;
        this.repaint();
    }

    /**
     * internal canvas class
     */
    private class AlgorithmCanvas extends JPanel {

        private HashMap<Character, Color> colorMap;

        private ArrayList<Color> colorList;

        public AlgorithmCanvas() {
            super(true);
            colorMap = new HashMap<>();
            colorList = new ArrayList<>();
            colorList.add(AlgorithmVisualizationHelper.Red);
            colorList.add(AlgorithmVisualizationHelper.Purple);
            colorList.add(AlgorithmVisualizationHelper.Blue);
            colorList.add(AlgorithmVisualizationHelper.Teal);
            colorList.add(AlgorithmVisualizationHelper.LightGreen);
            colorList.add(AlgorithmVisualizationHelper.Lime);
            colorList.add(AlgorithmVisualizationHelper.Amber);
            colorList.add(AlgorithmVisualizationHelper.DeepPurple);
            colorList.add(AlgorithmVisualizationHelper.Brown);
            colorList.add(AlgorithmVisualizationHelper.BlueGrey);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // anti-aliased
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // paint
            int w = canvasWidth / data.getM();
            int h = canvasHeight / data.getN();

            Board currentBoard = data.getCurrentBoard();
            for (int i = 0; i < currentBoard.getN(); i++)
                for (int j = 0; j < currentBoard.getM(); j++) {
                    char c = currentBoard.getData(i, j);
                    if (c != Board.EMPTY) {
                        if (!colorMap.containsKey(c)) {
                            int size = colorMap.size();
                            colorMap.put(c, colorList.get(size));
                        }
                        Color color = colorMap.get(c);
                        AlgorithmVisualizationHelper.setColor(g2d, color);

                        AlgorithmVisualizationHelper.fillRectangle(g2d, j * h + 2, i * w + 2, w - 4, h - 4);
                    }
                }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
