package game.gui;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HARD extends Application{
	private BackgroundImage backgroundImage;
    private Stage primaryStage;
    private Scene scene;
    private int windowWidth;
    private Label infoLabel; // To store the label
    private Label resources; // To store the label
    private ImageView currentImageView; // To store the currently selected ImageView
    private Battle battle;
    private StackPane root;
    private Image crack = new Image(getClass().getResource("images/Cracked.png").toExternalForm());
    private ImagePattern imagePattern = new ImagePattern(crack);
  
  
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle rectangle4;
    private Rectangle rectangle5;

    private ArrayList<VBox> titans = new ArrayList<>();
    
    private  Image gifImage;
    private  Image gifImage1;
    private Image gifImage11;
    private Image abnormal;
    
    private Text phaseText;
    private Text scoreText;
    private Text turnss;
    private Text resourcess;
    
    private Tooltip tooltip1;
    private Tooltip tooltip2;
    private Tooltip tooltip3;
    private Tooltip tooltip4;
    private Tooltip tooltip5;

    
    private int sc1 = 0;
    private int pc1 = 0;
    private int vs1 = 0;
    private int wt1 = 0;
    
    private int sc2 = 0;
    private int pc2 = 0;
    private int vs2 = 0;
    private int wt2 = 0;
    
    private int sc3 = 0;
    private int pc3 = 0;
    private int vs3 = 0;
    private int wt3 = 0;
    
    private int sc4 = 0;
    private int pc4 = 0;
    private int vs4 = 0;
    private int wt4 = 0;
    
    private int sc5 = 0;
    private int pc5 = 0;
    private int vs5 = 0;
    private int wt5 = 0;
    
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    
    private  MediaPlayer backgroundMediaPlayer;
    private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
   	private double width = screenSize.getWidth();
   	private double height = screenSize.getHeight();
   	private double diagonal = Math.sqrt((height*height)+(width*width));


        
	@Override
	public void start(Stage primaryStage) throws Exception {
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double diagonal = Math.sqrt((height*height)+(width*width));
		this.primaryStage = primaryStage;
        root = new StackPane();
        this.scene = new Scene(root, width/1.875, height/1.0546875);
        this.infoLabel = new Label();
        
        AnchorPane anchor = new AnchorPane();
        
     // Load images here to ensure they are not null
        try {
        	  gifImage= new Image(getClass().getResource("images/ColossalTitanFinal.png").toExternalForm());
        	  
  		    
  		      gifImage1 = new Image(getClass().getResource("images/PureTitanFinal.png").toExternalForm());
  		    
  		    
  		      gifImage11 = new Image(getClass().getResource("images/ArmouredTitanFinal.png").toExternalForm());
  		    
  		    abnormal = new Image(getClass().getResource("images/AbnormalTitanFinal.png").toExternalForm());
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            return; // Stop further execution if images can't be loaded
        }
        
        
        
     // Listener to update the windowWidth variable whenever the scene's width changes
        	scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            windowWidth = newValue.intValue();
            System.out.println("Current window width: " + windowWidth); // Output the width to console
        });
        	 try {
     			this.battle = new Battle(1, 0, 200, 5, 125);
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        
        String cursorImagePath = "/game/gui/cursor/cursorHover2-ezgif.com-resize.gif";
        Image cursorImage = new Image(getClass().getResource(cursorImagePath).toString());

        // Create a cursor from the image and set it for the scene
        scene.setCursor(new ImageCursor(cursorImage)); 
        
        // Path to the background image
        String backgroundImagePath = "images/HARD.jpg";  // Ensure the path is correct
        Image backgroundImage = new Image(getClass().getResource(backgroundImagePath).toExternalForm(), true);

        // Set background image with cover style
        BackgroundSize backgroundSize = new BackgroundSize(width/19.2, height/10.8, true, true, true, true);
        this.backgroundImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);

        // Set the BackgroundImage to the root
        root.setBackground(new Background(this.backgroundImage));
        
        
        
        if (backgroundMediaPlayer == null) {
            Media sound = new Media(getClass().getResource("music/Attack on Titan Theme (ətˈæk 0N tάɪtn x Footsteps of Doom)  EPIC FINALE VERSION.mp3").toExternalForm());
            backgroundMediaPlayer = new MediaPlayer(sound);
            backgroundMediaPlayer.setAutoPlay(true);
            backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMediaPlayer.setOnEndOfMedia(() -> backgroundMediaPlayer.seek(Duration.ZERO)); // Restart if ends unexpectedly
        }

        Image fire = new Image(getClass().getResource("images/on Fire.gif").toExternalForm());
        ImageView fireView = new ImageView(fire);
        fireView.setPreserveRatio(true);  // Preserve the aspect ratio
        fireView.setFitWidth(width);        // Set the desired width
        fireView.setFitHeight(height/6.75);       // Set the desired height
        
        //to right score aand phase
        HBox textContainer = new HBox(height/108); // 10 is the spacing between elements in pixels
        textContainer.setAlignment(Pos.TOP_RIGHT); // Align contents of the HBox to the right
        textContainer.setPadding(new Insets(10, 10, 10, 10)); // Set padding around the HBox (top, right, bottom, left)

        scoreText = new Text("Score: " + battle.getScore());
        scoreText.setFill(Color.WHITE); // Set text color
        scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, height/54)); // Set font and size
        phaseText = new Text("Phase: " + battle.getBattlePhase());
        phaseText.setFill(Color.WHITE); // Set text color
        phaseText.setFont(Font.font("Verdana", FontWeight.BOLD, height/54)); // Set font and size
        turnss = new Text("Turn: " + battle.getNumberOfTurns());
        turnss.setFill(Color.WHITE); // Set text color
        turnss.setFont(Font.font("Verdana", FontWeight.BOLD, height/54)); // Set font and size
        resourcess = new Text("Resources: " + battle.getResourcesGathered());
        resourcess.setFill(Color.WHITE); // Set text color
        resourcess.setFont(Font.font("Verdana", FontWeight.BOLD, height/43.2)); // Set font and size
        resourcess.setTranslateX(width/-128);
        textContainer.getChildren().addAll(resourcess,scoreText, phaseText,turnss);
        root.getChildren().add(textContainer);
        StackPane.setAlignment(textContainer, Pos.TOP_RIGHT);

        
        

     // Create a new Rectangle
        rectangle1 = new Rectangle();
        // Set the width and height of the rectangle
     // Bind the rectangle's width property to the width property of the scene
        rectangle1.widthProperty().bind(scene.widthProperty());
        rectangle1.setHeight(height/10.8);
        rectangle1.setTranslateY(height/-9);
        // Set the fill color of the rectangle
        rectangle1.setFill(Color.TRANSPARENT);
        rectangle1.setOpacity(0.3); // Set the opacity to 50%
        rectangle1.setStroke(Color.BLACK);
        rectangle1.setStrokeWidth(width/384);

        
     // Create a new Rectangle
        rectangle2 = new Rectangle();
        // Set the width and height of the rectangle
     // Bind the rectangle's width property to the width property of the scene
        rectangle2.widthProperty().bind(scene.widthProperty());
        rectangle2.setHeight(height/10.8);
        rectangle2.setTranslateY(height/-54);
        // Set the fill color of the rectangle
        rectangle2.setFill(Color.TRANSPARENT);
        rectangle2.setOpacity(0.3); // Set the opacity to 50%
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setStrokeWidth(width/384);

        
     // Create a new Rectangle
        rectangle3 = new Rectangle();
        // Set the width and height of the rectangle
     // Bind the rectangle's width property to the width property of the scene
        rectangle3.widthProperty().bind(scene.widthProperty());
        rectangle3.setHeight(height/10.8);
        rectangle3.setTranslateY(height/13.5);
        rectangle3.setFill(Color.TRANSPARENT);
        rectangle3.setOpacity(0.3); // Set the opacity to 50%
        rectangle3.setStroke(Color.BLACK);
        rectangle3.setStrokeWidth(width/384);
        
     // Create a new Rectangle
        rectangle4 = new Rectangle();
        // Set the width and height of the rectangle
     // Bind the rectangle's width property to the width property of the scene
        rectangle4.widthProperty().bind(scene.widthProperty());
        rectangle4.setHeight(height/10.8);
        rectangle4.setTranslateY(height/6);
        rectangle4.setFill(Color.TRANSPARENT);
        rectangle4.setOpacity(0.3); // Set the opacity to 50%
        rectangle4.setStroke(Color.BLACK);
        rectangle4.setStrokeWidth(width/384);
        
     // Create a new Rectangle
        rectangle5 = new Rectangle();
        // Set the width and height of the rectangle
     // Bind the rectangle's width property to the width property of the scene
        rectangle5.widthProperty().bind(scene.widthProperty());
        rectangle5.setHeight(height/10.8);
        rectangle5.setTranslateY(height/3.857142857);
        rectangle5.setFill(Color.TRANSPARENT);
        rectangle5.setOpacity(0.3); // Set the opacity to 50%
        rectangle5.setStroke(Color.BLACK);
        rectangle5.setStrokeWidth(width/384);
        
        // Add Tooltips for the rectangles
        tooltip1 = createCustomTooltip("Wall Health: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(0).getDangerLevel()+"\nSniper Cannon: x" + sc1 + "\nPiercing Cannon: x"+pc1 + "\nWall Trap: x" + wt1 +"\nVolleySpread: x" +vs1);
        Tooltip.install(rectangle1, tooltip1);

        tooltip2 = createCustomTooltip("Wall Health: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(1).getDangerLevel()+"\nSniper Cannon: x" + sc2 + "\nPiercing Cannon: x"+pc2 + "\nWall Trap: x" + wt2 +"\nVolleySpread: x" +vs2);
        Tooltip.install(rectangle2, tooltip2);

        tooltip3 = createCustomTooltip("Wall Health: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(2).getDangerLevel()+"\nSniper Cannon: x" + sc3 + "\nPiercing Cannon: x"+pc3 + "\nWall Trap: x" + wt3 +"\nVolleySpread: x" +vs3);
        Tooltip.install(rectangle3, tooltip3);
        
        tooltip4 = createCustomTooltip("Wall Health: "+battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(3).getDangerLevel()+"\nSniper Cannon: x" + sc4 + "\nPiercing Cannon: x"+pc4 + "\nWall Trap: x" + wt4 +"\nVolleySpread: x" +vs4);
        Tooltip.install(rectangle4, tooltip4);
        
        tooltip5 = createCustomTooltip("Wall Health: "+battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(4).getDangerLevel()+"\nSniper Cannon: x" + sc5 + "\nPiercing Cannon: x"+pc5 + "\nWall Trap: x" + wt5 +"\nVolleySpread: x" +vs5);
        Tooltip.install(rectangle5, tooltip5);

        
        String newImagePath = "images/WeaponShop.png";
        Image newImage = new Image(getClass().getResource(newImagePath).toExternalForm());
        ImageView imageView = new ImageView(newImage);
        // Preserve the aspect ratio
        imageView.setPreserveRatio(true);
        // Set the desired width or height while maintaining aspect ratio
        imageView.setFitWidth(width/1.92); // for example, set the width to 200
        imageView.setTranslateY(height/-2.769230769);
        imageView.setTranslateX(width/-5.647058824);
        
        Image newImage2 = new Image(getClass().getResource("images/Piercing Cannon.png").toExternalForm());
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setPreserveRatio(true);
        imageView2.setFitHeight(height/6.428571429); 
        imageView2.setTranslateY(height/-2.783505155);
        imageView2.setTranslateX(width/-3.147540984);
        
        Image newImage3 = new Image(getClass().getResource("images/Sniper Cannon.png").toExternalForm());
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setPreserveRatio(true);
        imageView3.setFitHeight(height/6.428571429); 
        imageView3.setTranslateY(height/-2.783505155);
        imageView3.setTranslateX(width/-4);
        
        Image newImage4 = new Image(getClass().getResource("images/Volley Spread Cannon.png").toExternalForm());
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setPreserveRatio(true);
        imageView4.setFitHeight(height/6.352941176); 
        imageView4.setTranslateY(height/-2.783505155);
        imageView4.setTranslateX(width/-5.517241379);
        
        Image newImage5= new Image(getClass().getResource("images/Wall Trap.png").toExternalForm());
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setPreserveRatio(true);
        imageView5.setFitHeight(height/6.428571429); 
        imageView5.setTranslateY(height/-2.783505155);
        imageView5.setTranslateX(width/-8.727272727);
        
        Image wallE1= new Image(getClass().getResource("images/Walls 1&3 Final.png").toExternalForm());
        ImageView wallEView1 = new ImageView(wallE1);
        wallEView1.setPreserveRatio(true);
        wallEView1.setFitHeight(height/7.2); 
        wallEView1.setTranslateY(height/3.724137931);
        
        Image wallE2= new Image(getClass().getResource("images/Walls 1&3 Final.png").toExternalForm());
        ImageView wallEView2 = new ImageView(wallE2);
        wallEView2.setPreserveRatio(true);
        wallEView2.setFitHeight(height/7.2); 
        wallEView2.setTranslateY(height/4.5);
        
        Image wallE3= new Image(getClass().getResource("images/Walls 1&3 Final.png").toExternalForm());
        ImageView wallEView3 = new ImageView(wallE3);
        wallEView3.setPreserveRatio(true);
        wallEView3.setFitHeight(height/7.2); 
        wallEView3.setTranslateY(height/5.684210526);
        
        Image wallE4= new Image(getClass().getResource("images/Walls 1&3 Final.png").toExternalForm());
        ImageView wallEView4 = new ImageView(wallE4);
        wallEView4.setPreserveRatio(true);
        wallEView4.setFitHeight(height/7.2); 
        wallEView4.setTranslateY(height/7.714285714);
        
        Image wallE5= new Image(getClass().getResource("images/Walls 1&3 Final.png").toExternalForm());
        ImageView wallEView5 = new ImageView(wallE5);
        wallEView5.setPreserveRatio(true);
        wallEView5.setFitHeight(height/7.2); 
        wallEView5.setTranslateY(height/12);
        
        VBox walls = new VBox();
        walls.setMouseTransparent(true);
        walls.setTranslateX(width/192);
        walls.getChildren().addAll(wallEView1,wallEView2,wallEView3,wallEView4,wallEView5);
        
Image soldier = new Image(getClass().getResource("images/ShootingFinal.gif").toExternalForm());
        
        ImageView soldierView1 = new ImageView(soldier);
        soldierView1.setPreserveRatio(true);
        soldierView1.setFitHeight(height/-11.02040816); 
        soldierView1.setFitWidth(width/12.15189873);
        
        ImageView soldierView2 = new ImageView(soldier);
        soldierView2.setPreserveRatio(true);
        soldierView2.setFitHeight(height/-11.02040816); 
        soldierView2.setFitWidth(width/12.15189873);
        soldierView2.setTranslateY(height/-54);
        
        ImageView soldierView3 = new ImageView(soldier);
        soldierView3.setPreserveRatio(true);
        soldierView3.setFitHeight(height/-11.02040816); 
        soldierView3.setFitWidth(width/12.15189873);
        soldierView3.setTranslateY(height/-31.76470588);
        
        ImageView soldierView4 = new ImageView(soldier);
        soldierView4.setPreserveRatio(true);
        soldierView4.setFitHeight(height/-11.02040816); 
        soldierView4.setFitWidth(width/12.15189873);
        soldierView4.setTranslateY(height/-24);
        
        ImageView soldierView5 = new ImageView(soldier);
        soldierView5.setPreserveRatio(true);
        soldierView5.setFitHeight(height/-11.02040816); 
        soldierView5.setFitWidth(width/12.15189873);
        soldierView5.setTranslateY(height/-19.63636364);
        
        VBox soldiers = new VBox(height/108);
        soldiers.setMouseTransparent(true);
        soldiers.setTranslateX(width/384);
        soldiers.setTranslateY(height/3.461538462);
        soldiers.getChildren().addAll(soldierView1,soldierView2,soldierView3,soldierView4,soldierView5);
        
        
     // Add event handlers to toggle rectangle fills and set labels
        imageView2.setOnMouseClicked(event -> handleImageClick(anchor ,imageView2, "Name: Anti-Titan Shell \nType: Piercing Cannon \nPrice: 25 \nDamage Point: 10", rectangle1, rectangle2, rectangle3,rectangle4,rectangle5));
        imageView3.setOnMouseClicked(event -> handleImageClick(anchor, imageView3, "Name: Long Range Spear \nType: Sniper Cannon \nPrice: 25 \nDamage Point: 35", rectangle1, rectangle2, rectangle3,rectangle4,rectangle5));
        imageView4.setOnMouseClicked(event -> handleImageClick(anchor,imageView4, "Name: Wall Spread Cannon \nType: Volley Spread Cannon \nPrice: 100 \nDamage Point: 5", rectangle1, rectangle2, rectangle3,rectangle4,rectangle5));
        imageView5.setOnMouseClicked(event -> handleImageClick(anchor, imageView5, "Name: Proximity Trap \nType: Wall Trap \nPrice: 75 \nDamage Point: 100", rectangle1, rectangle2, rectangle3,rectangle4,rectangle5));
        
        Button pass = new Button("PASS");
        // Setting the button's background to transparent using CSS
        pass.setStyle("-fx-background-color: transparent; -fx-text-fill: black;-fx-pref-width: 126px; -fx-pref-height: 58px;-fx-font-family: 'Comic Sans MS';-fx-font-size: 24px;-fx-font-weight: bold;");
        pass.setTranslateX(width/-2.549800797);
        pass.setTranslateY(height/-3.354037267);
        
     // Add event handler to the pass button to call battle.passTurn()
        pass.setOnAction(event -> {
           battle.passTurn();
           titansEasy(anchor, battle);
           if(battle.getOriginalLanes().get(0).isLaneLost()){
        	   rectangle1.setFill(imagePattern);
           }
           if(battle.getOriginalLanes().get(1).isLaneLost()){
        	   rectangle2.setFill(imagePattern);
           }
           if(battle.getOriginalLanes().get(2).isLaneLost()){
        	   rectangle3.setFill(imagePattern);
           }
           if(battle.getOriginalLanes().get(3).isLaneLost()){
        	   rectangle4.setFill(imagePattern);
           }
           if(battle.getOriginalLanes().get(4).isLaneLost()){
        	   rectangle5.setFill(imagePattern);
           }
           if(battle.getOriginalLanes().get(0).isLaneLost()&&battle.getOriginalLanes().get(1).isLaneLost()&&battle.getOriginalLanes().get(2).isLaneLost()&&battle.getOriginalLanes().get(3).isLaneLost()&&battle.getOriginalLanes().get(4).isLaneLost()){
        	   Image gifImage = new Image(getClass().getResource("images/gameOver.gif").toExternalForm());

               // Create an ImageView for the GIF
               ImageView gifImageView = new ImageView(gifImage);
               gifImageView.setPreserveRatio(true);
               root.getChildren().add(gifImageView);
               // Bind the ImageView size to the StackPane size to maintain aspect ratio
               gifImageView.fitWidthProperty().bind(root.widthProperty());
               gifImageView.fitHeightProperty().bind(root.heightProperty());
               gifImageView.setPreserveRatio(true);
            // Set the duration of the GIF in milliseconds (adjust this based on your GIF length)
               double gifDuration = 2800; // Example duration in milliseconds
               Timeline timeline = new Timeline(new KeyFrame(Duration.millis(gifDuration), event2 -> {
            	   transitionToGAMEOVER(primaryStage);
               }));
               timeline.setCycleCount(1); // Play the GIF only once
               timeline.play();
           }
           phaseText.setText("Phase: "+battle.getBattlePhase());
           scoreText.setText("Score: "+battle.getScore());
           turnss.setText("Turn: "+battle.getNumberOfTurns());
           resourcess.setText("Resources: "+battle.getResourcesGathered());
           resourcess.setFill(Color.WHITE);  
           tooltip1.setText ("Wall Health: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(0).getDangerLevel()+"\nSniper Cannon: x" + sc1 + "\nPiercing Cannon: x"+pc1 + "\nWall Trap: x" + wt1 +"\nVolleySpread: x" +vs1);
           tooltip2.setText ("Wall Health: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(1).getDangerLevel()+"\nSniper Cannon: x" + sc2 + "\nPiercing Cannon: x"+pc2 + "\nWall Trap: x" + wt2 +"\nVolleySpread: x" +vs2);
           tooltip3.setText ("Wall Health: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(2).getDangerLevel()+"\nSniper Cannon: x" + sc3 + "\nPiercing Cannon: x"+pc3 + "\nWall Trap: x" + wt3 +"\nVolleySpread: x" +vs3);
           tooltip4.setText ("Wall Health: "+battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(3).getDangerLevel()+"\nSniper Cannon: x" + sc4 + "\nPiercing Cannon: x"+pc4 + "\nWall Trap: x" + wt4 +"\nVolleySpread: x" +vs4);
           tooltip5.setText ("Wall Health: "+battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(4).getDangerLevel()+"\nSniper Cannon: x" + sc5 + "\nPiercing Cannon: x"+pc5 + "\nWall Trap: x" + wt5 +"\nVolleySpread: x" +vs5);

           
           if ((isRedWithOpacity(rectangle1) || isRedWithOpacity(rectangle2) || isRedWithOpacity(rectangle3)|| isRedWithOpacity(rectangle4)|| isRedWithOpacity(rectangle5))&& !isRectangleCracked(rectangle1) && !isRectangleCracked(rectangle2)&& !isRectangleCracked(rectangle3)&& !isRectangleCracked(rectangle4)&& !isRectangleCracked(rectangle5)) {
        	   rectangle1.setFill(Color.TRANSPARENT);
        	   rectangle2.setFill(Color.TRANSPARENT);
        	   rectangle3.setFill(Color.TRANSPARENT);
        	   rectangle4.setFill(Color.TRANSPARENT);
        	   rectangle5.setFill(Color.TRANSPARENT);
        	   resourcess.setStyle("-fx-control-inner-background: rgba(0, 0, 0, 0);");
           }
          
           
           if(battle.isGameOver()){
        	   Image gifImage = new Image(getClass().getResource("images/gameOver.gif").toExternalForm());

               // Create an ImageView for the GIF
               ImageView gifImageView = new ImageView(gifImage);
               gifImageView.setPreserveRatio(true);
               root.getChildren().add(gifImageView);
               // Bind the ImageView size to the StackPane size to maintain aspect ratio
               gifImageView.fitWidthProperty().bind(root.widthProperty());
               gifImageView.fitHeightProperty().bind(root.heightProperty());
               gifImageView.setPreserveRatio(true);
            // Set the duration of the GIF in milliseconds (adjust this based on your GIF length)
               double gifDuration = 2800; // Example duration in milliseconds
               Timeline timeline = new Timeline(new KeyFrame(Duration.millis(gifDuration), event2 -> {
            	   transitionToGAMEOVER(primaryStage);
               }));
               timeline.setCycleCount(1); // Play the GIF only once
               timeline.play();
               
          }
           
           /*Image image = new Image(getClass().getResource("images/Cracked.png").toExternalForm());
           ImagePattern imagePattern = new ImagePattern(image);
          if(battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()==0){
        	   System.out.print("Lost");
        	   rectangle1.setFill(imagePattern);
           }
          if(battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()==0){
       	   System.out.print("Lost");
       	   rectangle2.setFill(imagePattern);
          }
          if(battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()==0){
       	   System.out.print("Lost");
       	   rectangle3.setFill(imagePattern);
          }*/
        });
        
        
        anchor.setMouseTransparent(true);

        root.getChildren().addAll(anchor,rectangle1,rectangle2,rectangle3,rectangle4,rectangle5,walls,soldiers,imageView,imageView2,imageView3,imageView4,imageView5,infoLabel,pass);

        
        infoLabel.toFront();
        anchor.toFront();
        

        
        primaryStage.setTitle("ALfinale");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);  // Set the stage to be maximized
        primaryStage.show();
        
	}
	
	private Tooltip createCustomTooltip(String text) {
        Tooltip tooltip = new Tooltip(text);
        double a = height/60;
        tooltip.setStyle("-fx-font-size: "+a+"px;"); // Increase the font size
        // Set the tooltip delay using reflection
        try {
            Field fieldBehavior = Tooltip.class.getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldActivationTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldActivationTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldActivationTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(250))); // Set delay to 250ms
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tooltip;
    }
	
	
	private void handleImageClick(AnchorPane anchor, ImageView imageView, String text, Rectangle... rectangles) {
        boolean isCurrentLime = rectangles[0].getFill().equals(Color.LIME);

        // If the same image is clicked twice, toggle the fill and label
        if (imageView == currentImageView) {
            boolean isLime = rectangles[0].getFill().equals(Color.LIME);
            for (Rectangle rectangle : rectangles) {
            	if(!isRectangleCracked(rectangle))
            		rectangle.setFill(isLime ? Color.TRANSPARENT : Color.LIME);
            }
            infoLabel.setText(isLime ? "" : text);
        } else {
            // Update the label and keep rectangles lime
            for (Rectangle rectangle : rectangles) {
            	if(!isRectangleCracked(rectangle))
            		rectangle.setFill(Color.LIME);
            }
            infoLabel.setText(text);
            currentImageView = imageView;
        }

        // Set the font and position of the label
        infoLabel.setFont(Font.font("Comic Sans MS", height/49.09090909));  // Font family: Comic Sans MS, Size: 24
        infoLabel.setTextFill(Color.WHITE);                 // Text color: White
        infoLabel.setTranslateY(height/-2.734177215);
        infoLabel.setTranslateX(width/-192);
    	resourcess.setFill(Color.WHITE);             
        if (rectangle1.getFill().equals(Color.LIME)||rectangle2.getFill().equals(Color.LIME)||rectangle3.getFill().equals(Color.LIME)||rectangle4.getFill().equals(Color.LIME)||rectangle5.getFill().equals(Color.LIME)) {
            final int[] codes = new int[1];
            if (infoLabel.getText().equals("Name: Anti-Titan Shell \nType: Piercing Cannon \nPrice: 25 \nDamage Point: 10")){
                codes[0] = 1;
                a=true;
            	b=false;
            	c=false;
            	d=false;
                
            } else if (infoLabel.getText().equals("Name: Long Range Spear \nType: Sniper Cannon \nPrice: 25 \nDamage Point: 35")) {
                codes[0] = 2;
                a=false;
            	b=true;
            	c=false;
            	d=false;
            } else if (infoLabel.getText().equals("Name: Wall Spread Cannon \nType: Volley Spread Cannon \nPrice: 100 \nDamage Point: 5")) {
                codes[0] = 3;
                a=false;
            	b=false;
            	c=true;
            	d=false;
            } else if (infoLabel.getText().equals("Name: Proximity Trap \nType: Wall Trap \nPrice: 75 \nDamage Point: 100")) {
                codes[0] = 4;
                a=false;
            	b=false;
            	c=false;
            	d=true;
            }

            rectangle1.setOnMouseClicked(event -> {
                try {
                    battle.purchaseWeapon(codes[0], battle.getOriginalLanes().get(0));
                    rectangle1.setFill(Color.TRANSPARENT);
                    rectangle2.setFill(Color.TRANSPARENT);
                    rectangle3.setFill(Color.TRANSPARENT);
                    rectangle4.setFill(Color.TRANSPARENT);
                    rectangle5.setFill(Color.TRANSPARENT);
                    resourcess.setFill(Color.WHITE); 
                    infoLabel.setText("");
                    titansEasy(anchor, battle);
                    phaseText.setText("Phase: "+battle.getBattlePhase());
                    scoreText.setText("Score: "+battle.getScore());
                    turnss.setText("Turn: "+battle.getNumberOfTurns());
                    resourcess.setText("Resources: "+battle.getResourcesGathered());
                    if(a)
                    	pc1++;
                    if(b)
                    	sc1++;
                    if(c)
                    	vs1++;
                    if(d)
                    	wt1++;
                    tooltip1.setText ("Wall Health: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(0).getDangerLevel()+"\nSniper Cannon: x" + sc1 + "\nPiercing Cannon: x"+pc1 + "\nWall Trap: x" + wt1 +"\nVolleySpread: x" +vs1);
                    tooltip2.setText ("Wall Health: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(1).getDangerLevel()+"\nSniper Cannon: x" + sc2 + "\nPiercing Cannon: x"+pc2 + "\nWall Trap: x" + wt2 +"\nVolleySpread: x" +vs2);
                    tooltip3.setText ("Wall Health: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(2).getDangerLevel()+"\nSniper Cannon: x" + sc3 + "\nPiercing Cannon: x"+pc3 + "\nWall Trap: x" + wt3 +"\nVolleySpread: x" +vs3);
                    tooltip4.setText ("Wall Health: "+battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(3).getDangerLevel()+"\nSniper Cannon: x" + sc4 + "\nPiercing Cannon: x"+pc4 + "\nWall Trap: x" + wt4 +"\nVolleySpread: x" +vs4);
                    tooltip5.setText ("Wall Health: "+battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(4).getDangerLevel()+"\nSniper Cannon: x" + sc5 + "\nPiercing Cannon: x"+pc5 + "\nWall Trap: x" + wt5 +"\nVolleySpread: x" +vs5);

                    if(battle.isGameOver()){
                    	transitionToGAMEOVER(primaryStage);
                    }
                    
                } catch (InsufficientResourcesException e) {
                	rectangle1.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                	resourcess.setFill(Color.RED);             
                }
                catch(InvalidLaneException a){
                	Image crack = new Image(getClass().getResource("images/Cracked.png").toExternalForm());
                    ImagePattern imagePattern = new ImagePattern(crack);
                    rectangle1.setFill(imagePattern);

                }
                    
            });
            
            rectangle2.setOnMouseClicked(event -> {
                try {
                    battle.purchaseWeapon(codes[0], battle.getOriginalLanes().get(1));
                    rectangle1.setFill(Color.TRANSPARENT);
                    rectangle2.setFill(Color.TRANSPARENT);
                    rectangle3.setFill(Color.TRANSPARENT);
                    rectangle4.setFill(Color.TRANSPARENT);
                    rectangle5.setFill(Color.TRANSPARENT);
                    resourcess.setFill(Color.WHITE); 
                    infoLabel.setText("");
                    titansEasy(anchor, battle);
                    phaseText.setText("Phase: "+battle.getBattlePhase());
                    scoreText.setText("Score: "+battle.getScore());
                    turnss.setText("Turn: "+battle.getNumberOfTurns());
                    resourcess.setText("Resources: "+battle.getResourcesGathered());
                    if(a)
                    	pc2++;
                    if(b)
                    	sc2++;
                    if(c)
                    	vs2++;
                    if(d)
                    	wt2++;
                    tooltip1.setText ("Wall Health: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(0).getDangerLevel()+"\nSniper Cannon: x" + sc1 + "\nPiercing Cannon: x"+pc1 + "\nWall Trap: x" + wt1 +"\nVolleySpread: x" +vs1);
                    tooltip2.setText ("Wall Health: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(1).getDangerLevel()+"\nSniper Cannon: x" + sc2 + "\nPiercing Cannon: x"+pc2 + "\nWall Trap: x" + wt2 +"\nVolleySpread: x" +vs2);
                    tooltip3.setText ("Wall Health: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(2).getDangerLevel()+"\nSniper Cannon: x" + sc3 + "\nPiercing Cannon: x"+pc3 + "\nWall Trap: x" + wt3 +"\nVolleySpread: x" +vs3);;
                    tooltip4.setText ("Wall Health: "+battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(3).getDangerLevel()+"\nSniper Cannon: x" + sc4 + "\nPiercing Cannon: x"+pc4 + "\nWall Trap: x" + wt4 +"\nVolleySpread: x" +vs4);
                    tooltip5.setText ("Wall Health: "+battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(4).getDangerLevel()+"\nSniper Cannon: x" + sc5 + "\nPiercing Cannon: x"+pc5 + "\nWall Trap: x" + wt5 +"\nVolleySpread: x" +vs5);

                    if(battle.isGameOver()){
                    	transitionToGAMEOVER(primaryStage);
                    }
                } catch (InsufficientResourcesException e) {
                	rectangle2.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                	resourcess.setFill(Color.RED);             
                }
                catch(InvalidLaneException a){
                	Image crack = new Image(getClass().getResource("images/Cracked.png").toExternalForm());
                    ImagePattern imagePattern = new ImagePattern(crack);
                    rectangle2.setFill(imagePattern);

                }
            });
            
            rectangle3.setOnMouseClicked(event -> {
                try {
                    battle.purchaseWeapon(codes[0], battle.getOriginalLanes().get(2));
                    rectangle1.setFill(Color.TRANSPARENT);
                    rectangle2.setFill(Color.TRANSPARENT);
                    rectangle3.setFill(Color.TRANSPARENT);
                    rectangle4.setFill(Color.TRANSPARENT);
                    rectangle5.setFill(Color.TRANSPARENT);
                    resourcess.setFill(Color.WHITE); 
                    if(a)
                    	pc3++;
                    if(b)
                    	sc3++;
                    if(c)
                    	vs3++;
                    if(d)
                    	wt3++;
                    infoLabel.setText("");
                    titansEasy(anchor, battle);
                    phaseText.setText("Phase: "+battle.getBattlePhase());
                    scoreText.setText("Score: "+battle.getScore());
                    turnss.setText("Turn: "+battle.getNumberOfTurns());
                    resourcess.setText("Resources: "+battle.getResourcesGathered());
                    tooltip1.setText ("Wall Health: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(0).getDangerLevel()+"\nSniper Cannon: x" + sc1 + "\nPiercing Cannon: x"+pc1 + "\nWall Trap: x" + wt1 +"\nVolleySpread: x" +vs1);
                    tooltip2.setText ("Wall Health: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(1).getDangerLevel()+"\nSniper Cannon: x" + sc2 + "\nPiercing Cannon: x"+pc2 + "\nWall Trap: x" + wt2 +"\nVolleySpread: x" +vs2);
                    tooltip3.setText ("Wall Health: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(2).getDangerLevel()+"\nSniper Cannon: x" + sc3 + "\nPiercing Cannon: x"+pc3 + "\nWall Trap: x" + wt3 +"\nVolleySpread: x" +vs3);
                    tooltip4.setText ("Wall Health: "+battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(3).getDangerLevel()+"\nSniper Cannon: x" + sc4 + "\nPiercing Cannon: x"+pc4 + "\nWall Trap: x" + wt4 +"\nVolleySpread: x" +vs4);
                    tooltip5.setText ("Wall Health: "+battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(4).getDangerLevel()+"\nSniper Cannon: x" + sc5 + "\nPiercing Cannon: x"+pc5 + "\nWall Trap: x" + wt5 +"\nVolleySpread: x" +vs5);
                    if(battle.isGameOver()){
                    	transitionToGAMEOVER(primaryStage);
                    }
                } catch (InsufficientResourcesException e) {
                	rectangle3.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                	resourcess.setFill(Color.RED);             
                }
                catch(InvalidLaneException a){
                	Image crack = new Image(getClass().getResource("images/Cracked.png").toExternalForm());
                    ImagePattern imagePattern = new ImagePattern(crack);
                    rectangle3.setFill(imagePattern);

                }
            });
            rectangle4.setOnMouseClicked(event -> {
                try {
                    battle.purchaseWeapon(codes[0], battle.getOriginalLanes().get(3));
                    rectangle1.setFill(Color.TRANSPARENT);
                    rectangle2.setFill(Color.TRANSPARENT);
                    rectangle3.setFill(Color.TRANSPARENT);
                    rectangle4.setFill(Color.TRANSPARENT);
                    rectangle5.setFill(Color.TRANSPARENT);
                    resourcess.setFill(Color.WHITE); 
                    if(a)
                    	pc4++;
                    if(b)
                    	sc4++;
                    if(c)
                    	vs4++;
                    if(d)
                    	wt4++;
                   
                    infoLabel.setText("");
                    titansEasy(anchor, battle);
                    phaseText.setText("Phase: "+battle.getBattlePhase());
                    scoreText.setText("Score: "+battle.getScore());
                    turnss.setText("Turn: "+battle.getNumberOfTurns());
                    resourcess.setText("Resources: "+battle.getResourcesGathered());
                    tooltip1.setText ("Wall Health: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(0).getDangerLevel()+"\nSniper Cannon: x" + sc1 + "\nPiercing Cannon: x"+pc1 + "\nWall Trap: x" + wt1 +"\nVolleySpread: x" +vs1);
                    tooltip2.setText ("Wall Health: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(1).getDangerLevel()+"\nSniper Cannon: x" + sc2 + "\nPiercing Cannon: x"+pc2 + "\nWall Trap: x" + wt2 +"\nVolleySpread: x" +vs2);
                    tooltip3.setText ("Wall Health: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(2).getDangerLevel()+"\nSniper Cannon: x" + sc3 + "\nPiercing Cannon: x"+pc3 + "\nWall Trap: x" + wt3 +"\nVolleySpread: x" +vs3);
                    tooltip4.setText ("Wall Health: "+battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(3).getDangerLevel()+"\nSniper Cannon: x" + sc4 + "\nPiercing Cannon: x"+pc4 + "\nWall Trap: x" + wt4 +"\nVolleySpread: x" +vs4);
                    tooltip5.setText ("Wall Health: "+battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(4).getDangerLevel()+"\nSniper Cannon: x" + sc5 + "\nPiercing Cannon: x"+pc5 + "\nWall Trap: x" + wt5 +"\nVolleySpread: x" +vs5);
                    if(battle.isGameOver()){
                    	transitionToGAMEOVER(primaryStage);
                    }
                } catch (InsufficientResourcesException e) {
                	rectangle4.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                	resourcess.setFill(Color.RED);             
                }
                catch(InvalidLaneException a){
                	Image crack = new Image(getClass().getResource("images/Cracked.png").toExternalForm());
                    ImagePattern imagePattern = new ImagePattern(crack);
                    rectangle4.setFill(imagePattern);

                }
            });
            rectangle5.setOnMouseClicked(event -> {
                try {
                    battle.purchaseWeapon(codes[0], battle.getOriginalLanes().get(4));
                    rectangle1.setFill(Color.TRANSPARENT);
                    rectangle2.setFill(Color.TRANSPARENT);
                    rectangle3.setFill(Color.TRANSPARENT);
                    rectangle4.setFill(Color.TRANSPARENT);
                    rectangle5.setFill(Color.TRANSPARENT);
                    resourcess.setFill(Color.WHITE); 
                    if(a)
                    	pc5++;
                    if(b)
                    	sc5++;
                    if(c)
                    	vs5++;
                    if(d)
                    	wt5++;
                    infoLabel.setText("");
                    titansEasy(anchor, battle);
                    phaseText.setText("Phase: "+battle.getBattlePhase());
                    scoreText.setText("Score: "+battle.getScore());
                    turnss.setText("Turn: "+battle.getNumberOfTurns());
                    resourcess.setText("Resources: "+battle.getResourcesGathered());
                    tooltip1.setText ("Wall Health: "+battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(0).getDangerLevel()+"\nSniper Cannon: x" + sc1 + "\nPiercing Cannon: x"+pc1 + "\nWall Trap: x" + wt1 +"\nVolleySpread: x" +vs1);
                    tooltip2.setText ("Wall Health: "+battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(1).getDangerLevel()+"\nSniper Cannon: x" + sc2 + "\nPiercing Cannon: x"+pc2 + "\nWall Trap: x" + wt2 +"\nVolleySpread: x" +vs2);
                    tooltip3.setText ("Wall Health: "+battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(2).getDangerLevel()+"\nSniper Cannon: x" + sc3 + "\nPiercing Cannon: x"+pc3 + "\nWall Trap: x" + wt3 +"\nVolleySpread: x" +vs3);
                    tooltip4.setText ("Wall Health: "+battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(3).getDangerLevel()+"\nSniper Cannon: x" + sc4 + "\nPiercing Cannon: x"+pc4 + "\nWall Trap: x" + wt4 +"\nVolleySpread: x" +vs4);
                    tooltip5.setText ("Wall Health: "+battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()+"\nDanger Level: " + battle.getOriginalLanes().get(4).getDangerLevel()+"\nSniper Cannon: x" + sc5 + "\nPiercing Cannon: x"+pc5 + "\nWall Trap: x" + wt5 +"\nVolleySpread: x" +vs5);
                    if(battle.isGameOver()){
                    	transitionToGAMEOVER(primaryStage);
                    }
                } catch (InsufficientResourcesException e) {
                	rectangle5.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                	resourcess.setFill(Color.RED);             
                }
                catch(InvalidLaneException a){
                	Image crack = new Image(getClass().getResource("images/Cracked.png").toExternalForm());
                    ImagePattern imagePattern = new ImagePattern(crack);
                    rectangle5.setFill(imagePattern);

                }
            });
            
        }
    }
	
	
	public void titansEasy(AnchorPane ap, Battle battle) {
    	while(!titans.isEmpty()){
    		ap.getChildren().remove(titans.remove(0));
    	}
        for (Lane l : battle.getOriginalLanes()) {
            int i = battle.getOriginalLanes().indexOf(l);
            for (Titan t : l.getTitans()) {
                ImageView img = new ImageView(t instanceof PureTitan ? gifImage1 : t instanceof AbnormalTitan ? abnormal : t instanceof ArmoredTitan ? gifImage11 : gifImage);
                img.setPreserveRatio(true);
                img.setFitHeight(height/14.4);
                img.setFitWidth(width/25.6);
                ProgressBar healthBar = new ProgressBar((double)t.getCurrentHealth()/t.getBaseHealth());
                
                healthBar.setPrefWidth(width/20.21052632); // Set the preferred width of the health bar
                healthBar.setMinHeight(height/67.5); // Minimum height
                healthBar.setPrefHeight(height/67.5); // Preferred height
                healthBar.setMaxHeight(height/67.5); // Maximum height
                
                double a = width/1600;
                double b = diagonal/275.3633963;
                healthBar.setStyle(
                        "-fx-accent: red;" +
                        "-fx-background-insets: 0;" +
                        "-fx-padding: 0;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: "+a+";" +
                        "-fx-border-radius: "+b+";" +
                        "-fx-background-radius: "+b+";" +
                        "-fx-background-color: black, linear-gradient(to bottom, transparent 50%, black 50%);" // Ensure the background is fully black
                    );
                if(t instanceof PureTitan){
                	healthBar.setTranslateX(width/-384);
                }
                if(t instanceof AbnormalTitan){
                	healthBar.setTranslateX(width/-128);
                }
                if(t instanceof ArmoredTitan){
                	healthBar.setTranslateX(width/-384);
                }
                if(t instanceof ColossalTitan){
                	healthBar.setTranslateX(width/-640);
                }
                
                VBox v = new VBox(img,healthBar);
                fixPosition(v, i);
                AnchorPane.setLeftAnchor(v,(width/12.8)+ (width/213.3333333)*t.getDistance());
                ap.getChildren().add(v);
                titans.add(v);
            }
        }
        
    }

    public void fixPosition(VBox n, int i) {
        double topAnchor = (height/3.375) + (i * (height/10.8)); // Calculate position based on lane index
        AnchorPane.setTopAnchor(n, topAnchor);
    }
    
    public Scene getScene(){
    	return scene;
    }
    
    private boolean isRedWithOpacity(Rectangle rect) {
        Color color = (Color) rect.getFill();
        return color.equals(Color.RED.deriveColor(0, 1, 1, 0.5));
    }
    
    private void transitionToGAMEOVER(Stage primaryStage) {
        GAMEOVER gameover = new GAMEOVER();
        gameover.setScore(battle.getScore());
        gameover.setMusic(backgroundMediaPlayer);
        gameover.start(primaryStage);

        Scene newScene = gameover.getScene();
        
        StackPane newRoot = (StackPane) newScene.getRoot();
        newRoot.setOpacity(0); // Set initial opacity to 0

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
        fadeTransition.setToValue(1); // Set final opacity to 1
        fadeTransition.play();
        

        primaryStage.setScene(newScene);
     // Center the stage on the screen
        primaryStage.centerOnScreen();
        
}
    
    public int getScore(){
    	return battle.getScore();
    }
    
    // Function to check if the Rectangle's fill is a specific Image
    private boolean isRectangleCracked(Rectangle rectangle) {
        if (rectangle.getFill() instanceof ImagePattern) {
            ImagePattern imagePattern2 = (ImagePattern) rectangle.getFill();
            return imagePattern2.getImage().equals(crack);
        }
        return false;
    }

	
	public static void main(String[] args){
		System.setProperty("prism.allowhidpi", "false"); // Disable high DPI scaling for JavaFX
		System.setProperty("glass.gtk.uiScale", "1.0");
		launch(args);
	}
}




