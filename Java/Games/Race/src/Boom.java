import javax.swing.*;
import java.awt.*;

public class Boom {
    private int x, y;
    private int width = 128, height = 128;
    private Image imageBoom = new ImageIcon("res/boom.png").getImage();

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Image getImg() {
        return imageBoom;
    }
}
