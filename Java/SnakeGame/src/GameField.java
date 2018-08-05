import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener
{
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image head;
    private Image apple;
    private int appleX, appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean isLeft = false;
    private boolean isRight = true;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean inGame = true;

    public GameField() {
        setBackground(Color.BLACK);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void loadImages()
    {
        ImageIcon iiA = new ImageIcon("imgs/apple.png");
        apple = iiA.getImage();
        ImageIcon iiD = new ImageIcon("imgs/dot.png");
        dot = iiD.getImage();
        ImageIcon iiH = new ImageIcon("imgs/head.png");
        head = iiH.getImage();
    }

    public void initGame()
    {
        dots = 3; //start tail length

        for (int i = 0; i < dots; i++)
        {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }

        timer = new Timer(250, this);
        timer.start();
        createApple();
    }

    private void createApple()
    {
        appleX = new Random().nextInt(19) * DOT_SIZE;
        appleY = new Random().nextInt(19) * DOT_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(inGame)
        {
            g.drawImage(apple, appleX, appleY, this);

            g.drawImage(head, x[0], y[0], this);
            for (int i = 1; i < dots; i++)
            {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            String gameOverText = "Game Over";
            String tailLengthText = "Tail length: " + (dots-1);

            Font f = new Font("Arial", Font.PLAIN, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(gameOverText, 115, SIZE/2 - DOT_SIZE*2);

            g.setColor(Color.PINK);
            g.drawString(tailLengthText, 110, SIZE/2);
        }
    }

    public void move()
    {
        for (int i = dots-1; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(isLeft) {
            x[0] -= DOT_SIZE;
        }
        if(isRight) {
            x[0] += DOT_SIZE;
        }
        if(isUp) {
            y[0] -= DOT_SIZE;
        }
        if(isDown) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkApple()
    {
        if(x[0] == appleX && y[0] == appleY)
        {
            dots++;
            createApple();
        }
    }

    private void checkCollisionsMySelf()
    {
        for (int i = dots-1; i > 0; i--)
        {
            if(dots > 4 && x[0] == x[i] && y[0] == y[i])
            {
                inGame = false;
            }
        }
    }

    private void  checkCollisionsBounds()
    {
        if (x[0] < 0)
        {
            x[0] = SIZE - DOT_SIZE;
        }
        if (x[0] > SIZE - DOT_SIZE)
        {
            x[0] = 0;
        }
        if (y[0] < 0)
        {
            y[0] = SIZE - DOT_SIZE;
        }
        if (y[0] > SIZE - DOT_SIZE)
        {
            y[0] = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(inGame)
        {
            checkApple();
            checkCollisionsMySelf();
            checkCollisionsBounds();
            //checkPositionSnake();
            move();
        }

        repaint();
    }

    class FieldKeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !isRight) //LEFT
            {
                isLeft = true;
                isUp = false;
                isDown = false;
            }

            if (key == KeyEvent.VK_RIGHT && !isLeft)  //RIGHT
            {
                isRight = true;
                isUp = false;
                isDown = false;
            }

            if (key == KeyEvent.VK_UP && !isDown)  //UP
            {
                isUp = true;
                isLeft = false;
                isRight = false;
            }

            if (key == KeyEvent.VK_DOWN && !isUp)  //DOWN
            {
                isDown = true;
                isLeft = false;
                isRight = false;
            }
        }
    }
}
