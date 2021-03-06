import java.awt.Color;
import java.awt.Graphics;

public class Player {
    public boolean right, left;
    public int x, y, speed, width, height;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 2;
        this.speed = 2;
    }

    public void tick() {
        if (right) {
            x += speed;
        } else if (left) {
            x -= speed;
        }

        if (x + width > App.WIDTH) {
            x = App.WIDTH - width;
        } else if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}