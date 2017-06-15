package ui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import objects.Enemy;

public class NextWaveButton extends Label{
	private int difficulty = 0;
	private int amount = 1;
	public NextWaveButton(){
		super("");
		setBackground(new Background(new BackgroundFill(Color.web("#66FF66"), new CornerRadii(5), Insets.EMPTY)));
		ImageView iv = new ImageView(Main.forward);
		iv.setFitHeight(.05*Main.GAME_HEIGHT);
		this.setGraphic(iv);
		this.setOnMouseClicked(e -> {
			Main.topMenu.getChildren().remove(this);
			Timeline enemySpawner = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			    	Main.wave++;
			        for(int i = 0; i<amount; i++){
			        	new Timeline(new KeyFrame(Duration.millis(100*i), ev -> {
			        		Main.spawnEnemy(new Enemy(difficulty));
						})).play();
			        }
			        if(difficulty < 4){
			        	if(amount < 11){
				        	amount++;
				        }
				        else{
				        	amount = 1;
				        	difficulty++;
				        }
			        }
			        else{
			        	amount += 10;
			        }
			        
			    }
			}));
			enemySpawner.setCycleCount(Timeline.INDEFINITE);
			enemySpawner.play();
		});
	}
}
