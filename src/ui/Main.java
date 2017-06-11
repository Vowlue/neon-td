package ui;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import objects.Enemy;
import objects.Tile;
import objects.Tower;
import objects.TowerIcon;
import objects.towers.*;

public class Main extends Application{
	// CONSTANTS
	final static int GAME_HEIGHT = 700;
	final static int GAME_WIDTH = 1000;
	private final int EMPTY = 0;
	private final int BLACK = 1;
	private final int BLUE = 2;
	private final int YELLOW = 3;
	private final int GREEN = 4;
	private final int HEIGHT_D = 20;
	private final int WIDTH_D = 20;
	
	public final static String SINGLE_TARGET = "st";
	public final static String MULTI_TARGET = "aoe";
	public final static String ULTITY = "util";
	
	public static final double iconWidth = (.2*GAME_WIDTH)/4;
	public static final double iconHeight = (.95*GAME_HEIGHT)/13;
	//GAME VARIABLES
	private static int playerLives = 10;
	private static int playerSparks = 500;
	
	int[][] map;
	
	private static TowerIcon storedIcon;
	private static TowerIcon[] towerIcons;
	public static ArrayList<Enemy> enemies;
	public static ArrayList<Tower> placedTowers;
	
	//GAME ELEMENTS
	private Stage window;
	private Scene game;
	public static BorderPane gameLayout;
	
	public static GridPane mapLayout;
	HBox topMenu;
	static VBox shopMenu;
	Pane actionHold;

	public static TowerIcon t1;
	public static TowerIcon t2;
	public static TowerIcon t3;
	public static TowerIcon t4;
	public static TowerIcon t5;
	public static TowerIcon t6;
	public static TowerIcon t7;
	public static TowerIcon t8;
	public static TowerIcon t9;
	
	private static NextWaveButton nextWave;
	private static LivesIndicator lifeInd;
	private static SparksIndicator sprkInd;
	public static DescriptionUI dui;
	
	//GAME IMAGES
	private static Image blackTile;
	private static Image uBlueTile;
	private static Image uGreenTile;
	private static Image uYellowTile;
	
	public static Image boost;
	public static Image electric;
	public static Image fire;
	public static Image ice;
	public static Image laser;
	public static Image mine;
	public static Image orbital;
	public static Image shield;
	public static Image sniper;
	
	public static Image heart;
	public static Image spark;
	public static Image towersLogo;
	static Image forward;
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		blackTile = new Image(new FileInputStream("images/black tile.png"));
		uBlueTile = new Image(new FileInputStream("images/blue unshaded tile.png"));
		uGreenTile = new Image(new FileInputStream("images/green unshaded tile.png"));
		uYellowTile = new Image(new FileInputStream("images/yellow unshaded tile.png"));
		
		boost = new Image(new FileInputStream("images/towers/boost.png"));
		electric = new Image(new FileInputStream("images/towers/electric.png"));
		fire = new Image(new FileInputStream("images/towers/fire.png"));
		ice = new Image(new FileInputStream("images/towers/ice.png"));
		laser = new Image(new FileInputStream("images/towers/laser.png"));
		orbital = new Image(new FileInputStream("images/towers/orbital.png"));
		shield = new Image(new FileInputStream("images/towers/shield.png"));
		mine = new Image(new FileInputStream("images/towers/mine.png"));
		sniper = new Image(new FileInputStream("images/towers/sniper.png"));
		
		heart = new Image(new FileInputStream("images/heart.png"));
		spark = new Image(new FileInputStream("images/spark.png"));
		towersLogo = new Image(new FileInputStream("images/towers.png"));
		forward = new Image(new FileInputStream("images/forward.png"));
		
		placedTowers = new ArrayList<Tower>();
		towerIcons = new TowerIcon[9];
		t1 = new TowerIcon("boost", 0, boost, TowerIcon.BLUE, "t1");
		t2 = new TowerIcon("electric", 0, electric, TowerIcon.BLUE, "t2");
		t3 = new TowerIcon("fire", 0, fire, TowerIcon.BLUE, "t3");
		t4 = new TowerIcon("ice", 0, ice, TowerIcon.YELLOW, "t4");
		t5 = new TowerIcon("laser", 0, laser, TowerIcon.YELLOW, "t5");
		t6 = new TowerIcon("orbital", 0, orbital, TowerIcon.YELLOW, "t6");
		t7 = new TowerIcon("shield", 0, shield, TowerIcon.GREEN, "t7");
		t8 = new TowerIcon("mine", 0, mine, TowerIcon.GREEN, "t8");
		t9 = new TowerIcon("sniper", 0, sniper, TowerIcon.GREEN, "t9");
		towerIcons[0] = t1;
		towerIcons[1] = t2;
		towerIcons[2] = t3;
		towerIcons[3] = t4;
		towerIcons[4] = t5;
		towerIcons[5] = t6;
		towerIcons[6] = t7;
		towerIcons[7] = t8;
		towerIcons[8] = t9;
		
		
		enemies = new ArrayList<Enemy>();
		
		window = stage;
		window.setTitle("Neon Tower Defense");
		
		gameLayout = new BorderPane();
		setupTopMenu();
		setupActionMenu();
		setupShopMenu();
		setupMap();
		
		game = new Scene(gameLayout, GAME_WIDTH, GAME_HEIGHT);
		//game.addEventFilter(MouseEvent.MOUSE_PRESSED, x -> {System.out.println(x.getSceneX() + " " + x.getSceneY());handle(x);});
		game.addEventFilter(MouseEvent.MOUSE_PRESSED, x -> {handle(x);});
		game.getStylesheets().add("style/TDStyle.css");
		//TESTING REMOVE LATER
		game.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.A){
				spawnEnemy(new Enemy((int)(Math.random()*5)));
			}
			if(e.getCode() == KeyCode.S){
				lifeInd.removeLife();
			}
		});
		
		window.setScene(game);
		window.setResizable(false);
		window.sizeToScene();
		window.show();
	}
	
	//SET UP METHODS
	private void setupTopMenu(){
		topMenu = new HBox();
		topMenu.getStyleClass().add("uimenu");
		topMenu.setStyle("-fx-background-color:#260d0d");
		topMenu.setPrefWidth(GAME_WIDTH);
		topMenu.setPrefHeight(.05*GAME_HEIGHT);
		nextWave = new NextWaveButton();
		lifeInd = new LivesIndicator(playerLives);
		sprkInd = new SparksIndicator(playerSparks);
		topMenu.getChildren().addAll(nextWave, lifeInd, sprkInd);
		gameLayout.setTop(topMenu);
	}
	private void setupActionMenu(){
		actionHold = new Pane();
		actionHold.setPrefWidth(.1*GAME_WIDTH);
		actionHold.prefHeight(.95*GAME_HEIGHT);
		Pane actionMenu = new Pane(actionHold);
		gameLayout.setLeft(actionMenu);
	}
	private void setupShopMenu() throws FileNotFoundException{
		shopMenu = new VBox();
		shopMenu.setStyle("-fx-background-color:#330033");
		VBox towerMenu = new VBox();
		ImageView towerTitle = new ImageView(towersLogo);
		towerTitle.setFitHeight(40);
		towerTitle.setFitWidth(150);
		towerTitle.setId("towerTitle");
		TowerMenu blueMenu = new TowerMenu("Blue Towers", t1, t2, t3, "#3399FF");
		TowerMenu yellowMenu = new TowerMenu("Yellow Towers", t4, t5, t6, "yellow");
		TowerMenu greenMenu = new TowerMenu("Green Towers", t7, t8, t9, "green");
		towerMenu.getChildren().addAll(towerTitle, blueMenu, yellowMenu, greenMenu);
		VBox eventContainer = new VBox();
		Label eventTitle = new Label("Events");
		HBox eventMenu = new HBox(5);
		eventMenu.setPadding(new Insets(10, 5, 10, 5));
		eventMenu.setStyle("-fx-background-color:pink");
		//eventMenu.getChildren().addAll(new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13));
		eventContainer.getChildren().addAll(eventTitle, eventMenu);
		dui = new DescriptionUI("");
		shopMenu.getChildren().addAll(towerMenu, eventContainer, dui);
		gameLayout.setRight(shopMenu);
	}
	private void setupMap() throws FileNotFoundException{
		map = generateMap();
		mapLayout = new GridPane();
		mapLayout.setId("map");
		ColumnConstraints c = new ColumnConstraints();
		RowConstraints r = new RowConstraints();
		c.setPercentWidth(100/WIDTH_D);
		r.setPercentHeight(100/HEIGHT_D);
		for(int i = 0; i<HEIGHT_D; i++){
			mapLayout.getColumnConstraints().add(c);
			mapLayout.getRowConstraints().add(r);
		}
		for(int i = 0; i<HEIGHT_D; i++){
			for(int j = 0; j<WIDTH_D; j++){
				ImageView iv;
				switch(map[j][i]){
				case BLACK: iv = new ImageView(blackTile);break;
				case BLUE: iv = new ImageView(uBlueTile);break;
				case YELLOW: iv = new ImageView(uYellowTile);break;
				case GREEN: iv = new ImageView(uGreenTile);break;
				default: iv = new ImageView(new Image(new FileInputStream("images/grass.png")));break;
				}
				Tile p = new Tile(iv);
				switch(map[j][i]){
				case BLACK: p.getStyleClass().add("path");break;
				case BLUE: p.getStyleClass().add("blue tile");break;
				case YELLOW: p.getStyleClass().add("yellow tile");break;
				case GREEN: p.getStyleClass().add("green tile");break;
				default: p.getStyleClass().add("broke");break;
				}
				iv.fitWidthProperty().bind(p.widthProperty());
				iv.fitHeightProperty().bind(p.heightProperty());
				mapLayout.add(p, i, j);
			}
		}
		gameLayout.setCenter(mapLayout);
	}
	//METHODS OF CLASS
	public static void addNode(Node n){
		gameLayout.getChildren().add(n);
	}
	public static void storeTower(TowerIcon t){
		storedIcon = t;
	}
	public static TowerIcon getTower(){
		return storedIcon;
	}
	public static void loseLife(){
		setPlayerHp(Main.getPlayerHp()-1);
		lifeInd.removeLife();
	}
	//HELPERS
	private int[][] generateMap(){
		int[][] map = new int[HEIGHT_D][WIDTH_D];
		//generate the path
		int p1 = 10;
		int p2 = 0;
		while(p2 < 5){
			map[p1][p2] = BLACK;
			p2++;
		}
		while(p1 > 4){
			map[p1][p2] = BLACK;
			p1--;
		}
		while(p2 < 10){
			map[p1][p2] = BLACK;
			p2++;
		}
		while(p1 < 16){
			map[p1][p2] = BLACK;
			p1++;
		}
		while(p2 < 15){
			map[p1][p2] = BLACK;
			p2++;
		}
		while(p1 > -1){
			map[p1][p2] = BLACK;
			p1--;
		}
		for(int i = 0; i<HEIGHT_D; i++){
			for(int j = 0; j<WIDTH_D; j++){
				if(map[i][j] == EMPTY)
					map[i][j] = randomInt(2, 4);
			}
		}
		return map;
	}
	private int randomInt(int low, int high){
		return (int)(Math.random()*(high-low+1))+low;
	}
	public static int getPlayerHp() {
		return playerLives;
	}
	public static void setPlayerHp(int hp) {
		playerLives = hp;
	}
	public static void removeNode(Node n){
		gameLayout.getChildren().remove(n);
	}
	public static void removeEnemy(Enemy en){
		gameLayout.getChildren().remove(en);
	}
	public static ArrayList<Node> getAllNodes(Parent root) {
	    ArrayList<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
	}
	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	        nodes.add(node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    }
	}
	public static ObservableList<Node> getAllTiles(){
		return mapLayout.getChildren();
	}
	public static ArrayList<Tile> getAllTiles(String style){
		ArrayList<Tile> ret = new ArrayList<Tile>();
		ObservableList<Node> nodes = getAllTiles();
		for(Node n: nodes){
			if(n.getStyleClass().contains(style))
				ret.add((Tile)n);
		}
		return ret;
	}
	public static boolean placingTower(){
		for(TowerIcon t: towerIcons){
			if(t.isClicked())
				return true;
		}
		return false;
	}
	public static void createIndicator(Circle c){
		addNode(c);
	}
	private static void handle(Event e){
		//if the target of the event is a Tower and it is currently showing its indicator, don't allow the event to happen
		EventTarget et = e.getTarget();
		if(et instanceof Tower && ((Tower)et).isShowing()){
			return;
		}
		Object[] nodes = gameLayout.getChildren().toArray();
		for(Object n: nodes){
			if(n instanceof Tower){
				((Tower)n).unshowOptions();
			}
		}
	}
	private void spawnEnemy(Enemy e){
		gameLayout.getChildren().add(e);
		enemies.add(e);
	}
	public double getDistanceBetween(Node n1, Node n2){
		Point p1 = getCenterCoords(n1);
		Point p2 = getCenterCoords(n2);
		return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2) + Math.pow(p1.getY()-p2.getY(), 2));
	}
	private Point getCenterCoords(Node node){
		Bounds b = node.localToScene(node.getBoundsInLocal());
		return new Point((int)(b.getMinX()+b.getWidth()/2), (int)(b.getMinY()+b.getHeight()/2));
	}
	public static void placeTower(Tower tower, Tile tile){
		placedTowers.add(tower);
		addNode(tower);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
