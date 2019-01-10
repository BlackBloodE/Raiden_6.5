import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class home extends Application {

    static Group root3 = new Group();
    static ImageView bg = new ImageView(new Image("space.jpg"));
    static ImageView icon = new ImageView(new Image("icon.gif"));
    static Button btnStart = new Button("Start");
    static Stage stage3 = new Stage();

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        String media_URL = getClass().getResource("BGM/1995/01 Title.mp3").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
        Media media = new Media(media_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true); //设置自动播放

        stage3.setScene(new Scene(root3, 480, 720));
        stage3.show();
        stage3.setResizable(false);
        stage3.setTitle("雷電");
        stage3.getIcons().add(new Image("icon.gif"));
        bg.setLayoutX(-586);
        bg.setLayoutY(0);
        bg.setFitWidth(1462);
        bg.setFitHeight(764);
        root3.getChildren().add(bg);
        icon.setLayoutX(145);
        icon.setLayoutY(135);
        icon.setFitWidth(190);
        icon.setFitHeight(280);
        root3.getChildren().add(icon);
        btnStart.setLayoutX(176);
        btnStart.setLayoutY(485);
        btnStart.setFont(Font.font(36));
        root3.getChildren().add(btnStart);
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String media_URL2 = getClass().getResource("BGM/1995/02 [Raiden] Coin.mp3").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
                Media media2 = new Media(media_URL2);
                MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
                mediaPlayer2.play(); //设置自动播放
                Raiden.Raiden();
                stage3.close();
                mediaPlayer.stop();
            }
        });
    }
}
