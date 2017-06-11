package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class NextWaveButton extends Label{
	public NextWaveButton(){
		super("");
		setBackground(new Background(new BackgroundFill(Color.web("#66FF66"), new CornerRadii(5), Insets.EMPTY)));
		ImageView iv = new ImageView(Main.forward);
		iv.setFitHeight(.05*Main.GAME_HEIGHT);
		this.setGraphic(iv);
	}
}
