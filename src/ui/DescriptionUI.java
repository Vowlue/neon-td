package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class DescriptionUI extends Pane{
	public DescriptionUI(String description){
		super();
		Label l = new Label(description);
		l.setId("description");
		l.setWrapText(true);
		l.setTextAlignment(TextAlignment.JUSTIFY);
		getChildren().add(l);
		l.setPrefSize(230, 200);
		setStyle("-fx-background-color:red");
	}
}
