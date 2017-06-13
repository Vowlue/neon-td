package objects.towers;

import java.awt.Point;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import objects.TargetedTower;
import objects.Enemy;
import ui.Main;

public class ElectricTower extends TargetedTower{
	private int bounces;
	private int bounceRange;
	public ElectricTower(double x, double y, double width, double height) {
		super(200, 6, 200, "electric", Main.electric, x, y, width, height);
		bounces = 3;
		bounceRange = 50;
	}
	@Override
	public void fire(Enemy enemy) {
		if(canFire()){
			chain(enemy, bounces);
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}
	// turn this to not recursion
	private void chain(Enemy t, int b){
		Path p = new Path();
		Point center = Main.getCenterCoords(this);
		p.setStroke(Color.YELLOW);
		p.setStrokeWidth(4);
		p.getElements().add(new MoveTo(center.getX(), center.getY()));
		ArrayList<Enemy> targets = new ArrayList<Enemy>();
		targets.add(t);
		p.getElements().add(new LineTo(t.getTranslateX()+t.getLayoutX()+t.getCenterX(), t.getTranslateY()+t.getLayoutY()+t.getCenterY()));
		Enemy current = t;
		t.takeDamage(getDamage());
		while(b > 0){
			//find closest enemy behind the first that is within the bounce range
			ArrayList<Enemy> enemies = Main.getEnemies();
			Enemy next = null;
			double dist = bounceRange;
			for(Enemy e: enemies){
				double cDist = Main.getDistanceBetween(current, e);
				if(current.getCompletion() > e.getCompletion() && cDist <= bounceRange){
					if(cDist <= dist){
						next = e;
						dist = cDist;
					}
				}
			}
			if(next == null)
				break;
			next.takeDamage(getDamage());
			targets.add(next);
			p.getElements().add(new LineTo(next.getTranslateX()+next.getLayoutX()+next.getCenterX(), next.getTranslateY()+next.getLayoutY()+next.getCenterY()));
			current = next;
			b--;
			
		}
		Main.addNode(p);
		new Timeline(new KeyFrame(Duration.millis(30), ev -> Main.removeNode(p))).play();
		/*if(b == bounces){
			Point center = Main.getCenterCoords(this);
			p = new Path();
			p.setStroke(Color.YELLOW);
			p.setStrokeWidth(2);
			Main.addNode(p);
			p.getElements().add(new MoveTo(center.getX(), center.getY()));
		}
		else if(b == 0){
			new Timeline(new KeyFrame(Duration.millis(30), ev -> Main.removeNode(p))).play();
		}
		else if(b > 0){
			p.getElements().add(new LineTo(t.getTranslateX()+t.getLayoutX()+t.getCenterX(), t.getTranslateY()+t.getLayoutY()+t.getCenterY()));
			t.takeDamage(getDamage());
			ArrayList<Enemy> targets = Main.getEnemies();
			for(Enemy e: targets){
				if(t.getCompletion() > e.getCompletion() && Main.getDistanceBetween(t, e) <= bounceRange){
					chain(e, b-1);
				}
			}
		}*/
	}
}
