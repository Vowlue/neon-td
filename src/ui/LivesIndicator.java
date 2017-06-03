package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class LivesIndicator extends HBox{
	private int counter;
	public LivesIndicator(int lives) {
		Label lText = new Label("Lives");
		getChildren().add(lText);
		for(int i = 0; i<lives; i++){
			ImageView iv = new ImageView(Main.heart);
			iv.setFitHeight(.05*Main.GAME_HEIGHT);
			iv.setFitWidth(.05*Main.GAME_HEIGHT);
			getChildren().add(iv);
		}
		setBackground(new Background(new BackgroundFill(Color.web("#3385ff"), new CornerRadii(5), Insets.EMPTY)));
		counter = lives;
	}
	public void removeLife(){
		if(counter >= 0)
			getChildren().remove(counter);
		counter--;
	}

}
