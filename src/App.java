import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App extends Canvas implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    public static int WIDTH = 120;
    public static int HEIGHT = 160;
    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public App() {
        this.setPreferredSize(new DimensionUIResource(WIDTH * SCALE, HEIGHT * SCALE));
        this.addKeyListener(this);
        player = new Player(40, HEIGHT - 3);
        enemy = new Enemy(40, 0);
        ball = new Ball(40, (HEIGHT / 2) - 1);
    }

    public static void main(String[] args) {
        App app = new App();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(app);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(app).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void renderObjects(Graphics g) {
        player.render(g);
        enemy.render(g);
        ball.render(g);
    }

    public void render() {
        // Criando os Bufferes
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(1);
            return;
        }
        // Criando Layers
        bs.getDrawGraphics().drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        // Exibindo Layers
        bs.show();

        // Definindo g como uma Layer
        Graphics g = layer.getGraphics();

        // Renderizando background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Renderizando Layers
        renderObjects(g);
    }

    public void run() {
        while (true) {
            requestFocus();
            tick();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
