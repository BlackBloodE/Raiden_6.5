import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class Time implements Runnable{

    int i = 0;
    double Y = 0;

    public Time(){

    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!Raiden.dead){
                            i++;
                            Raiden.t++;
                            Raiden.lab.setText(String.valueOf(Raiden.t));
                            if (i == 2){
                                Enemy_go.Enemy_go(i);
                                Enemy enemy = new Enemy((int)(Math.random()*330+1), Y,0);
                                Raiden.root.getChildren().add(enemy);
                                Enemy_go.EG = true;
                            } else if (i == 4){
                                Enemy_go2.Enemy_go2(i);
                                Enemy enemy2 = new Enemy((int)(Math.random()*330+1), Y,1);
                                Raiden.root.getChildren().add(enemy2);
                                Enemy_go2.EG = true;
                            }else if (i == 6){
                                Enemy_go3.Enemy_go3(i);
                                Enemy enemy2 = new Enemy((int)(Math.random()*330+1), Y,2);
                                Raiden.root.getChildren().add(enemy2);
                                Enemy_go3.EG = true;
                            }else if (i == 8){
                                Enemy_go4.Enemy_go4(i);
                                Enemy enemy2 = new Enemy((int)(Math.random()*330+1), Y,3);
                                Raiden.root.getChildren().add(enemy2);
                                Enemy_go4.EG = true;
                            }else if (i == 10){
                                Enemy_go5.Enemy_go5(i);
                                Enemy enemy2 = new Enemy((int)(Math.random()*330+1), Y,4);
                                Raiden.root.getChildren().add(enemy2);
                                Enemy_go5.EG = true;
                                i = 0;
                            }
                            if (Raiden.space){
                                if (Raiden.shoot_time > 0 && Raiden.shoot_time != 0){
                                    Raiden.shoot_time--;
                                    Raiden.cd.setText("CD:" + Raiden.shoot_time);
                                }
                            }else {
                                if (Raiden.shoot_time < 3){
                                    Raiden.shoot_time++;
                                    Raiden.cd.setText("CD:" + Raiden.shoot_time);
                                }
                            }

                        }

                    }
                });
            }
        },1000,1000);
    }
}
