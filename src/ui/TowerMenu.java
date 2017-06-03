package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import objects.TowerIcon;

public class TowerMenu extends VBox{
	public TowerMenu(String title, TowerIcon t1, TowerIcon t2, TowerIcon t3, String color){
		Label t = new Label(title);
		HBox menu = new HBox(5);
		menu.setPadding(new Insets(10, 5, 10, 5));
		menu.getChildren().addAll(t1, t2, t3);
		setStyle("-fx-background-color:"+color);
		menu.getStyleClass().add("menu");
		getStyleClass().add("menu");
		getChildren().addAll(t, menu);
		
	}
}
