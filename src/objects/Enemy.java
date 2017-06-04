package objects;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import ui.Main;

public class Enemy extends Circle{
	private final Color[] colorArr = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK};
	private final static int[] radiusArr = {10, 11, 12, 13, 14, 15};
	private final double[] pathX = {285, 285, 452, 452, 620, 620};
	private final double[] pathY = {383, 185, 185, 583, 583, 50};
	private int hp;
	private PathTransition pt;
	private int stage;
	public Enemy(int stage) {
		super(101+radiusArr[stage], 384, radiusArr[stage]);
		hp = 10+stage*20;
		setStroke(colorArr[stage]);
		setFill(Color.TRANSPARENT);
		this.stage = stage;
		pathTransition();
	}
	private void pathTransition() {
		Path path = new Path();
	    path.getElements().add(new MoveTo(101+radiusArr[stage], 384));
	    for(int i = 0; i<pathX.length; i++){
	        path.getElements().add(new LineTo(pathX[i], pathY[i]));
	    }
	    pt = new PathTransition();
	    pt.setDuration(Duration.millis(10000));
	    pt.setPath(path);
	    pt.setNode(this);
	    pt.setOnFinished(e -> {
	    	Main.removeEnemy(this);
	    	Main.loseLife();
	    });
	    pt.play();
	}
	public void setStage(int stage){
		this.setRadius(radiusArr[stage]);
		this.setStroke(colorArr[stage]);
	}
	public void takeDamage(int damage){
		hp-=damage;
		if(hp <= 0)
			Main.removeEnemy(this);
	}

}
