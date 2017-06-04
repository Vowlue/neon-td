package ui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class NextWaveButton extends Label{
	public NextWaveButton(){
		super("Next Wave");
		setStyle("-fx-background-color: limegreen");
		ImageView iv = new ImageView(Main.forward);
		iv.setFitHeight(.05*Main.GAME_HEIGHT);
		this.setGraphic(iv);
	}
}
