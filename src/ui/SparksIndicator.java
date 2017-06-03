package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SparksIndicator extends HBox{
	public SparksIndicator(int sparks){
		Label lText = new Label(""+sparks);
		ImageView sparky = new ImageView(Main.spark);
		getChildren().addAll(lText, sparky);
		setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(5), Insets.EMPTY)));
	}
}
