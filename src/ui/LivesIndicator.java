package ui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LivesIndicator extends HBox{
	public LivesIndicator(int lives) {
		Label lText = new Label("Lives");
		getChildren().add(lText);
		for(int i = 0; i<lives; i++){
			ImageView iv = new ImageView(Main.heart);
			iv.setFitHeight(.05*Main.GAME_HEIGHT);
			iv.setFitWidth(.05*Main.GAME_HEIGHT);
			getChildren().add(iv);
			System.out.println();
			//can remove hearts at the end by using a new counter to remove hearts.
			//ties in with game ending
		}
	}

}
