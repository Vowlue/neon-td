package ui;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objects.AoeTower;
import objects.Enemy;
import objects.TargetedTower;
import objects.Tile;
import objects.Tower;
import objects.TowerIcon;
import objects.towers.BoosterTower;
import objects.towers.ElectricTower;
import objects.towers.FireTower;
import objects.towers.IceTower;
import objects.towers.LaserTower;
import objects.towers.MineTower;
import objects.towers.OrbitalTower;
import objects.towers.ShieldTower;
import objects.towers.SniperTower;

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
	public static int playerSparks = 500;
	private static int shields;
	public static double dmgMult = 1.1;
	
	int[][] map;
	
	private static TowerIcon storedIcon;
	private static TowerIcon[] towerIcons;
	public static ArrayList<Enemy> enemies;
	public static ArrayList<Tower> placedTowers;
	
	//GAME ELEMENTS
	private Stage window;
	public static BorderPane gameLayout;
	
	public static GridPane mapLayout;
	public static HBox topMenu;
	static VBox shopMenu;
	public static Pane upgradeMenu;

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
	public static int wave;
	
	private static Label wavesSurvived;
	
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
	public static Image forward;
	public static Image crosshair;
	public static Image orbiter;
	public static Image bomb;
	
	public static Image starry;
	public static Image neonTD;
	public static Image start1;
	public static Image start2;
	public static Image stars;
	public static Image end;
	
	//game scenes
	private Scene start;
	private Scene game;
	private Scene gameOver;
	
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
		
		crosshair = new Image(new FileInputStream("images/crosshair.png"));
		orbiter = new Image(new FileInputStream("images/orbiter.png"));
		bomb = new Image(new FileInputStream("images/bomb.png"));
		
		starry = new Image(new FileInputStream("images/starry.png"));
		neonTD = new Image(new FileInputStream("images/neontd.png"));
		start1 = new Image(new FileInputStream("images/start1.png"));
		start2 = new Image(new FileInputStream("images/start2.png"));
		stars = new Image(new FileInputStream("images/stars.jpg"));
		end = new Image(new FileInputStream("images/end.png"));
		
		placedTowers = new ArrayList<Tower>();
		towerIcons = new TowerIcon[9];
		t1 = new TowerIcon("boost", 800, boost, TowerIcon.BLUE, "Booster Tower: \nWith its leafy prowlress, this tower boosts the power of each tower situated in its radius. However, it does nothing on its own."); //800
		t2 = new TowerIcon("electric", 400, electric, TowerIcon.BLUE, "Electric Tower: \nWith a shocking blast at its dispoal, this tower blasts ememies with electricity, bouncing if they are close enough."); // 400
		t3 = new TowerIcon("fire", 200, fire, TowerIcon.BLUE, "Fire Tower: \nVery hot; Any enemies in its area will be roasted with heat."); // 200
		t4 = new TowerIcon("ice", 200, ice, TowerIcon.YELLOW, "Ice Tower: \nChillingly cold, this tower applies a slowing debuff on enemies with a twinge of pain."); // 200 
		t5 = new TowerIcon("laser", 250, laser, TowerIcon.YELLOW, "Laser Tower: \nThis tower repeatedly fires on enemies with red lasers.");// 250? but nerf
		t6 = new TowerIcon("orbital", 500, orbital, TowerIcon.YELLOW, "Orbital Tower: \nAlthough this tower itself does nothing, it has a little orbital that hits enemies it contacts."); // 500?
		t7 = new TowerIcon("shield", 1000, shield, TowerIcon.GREEN, "Shield Tower: \nIf you're feeling like you need less arsenal and more protection, this tower protects your life once."); // 1000?
		t8 = new TowerIcon("mine", 1000, mine, TowerIcon.GREEN, "Mine Tower: \nIt launches a sticky bomb that attaches itself to an enemy and blowing up; if the enemy dies, it falls to the ground."); //pretty op 1000?
		t9 = new TowerIcon("sniper", 600, sniper, TowerIcon.GREEN, "Sniper Tower: \nAiming at the first target with a deft range, this tower snipes down its targets."); // 600
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
		wave = 0;
		
		window = stage;
		window.setTitle("Neon Tower Defense");
		
		gameLayout = new BorderPane();
		setupTopMenu();
		setupUpgradeMenu();
		setupShopMenu();
		setupMap();
		
		Pane startPane = new Pane();
		startPane.setBackground(new Background(new BackgroundImage(starry, null, null, null, null)));
		ImageView title = new ImageView(neonTD);
		title.setX(GAME_WIDTH/2-250);
		ImageView startButton = new ImageView(start1);
		startButton.setX(GAME_WIDTH/2-100);
		startButton.setY(GAME_HEIGHT/2);
		startButton.setOnMouseEntered(e -> startButton.setImage(start2));
		startButton.setOnMouseExited(e -> startButton.setImage(start1));
		startButton.setOnMouseClicked(e -> window.setScene(game));
		startPane.getChildren().addAll(title, startButton);
		start = new Scene(startPane, GAME_WIDTH, GAME_HEIGHT);
		window.setScene(start);
		
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
		
		Pane gameOverPane = new Pane();
		gameOverPane.setBackground(new Background(new BackgroundImage(stars, null, null, null, null)));
		gameOver = new Scene(gameOverPane, GAME_WIDTH, GAME_HEIGHT);
		ImageView overTitle = new ImageView(end);
		overTitle.setX(GAME_WIDTH/2-320);
		overTitle.setY(50);
		wavesSurvived = new Label();
		wavesSurvived.setLayoutX(GAME_WIDTH/2-150);
		wavesSurvived.setLayoutY(GAME_HEIGHT/2-50);
		wavesSurvived.setTextFill(Color.RED);
		wavesSurvived.setStyle("-fx-font-family: verdana;-fx-font-size: 21;");
		gameOverPane.getChildren().addAll(overTitle, wavesSurvived);
		window.setResizable(false);
		window.sizeToScene();
		//window.setScene(gameOver);
		
		//game loop
	    AnimationTimer gameLoop = new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	        	if(playerLives == 0){
	        		wavesSurvived.setText("You have survived "+wave+" waves.");
	        		window.setScene(gameOver);
	        	}
	        	shields = 0;
	            for(Tower t: placedTowers){
	            	if(t instanceof TargetedTower){
	            		TargetedTower tt = (TargetedTower)t;
	            		tt.setDamage(tt.getDamage());
		            	Enemy target = null;
		            	double percentDone = 0;
		            	for(Enemy e: enemies){
		            		if(t.inRange(e) && 
		            		e.getCompletion() > percentDone){
		            			target = e;
		            			percentDone = e.getCompletion();
		            		}
		            	}
		            	if(percentDone > 0)
	            			((TargetedTower)t).fire(target);
	            	}
	            	else if(t instanceof AoeTower){
	            		ArrayList<Enemy> inAoe = new ArrayList<Enemy>();
	            		for(Enemy e: enemies){
	            			if(t.inRange(e))
	            				inAoe.add(e);
	            		}
	            		if(inAoe.size() > 0)
	            			((AoeTower) t).fire(inAoe);
	            	}
	            	else if(t instanceof OrbitalTower){
	            		ArrayList<Enemy> hit = new ArrayList<Enemy>();
	            		OrbitalTower ot = (OrbitalTower)t;
	            		ImageView orbiter = ot.getOrbiter();
	            		for(Enemy e: enemies){
	            			if(orbiter.localToScene(orbiter.getBoundsInLocal()).intersects(e.localToScene(e.getBoundsInLocal()))){
	            				hit.add(e);
	            			}
	            		}
	            		hit.forEach(l -> ot.contactDamage(l));
	            	}
	            	else if(t instanceof BoosterTower){
	            		ArrayList<Tower> towers = getPlacedTowers();
	            		for(Tower tow: towers){
	            			if(getDistanceBetween(tow, t) <= t.getRange() && !tow.isBoosted())
	            			((BoosterTower)t).boostTower(tow);
	            		}
	            	}
	            	else if(t instanceof ShieldTower){
	            		shields++;
	            	}
	            }
	        }
	    };
	    
	    gameLoop.start();
		
	    //show stage after done with setup
		window.show();
	}
	
	//SET UP METHODS
	private void setupTopMenu(){
		topMenu = new HBox(100);
		topMenu.getStyleClass().add("uimenu");
		topMenu.setStyle("-fx-background-color:#d1d1e0");
		topMenu.setPrefWidth(GAME_WIDTH);
		topMenu.setPrefHeight(.05*GAME_HEIGHT);
		nextWave = new NextWaveButton();
		lifeInd = new LivesIndicator(playerLives);
		sprkInd = new SparksIndicator(playerSparks);
		topMenu.getChildren().addAll(nextWave, lifeInd, sprkInd);
		gameLayout.setTop(topMenu);
	}
	private void setupUpgradeMenu(){
		upgradeMenu = new Pane();
		upgradeMenu.setPrefWidth(.1*GAME_WIDTH);
		upgradeMenu.prefHeight(.95*GAME_HEIGHT);
		upgradeMenu.setStyle("-fx-background-color:#330033");
		gameLayout.setLeft(upgradeMenu);
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
		dui = new DescriptionUI();
		shopMenu.getChildren().addAll(towerMenu, dui);
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
	public static ArrayList<Tower> getPlacedTowers(){
		ArrayList<Tower> t = new ArrayList<Tower>();
		for(Tower tw: placedTowers){
			t.add(tw);
		}
		return t;
	}
	public static void loseLife(){
		setPlayerHp(Main.getPlayerHp()-1);
		lifeInd.removeLife();
	}
	public static double getDistanceBetween(Node n1, Node n2){
		Point p1 = getCenterCoords(n1);
		Point p2 = getCenterCoords(n2);
		return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2) + Math.pow(p1.getY()-p2.getY(), 2));
	}
	public static void placeTower(TowerIcon ti, Tile tile){
		Tower t;
		double x = mapLayout.getLayoutX()+tile.getLayoutX();
		double y = mapLayout.getLayoutY()+tile.getLayoutY();
		double width = tile.getWidth();
		double height = tile.getHeight();
		switch(ti.getIdCode()){
		case "boost": 
			t = new BoosterTower(x,y,width,height);break;
		case "electric": 
			t = new ElectricTower(x,y,width,height);break;
		case "fire": 
			t = new FireTower(x,y,width,height);break;
		case "ice": 
			t = new IceTower(x,y,width,height);break;
		case "laser": 
			t = new LaserTower(x,y,width,height);break;
		case "mine": 
			t = new MineTower(x,y,width,height);break;
		case "orbital": 
			t = new OrbitalTower(x,y,width,height);break;
		case "shield": 
			t = new ShieldTower(x,y,width,height);break;
		case "sniper": 
			t = new SniperTower(x,y,width,height);break;
		default: t = null; break;
		}
		placedTowers.add(t);
		addNode(t);
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
	public static int getPlayerHp() {
		return playerLives;
	}
	public static void setPlayerHp(int hp) {
		playerLives = hp;
	}
	public static void removeNode(Node n){
		gameLayout.getChildren().remove(n);
	}
	public static ArrayList<Node> getAllNodes(Parent root) {
	    ArrayList<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
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
	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	        nodes.add(node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    }
	}
	private static void handle(Event e){
		//if the target of the event is a Tower and it is currently showing its indicator, don't allow the event to happen
		EventTarget et = e.getTarget();
		if(et instanceof Node && ((Node)et).getId() != null &&((Node)et).getId().equals("options") || et instanceof Tower && ((Tower)et).isShowing()){
			return;
		}
		Object[] nodes = gameLayout.getChildren().toArray();
		for(Object n: nodes){
			if(n instanceof Tower){
				((Tower)n).unshowOptions();
			}
		}
	}
	public static void spawnEnemy(Enemy e){
		gameLayout.getChildren().add(e);
		enemies.add(e);
	}
	public static void removeEnemy(Enemy e){
		enemies.remove(e);
		removeNode(e);
	}
	public static Point getCenterCoords(Node node){
		Bounds b = node.localToScene(node.getBoundsInLocal());
		return new Point((int)(b.getMinX()+b.getWidth()/2), (int)(b.getMinY()+b.getHeight()/2));
	}
	public static ArrayList<Enemy> getEnemies(){
		ArrayList<Enemy> current = new ArrayList<Enemy>();
		for(Enemy e: enemies){
			current.add(e);
		}
		return current;
	}
	public static void changeMoney(int m){
		playerSparks += m;
		sprkInd.setMoney(playerSparks);
	}
	public static void takeDamage(Enemy e){
		if(shields > 0){
			for(Tower t: placedTowers){
				if(t instanceof ShieldTower){
					removeNode(t);
					placedTowers.remove(t);
					break;
				}
			}
		}
		else
			loseLife();
		removeEnemy(e);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
