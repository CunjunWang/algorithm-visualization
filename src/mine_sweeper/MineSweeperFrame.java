package mine_sweeper;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-24.
 */
public class MineSweeperFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public MineSweeperFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        MineSweeperFrame.AlgorithmCanvas canvas = new MineSweeperFrame.AlgorithmCanvas();
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

    private MineSweeperData data;

    public void render(MineSweeperData data) {
        this.data = data;
        this.repaint();
    }

    /**
     * internal canvas class
     */
    private class AlgorithmCanvas extends JPanel {

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

            for (int i = 0; i < data.getN(); i++)
                for (int j = 0; j < data.getM(); j++) {
                    if (data.open[i][j])
                        if (data.isMine(i, j))
                            AlgorithmVisualizationHelper.putImage(g2d, j * w, i * h, MineSweeperData.MINE_IMG_URL);
                        else
                            AlgorithmVisualizationHelper.putImage(g2d, j * w, i * h, MineSweeperData.numberImgUrl(data.getNumber(i, j)));
                    else {
                        if (data.flags[i][j])
                            AlgorithmVisualizationHelper.putImage(g2d, j * w, i * h, MineSweeperData.FLAG_IMG_URL);
                        else
                            AlgorithmVisualizationHelper.putImage(g2d, j * w, i * h, MineSweeperData.BLOCK_IMG_URL);
                    }
                }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
