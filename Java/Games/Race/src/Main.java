
import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();

        JFrame mw = new JFrame("Java Race");
        mw.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mw.setResizable(false);
        mw.setSize(1100, 600);
        mw.setLocation((int)(screenSize.getWidth()/2-mw.getWidth()/2), (int)(screenSize.getHeight()/2-mw.getHeight()/2));
        mw.add(new Road());
        mw.setVisible(true);
    }
}
