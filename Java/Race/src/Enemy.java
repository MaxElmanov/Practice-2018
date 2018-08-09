import javax.swing.*;
import java.awt.*;

public class Enemy {

    private int x, y, v, width = 150, height = 55;
    private Image image = new ImageIcon("res/enemy.png").getImage();
    private Road road;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Image getImage() {
        return image;
    }

    public Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    public void move() {
        x = x - road.getPlayer().getV() + this.v;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
