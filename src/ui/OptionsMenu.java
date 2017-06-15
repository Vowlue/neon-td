package ui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import objects.AoeTower;
import objects.Tower;

public class OptionsMenu extends VBox{
	public OptionsMenu(Tower t){
		setId("options");
		this.setPrefWidth(.1*Main.GAME_WIDTH);
		setBackground(new Background(new BackgroundFill(Color.web("#9966ff"), new CornerRadii(7), Insets.EMPTY)));
		Text sellText = new Text("Sell "+(int)(.75*t.getTowerIcon().getCost()));
		sellText.setWrappingWidth(getPrefWidth());
		sellText.setId("options");
		sellText.setFill(Color.ALICEBLUE);
		HBox sellButton = new HBox(new ImageView(Main.spark), new ImageView(Main.spark));
		sellButton.setId("options");
		sellButton.setBackground(new Background(new BackgroundFill(Color.web("#6699ff"), new CornerRadii(7), Insets.EMPTY)));
		sellButton.setOnMouseClicked(click -> {
			t.unshowOptions();
			Main.removeNode(t);
			Main.changeMoney((int)(.75*t.getTowerIcon().getCost()));
		});
		Text upgradeText = new Text("Upgrades");
		upgradeText.setId("options");
		upgradeText.setFill(Color.ALICEBLUE);
		
		if(t instanceof AoeTower){
			
		}
		getChildren().addAll(sellText, sellButton);
	}
}
