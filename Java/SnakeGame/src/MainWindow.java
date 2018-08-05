import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() throws HeadlessException
    {
        Dimension myScreenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setResizable(false);
        setLocation(myScreenSize.width/2-320/2, myScreenSize.height/2-345/2);
        this.add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args)
    {
        MainWindow mw = new MainWindow();
    }
}
