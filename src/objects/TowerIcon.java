package objects;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.Main;

public class TowerIcon extends VBox{
	public final static String BLUE = "blue tile";
	public final static String GREEN = "green tile";
	public final static String YELLOW = "yellow tile";
	
	boolean clicked;
	private ImageView iv;
	private String color;
	private String idCode;
	private int cost;
	
	public TowerIcon(String idCode, int cost, Image i, String color, String description) {
		super();
		this.cost = cost;
		this.idCode = idCode;
		iv = new ImageView(i);
		setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), Insets.EMPTY)));
		this.setStyle("-fx-alignment: center;");
		this.color = color; 
		iv.setFitWidth(Main.iconWidth);
		iv.setFitHeight(Main.iconHeight);
		Label lb = new Label(""+cost);
		lb.setStyle("-fx-background-color: white;-fx-alignment: center;");
		lb.setMinWidth(50);
		clicked = false;
		this.setOnMouseEntered(e ->{
			Main.dui.changeText(description);
		});
		this.setOnMouseExited(e ->{
			Main.dui.changeText("");
		});
		this.setOnMouseClicked(e ->{
			if(!clicked)
				selectTower();
			else
				undoSelection();
			}
		);
		getChildren().addAll(iv, lb);
	}
	public String getIdCode(){
		return idCode;
	}
	public Image getImage(){
		return iv.getImage();
	}
	public boolean isClicked(){
		return clicked;
	}
	public void setClicked(boolean b){
		clicked = b;
	}
	private void undoSelection() {
		clicked = false;
		ArrayList<Tile> tiles = Main.getAllTiles(color);
		for(Tile t: tiles){
				t.setBackground(Background.EMPTY);
				t.setPlacement(false);
		}
	}
	private void selectTower(){
		if(Main.playerSparks >= cost && !Main.placingTower()){
			clicked = true;
			ArrayList<Tile> tiles = Main.getAllTiles(color);
			Main.storeTower(this);
			for(Tile t: tiles){
				if(!t.hasTower()){
					switch(color){
					case BLUE: 
						t.setBackground(new Background(new BackgroundFill(Color.rgb(64, 36, 123), new CornerRadii(4), Insets.EMPTY))); break;
					case YELLOW:
						t.setBackground(new Background(new BackgroundFill(Color.rgb(253, 219, 0), new CornerRadii(4), Insets.EMPTY))); break;
					case GREEN:
						t.setBackground(new Background(new BackgroundFill(Color.rgb(119, 219, 0), new CornerRadii(4), Insets.EMPTY))); break;
					default: 
						t.setBackground(Background.EMPTY); break;
					}
				t.setPlacement(true);
				}
			}
		}
	}
	public int getCost() {
		return cost;
	}
	
}
