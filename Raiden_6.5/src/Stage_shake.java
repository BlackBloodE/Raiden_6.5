import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Stage_shake implements Runnable {
    static Stage stage = Raiden.stageR;
    int i = 0;
    @Override
    public void run() {
        while (true){
            if (!Raiden.dead){
                //System.out.println("in:" + Raiden.shake);
                if (Raiden.shake){
                    String media_URL = getClass().getResource("BGM/explosion1.mp3").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
                    Media media = new Media(media_URL);
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    //System.out.println("in");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (i < 10){
                                        i++;
                                        System.out.println(i);
                                        if (i %2 == 0){
                                            stage.setX(stage.getX() - 10);
                                            System.out.println("-10");
                                        }else {
                                            stage.setX(stage.getX() + 10);
                                            System.out.println("+10");
                                            Raiden.shake = false;
                                        }
                                    }
                                }
                            });
                        }
                    },0,100);
                    i = 0;

                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
