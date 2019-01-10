import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Enemy_go implements Runnable {
    static int I;
    static boolean EG = false;
    int hit_time = 0;
    int e = 0;
    @Override
    public void run() {

        while (!Raiden.dead){

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (EG){
                        if (Enemy.enemies[e]!=null){
                            Enemy.enemies[e].setLayoutY(Enemy.enemies[e].getLayoutY() + 1);
                            //System.out.println("in");
                            if (Enemy.enemies[e].getImpect(Raiden.laser.getLayoutX(),Raiden.laser.getLayoutY(),Raiden.laser.getFitHeight(),Raiden.laser.getFitWidth()) && Raiden.ltf)
                            {
                                hit_time++;
                                if (hit_time %10 == 0){
                                    String media_URL = getClass().getResource("BGM/bomb.mp3").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
                                    Media media = new Media(media_URL);
                                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                                    mediaPlayer.play();
                                }
                                if (hit_time %2 == 0){
                                    Enemy.enemies[e].setRotate(30);
                                }else {
                                    Enemy.enemies[e].setRotate(-30);
                                }
                                if (hit_time > 100){
                                    Enemy.enemies[e].setLayoutX(-500);
                                    Raiden.root.getChildren().remove(Enemy.enemies[e]);
                                    //System.out.println("0");
                                    Raiden.Score++;
                                    if (Raiden.Score > 9){
                                        Raiden.ScoreLab.setLayoutX(315);
                                    }
                                    Raiden.ScoreLab.setText("Kill:" + String.valueOf(Raiden.Score));
                                    hit_time = 0;
                                }
                            }
                            if (Enemy.enemies[e].getImpect(Raiden.player1.getLayoutX(),Raiden.player1.getLayoutY(),Raiden.player1.getFitHeight(),Raiden.player1.getFitWidth())){
                                System.out.println("Game Over:" + e);
                                Enemy.enemies[e].setLayoutX(-500);
                                Raiden.hp--;
                                Raiden.Impact();
                                if (Raiden.hp == 2){
                                    Raiden.HP.setImage(new Image("HP2.png"));
                                }else if (Raiden.hp == 1){
                                    Raiden.HP.setImage(new Image("HP1.png"));
                                }else if (Raiden.hp == 0){
                                    Raiden.HP.setVisible(false);
                                }if (Raiden.hp < 0){
                                    Raiden.dead = true;
                                    Raiden.Dead();
                                }
                            }
                        }
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

    public static void Enemy_go(int i) {
        I = i;
    }
}
