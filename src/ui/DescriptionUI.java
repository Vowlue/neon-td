package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class DescriptionUI extends Pane{
	Label l;
	public DescriptionUI(){
		super();
		l = new Label("");
		l.setId("description");
		setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(5), Insets.EMPTY)));
		l.setWrapText(true);
		l.setTextAlignment(TextAlignment.JUSTIFY);
		getChildren().add(l);
		l.setPrefSize(230, 200);
	}
	public void changeText(String text){
		l.setText(text);
	}
}
