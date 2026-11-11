package game.gui;

import java.awt.Dimension;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;


public class Rules extends Application {

    private MediaPlayer backgroundMediaPlayer;
    private Scene scene;
    private int textIndex = 0;
    private int weaponIndex = 0;
    private int titanIndex = 0;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    private ImageView imageView11;
    private ImageView imageView12;
    private ImageView imageView13;
    private ImageView imageView14;
    private ImageView imageView15;
    private ImageView imageView16;
    private Button button;
    private ImageView cursorView;
    private ImageView cursorView2;
    private ImageView cursorView3;
    private VBox bulletPointsBox;
    private boolean tutorialStart = true;
    private boolean weaponStart = false;
    private boolean titanStart = false;
    private Text tutorialText;
    private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private double width = screenSize.getWidth();
	private double height = screenSize.getHeight();
	private double diagonal = Math.sqrt((height*height)+(width*width));
    private boolean isEnterDisabled = false;  // Flag to track if Enter key is disabled


    private String[] tutorialTexts = {
        "Welcome, brave defender, to the Utopia District. The titans are on the move,\n" +
        "and we need your strategic mind to protect these walls. Let me guide you\nthrough the essentials.",
        "Your primary objective is to protect these walls from the attacking titans.\nTitans will spawn on the right and move towards the walls each turn. If they\nreach the wall, they will damage it. Defend all lanes, for if they fall, it's game\nover.",
        "Keep an eye on the top right corner. Here, you'll see your available resources,\nnumber of turns, score, and the current phase of battle. Stay informed to\nmake the best decisions.",
        "Hover over a lane for 1.5 seconds to get detailed information about it: wall\nhealth, danger level, and the number of weapons deployed. This will help you\nplan your defenses effectively.",
        "On the top left, you'll find the weapon shop. Click on a weapon to see its\ndetails. Each weapon has a cost, so make sure you have enough resources\nbefore deploying.",
        "To deploy a weapon, click on it in the shop, then click on the highlighted lane.\nIf you have enough resources, the weapon will be deployed. You can only\npurchase and deploy one weapon per turn.",
        "If you don't have enough resources, the lanes and resources will turn red,\nindicating you need more resources.",
        "Each turn, you can either purchase and deploy one weapon or pass your turn.\nAfter your action, the turn ends and titans will move one step closer to the\nwalls. Plan your moves carefully, as every decision matters.",
        "The battle gets tougher with each phase: Early, Intense, and Grumbling. Stay\nvigilant and adapt your tactics as the waves of titans grow stronger.",
        "Defeating titans grants resources and increases your score. Use these\nresources to strengthen your defenses and keep the titans at bay.",
        "Manage your resources wisely, deploy weapons strategically, and monitor\nlane status constantly. Your vigilance and strategy will be the key to\ndefending Utopia District.",
        "Now, brave defender, the fate of Utopia District is in your hands. Stand firm,\nstrategize, and let no titan breach these walls. Good luck!",
        ""
    };
    
    private String[] weaponTexts = {
    	    "Defender, our arsenal is the key to holding back the titan onslaught. Let me\nintroduce you to the different types of weapons at your disposal and their\nunique properties.",
    	    "First, we have the Piercing Cannon. This weapon costs 25 resources and\ndeals 10 damage. It targets the closest 5 titans to the wall in its lane,\nmaking it effective against clustered enemies.",
    	    "Next, the Sniper Cannon. For 25 resources, this powerful weapon deals 35\ndamage. It targets the first closest titan to the wall in its lane, perfect\nfor picking off high-priority threats.",
    	    "The Volley Spread Cannon is a versatile weapon costing 100 resources and\ndealing 5 damage. It attacks all titans within its range, from 20 to 50\ndistance units. This makes it ideal for controlling the battlefield.",
    	    "Finally, we have the Wall Trap. This proximity weapon costs 75 resources\nand deals a massive 100 damage. It only activates when a titan reaches the\nwall, providing a last line of defense.",
    	    "Each weapon has its unique strengths and is best used in different\nsituations. Deploy them strategically to maximize their effectiveness and\nkeep the titans at bay.",
    	    "Remember, you can only purchase and deploy one weapon per turn. Choose\nwisely and adapt to the situation on the battlefield. Your strategic\ndeployment of these weapons will be crucial in defending Utopia District.",
    	    "Now, go forth and arm yourself with this knowledge. Use these weapons\nwisely, and may they serve you well in our defense against the titans. Good\nluck, defender!",
    	    ""
    	};
    
    private String[] titanTexts = {
    		"Defender, our enemy is relentless and varied. Understanding the different\ntypes of titans you will face is crucial for our defense. Let me introduce\nyou to these fearsome adversaries and their unique properties.",
    		"First, we have the Pure Titan. These are the most basic titans with 100 HP,\ndealing 15 damage per attack. They move at a speed of 10 distance units per\nturn. Though they are not particularly strong, they can overwhelm in numbers.",
    		"Next, the Abnormal Titan. These titans are unpredictable, performing their\nattack action twice per turn. They have 100 HP and deal 20 damage per\nattack with a speed of 15 distance units per turn. Their erratic behavior\nmakes them a significant threat.",
    		"The Armored Titan is a formidable foe, taking only 25% of the damage from\nattacks. With 200 HP and 85 damage per attack, they are slow with a speed\nof 10 distance units per turn. Their resilience can make them challenging\nto defeat.",
    		"Finally, the Colossal Titan. These giants have 1000 HP and deal a devastating\n100 damage per attack. They start slow with a speed of 5 distance units\nper turn but increase their speed by 1 unit after every movement. Their\nsheer size and power make them a dire threat.",
    		"Each type of titan requires a different strategy. Pure Titans can be\noverwhelmed with numbers, Abnormal Titans need to be taken down quickly,\nArmored Titans require heavy firepower, and Colossal Titans demand constant\npressure due to their growing speed.",
    		"Adapt your defenses to counter the specific threats each titan presents.\nStay alert and manage your resources wisely to deploy the right weapons at\nthe right time. Understanding your enemy is the first step to victory.",
    		"Now, with this knowledge, face the titans with confidence. Defend the walls\nof Utopia District and let no titan breach our defenses. Good luck, defender!",
    		""
    };

    @Override
    public void start(Stage primaryStage) {
    	disableEnterKeyForSeconds(2);
    	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double diagonal = Math.sqrt((height*height)+(width*width));
        // Create a button
        button = new Button("Skip");

        // Set the font to Arial Black with a larger font size and make it bold
        button.setFont(Font.font("Arial Black", FontWeight.BOLD, height/49.09090909));

        // Set preferred width and height of the button
        button.setPrefWidth(width/10.66666667); // Set the preferred width
        button.setPrefHeight(height/21.6); // Set the preferred height
        
        button.setTranslateX(width/3.147540984);
        button.setTranslateY(height/-2.634146341);
        
        double x = diagonal/88.1162868;
        double y = width/600;
        double z=height/108;
        double w=width/96;
        

        // Style the button using CSS
        button.setStyle(
            "-fx-background-color: #090909;" +
            "-fx-background-radius: "+x+"px;" +
            "-fx-border-radius: "+x+"px;" +
            "-fx-border-color: #263d3a;" +
            "-fx-border-width: "+y+"px;" +
            "-fx-text-fill: white;" +
            "-fx-padding: "+z+"px "+w+"px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 5, 0, 0, 1);"
        );

        // Create hover animation
        button.setOnMouseEntered(event -> {
            Timeline hoverTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleXProperty(), 1)),
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleYProperty(), 1)),
                new KeyFrame(Duration.ZERO, new KeyValue(button.styleProperty(), button.getStyle())),
                new KeyFrame(Duration.millis(200), new KeyValue(button.scaleXProperty(), 1.1)),
                new KeyFrame(Duration.millis(200), new KeyValue(button.scaleYProperty(), 1.1)),
                new KeyFrame(Duration.millis(200), new KeyValue(button.styleProperty(), 
                    button.getStyle() + "-fx-background-color: #1d1d1d;"))
            );
            hoverTimeline.play();
        });

        button.setOnMouseExited(event -> {
            Timeline hoverTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleXProperty(), 1.1)),
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleYProperty(), 1.1)),
                new KeyFrame(Duration.ZERO, new KeyValue(button.styleProperty(), 
                    button.getStyle() + "-fx-background-color: #1d1d1d;")),
                new KeyFrame(Duration.millis(200), new KeyValue(button.scaleXProperty(), 1)),
                new KeyFrame(Duration.millis(200), new KeyValue(button.scaleYProperty(), 1)),
                new KeyFrame(Duration.millis(200), new KeyValue(button.styleProperty(), 
                    button.getStyle() + "-fx-background-color: #090909;"))
            );
            hoverTimeline.play();
        });

        // Create click animation
        button.setOnMousePressed(event -> {
            Timeline clickTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleXProperty(), 1)),
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleYProperty(), 1)),
                new KeyFrame(Duration.millis(100), new KeyValue(button.scaleXProperty(), 0.95)),
                new KeyFrame(Duration.millis(100), new KeyValue(button.scaleYProperty(), 0.95))
            );
            clickTimeline.play();
        });

        button.setOnMouseReleased(event -> {
            Timeline clickTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleXProperty(), 0.95)),
                new KeyFrame(Duration.ZERO, new KeyValue(button.scaleYProperty(), 0.95)),
                new KeyFrame(Duration.millis(100), new KeyValue(button.scaleXProperty(), 1)),
                new KeyFrame(Duration.millis(100), new KeyValue(button.scaleYProperty(), 1))
            );
            clickTimeline.play();
        });
        
        button.setOnAction(event -> transitionToModee(primaryStage));
        
        String cursorImagePath = "/game/gui/cursor/cursorHover2-ezgif.com-resize.gif";
        Image cursorImage = new Image(getClass().getResource(cursorImagePath).toString());
        cursorView = new ImageView(cursorImage);
        cursorView.setFitWidth(width/27.42857143);
        cursorView.setFitHeight(height/15.42857143);
        cursorView.setTranslateX(width/6.193548387);
        cursorView.setTranslateY(height/-3.176470588);
        cursorView.setOpacity(0);
        
        cursorView2 = new ImageView(cursorImage);
        cursorView2.setFitWidth(width/27.42857143);
        cursorView2.setFitHeight(height/15.42857143);
        cursorView2.setTranslateX(width/-7.836734694);
        cursorView2.setTranslateY(height/-15.88235294);
        cursorView2.setOpacity(0);
        
        cursorView3 = new ImageView(cursorImage);
        cursorView3.setFitWidth(width/27.42857143);
        cursorView3.setFitHeight(height/15.42857143);
        cursorView3.setTranslateX(width/-3.764705882);
        cursorView3.setTranslateY(height/-4.537815126);
        cursorView3.setOpacity(0);
        
        

        // Create an image view and load the image
        String imagePath = "images/background4.jpg";
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        
        String newImagePath = "images/dungeon.jpg";
        Image newImage = new Image(getClass().getResource(newImagePath).toExternalForm());
        ImageView imageView = new ImageView(newImage);
        
        imageView.setFitWidth(width/1.2); // Set the desired width
        imageView.setFitHeight(height/1.336633663); // Set the desired height

        imageView.setPreserveRatio(true);
        
        String newImagePath2 = "images/CharMain.png";
        Image newImage2 = new Image(getClass().getResource(newImagePath2).toExternalForm());
        imageView2 = new ImageView(newImage2);
        
        imageView2.setFitWidth(width/4.8); // Set the desired width
        imageView2.setFitHeight(height/2.7); // Set the desired height

        imageView2.setPreserveRatio(true);
        
        imageView2.setTranslateY(height/12.55813953);
        imageView2.setMouseTransparent(true);
        
        
        String ImagePath3 = "images/Newest arrow GIF (2).gif";
        Image Image3 = new Image(getClass().getResource(ImagePath3).toExternalForm());
        imageView3 = new ImageView(Image3);
        imageView3.setPreserveRatio(true);
        imageView3.setFitWidth(width/19.2); // Set the desired width
        imageView3.setFitHeight(height/10.8);
        imageView3.setTranslateY(height/2.571428571);
        imageView3.setTranslateX(width/5.485714286);
        
        imageView3.setMouseTransparent(true);
        
        String ImagePath4 = "images/Scene1.png";
        Image Image4 = new Image(getClass().getResource(ImagePath4).toExternalForm());
        imageView4 = new ImageView(Image4);
        imageView4.setFitWidth(width/1.2); // Set the desired width
        imageView4.setFitHeight(height/1.336633663); // Set the desired height
        imageView4.setPreserveRatio(true);
        imageView4.setOpacity(0);
        
        String ImagePath5 = "images/Scene2.png";
        Image Image5 = new Image(getClass().getResource(ImagePath5).toExternalForm());
        imageView5 = new ImageView(Image5);
        imageView5.setFitWidth(width/1.2); // Set the desired width
        imageView5.setFitHeight(height/1.336633663); // Set the desired height
        imageView5.setPreserveRatio(true);
        imageView5.setOpacity(0);
        
        String ImagePath6 = "images/Scene3.png";
        Image Image6 = new Image(getClass().getResource(ImagePath6).toExternalForm());
        imageView6 = new ImageView(Image6);
        imageView6.setFitWidth(width/1.2); // Set the desired width
        imageView6.setFitHeight(height/1.336633663); // Set the desired height
        imageView6.setPreserveRatio(true);
        imageView6.setOpacity(0);
        
        String ImagePath7 = "images/Scene4.png";
        Image Image7 = new Image(getClass().getResource(ImagePath7).toExternalForm());
        imageView7 = new ImageView(Image7);
        imageView7.setFitWidth(width/1.2); // Set the desired width
        imageView7.setFitHeight(height/1.336633663); // Set the desired height
        imageView7.setPreserveRatio(true);
        imageView7.setOpacity(0);
        
        String ImagePath8 = "images/CharWeapon.png";
        Image Image8 = new Image(getClass().getResource(ImagePath8).toExternalForm());
        imageView8 = new ImageView(Image8);
        imageView8.setFitWidth(width/4.8); // Set the desired width
        imageView8.setFitHeight(height/2.7); // Set the desired height
        imageView8.setPreserveRatio(true);
        imageView8.setTranslateY(height/12.55813953);
        imageView8.setMouseTransparent(true);
        imageView8.setTranslateX(width/-2.021052632);
        
        String ImagePath9 = "images/Scene5.png";
        Image Image9 = new Image(getClass().getResource(ImagePath9).toExternalForm());
        imageView9 = new ImageView(Image9);
        imageView9.setFitWidth(width/1.2); // Set the desired width
        imageView9.setFitHeight(height/1.336633663); // Set the desired height
        imageView9.setPreserveRatio(true);
        imageView9.setOpacity(0);
        
        String ImagePath10 = "images/Scene6.png";
        Image Image10 = new Image(getClass().getResource(ImagePath10).toExternalForm());
        imageView10 = new ImageView(Image10);
        imageView10.setFitWidth(width/1.2); // Set the desired width
        imageView10.setFitHeight(height/1.336633663); // Set the desired height
        imageView10.setPreserveRatio(true);
        imageView10.setOpacity(0);
        
        String ImagePath11 = "images/Scene7.png";
        Image Image11 = new Image(getClass().getResource(ImagePath11).toExternalForm());
        imageView11 = new ImageView(Image11);
        imageView11.setFitWidth(width/1.2); // Set the desired width
        imageView11.setFitHeight(height/1.336633663); // Set the desired height
        imageView11.setPreserveRatio(true);
        imageView11.setOpacity(0);
        
        String ImagePath12 = "images/CharTitan.png";
        Image Image12 = new Image(getClass().getResource(ImagePath12).toExternalForm());
        imageView12 = new ImageView(Image12);
        imageView12.setFitWidth(width/4.8); // Set the desired width
        imageView12.setFitHeight(height/2.7); // Set the desired height
        imageView12.setPreserveRatio(true);
        imageView12.setTranslateY(height/12.55813953);
        imageView12.setMouseTransparent(true);
        imageView12.setTranslateX(width/-2.021052632);
        
        String ImagePath13 = "images/PureTitanFinal.png";
        Image Image13 = new Image(getClass().getResource(ImagePath13).toExternalForm());
        imageView13 = new ImageView(Image13);
        imageView13.setFitWidth(width/9.6); // Set the desired width
        imageView13.setFitHeight(height/5.4); // Set the desired height
        imageView13.setPreserveRatio(true);
        imageView13.setTranslateX(width/4.8);
        imageView13.setTranslateY(height/-5.4);
        imageView13.setOpacity(0);
        
        String ImagePath14 = "images/AbnormalTitanFinal.png";
        Image Image14 = new Image(getClass().getResource(ImagePath14).toExternalForm());
        imageView14 = new ImageView(Image14);
        imageView14.setFitWidth(width/9.6); // Set the desired width
        imageView14.setFitHeight(height/5.4); // Set the desired height
        imageView14.setPreserveRatio(true);
        imageView14.setTranslateX(width/4.8);
        imageView14.setTranslateY(height/-5.4);
        imageView14.setOpacity(0);
        
        String ImagePath15 = "images/ArmouredTitanFinal.png";
        Image Image15 = new Image(getClass().getResource(ImagePath15).toExternalForm());
        imageView15 = new ImageView(Image15);
        imageView15.setFitWidth(width/9.6); // Set the desired width
        imageView15.setFitHeight(height/5.4); // Set the desired height
        imageView15.setPreserveRatio(true);
        imageView15.setTranslateX(width/4.8);
        imageView15.setTranslateY(height/-5.4);
        imageView15.setOpacity(0);

        String ImagePath16 = "images/ColossalTitanFinal.png";
        Image Image16 = new Image(getClass().getResource(ImagePath16).toExternalForm());
        imageView16 = new ImageView(Image16);
        imageView16.setFitWidth(width/9.6); // Set the desired width
        imageView16.setFitHeight(height/5.4); // Set the desired height
        imageView16.setPreserveRatio(true);
        imageView16.setTranslateX(width/4.8);
        imageView16.setTranslateY(height/-5.4);
        imageView16.setOpacity(0);
        
        
        Button b = new Button();
        b.setFont(Font.font("Arial Black", FontWeight.BOLD, height/49.09090909));

        // Set preferred width and height of the button
        b.setPrefWidth(width/2.181818182); // Set the preferred width
        b.setPrefHeight(height/6.352941176); // Set the preferred height
        
        double a = width/480;

        // Style the button using CSS
        b.setStyle(
            "-fx-background-color: rgba(9, 9, 9, 0.9);" +
            "-fx-background-radius: "+x+"px;" +
            "-fx-border-radius: "+x+"px;" +
            "-fx-border-color: #263d3a;" +
            "-fx-border-width: "+a+"px;" +
            "-fx-text-fill: white;" +
            "-fx-padding: "+z+"px "+w+"px;;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 5, 0, 0, 1);"
        );
        
        b.setTranslateY(height/2.918918919);
        b.setMouseTransparent(true);
        
       /* Text welcomeText = new Text(
            "Welcome, brave defender, to the Utopia District. The titans are on the move,\n" +
            "and we need your strategic mind to protect these walls. Let me guide you\nthrough the essentials."
        );

        // Set the font, size, and style of the text
        welcomeText.setFont(Font.font("Arial Black", FontWeight.BOLD, 20));
        welcomeText.setFill(Color.WHITE);

        // Set text alignment to center
        welcomeText.setTextAlignment(TextAlignment.LEFT);
        welcomeText.setTranslateY(365);
        welcomeText.setTranslateX(5);*/
        
        tutorialText = new Text(tutorialTexts[textIndex]);

        // Set the font, size, and style of the text
        tutorialText.setFont(Font.font("Arial Black", FontWeight.BOLD, height/54));
        tutorialText.setFill(Color.WHITE);

        // Set text alignment to center
        tutorialText.setTextAlignment(TextAlignment.LEFT);
        tutorialText.setTranslateY(height/2.95890411);
        tutorialText.setTranslateX(width/384);

        // Create a TextFlow to contain the tutorial text
        TextFlow textFlow = new TextFlow(tutorialText);
        textFlow.setMaxWidth(width/1.92); // Set the maximum width of the text area
        textFlow.setTranslateY(height/2.4);
        textFlow.setTranslateX(width/27.42857143);

        
        Text weaponText = new Text(weaponTexts[weaponIndex]);
        weaponText.setFont(Font.font("Arial Black", FontWeight.BOLD, height/54));
        weaponText.setFill(Color.WHITE);
        weaponText.setTextAlignment(TextAlignment.LEFT);
        weaponText.setTranslateY(height/2.95890411);
        weaponText.setTranslateX(width/384);
        TextFlow textFlow2 = new TextFlow(weaponText);
        textFlow2.setMaxWidth(width/1.92);
        textFlow2.setTranslateY(height/2.4);
        textFlow2.setTranslateX(width/27.42857143);
        textFlow2.setOpacity(0);
        
        
        Text titanText = new Text(titanTexts[titanIndex]);
        titanText.setFont(Font.font("Arial Black", FontWeight.BOLD, height/54));
        titanText.setFill(Color.WHITE);
        titanText.setTextAlignment(TextAlignment.LEFT);
        titanText.setTranslateY(height/2.95890411);
        titanText.setTranslateX(width/384);
        TextFlow textFlow3 = new TextFlow(titanText);
        textFlow3.setMaxWidth(width/1.92);
        textFlow3.setTranslateY(height/2.4);
        textFlow3.setTranslateX(width/27.42857143);
        textFlow3.setOpacity(0);
        
            

     // Create bullet points
        Text bulletPoint1 = createBulletPoint("• Repeat the Instructions again.", () -> {
            textIndex = 0;
            weaponIndex=0;
            tutorialStart=true;
            weaponStart=false;
            titanStart=false;
            
            // Execute the TranslateTransition
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageView2);
            translateTransition.setByX(width/2.021052632); // Adjust the value as needed
            translateTransition.play();
            
            // Smoothly set the opacity of all bullet points to zero
            for (int i = 0; i < bulletPointsBox.getChildren().size(); i++) {
                Text bulletPoint = (Text) bulletPointsBox.getChildren().get(i);
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), bulletPoint);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.play();
            }
            
            bulletPointsBox.setMouseTransparent(true);
            cursorView2.setTranslateX(width/-7.836734694);
            cursorView2.setTranslateY(height/-15.88235294);
            
            tutorialText.setText(tutorialTexts[textIndex]);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), tutorialText);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
            
            
        });
        Text bulletPoint2 = createBulletPoint("• Tell me about the Titans.", () -> {
        	titanIndex=0;
        	tutorialStart=false;
            weaponStart=false;
            titanStart=true;
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), textFlow3);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
            for (int i = 0; i < bulletPointsBox.getChildren().size(); i++) {
                Text bulletPoint = (Text) bulletPointsBox.getChildren().get(i);
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), bulletPoint);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.play();
            }
            bulletPointsBox.setMouseTransparent(true);
            FadeTransition imageFadeIn5 = new FadeTransition(Duration.seconds(1), imageView3);
            imageFadeIn5.setFromValue(0.0);
            imageFadeIn5.setToValue(1.0);
            imageFadeIn5.play();
            
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageView12);
            translateTransition.setByX(width/2.021052632); // Adjust the value as needed
            translateTransition.play();
            
            tutorialText.setText(titanTexts[titanIndex]);
            FadeTransition fadeIn5 = new FadeTransition(Duration.seconds(2), tutorialText);
            fadeIn5.setFromValue(0.0);
            fadeIn5.setToValue(1.0);
            fadeIn5.play();
            
            
        });        
        Text bulletPoint3 = createBulletPoint("• Tell me about the Weapons.", () -> { 
        	
        	 weaponIndex=0;
        	 tutorialStart=false;
             weaponStart=true;
             titanStart=false;
             FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), textFlow2);
             fadeIn.setFromValue(0.0);
             fadeIn.setToValue(1.0);
             fadeIn.play();
             for (int i = 0; i < bulletPointsBox.getChildren().size(); i++) {
                 Text bulletPoint = (Text) bulletPointsBox.getChildren().get(i);
                 FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), bulletPoint);
                 fadeOut.setFromValue(1);
                 fadeOut.setToValue(0);
                 fadeOut.play();
             }
             
             cursorView3.setTranslateX(width/-3.764705882);
             cursorView3.setTranslateY(height/-4.537815126);
             bulletPointsBox.setMouseTransparent(true);
             FadeTransition imageFadeIn5 = new FadeTransition(Duration.seconds(1), imageView3);
             imageFadeIn5.setFromValue(0.0);
             imageFadeIn5.setToValue(1.0);
             imageFadeIn5.play();
             
             TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageView8);
             translateTransition.setByX(width/2.021052632); // Adjust the value as needed
             translateTransition.play();
             
             tutorialText.setText(weaponTexts[weaponIndex]);
             FadeTransition fadeIn5 = new FadeTransition(Duration.seconds(2), tutorialText);
             fadeIn5.setFromValue(0.0);
             fadeIn5.setToValue(1.0);
             fadeIn5.play();
             
        	
        });
        Text bulletPoint4 = createBulletPoint("• I am ready to play.", () -> transitionToModee(primaryStage));

        // Set initial opacity to zero
        bulletPoint1.setOpacity(0);
        bulletPoint2.setOpacity(0);
        bulletPoint3.setOpacity(0);
        bulletPoint4.setOpacity(0);

        // Create a VBox for bullet points
        bulletPointsBox = new VBox(height/135, bulletPoint1, bulletPoint2, bulletPoint3, bulletPoint4);
        bulletPointsBox.setTranslateY(height/1.35);
        bulletPointsBox.setTranslateX(width/3.84);
        bulletPointsBox.setMouseTransparent(true);
        
        
        
        // Create a layout and add the image and button to it
        StackPane root = new StackPane(imageView, imageView4,imageView5,imageView6,imageView7,imageView9,imageView10,imageView11,button, b, imageView2,imageView8,imageView12,imageView13,imageView14,imageView15,imageView16, textFlow,textFlow2,textFlow3, imageView3,cursorView,cursorView2,cursorView3,bulletPointsBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black;"); // Set the background color to black for visibility
        
        // Create a scene and add the layout to the scene
        scene = new Scene(root, width/1.2, height/1.08);
        
        
        // Set the scene to the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Styled Button Example");
        primaryStage.show();
        
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.setVolume(0.2); // Set to 20% volume
        }
        
        // Event handler for mouse click and enter key
        scene.setOnMouseClicked(event -> {
        	if(!isEnterDisabled){
	        	updateTutorialText(tutorialText);
	        	updateWeaponText(weaponText);
	        	updateTitanText(titanText);
        	}
        });
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !isEnterDisabled) {
                updateTutorialText(tutorialText);
            	updateWeaponText(weaponText);
            	updateTitanText(titanText);
            }
        });
    }
    
    private void disableEnterKeyForSeconds(int seconds) {
        isEnterDisabled = true;  // Disable Enter key
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> isEnterDisabled = false);  // Re-enable after delay
        pause.play();
    }
    
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.backgroundMediaPlayer = mediaPlayer;
    }

    public Scene getScene() {
        return scene;
    }
    
    private void updateTutorialText(Text tutorialText) {
        if (textIndex < 12 && tutorialStart) {
        	disableEnterKeyForSeconds(2);
        	if (textIndex == 0) {
                FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView4);
                imageFadeIn.setFromValue(0.0);
                imageFadeIn.setToValue(1.0);
                imageFadeIn.play();
            }
        	 if (textIndex == 1) {
                 FadeTransition buttonFadeOut = new FadeTransition(Duration.seconds(1), button);
                 buttonFadeOut.setFromValue(1.0);
                 buttonFadeOut.setToValue(0.0);
                 buttonFadeOut.play();
                 FadeTransition cursorFadeIn = new FadeTransition(Duration.seconds(1), cursorView);
                 cursorFadeIn.setFromValue(0.0);
                 cursorFadeIn.setToValue(1.0);
                 cursorFadeIn.play();
                 
                 // Create a Timeline for the cursor movement
                 Timeline cursorTimeline = new Timeline(
                     new KeyFrame(Duration.ZERO, new KeyValue(cursorView.translateXProperty(), cursorView.getTranslateX())),
                     new KeyFrame(Duration.seconds(1.5), new KeyValue(cursorView.translateXProperty(), cursorView.getTranslateX() + (width/4.8))),
                     new KeyFrame(Duration.seconds(3), new KeyValue(cursorView.translateXProperty(), cursorView.getTranslateX()))
                 );
                 cursorTimeline.setCycleCount(Animation.INDEFINITE);
                 cursorTimeline.setAutoReverse(true);
                 cursorTimeline.play();
        
             }
        	 if(textIndex == 2){
        		 FadeTransition buttonFadeOut = new FadeTransition(Duration.seconds(1), button);
                 buttonFadeOut.setFromValue(0.0);
                 buttonFadeOut.setToValue(1.0);
                 buttonFadeOut.play();
                 FadeTransition cursorFadeIn = new FadeTransition(Duration.seconds(1), cursorView);
                 cursorFadeIn.setFromValue(1.0);
                 cursorFadeIn.setToValue(0.0);
                 cursorFadeIn.play();
                 FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView5);
                 imageFadeIn.setFromValue(0.0);
                 imageFadeIn.setToValue(1.0);
                 imageFadeIn.play();
                 FadeTransition cursorFadeIn2 = new FadeTransition(Duration.seconds(1), cursorView2);
                 cursorFadeIn2.setFromValue(0.0);
                 cursorFadeIn2.setToValue(1.0);
                 cursorFadeIn2.play();
        	 }
        	 if(textIndex==3){
        		 FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView6);
                 imageFadeIn.setFromValue(0.0);
                 imageFadeIn.setToValue(1.0);
                 imageFadeIn.play();
                 
              // Move cursorView2 vertically up
                 TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), cursorView2);
                 translateTransition.setByY(height/-5.4); // Adjust the value as needed
                 translateTransition.play();
        	 }
        	 if(textIndex==5){
             	FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView7);
                 imageFadeIn.setFromValue(0.0);
                 imageFadeIn.setToValue(1.0);
                 imageFadeIn.play();
                 
                 cursorView.setTranslateX(width/5.485714286);
                 FadeTransition cursorFadeIn = new FadeTransition(Duration.seconds(1), cursorView);
                 cursorFadeIn.setFromValue(0.0);
                 cursorFadeIn.setToValue(1.0);
                 cursorFadeIn.play();
                 
                 // Create a Timeline for the cursor movement
                 Timeline cursorTimeline = new Timeline(
                     new KeyFrame(Duration.ZERO, new KeyValue(cursorView.translateXProperty(), cursorView.getTranslateX())),
                     new KeyFrame(Duration.seconds(1), new KeyValue(cursorView.translateXProperty(), cursorView.getTranslateX() + (width/19.2))),
                     new KeyFrame(Duration.seconds(2), new KeyValue(cursorView.translateXProperty(), cursorView.getTranslateX()))
                 );
                 cursorTimeline.setCycleCount(Animation.INDEFINITE);
                 cursorTimeline.setAutoReverse(true);
                 cursorTimeline.play();
             }
        	 if(textIndex==6){
        		 FadeTransition cursorFadeIn = new FadeTransition(Duration.seconds(1), cursorView);
                 cursorFadeIn.setFromValue(1.0);
                 cursorFadeIn.setToValue(0.0);
                 cursorFadeIn.play();
                 
                 FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView7);
                 imageFadeIn.setFromValue(1.0);
                 imageFadeIn.setToValue(0.0);
                 imageFadeIn.play();
                 
                 TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), cursorView2);
                 translateTransition.setByX(width/-5.485714286); // Adjust the value as needed
                 translateTransition.play();
                 
                 TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), cursorView2);
                 translateTransition2.setByY(height/20.76923077); // Adjust the value as needed
                 translateTransition2.play();
                 
                 
        	 }
        	 if(textIndex==7){
        		 FadeTransition cursorFadeIn = new FadeTransition(Duration.seconds(1), cursorView2);
                 cursorFadeIn.setFromValue(1.0);
                 cursorFadeIn.setToValue(0.0);
                 cursorFadeIn.play();
                 
                 imageView5.setOpacity(0);
        	 }
        	if(textIndex==11){
        		FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView6);
                imageFadeIn.setFromValue(1.0);
                imageFadeIn.setToValue(0.0);
                imageFadeIn.play();
        		
        		FadeTransition imageFadeIn2 = new FadeTransition(Duration.seconds(1), imageView);
                imageFadeIn2.setFromValue(0.0);
                imageFadeIn2.setToValue(1.0);
                imageFadeIn2.play();
                
                /*FadeTransition imageFadeIn3 = new FadeTransition(Duration.seconds(1), imageView5);
                imageFadeIn3.setFromValue(1.0);
                imageFadeIn3.setToValue(0.0);
                imageFadeIn3.play();*/
                
                FadeTransition imageFadeIn4 = new FadeTransition(Duration.seconds(1), imageView4);
                imageFadeIn4.setFromValue(1.0);
                imageFadeIn4.setToValue(0.0);
                imageFadeIn4.play();
                
                FadeTransition imageFadeIn5 = new FadeTransition(Duration.seconds(1), imageView3);
                imageFadeIn5.setFromValue(1.0);
                imageFadeIn5.setToValue(0.0);
                imageFadeIn5.play();
        		
        		TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageView2);
                translateTransition.setByX(width/-2.021052632); // Adjust the value as needed
                translateTransition.play();
                
                // Fade in bullet points
                FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(0));
                fadeIn1.setFromValue(0);
                fadeIn1.setToValue(1);
                fadeIn1.play();

                FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(1));
                fadeIn2.setFromValue(0);
                fadeIn2.setToValue(1);
                fadeIn2.play();

                FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(2));
                fadeIn3.setFromValue(0);
                fadeIn3.setToValue(1);
                fadeIn3.play();

                FadeTransition fadeIn4 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(3));
                fadeIn4.setFromValue(0);
                fadeIn4.setToValue(1);
                fadeIn4.play();
                
                bulletPointsBox.setMouseTransparent(false);
                
        	}
            textIndex++;
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), tutorialText);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                tutorialText.setText(tutorialTexts[textIndex]);
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), tutorialText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
        }
       
    }
    
    private void updateWeaponText(Text weaponText){
    	if(weaponStart && weaponIndex<8){
    		disableEnterKeyForSeconds(2);
    		if(weaponIndex==0){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView9);
                imageFadeIn.setFromValue(0.0);
                imageFadeIn.setToValue(1.0);
                imageFadeIn.play();
                
                FadeTransition cursorFadeIn = new FadeTransition(Duration.seconds(1), cursorView3);
                cursorFadeIn.setFromValue(0.0);
                cursorFadeIn.setToValue(1.0);
                cursorFadeIn.play();
                
                tutorialText.setText("");
                FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), tutorialText);
                fadeIn2.setFromValue(1.0);
                fadeIn2.setToValue(0.0);
                fadeIn2.play();
    		}
    		if(weaponIndex==1){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView10);
                imageFadeIn.setFromValue(0.0);
                imageFadeIn.setToValue(1.0);
                imageFadeIn.play();
                
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), cursorView3);
                translateTransition.setByX(width/17.45454545); // Adjust the value as needed
                translateTransition.play();
    		}
    		if(weaponIndex==2){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView6);
                imageFadeIn.setFromValue(0.0);
                imageFadeIn.setToValue(1.0);
                imageFadeIn.play();
                
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), cursorView3);
                translateTransition.setByX(width/17.45454545); // Adjust the value as needed
                translateTransition.play();
    		}
    		if(weaponIndex==3){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView11);
                imageFadeIn.setFromValue(0.0);
                imageFadeIn.setToValue(1.0);
                imageFadeIn.play();
    			
    			TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), cursorView3);
                translateTransition.setByX(width/17.45454545); // Adjust the value as needed
                translateTransition.play();
    		}
    		if(weaponIndex==7){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView9);
                imageFadeIn.setFromValue(1.0);
                imageFadeIn.setToValue(0.0);
                imageFadeIn.play();
                
                FadeTransition imageFadeIn2 = new FadeTransition(Duration.seconds(1), imageView10);
                imageFadeIn2.setFromValue(1.0);
                imageFadeIn2.setToValue(0.0);
                imageFadeIn2.play();
                
                FadeTransition imageFadeIn4 = new FadeTransition(Duration.seconds(1), imageView6);
                imageFadeIn4.setFromValue(1.0);
                imageFadeIn4.setToValue(0.0);
                imageFadeIn4.play();
                
                FadeTransition imageFadeIn3 = new FadeTransition(Duration.seconds(1), imageView11);
                imageFadeIn3.setFromValue(1.0);
                imageFadeIn3.setToValue(0.0);
                imageFadeIn3.play();
                
                FadeTransition imageFadeIn5 = new FadeTransition(Duration.seconds(1), cursorView3);
                imageFadeIn5.setFromValue(1.0);
                imageFadeIn5.setToValue(0.0);
                imageFadeIn5.play();
                
                // Fade in bullet points
                FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(0));
                fadeIn1.setFromValue(0);
                fadeIn1.setToValue(1);
                fadeIn1.play();

                FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(1));
                fadeIn2.setFromValue(0);
                fadeIn2.setToValue(1);
                fadeIn2.play();

                FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(2));
                fadeIn3.setFromValue(0);
                fadeIn3.setToValue(1);
                fadeIn3.play();

                FadeTransition fadeIn4 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(3));
                fadeIn4.setFromValue(0);
                fadeIn4.setToValue(1);
                fadeIn4.play();
                
                bulletPointsBox.setMouseTransparent(false);
                
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1),imageView8 );
                translateTransition.setByX(width/-2.021052632); // Adjust the value as needed
                translateTransition.play();
                
    		}
    		
    		
    		weaponIndex++;
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), weaponText);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                weaponText.setText(weaponTexts[weaponIndex]);
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), weaponText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
    	}
    }
    
    private void updateTitanText(Text titanText){
    	if(titanStart && titanIndex<8){
    		disableEnterKeyForSeconds(2);
    		if(titanIndex==0){
                tutorialText.setText("");
                FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), tutorialText);
                fadeIn2.setFromValue(1.0);
                fadeIn2.setToValue(0.0);
                fadeIn2.play();
                
                FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView13);
                imageFadeIn.setFromValue(0.0);
                imageFadeIn.setToValue(1.0);
                imageFadeIn.play();
                
    		}
    		if(titanIndex==1){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView13);
                imageFadeIn.setFromValue(1.0);
                imageFadeIn.setToValue(0.0);
                imageFadeIn.play();
                
                FadeTransition imageFadeIn2 = new FadeTransition(Duration.seconds(1), imageView14);
                imageFadeIn2.setFromValue(0.0);
                imageFadeIn2.setToValue(1.0);
                imageFadeIn2.play();
    		}
    		if(titanIndex==2){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView14);
                imageFadeIn.setFromValue(1.0);
                imageFadeIn.setToValue(0.0);
                imageFadeIn.play();
                
                FadeTransition imageFadeIn2 = new FadeTransition(Duration.seconds(1), imageView15);
                imageFadeIn2.setFromValue(0.0);
                imageFadeIn2.setToValue(1.0);
                imageFadeIn2.play();
    		}
    		if(titanIndex==3){
    			FadeTransition imageFadeIn = new FadeTransition(Duration.seconds(1), imageView15);
                imageFadeIn.setFromValue(1.0);
                imageFadeIn.setToValue(0.0);
                imageFadeIn.play();
                
                FadeTransition imageFadeIn2 = new FadeTransition(Duration.seconds(1), imageView16);
                imageFadeIn2.setFromValue(0.0);
                imageFadeIn2.setToValue(1.0);
                imageFadeIn2.play();
    		}
    		if(titanIndex==4){
    			FadeTransition imageFadeIn2 = new FadeTransition(Duration.seconds(1), imageView16);
                imageFadeIn2.setFromValue(1.0);
                imageFadeIn2.setToValue(0.0);
                imageFadeIn2.play();
    		}
    		if(titanIndex==7){
                
                // Fade in bullet points
                FadeTransition fadeIn1 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(0));
                fadeIn1.setFromValue(0);
                fadeIn1.setToValue(1);
                fadeIn1.play();

                FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(1));
                fadeIn2.setFromValue(0);
                fadeIn2.setToValue(1);
                fadeIn2.play();

                FadeTransition fadeIn3 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(2));
                fadeIn3.setFromValue(0);
                fadeIn3.setToValue(1);
                fadeIn3.play();

                FadeTransition fadeIn4 = new FadeTransition(Duration.seconds(1), bulletPointsBox.getChildren().get(3));
                fadeIn4.setFromValue(0);
                fadeIn4.setToValue(1);
                fadeIn4.play();
                
                bulletPointsBox.setMouseTransparent(false);
                
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1),imageView12 );
                translateTransition.setByX(width/-2.021052632); // Adjust the value as needed
                translateTransition.play();
                
    		}
    		
    		
    		titanIndex++;
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), titanText);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                titanText.setText(titanTexts[titanIndex]);
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), titanText);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
            fadeOut.play();
    	}
    }
    
    private void transitionToModee(Stage primaryStage) {
        Modee modee = new Modee();
        modee.setMediaPlayer(backgroundMediaPlayer); // Pass the existing media player
        modee.start(primaryStage);

        Scene newScene = modee.getScene();
        
        // Restore the background music volume
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.setVolume(1.0); // Set to full volume
        }

        StackPane newRoot = (StackPane) newScene.getRoot();
        newRoot.setOpacity(0); // Set initial opacity to 0

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
        fadeTransition.setToValue(1); // Set final opacity to 1
        fadeTransition.play();

        primaryStage.setScene(newScene);
        // Center the stage on the screen
        primaryStage.centerOnScreen();
    }
    
    private Text createBulletPoint(String text, Runnable onClick) {
        Text bulletPoint = new Text(text);
        bulletPoint.setFont(Font.font("Arial Black", FontWeight.BOLD, height/54));
        bulletPoint.setFill(Color.WHITE);

        bulletPoint.setOnMouseEntered(event -> {
            ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.3), bulletPoint);
            scaleIn.setToX(1.1);
            scaleIn.setToY(1.1);
            scaleIn.play();
            
            Timeline colorTransition = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(bulletPoint.fillProperty(), Color.WHITE)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(bulletPoint.fillProperty(), Color.LIGHTBLUE))
            );
            colorTransition.play();
        });

        bulletPoint.setOnMouseExited(event -> {
            ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.3), bulletPoint);
            scaleOut.setToX(1.0);
            scaleOut.setToY(1.0);
            scaleOut.play();
            
            Timeline colorTransition = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(bulletPoint.fillProperty(), Color.LIGHTBLUE)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(bulletPoint.fillProperty(), Color.WHITE))
            );
            colorTransition.play();
        });

        bulletPoint.setOnMouseClicked(event -> {
            RotateTransition rotate = new RotateTransition(Duration.seconds(0.3), bulletPoint);
            rotate.setByAngle(360);
            rotate.setAutoReverse(true);
            rotate.setCycleCount(2);
            rotate.setOnFinished(e -> onClick.run());
            rotate.play();
        });

        return bulletPoint;
    }


    public static void main(String[] args) {
    	System.setProperty("prism.allowhidpi", "false"); // Disable high DPI scaling for JavaFX
    	System.setProperty("glass.gtk.uiScale", "1.0");
		launch(args);
    }
}
