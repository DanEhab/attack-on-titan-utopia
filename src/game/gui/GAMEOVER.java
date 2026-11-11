package game.gui;

import game.leaderboard.FirebaseService;

import java.awt.Dimension;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GAMEOVER extends Application {
	
	private Scene scene;
	private int score;
    private MediaPlayer mediaPlayer;
    private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
   	private double width = screenSize.getWidth();
   	private double height = screenSize.getHeight();
   	private double diagonal = Math.sqrt((height*height)+(width*width));
    private Text errorMessage;


    @Override
    public void start(Stage primaryStage) {
    	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double diagonal = Math.sqrt((height*height)+(width*width));
        // Load the background image
        Image backgroundImage = new Image(getClass().getResource("images/GAMEOVER.jpg").toExternalForm());

        // Create an ImageView for the background image
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setFitWidth(width); // Set an initial width to cover the scene width
        backgroundImageView.setFitHeight(height); // Set an initial height to cover the scene height

        // Create a StackPane and add the ImageView to it
        StackPane root = new StackPane(backgroundImageView);
        Label l = new Label("Score: "+score);
        l.setFont(new Font("Arial", height/22.5));
        
        // Change the text color to red
        l.setTextFill(Color.WHITE);
        l.setTranslateX(width/2.4);
        l.setTranslateY(height/-2.4);

        root.getChildren().add(l);

     
        scene = new Scene(root, width, height);
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	// mediaPlayer.stop(); // Stop the background music
            	showNameEntryScreen(primaryStage);            }
        });

        // Set the scene on the primary stage
        primaryStage.setTitle("GAME OVER");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void showNameEntryScreen(Stage primaryStage) {
        StackPane nameEntryRoot = new StackPane();
        nameEntryRoot.setStyle("-fx-background-color: black;");

        Text promptText = new Text("Enter your name for the leaderboard🏆");
        promptText.setFont(new Font("Arial", height / 15));
        promptText.setFill(Color.WHITE);

        Text nameText = new Text("");  // Will display the entered name
        nameText.setFont(new Font("Arial", height / 20));
        nameText.setFill(Color.WHITE);
        nameText.setTranslateY(height / 10);

        // Blinking cursor effect
        Text cursor = new Text("_");
        cursor.setFont(new Font("Arial", height / 20));
        cursor.setFill(Color.WHITE);
        cursor.setTranslateY(height / 10 + 5);
        FadeTransition blink = new FadeTransition(Duration.seconds(0.5), cursor);
        blink.setFromValue(1.0);
        blink.setToValue(0.0);
        blink.setCycleCount(Animation.INDEFINITE);
        blink.play();
        
     // Error message for duplicate name
        errorMessage = new Text("");
        errorMessage.setFont(new Font("Arial", height / 25));
        errorMessage.setFill(Color.RED);
        errorMessage.setTranslateY(height / 5);
        
        nameEntryRoot.getChildren().addAll(promptText, nameText, cursor,errorMessage);

        Scene nameEntryScene = new Scene(nameEntryRoot, width, height);
        StringBuilder playerName = new StringBuilder(); // To collect user input

        nameEntryScene.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                errorMessage.setText(""); // Clear error message on typing
            	playerName.append(event.getText());
                nameText.setText(playerName.toString());
            } else if (event.getCode() == KeyCode.BACK_SPACE && playerName.length() > 0) {
                playerName.deleteCharAt(playerName.length() - 1);
                nameText.setText(playerName.toString());
            } else if (event.getCode() == KeyCode.ENTER) {
                if (!playerName.toString().trim().isEmpty()) {
                    savePlayerScore(playerName.toString().trim(), score,primaryStage);
                    //showLeaderboard(primaryStage);
                }
            }
        });

        primaryStage.setScene(nameEntryScene);
    }

    private void savePlayerScore(String playerName, int score, Stage primaryStage) {
        FirebaseService firebaseService = new FirebaseService();
        try {
            boolean isScoreSubmitted = firebaseService.submitScoreIfUnique(playerName, score);
            if (isScoreSubmitted) {
                showLeaderboard(primaryStage, playerName, score);
            } else {
                // If the name is already taken, you could show an error message or allow the player to re-enter the name
            	errorMessage.setText("Name already taken! Please choose a different name.");            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLeaderboard(Stage primaryStage, String playerName, int score) {
        LeaderboardUI leaderboardUI = new LeaderboardUI(playerName, score);
        try {
            leaderboardUI.start(primaryStage);  // Show the leaderboard
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public Scene getScene(){
    	return this.scene;
    }
    
    public void setScore(int a){
    	this.score=a;
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
    public void setMusic(MediaPlayer a){
    	this.mediaPlayer=a;
    }

    public static void main(String[] args) {
    	System.setProperty("prism.allowhidpi", "false"); // Disable high DPI scaling for JavaFX
    	System.setProperty("glass.gtk.uiScale", "1.0");
    	
		launch(args);
    }
}