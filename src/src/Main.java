import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BufferedImage image = carregarImagem("assets/teste2.png");

        System.out.println("Largura: " + image.getWidth() + " | Altura: " + image.getHeight());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a coordenada X: ");
        int x = scanner.nextInt();

        System.out.print("Digite a coordenada Y: ");
        int y = scanner.nextInt();

        System.out.print("Escolha o modo (1 = Pilha / 2 = Fila): ");
        int modo = scanner.nextInt();

        JFrame frame = criarJanela(image);
        ImagePanel panel = (ImagePanel) frame.getContentPane().getComponent(0);

        FloodFill floodFill = new FloodFill(image, panel, x, y);

        if (modo == 1) {
            System.out.println("Iniciando Stack Fill (DFS)...");
            floodFill.stackFill();
        } else {
            System.out.println("Iniciando Queue Fill (BFS)...");
            floodFill.queueFill();
        }
    }

    private static BufferedImage carregarImagem(String caminho) {
        try {
            BufferedImage img = ImageIO.read(new File(caminho));
            if (img == null) throw new IOException("Imagem não encontrada: " + caminho);
            System.out.println("Imagem carregada: " + caminho +
                    " [" + img.getWidth() + "x" + img.getHeight() + "]");
            return img;
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
            System.err.println("Certifique-se de que existe um arquivo PNG em: " + caminho);
            System.exit(1);
            return null;
        }
    }

    private static JFrame criarJanela(BufferedImage imagem) {
        ImagePanel panel = new ImagePanel(imagem);
        JFrame frame = new JFrame("Flood Fill");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(imagem.getWidth(), imagem.getHeight());
        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }
}
