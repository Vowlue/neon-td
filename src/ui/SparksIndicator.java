package ui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SparksIndicator extends HBox{
	Label lText;
	public SparksIndicator(int sparks){
		lText = new Label(sparks+" ");
		ImageView sparky = new ImageView(Main.spark);
		getChildren().addAll(lText, sparky);
		this.setPrefWidth(Main.GAME_WIDTH*.2);
		setStyle("-fx-alignment: center-right");
	}
	public void setMoney(int m){
		lText.setText(""+m);
	}
}
