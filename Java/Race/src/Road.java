import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Road extends JPanel implements ActionListener, Runnable{

    private Image imageRoad = new ImageIcon("res/road.png").getImage();

    private Player player = new Player();
    public Player getPlayer() {
        return player;
    }

    private Timer mainTimer = new Timer(20, this);

    private Thread enemiesFactory = new Thread(this);
    private Thread audioThread = new Thread(new Audio());

    private List<Enemy> enemies = new ArrayList<>();
    private List<Boom> booms = new ArrayList<>();

    public Road() {
        mainTimer.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imageRoad, player.getLayerX1(), 0, null);
        g.drawImage(imageRoad, player.getLayerX2(), 0, null);
        g.drawImage(player.getImage(), player.getX(), player.getY(), null);
        try {
            for (Enemy e : enemies) {
                g.drawImage(e.getImage(), e.getX(), e.getY(), null);
                checkPositionEnemy(e); //this function must be last
            }
        }
        catch(Exception e){ e.printStackTrace(); }

        try {
            for (Boom b : booms) {
                g.drawImage(b.getImg(), b.getX(), b.getY(), null);
                booms.remove(b);
            }
        }
        catch(Exception e){ e.printStackTrace(); }

        try { drawText(g); }
        catch(Exception e){ e.printStackTrace(); }

        showRunDistance(g);
    }

    private void showRunDistance(Graphics g)
    {
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.setColor(Color.BLACK);
        g.drawString("Distance: " + player.getS(), 0, 555);
    }

    private void drawText(Graphics g) {
        double speed = player.getV() * 3;
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Speed: "+ speed + "km/h", 0, 20);
    }

    private void checkPositionEnemy(Enemy e) {
        if(e.getX() <= -2400 || e.getX() >= 2400) {
            enemies.remove(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        for(Enemy enemy : enemies) { enemy.move(); }

        repaint();

        playerCollidedWithEnemy();
        enemyCollidedYourself();
    }

    private void  playerCollidedWithEnemy() {
        try {
            for (Enemy e : enemies) {
                if (e.getRectangle().intersects(player.getRectangle())) {
                    booms.add(new Boom(e.getX()-64, e.getY()-64));
                    JOptionPane.showMessageDialog(null, "You drove " + (double)player.getS()/1000 + " km !");
                    System.exit(1);
                }
            }
        }
        catch(RuntimeException re) {
            re.printStackTrace();
        }
    }

    private void  enemyCollidedYourself() {
        try {
            for (Enemy outEnemy : enemies) {
                for (Enemy inEnemy : enemies) {
                    if (inEnemy.getRectangle().intersects(outEnemy.getRectangle()) && outEnemy != inEnemy) {
                        booms.add(new Boom(inEnemy.getX()-64, inEnemy.getY()-64));
                        enemies.remove(inEnemy);
                        enemies.remove(outEnemy);
                        break;
                    }
                }
            }
        }
        catch(RuntimeException re) {
            re.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true)
        {
            Random rand = new Random();
            try
            {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200,
                        rand.nextInt(470),
                        rand.nextInt(60),
                        this));
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}




