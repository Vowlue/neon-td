package objects;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Enemy extends Circle{
	private final Color[] colorArr = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK};
	private final static int[] radiusArr = {10, 11, 12, 13, 14, 15};
	private final int speed = 40; //pixels per second
	private int[] xMovement = {171, 0, 150};
	private int[] yMovement = {0, -200, 0};
	public Enemy(int stage) {
		super(101+radiusArr[stage], 384, radiusArr[stage]);
		setStroke(colorArr[stage]);
		setFill(Color.TRANSPARENT);
		
		for(int i = 0; i<xMovement.length; i++){
			double duration = 1.000*Math.abs(xMovement[i]+yMovement[i])/speed;
			TranslateTransition t = new TranslateTransition();
			t.setDuration(Duration.seconds(duration));
			t.setNode(this);
			t.setByX(xMovement[i]);
			t.setByY(yMovement[i]);
			
			double delay = 0;
			for(int j = i; j>0; j--){
				delay += 1.000*Math.abs(xMovement[j]+yMovement[j])/speed;
			}
			t.setDelay(Duration.seconds(delay));
			
			System.out.println(delay);
			System.out.println(xMovement[i]+ " " + yMovement[i]);
			
			t.play();
			
			t.setOnFinished(e -> {
				System.out.println("wow");
			});
		}
	}

}
