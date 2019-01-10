import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends ImageView {
    static Enemy[] enemies = new Enemy[10];
    public Enemy(double x,double y,int i){
        Enemy.this.setLayoutX(x);
        Enemy.this.setLayoutY(y);
        Enemy.this.setFitHeight(150);
        Enemy.this.setFitWidth(150);
        Enemy.this.setImage(new Image("enemy.png"));
        enemies[i] = Enemy.this;
    }
    public boolean getImpect(double x,double y,double FH,double FW){
        return (Enemy.this.getLayoutX() < x + FW &&
                Enemy.this.getLayoutX() + Enemy.this.getFitWidth() > x &&
                Enemy.this.getLayoutY() < y + FH &&
                Enemy.this.getLayoutY() + Enemy.this.getFitHeight() > y);
    }
}
