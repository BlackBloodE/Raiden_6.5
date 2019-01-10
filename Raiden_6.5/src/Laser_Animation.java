import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class Laser_Animation implements Runnable {
    int i = 0;
    String media_URL = getClass().getResource("BGM/laser_beam1.mp3").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
    Media media = new Media(media_URL);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    @Override
    public void run() {
        while (true){
            if (!Raiden.dead){
                if (Raiden.ltf){

                    Raiden.laser.setVisible(Raiden.ltf);

                    mediaPlayer.play();
                    //System.out.println("in");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (Raiden.ltf){
                                        if (i < 50){
                                            i++;
                                        }
                                        System.out.println(i);
                                        Raiden.laser.setFitWidth(i);
                                        //Raiden.laser.setLayoutX(Raiden.laser.getLayoutX() - i/2);
                                    }
                                    else{
                                        i = 0;
                                        Raiden.laser.setVisible(Raiden.ltf);
                                        mediaPlayer.stop();
                                    }

//                                    else {
////                                        i--;
////                                        Raiden.laser.setFitWidth(Raiden.laser.getFitWidth() - i);
////                                        System.out.println(i);
//                                    }
                                }
                            });
                        }
                    },0,100);
                    //i = 0;
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
