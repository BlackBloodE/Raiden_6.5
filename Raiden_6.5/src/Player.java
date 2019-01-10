import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player extends ImageView implements Runnable{

    public Player(double x,double y){
        Player.this.setLayoutX(x);
        Player.this.setLayoutY(y);
        Player.this.setImage(new Image("player.png"));
    }
//    public boolean getImpect(double x,double y,double FH,double FW){
//        return (this.getLayoutX() < x + FW && this.getLayoutX() + FW > x && this.getLayoutY() < y + FH && y + FH > this.getLayoutY());
//    }

    @Override
    public void run() {
        String media_URL = getClass().getResource("BGM/laser_beam1.mp3").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
        Media media = new Media(media_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        while (true){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (Raiden.w){
                        if (Player.this.getLayoutY() <= -50) { }
                        else {Player.this.setLayoutY(Player.this.getLayoutY() - 10);}
                        Raiden.laser.setLayoutY(Player.this.getLayoutY() -720);
                    }
                    if (Raiden.s){
                        if (Player.this.getLayoutY() >= 600) { }
                        else {Player.this.setLayoutY(Player.this.getLayoutY() + 10);}
                        Raiden.laser.setLayoutY(Player.this.getLayoutY() -720);
                    }
                    if (Raiden.a){
                        if (Player.this.getLayoutX() <= -70) { }
                        else {Player.this.setLayoutX(Player.this.getLayoutX() - 10);}
                        Raiden.laser.setLayoutX(Player.this.getLayoutX() +50);
                    }
                    if (Raiden.d){
                        if (Player.this.getLayoutX() >= 400) { }
                        else {Player.this.setLayoutX(Player.this.getLayoutX() + 10);}
                        Raiden.laser.setLayoutX(Player.this.getLayoutX() +50);
                    }
                    if (Raiden.space) {
                        if (Raiden.shoot_time > 0){
                            Raiden.ltf = true;
                            Raiden.laser.setVisible(Raiden.ltf);
                            mediaPlayer.play();
                        }else {
                            Raiden.ltf = false;
                            Raiden.laser.setVisible(Raiden.ltf);
                            mediaPlayer.stop();
                        }
                    }
                    else{
                        Raiden.ltf = false;
                        Raiden.laser.setVisible(Raiden.ltf);
                        mediaPlayer.stop();
                    }
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
