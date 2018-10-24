import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_V = 65;
    public static final int MAX_TOP = 10;
    public static final int MAX_BOTTOM = 470;

    private Image image_forward = new ImageIcon("res/player.png").getImage();
    private Image image_up = new ImageIcon("res/player_up.png").getImage();
    private Image image_down = new ImageIcon("res/player_down.png").getImage();
    private Image currentImg = image_forward; //default

    private int x = 10, y = 110;
    private int width = 180, height = 55;
    private int v = 0, dv, s, dy;

    private int layerX1 = 0;
    private int layerX2 = 1200;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getV() {
        return v;
    }

    public int getS() {
        return s;
    }

    public Image getImage() {
        return currentImg;
    }

    public int getLayerX1() {
        return layerX1;
    }

    public int getLayerX2() {
        return layerX2;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {

        if(layerX1 <= -1200)
        {
            layerX1 = layerX2 + 1200;
        }
        else if(layerX2 <= -1200){
            layerX2 = layerX1 + 1200;
        }
        else {
            s += v;

            //axis X
            v += dv;
            if(v <= 0) v = 0;
            if(v >= MAX_V) v = MAX_V;

            //axis Y
            y -= dy;
            if(y <= MAX_TOP) y = MAX_TOP;
            if(y >= MAX_BOTTOM) y = MAX_BOTTOM;

            layerX1 -= v;
            layerX2 -= v;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            dv = 1;
        }
        if(key == KeyEvent.VK_LEFT) {
            dv = -1;
        }
        if(key == KeyEvent.VK_UP) {
            dy = 10;
            currentImg = image_up;
        }
        if(key == KeyEvent.VK_DOWN) {
            dy = -10;
            currentImg = image_down;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            currentImg = image_forward;
        }
    }
}
