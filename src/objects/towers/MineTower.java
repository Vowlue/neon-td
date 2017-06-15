package objects.towers;

import java.awt.Point;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import objects.Enemy;
import objects.TargetedTower;
import ui.Main;

public class MineTower extends TargetedTower{

	public MineTower(double x, double y, double width, double height) {
		super(200, 30, 3000, "mine", Main.mine, x, y, width, height);
	}

	@Override
	public void fire(Enemy enemy) {
		if(canFire()){
			setCanFire(false);
			ImageView mine = new ImageView(Main.bomb);
			mine.setFitWidth(enemy.getRadius());
			mine.setFitHeight(enemy.getRadius());
			mine.setLayoutX(enemy.getLayoutX()+enemy.getCenterX()+enemy.getRadius()/2-mine.getFitWidth());
			mine.setLayoutY(enemy.getLayoutY()+enemy.getCenterY()+enemy.getRadius()/2-mine.getFitHeight());
			mine.xProperty().bind(enemy.translateXProperty());
			mine.yProperty().bind(enemy.translateYProperty());
			Main.addNode(mine);
			new Timeline(new KeyFrame(getDelay(), x -> setCanFire(true))).play();
			new Timeline(new KeyFrame(getDelay().divide(2), e -> {
				Point center = Main.getCenterCoords(mine);
				Circle aoe = new Circle(center.getX(), center.getY(), getRange(), Color.rgb(255, 102, 204, 0.3));
				aoe.setStroke(Color.rgb(204, 51, 0, 0.3));
				aoe.setEffect(new GaussianBlur());
				Main.addNode(aoe);
				Main.removeNode(mine);
				new Timeline(new KeyFrame(Duration.millis(50), ev -> {
					ArrayList<Enemy> enemies = Main.getEnemies();
					for(Enemy enem: enemies){
						if(Main.getDistanceBetween(aoe, enem) <= getRange()){
							enem.takeDamage(getBaseDamage());
						}
					}
					Main.removeNode(aoe);
				})).play();
			})).play();
		}
	}


}
