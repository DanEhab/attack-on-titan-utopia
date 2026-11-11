

package game.gui;

import java.awt.Dimension;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class HelloWorld extends Application {
	
	
	
	
	
	
	MediaPlayer mediaPlayer;
    @Override
    public void start(Stage primaryStage) {
        // Creating a StackPane to hold all the elements
        StackPane root = new StackPane();
        
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double diagonal = Math.sqrt((height*height)+(width*width));
        
        // Setting StackPane background color to black
        root.setStyle("-fx-background-color: black;");
        
        Media sound = new Media(getClass().getResource("music/GIANT FOOTSTEPS SOUND EFFECT.mp3").toExternalForm());
        // Loading and playing audio in the background
       
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(1.0);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        
        // Loading gif image
        Image gifImage = new Image(getClass().getResource("images/Attack-On-Titan-5-13-2024 (2).gif").toExternalForm());
        ImageView gifImageView = new ImageView(gifImage);
        gifImageView.setFitWidth(width/2); // Set your desired width
        gifImageView.setFitHeight(height/5.357142857); // Set your desired height
        // Adding gif image to the StackPane
        root.getChildren().add(gifImageView);
        
        
        
        
        // Adding text
        Text text = new Text("Press 'Enter' to start...");
        text.setFont(Font.font("Arial",height/45));
        text.setStyle("-fx-fill: white");
        text.setTranslateY(height/10.8);
        
        
        
        
        
        // Adding text to the StackPane
        root.getChildren().add(text);
        
        // Apply scale transformation to zoom out everything
        root.setScaleX(1); // Scale down to 80%
        root.setScaleY(1); // Scale down to 80%
        

        // Creating the scene and setting it to the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Window");
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                mediaPlayer.stop();
                transitionToNewScene(primaryStage);
            }
        });
        scene.setOnMouseClicked(event -> transitionToNewScene(primaryStage));
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
        mediaPlayer.setMute(true);

        primaryStage.setScene(newScene);
        // Center the stage on the screen
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        System.setProperty("prism.allowhidpi", "false"); // Disable high DPI scaling for JavaFX
        System.setProperty("glass.gtk.uiScale", "1.0");
        launch(args);
    }
}
