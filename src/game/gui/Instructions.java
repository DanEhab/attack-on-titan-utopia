package game.gui;

import java.awt.Dimension;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Instructions extends Application{
	
	private BackgroundImage backgroundImage;
    private Stage primaryStage;
    private Scene scene;
    private int pressCount=0;
    private MediaPlayer narrationMediaPlayer;
    private MediaPlayer backgroundMediaPlayer;
    private Circle circle;
    private Label label;
    private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private double width = screenSize.getWidth();
	private double height = screenSize.getHeight();
	private double diagonal = Math.sqrt((height*height)+(width*width));
    private boolean isEnterDisabled = false;  // Flag to track if Enter key is disabled

    @Override
	public void start(Stage primaryStage) {
    	disableEnterKeyForSeconds(3);
    	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double diagonal = Math.sqrt((height*height)+(width*width));
    	this.primaryStage = primaryStage;
        StackPane root = new StackPane();
        this.scene = new Scene(root, width, height);
        Image gifImage = new Image(getClass().getResource("images/sea.gif").toExternalForm());
        ImageView gifImageView = new ImageView(gifImage);
        gifImageView.setFitWidth(width); // Set your desired width
        gifImageView.setFitHeight(height); // Set your desired height
        
        
     // Create a rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setTranslateY(height/-2.460136674);
        rectangle.setTranslateX(width/28.65671642);
        rectangle.setWidth(width/1.066666667); // Width of the rectangle
        rectangle.setHeight(height/6.506024096); // Height of the rectangle
        rectangle.setFill(Color.web("#ECD591"));
        rectangle.setStroke(Color.web("#382E22")); // Border color of the rectangle
        rectangle.setStrokeWidth(width/640); // Border thickness
        

        
        label = new Label();
        label.setFont(new Font("Comic Sans MS", height/30)); // Set font family and size
        label.setStyle("-fx-text-fill: #382E22;"); // Set text color to white
        label.setTranslateY(height/-2.482758621);
        label.setTranslateX(width/24);
        String text = "*Armin*: \"The Titans won't relent. Our walls are all that stand between them and us.\"";
        
        
        Label enter = new Label("Press enter to continue");
        enter.setFont(new Font("Arial", height/43.2)); // Set font family and size
        enter.setStyle("-fx-text-fill: GREY;"); // Set text color to white
        enter.setTranslateY(height/-2.88);
        enter.setTranslateX(width/2.461538462);
        
        
        // Create a DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GREY);
        dropShadow.setRadius(diagonal/220.290717);
        enter.setEffect(dropShadow);

        // Create a Timeline to animate the glow effect
        Timeline timeline2 = new Timeline(
            new KeyFrame(Duration.ZERO, event -> dropShadow.setRadius(diagonal/220.290717)),
            new KeyFrame(Duration.seconds(0.5), event -> dropShadow.setRadius(diagonal/110.1453585)),
            new KeyFrame(Duration.seconds(1), event -> dropShadow.setRadius(diagonal/220.290717))
        );
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
        

        // Create a timeline to animate the text
        Timeline timeline = new Timeline();
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(25 * i), // Delay for each character
                event -> label.setText(text.substring(0, index + 1))
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        // Start the animation
        timeline.play();
        
        // Loading and playing narration audio
        Media armin1 = new Media(getClass().getResource("music/Armin1.mp3").toExternalForm());
        narrationMediaPlayer = new MediaPlayer(armin1);
        narrationMediaPlayer.setAutoPlay(true);
        
        // Adding gif image to the StackPane
        root.getChildren().addAll(gifImageView,rectangle,label,enter);
        
       
     // Loading and playing background music
        Media sound = new Media(getClass().getResource("music/mina.mp3").toExternalForm());
        backgroundMediaPlayer = new MediaPlayer(sound);
        backgroundMediaPlayer.setAutoPlay(true);
        backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMediaPlayer.setOnEndOfMedia(() -> backgroundMediaPlayer.seek(Duration.ZERO)); // Restart if ends unexpectedly

        
        
       // button4.setStyle("-fx-border-width: 2px;-fx-border-color: #ECD591;-fx-background-color: #382E22; -fx-text-fill: #ECD591;-fx-background-radius: 10;-fx-border-radius: 10px; -fx-font-size: 18px; -fx-min-width: 120px; -fx-min-height: 45px;-fx-font-family: Verdana;");


        
        
     // Create a circle
        circle = new Circle();
        circle.setTranslateX(width/-2.211981567);
        circle.setTranslateY(height/-2.465753425);
        circle.setRadius(diagonal/25.32077207); // Radius of the circle
        circle.setFill(Color.BLUE); // Fill color of the circle
        circle.setStroke(Color.web("#382E22"));
        circle.setStrokeWidth(width/640); // Border thickness
        
        Image circleImage = new Image(getClass().getResource("images/Armin.jpg").toExternalForm());
        circle.setFill(new ImagePattern(circleImage)); // Fill the circle with an image



        // Add the circle to the StackPane
        root.getChildren().add(circle);
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !isEnterDisabled) {
                handleEnterPress();
            }
        });
        
        primaryStage.setTitle("Game Background");
        primaryStage.setScene(scene);
        primaryStage.show();
        
}
    
    private void disableEnterKeyForSeconds(int seconds) {
        isEnterDisabled = true;  // Disable Enter key
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> isEnterDisabled = false);  // Re-enable after delay
        pause.play();
    }
    
    private void updateCircleImage(String imagePath) {
        Image circleImage = new Image(getClass().getResource(imagePath).toExternalForm());
        circle.setFill(new ImagePattern(circleImage));
    }
    
    private void updateNarrationMediaPlayer(String audioPath) {
        if (narrationMediaPlayer != null) {
            narrationMediaPlayer.stop();
        }
        Media media = new Media(getClass().getResource(audioPath).toExternalForm());
        narrationMediaPlayer = new MediaPlayer(media);
        narrationMediaPlayer.setAutoPlay(true);
    }
    
    private void handleEnterPress() {
        pressCount++;
        if (pressCount < 8) {
            disableEnterKeyForSeconds(3);  // Disable Enter key for 2 seconds after each press
            updateContent();
        } else {
        	stopBackgroundMusic();
        	transitionToNewScene(primaryStage);
        }
    }
    
    private void updateContent() {
        String[] texts = {
            "*Eren*: \"We've been training for this. It's time to put our skills to the test. \nWe need to fortify our defenses.\"",
            "*Mikasa*: \"Eren's right. We'll set up our arsenal along the lanes. \nEach weapon will serve as a sentinel against the Titans.\"",
            "*Armin*: \"We must be precise. The placement of our weapons is crucial. They need to \ncover every possible approach.\"",
            "*Eren*: \"I'll oversee the distribution of the weapons. We'll make sure there's no weak point \nin our defense.\"",
            "*Mikasa*: \"And I'll be on the front lines, ensuring that any Titan that dares approach will \nmeet their end.\"",
            "*Armin*: \"With our combined efforts, we'll protect our home. Not a single Titan will breach \nthese walls.\"",
            "*Eren*: \"Together, we stand strong. For humanity, for our future, we will not falter.\""
        };

        String[] images = {
            "images/Eren.jpg",
            "images/Mikasa.jpg",
            "images/Armin.jpg",
            "images/Eren.jpg",
            "images/Mikasa.jpg",
            "images/Armin.jpg",
            "images/Eren.jpg"
        };

        String[] audios = {
            "music/Eren1.mp3",
            "music/Mikasa1.mp3",
            "music/Armin2.mp3",
            "music/EREN2.mp3",
            "music/Mikasa2.mp3",
            "music/Armin3.mp3",
            "music/Eren1.mp3"
        };

        int index = (pressCount - 1) % texts.length;
        String text = texts[index];
        String image = images[index];
        String audio = audios[index];

        // Update the label text with animation
        label.setText("");
        Timeline timeline = new Timeline();
        for (int i = 0; i < text.length(); i++) {
            final int indexChar = i;
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(25 * i), // Delay for each character
                event -> label.setText(text.substring(0, indexChar + 1))
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();

        // Update the circle image
        updateCircleImage(image);

     // Update the narration media player
        updateNarrationMediaPlayer(audio);
    }
    
    private void stopBackgroundMusic() {
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.stop();
        }
    }
    
    private void transitionToNewScene(Stage primaryStage) {
        Main main = new Main();
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
    


    public static void main(String[] args){
    	System.setProperty("prism.allowhidpi", "false"); // Disable high DPI scaling for JavaFX
    	System.setProperty("glass.gtk.uiScale", "1.0");
		launch(args);
    }

}
