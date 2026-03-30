import Structures.Collection;
import Structures.Queue;
import Structures.Stack;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FloodFill {
    private final Point startPoint;
    private final BufferedImage image;
    private final ImagePanel panel;

    private static final int NEW_COLOR      = 202020;
    private static final int TOLERANCE      = 30;
    private static final int FRAME_INTERVAL = 10;

    public FloodFill(BufferedImage image, ImagePanel panel, int x, int y) {
        if (image == null) throw new IllegalArgumentException("imagem faiada!");
        this.image      = image;
        this.panel      = panel;
        this.startPoint = new Point(x, y);
    }

    public void stackFill() throws InterruptedException {
        runFill(new Stack<>());
    }

    public void queueFill() throws InterruptedException {
        runFill(new Queue<>());
    }


    private void runFill(Collection<Point> collection) throws InterruptedException {
        int oldColor = image.getRGB(startPoint.x, startPoint.y);
        if (oldColor == NEW_COLOR) return;

        int width      = image.getWidth();
        int height     = image.getHeight();
        int painted    = 0;
        int frameIndex = 0;

        new File("assets/frames").mkdirs();
        collection.add(startPoint);

        while (!collection.isEmpty()) {
            Point p = collection.remove();

            if (p.x < 0 || p.x >= width || p.y < 0 || p.y >= height) continue;
            if (isColorDifferent(image.getRGB(p.x, p.y), oldColor)) continue;

            image.setRGB(p.x, p.y, NEW_COLOR);
//            panel.repaint();
//            Thread.sleep(1);
            painted++;

            // Salva frame a cada FRAME_INTERVAL pixels pintados
            if (painted % FRAME_INTERVAL == 0) {
                saveFrame(frameIndex++);
            }

            collection.add(new Point(p.x + 1, p.y));
            collection.add(new Point(p.x - 1, p.y));
            collection.add(new Point(p.x, p.y + 1));
            collection.add(new Point(p.x, p.y - 1));
        }

        saveFrame(frameIndex);
        saveOutputImage();
        panel.playAnimation();
    }


    private boolean isColorDifferent(int color1, int color2) {
        int r = Math.abs(((color1 >> 16) & 0xFF) - ((color2 >> 16) & 0xFF));
        int g = Math.abs(((color1 >> 8)  & 0xFF) - ((color2 >> 8)  & 0xFF));
        int b = Math.abs((color1 & 0xFF)          - (color2 & 0xFF));
        return (r + g + b) > TOLERANCE;
    }

    private void saveFrame(int index) {
        try {
            ImageIO.write(image, "png", new File(String.format("assets/frames/frame_%04d.png", index)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveOutputImage() {
        try {
            ImageIO.write(image, "png", new File("assets/output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}