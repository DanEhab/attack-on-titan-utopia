package game.gui;

import java.awt.Dimension;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Modee extends Application {
    private BackgroundImage backgroundImage;
    private Stage primaryStage;
    private Scene scene;
    private int lane;
    private int resources;
    private MediaPlayer backgroundMediaPlayer;
    private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private double width = screenSize.getWidth();
	private double height = screenSize.getHeight();
	private double diagonal = Math.sqrt((height*height)+(width*width));

    @Override
    public void start(Stage primaryStage) {
    	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double diagonal = Math.sqrt((height*height)+(width*width));
        this.primaryStage = primaryStage;
        StackPane root = new StackPane();
        this.scene = new Scene(root, width/1.875, height/1.0546875);
        
        String cursorImagePath = "/game/gui/cursor/cursorHover2-ezgif.com-resize.gif";
        Image cursorImage = new Image(getClass().getResource(cursorImagePath).toString());

        // Create a cursor from the image and set it for the scene
        scene.setCursor(new ImageCursor(cursorImage));

        // Setup image for the background
        String imagePath = "images/background4.jpg";
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

        root.setBackground(new Background(backgroundImage));

        // Adding an image view for another image but not adding buttons
        String newImagePath = "images/Attack-On-Titan-5-13-2024.png";
        Image newImage = new Image(getClass().getResource(newImagePath).toExternalForm());
        ImageView imageView = new ImageView(newImage);
        imageView.setFitHeight(height/4.251968504);
        imageView.setFitWidth(width/1.875);
        imageView.setPreserveRatio(true);
        imageView.setTranslateY(height/-2.571428571);
        root.getChildren().add(imageView);
        
        Text text = new Text("CHOOSE MODE");
        text.setFont(Font.font("Verdana", height/54));  // Match font family and size with the button
        text.setFill(Color.web("#ECD591"));
        text.setTranslateY(height/3.085714286);
        
        String additionalImagePath = "images/return button.png";  // Path to your new image
        Image additionalImage = new Image(getClass().getResource(additionalImagePath).toExternalForm());
        ImageView additionalImageView = new ImageView(additionalImage);
        additionalImageView.setTranslateY(height/2.634146341); // You can adjust this position as needed
        additionalImageView.setTranslateX(width/4.266666666666666667); // You can adjust this position as needed
        additionalImageView.setFitWidth(width/12.8);  // Set the width to a smaller value
        additionalImageView.setFitHeight(height/7.2); // Set the height to a smaller value
        additionalImageView.setPreserveRatio(true);  // Preserve the aspect ratio
         
     // Create a Circle with a specific radius
        Circle circle = new Circle();
        circle.setRadius(diagonal/57.97124132); // Set the radius of the circle
        circle.setFill(Color.TRANSPARENT); // Set the fill color of the circle
        circle.setStrokeWidth(0); // Set the stroke width of the circle
        circle.setTranslateY(height/2.634146341);
        circle.setTranslateX(width/4.26666666666667);
        circle.setOnMouseClicked(event -> transitionToNewScene(primaryStage,backgroundMediaPlayer));

        double z = width/960;
        double a = diagonal/220.290717;
        double b = height/60;
        double c = width/9.6;
        double d = height/27;
        Button button1 = new Button("EASY");
        button1.setStyle("-fx-border-width: "+z+"px;-fx-border-color: #ECD591;-fx-background-color: #382E22; -fx-text-fill: #ECD591;-fx-background-radius: "+a+";-fx-border-radius: "+a+"px; -fx-font-size: "+b+"px; -fx-min-width: "+c+"px; -fx-min-height: "+d+"px;-fx-font-family: Verdana;");
        button1.setMouseTransparent(false);
        button1.setTranslateY(height/2.634146341);
        button1.setTranslateX(width/-17.45454545);
        applyHoverEffect(button1);
        
        
        Button button2 = new Button("HARD");
        button2.setStyle("-fx-border-width: "+z+"px;-fx-border-color: #ECD591;-fx-background-color: #382E22; -fx-text-fill: #ECD591;-fx-background-radius: "+a+";-fx-border-radius: "+a+"px; -fx-font-size: "+b+"px; -fx-min-width: "+c+"px; -fx-min-height: "+d+"px;-fx-font-family: Verdana;");
        button2.setMouseTransparent(false);
        button2.setTranslateY(height/2.634146341);
        button2.setTranslateX(width/17.45454545);
        applyHoverEffect(button2);

        // Add text to the root
        root.getChildren().addAll(text,button1,button2,additionalImageView,circle);
        
        button1.setOnMouseClicked(event ->{
        	backgroundMediaPlayer.stop();
        	toAlfinale(primaryStage);
        });
        button2.setOnMouseClicked(event ->{
        	backgroundMediaPlayer.stop();
        	toHard(primaryStage);
        });


        // Set the scene to the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.backgroundMediaPlayer = mediaPlayer;
    }
    
    private void transitionToNewScene(Stage primaryStage, MediaPlayer mediaPlayer) {
        Main main = new Main();
        main.setMediaPlayer(mediaPlayer); // Pass the existing media player
        main.start(primaryStage);

        Scene newScene = main.getScene();
        
        StackPane newRoot = (StackPane) newScene.getRoot();
        newRoot.setOpacity(0); // Set initial opacity to 0

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
        fadeTransition.setToValue(1); // Set final opacity to 1
        fadeTransition.play();
        

        primaryStage.setScene(newScene);
     // Center the stage on the screen
        primaryStage.centerOnScreen();
}
    
    public Scene getScene() {
        return scene;
    }
    
    private void applyHoverEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), button);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), button);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        button.setOnMouseEntered(event -> scaleIn.play());
        button.setOnMouseExited(event -> scaleOut.play());
    }
    
    
    private void toAlfinale(Stage primaryStage) {
        try {
            ALfinale aLfinale = new ALfinale();
            aLfinale.start(primaryStage);
            Scene newScene = aLfinale.getScene();
            
            StackPane newRoot = (StackPane) newScene.getRoot();
            newRoot.setOpacity(0); // Set initial opacity to 0

            // Set the new scene before starting the transition
            primaryStage.setScene(newScene);
            primaryStage.centerOnScreen(); // Center the stage on the screen
            
            // Fade transition
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
            fadeTransition.setToValue(1); // Set final opacity to 1
            fadeTransition.play();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            System.err.println("Could not transition to the new scene: " + e.getMessage());
        }
    }
    private void toHard(Stage primaryStage) {
        try {
            HARD hard = new HARD();
            hard.start(primaryStage);
            Scene newScene = hard.getScene();
            
            StackPane newRoot = (StackPane) newScene.getRoot();
            newRoot.setOpacity(0); // Set initial opacity to 0

            // Set the new scene before starting the transition
            primaryStage.setScene(newScene);
            primaryStage.centerOnScreen(); // Center the stage on the screen
            
            // Fade transition
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
            fadeTransition.setToValue(1); // Set final opacity to 1
            fadeTransition.play();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            System.err.println("Could not transition to the new scene: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
    	System.setProperty("prism.allowhidpi", "false"); // Disable high DPI scaling for JavaFX
    	System.setProperty("glass.gtk.uiScale", "1.0");
		launch(args);
    }
}