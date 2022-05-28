import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    public double x, y;
    public int width, height;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 2;
    }

    public void tick() {
        x += (App.ball.x - x - 6) * 0.07;
        if (x + width > App.WIDTH) {
            x = App.WIDTH - width;
        } else if (x < 0) {
            x = 0;
        }
    };

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, width, height);
    }
}