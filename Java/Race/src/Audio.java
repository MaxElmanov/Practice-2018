
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Audio implements Runnable{

    @Override
    public void run() {
        try {
            Player p = new Player(new FileInputStream("res/bg_music.mp3"));
            p.play();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
