import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Raiden extends Application {

    static ImageView background = new ImageView(new Image("bg.png"));
    static ImageView HP = new ImageView(new Image("HP3.png"));
    static boolean w = false;
    static boolean s = false;
    static boolean a = false;
    static boolean d = false;
    static boolean space = false;
    static boolean dead = false;
    static boolean ltf = false;
    static boolean shake = false;
    static Group root = new Group();
    static Label lab = new Label();
    static Label ScoreLab = new Label();
    static Label live = new Label("HP:");
    static Label lose = new Label("Game Over");
    static Label lab2 = new Label("得分:");
    static Label lab3 = new Label("遊戲時間:");
    static Label ScoreLab2 = new Label();
    static Label lab4 = new Label();
    static Label cd = new Label("CD:");
    static Button btn = new Button("關閉遊戲");
    static int t = 0;
    static int Score = 0;
    static int hp = 3;
    static int Impact_Time = 0;
    static int st = 0;
    static int shoot_time = 3;
    static Laser laser;
    static Player player1;
    static Thread e;
    static Thread e2;
    static Thread e3;
    static Thread e4;
    static Thread e5;
    static Thread time;
    static Thread ss;
    //static Thread LA;
    static Timeline timeline = new Timeline();
    static Media media;
    static MediaPlayer Mplayer;


    static Stage stageR = new Stage();

    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Raiden();
    }

    public static void Raiden(){
        String url = "http://66.90.93.122/ost/raiden-v-original-soundtrack/pjvqkpqi/Unknown%20Pollution.mp3";
        try {
            //media = new Media(new File(str2).toURI().toString());
            media = new Media(url);
            Mplayer = new MediaPlayer(media);
            Mplayer.setAutoPlay(true); //设置自动播放
        }catch (Exception e){
            e.printStackTrace();
        }
        stageR.setTitle("雷電");
        stageR.getIcons().add(new Image("icon.gif"));
        //設定背景
        background.setX(0);
        background.setY(-1083);
        background.setFitHeight(1803);
        background.setFitWidth(480);
        root.getChildren().add(background);
        //設定背景動畫
        KeyValue yValue = new KeyValue(background.yProperty(), -183);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(5000), yValue);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
        //設定戰機
        player1 = new Player(165, 556);
        player1.setFitWidth(150);
        player1.setFitHeight(150);
        root.getChildren().add(player1);
        //戰機控制
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Thread thread1 = new Thread(player1);
                thread1.start();
            }
        });
        //設定子彈
        laser = new Laser(player1.getLayoutX() + 50,player1.getLayoutY()-720);
        laser.setVisible(ltf);
        //設定敵人
        e = new Thread(new Enemy_go());
        e.start();
        e2 = new Thread(new Enemy_go2());
        e2.start();
        e3 = new Thread(new Enemy_go3());
        e3.start();
        e4 = new Thread(new Enemy_go4());
        e4.start();
        e5 = new Thread(new Enemy_go5());
        e5.start();



        Scene scene = new Scene(root, 480, 720);
        player1.requestFocus();
        //root.requestFocus();//很重要!!!沒有這個鍵盤事件會沒反應
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        w = true;
                        break;
                    case S:
                        s = true;
                        break;
                    case A:
                        a = true;
                        break;
                    case D:
                        d = true;
                        break;
                    case SPACE:
                        space = true;
                        break;
                }
            }
        });
        root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case W:
                        w = false;
                        break;
                    case S:
                        s = false;
                        break;
                    case A:
                        a = false;
                        break;
                    case D:
                        d = false;
                        break;
                    case SPACE:
                        space = false;
                        break;
                }
            }
        });

        // Set the scene and display the stage
        stageR.setScene(scene);
        stageR.setResizable(false);
        stageR.show();
        //左上角的計時
        lab.setLayoutX(0);
        lab.setLayoutY(0);
        root.getChildren().add(lab);
        lab.setText("0");
        lab.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.8),null,new Insets(0))));
        lab.setFont(new Font(60));

        //右上角的計分
        ScoreLab.setLayoutX(348);
        ScoreLab.setLayoutY(0);
        root.getChildren().add(ScoreLab);
        ScoreLab.setText("Kill:" + String.valueOf(Score));
        ScoreLab.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.8),null,new Insets(0))));
        ScoreLab.setFont(new Font(60));

        //右下角CD
        cd.setLayoutX(415);
        cd.setLayoutY(685);
        root.getChildren().add(cd);
        cd.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.8),null,new Insets(0))));
        cd.setFont(new Font(30));
        cd.setText("CD:" + shoot_time);

        //右下角血量
        live.setLayoutX(0);
        live.setLayoutY(685);
        live.setFont(Font.font(30));
        live.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.8),null,new Insets(0))));
        root.getChildren().add(live);

        HP.setLayoutX(50);
        HP.setLayoutY(685);
        HP.setFitHeight(30);
        HP.setFitWidth(150);
        root.getChildren().add(HP);

        time = new Thread(new Time());
        time.start();


        ss = new Thread(new Stage_shake());
        ss.start();

//        LA = new Thread(new Laser_Animation());
//        LA.start();
    }

    public static void Impact(){
        Impact_Time = st;
        shake = true;
        System.out.println(shake);
    }

    public static void Dead(){
        if (dead){
            timeline.stop();
            Mplayer.stop();
            Group root2 = new Group();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2, 480, 200));
            stage2.show();
            stage2.setResizable(false);
            lose.setLayoutX(145);
            lose.setLayoutY(14);
            lose.setFont(Font.font(36));
            root2.getChildren().add(lose);
            lab3.setLayoutX(25);
            lab3.setLayoutY(66);
            lab3.setFont(Font.font(24));
            root2.getChildren().add(lab3);
            lab4.setLayoutX(127);
            lab4.setLayoutY(66);
            lab4.setFont(Font.font(24));
            lab4.setText(String.valueOf(t));
            root2.getChildren().add(lab4);
            lab2.setLayoutX(336);
            lab2.setLayoutY(66);
            lab2.setFont(Font.font(24));
            root2.getChildren().add(lab2);
            ScoreLab2.setLayoutX(390);
            ScoreLab2.setLayoutY(66);
            ScoreLab2.setFont(Font.font(24));
            ScoreLab2.setText(String.valueOf(Score));
            root2.getChildren().add(ScoreLab2);
            btn.setLayoutX(189);
            btn.setLayoutY(126);
            btn.setFont(Font.font(24));
            root2.getChildren().add(btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.exit(0);
                }
            });
        }
    }
}
