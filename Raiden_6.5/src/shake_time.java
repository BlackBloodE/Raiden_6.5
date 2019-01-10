import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class shake_time implements Runnable {
    int i = 0;
    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                        Raiden.st = i;
                    }
                });
            }
        },0,100);
    }
}
