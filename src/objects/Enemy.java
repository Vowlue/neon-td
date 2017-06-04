package objects;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import ui.Main;

public class Enemy extends Circle{
	private final Color[] colorArr = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK};
	private final static int[] radiusArr = {10, 11, 12, 13, 14, 15};
	private final int speed = 400; //pixels per second set back to ~100 when done
	private double[] xMovement = {171, 0, 169, 0, 168, 0};
	private double[] yMovement = {0, -200, 0, 399, 0, -532};
	private SequentialTransition seq;
	public Enemy(int stage) {
		super(101+radiusArr[stage], 384, radiusArr[stage]);
		setStroke(colorArr[stage]);
		setFill(Color.TRANSPARENT);
		
		transitionAction();
	}
	private void transitionAction(){
		seq = new SequentialTransition();
		for(int i = 0; i<xMovement.length; i++){
			double duration = Math.abs(xMovement[i]+yMovement[i])/speed*1.000;
			TranslateTransition t = new TranslateTransition();
			t.setDuration(Duration.seconds(duration));
			t.setNode(this);
			t.setByX(xMovement[i]);
			t.setByY(yMovement[i]);
			seq.getChildren().add(t);
		}
		seq.setOnFinished(e -> {
			Main.removeEnemy(this);
			Main.loseLife();
		});
		seq.play();
	}
	public void setStage(int stage){
		this.setRadius(radiusArr[stage]);
		this.setStroke(colorArr[stage]);
	}

}
