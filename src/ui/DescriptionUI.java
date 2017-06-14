package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class DescriptionUI extends Pane{
	Label l;
	public DescriptionUI(){
		super();
		l = new Label("");
		l.setId("description");
		l.setWrapText(true);
		l.setTextAlignment(TextAlignment.JUSTIFY);
		getChildren().add(l);
		l.setPrefSize(230, 200);
		setStyle("-fx-background-color:red");
	}
	public void changeText(String text){
		l.setText(text);
	}
}
