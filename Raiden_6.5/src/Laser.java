import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Laser extends ImageView {
    public Laser(double x, double y){
        Laser.this.setLayoutX(x);
        Laser.this.setLayoutY(y);
        Laser.this.setFitHeight(720);
        Laser.this.setFitWidth(50);
        Laser.this.setImage(new Image("Laser.png"));
        Raiden.root.getChildren().add(Laser.this);
    }


}
