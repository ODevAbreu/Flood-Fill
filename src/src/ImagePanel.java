import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    // Exibe a animação lendo um frame por vez do disco — evita OutOfMemoryError
    public void playAnimation() {
        int total = contarFrames();
        if (total == 0) return;

        JFrame animFrame = new JFrame("FloodFill - Animação");
        animFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        animFrame.setSize(image.getWidth(), image.getHeight());
        animFrame.add(this);
        animFrame.setVisible(true);

        final int[] idx = {0};
        new javax.swing.Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idx[0] >= total) {
                    ((javax.swing.Timer) e.getSource()).stop();
                    return;
                }
                // Lê apenas o frame atual do disco e descarta o anterior
                File f = new File(String.format("assets/frames/frame_%04d.png", idx[0]++));
                try {
                    setImage(ImageIO.read(f));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    // Conta quantos frames existem sem carregá-los na memória
    private int contarFrames() {
        int count = 0;
        while (new File(String.format("assets/frames/frame_%04d.png", count)).exists()) {
            count++;
        }
        return count;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) return;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
