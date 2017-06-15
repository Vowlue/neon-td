package objects.towers;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class OrbitalTower extends Tower{
	private ImageView orbiter;
	private double damage;
	private ArrayList<Enemy> hitEnemies;
	private Duration rotatePeriod;
	public OrbitalTower(double x, double y, double width, double height) {
		super("orbital", Main.orbital, x, y, width, height, 50);
		hitEnemies = new ArrayList<Enemy>();
		damage = 20;
		rotatePeriod = Duration.seconds(1);
		orbiter = new ImageView(Main.orbiter);
		Main.addNode(orbiter);
		orbiter.setFitWidth(width/2);
		orbiter.setFitHeight(height/2);
		orbiter.setX(x+getRange()+width/2-orbiter.getFitWidth()/2);
		orbiter.setY(y+height/2-orbiter.getFitHeight()/2);
		movePivot(orbiter, -Main.getDistanceBetween(this, orbiter), 0);
		RotateTransition rt = new RotateTransition(rotatePeriod, orbiter);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setByAngle(360);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.play();
        new Timeline(new KeyFrame(rotatePeriod, e -> hitEnemies.clear())).play();
	}
	private void movePivot(Node node, double x, double y){
        node.getTransforms().add(new Translate(-x,-y));
        node.setTranslateX(x); 
        node.setTranslateY(y);
    }
	public double getDamage(){
		return damage;
	}
	public ImageView getOrbiter(){
		return orbiter;
	}
	public void contactDamage(Enemy e){
		if(!hitEnemies.contains(e)){
			e.takeDamage(getDamage());
			hitEnemies.add(e);
		}
	}
	public void setDamage(double d) {
		damage = d;
	}

}