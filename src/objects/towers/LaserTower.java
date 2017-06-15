package objects.towers;

import java.awt.Point;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import objects.TargetedTower;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class LaserTower extends TargetedTower{

	public LaserTower(double x, double y, double width, double height) {
		super(150, 4, 50, "laser", Main.laser, x, y, width, height);
	}

	public void fire(Enemy enemy) {
		if(canFire()){
			Path p = new Path();
			p.setStrokeWidth(3);
			p.setStroke(Color.RED);
			Point center = Main.getCenterCoords(this);
			p.getElements().add(new MoveTo(center.getX(), center.getY()));
			p.getElements().add(new LineTo(enemy.getTranslateX()+enemy.getLayoutX()+enemy.getCenterX(), enemy.getTranslateY()+enemy.getLayoutY()+enemy.getCenterY()));
			Main.addNode(p);
			new Timeline(new KeyFrame(Duration.millis(30), ev -> {
				enemy.takeDamage(getBaseDamage());
				Main.removeNode(p);
			})).play();
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}

}
