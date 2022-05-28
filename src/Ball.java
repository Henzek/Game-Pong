import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Ball {
    public double x, y;
    public int width, height;

    public double dx, dy;
    public double speed = 2;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 5;

        int angle = new Random().nextInt(75) + 46;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
    }

    public void tick() {
        if (x + (dx * speed) + width >= App.WIDTH) {
            dx *= -1;
        } else if (x + (dx * speed) < 0) {
            dx *= -1;
        }

        if (y >= App.HEIGHT) {
            System.out.println("Ponto do inimigo");
            new App();
            return;
        } else if (y < 0) {
            System.out.println("Ponto do jogador");
            new App();
            return;
        }

        Rectangle bounds = new Rectangle((int) (x + (dx * speed)), (int) (y + (dy * speed)), width, height);

        Rectangle boundsPlayer = new Rectangle(App.player.x, App.player.y, App.player.width, App.player.height);
        Rectangle boundsEnemy = new Rectangle((int) App.enemy.x, (int) App.enemy.y, App.enemy.width, App.enemy.height);

        if (bounds.intersects(boundsPlayer)) {
            int angle = new Random().nextInt(75) + 46;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy > 0)
                dy *= -1;
        } else if (bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt(75) + 46;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy < 0)
                dy *= -1;
        }

        x += dx * speed;
        y += dy * speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) x, (int) y, width, height);
    }
}