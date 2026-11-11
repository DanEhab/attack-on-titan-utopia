package game.gui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.geometry.Pos;

import java.awt.Dimension;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Main extends Application {
    private BackgroundImage backgroundImage;
    private Stage primaryStage;
    private Scene scene;
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
        this.scene = new Scene(root, width/ 1.875, height/ 1.0546875);
        
        
        
        String cursorImagePath = "/game/gui/cursor/cursorHover2-ezgif.com-resize.gif";
        Image cursorImage = new Image(getClass().getResource(cursorImagePath).toString());
        

        

        // Create a cursor from the image and set it for the scene
        scene.setCursor(new ImageCursor(cursorImage)); 
       

        
        String newImagePath = "images/Attack-On-Titan-5-13-2024.png";
        Image newImage = new Image(getClass().getResource(newImagePath).toExternalForm());
        ImageView imageView = new ImageView(newImage);
        imageView.setFitHeight(height/4.251968504);
        imageView.setFitWidth(width/1.875);
        imageView.setPreserveRatio(true);

        
        imageView.setTranslateY(height/-2.571428571);
        
        String imagePath = "images/background4.jpg";
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());


        // Create a BackgroundImage object
        backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)); // Use COVER instead of AUTO

        // Set the BackgroundImage to the root
        root.setBackground(new Background(backgroundImage));
        
        double width1 = (width/9.6);
        double width2 = (width/16);
        double height1 = (height/27);
        double height2 =(height/24);
        double fontSize = height/60;
        double fontSize2 = height/54;
        double num = width/960;
        double num2=diagonal/220.290717;
        
        // Add buttons
        Button button1 = new Button("Story");
        button1.setStyle("-fx-border-width: "+num+"px;-fx-border-color: #ECD591;-fx-background-color: #382E22; -fx-text-fill: #ECD591;-fx-background-radius: "+num2+";-fx-border-radius: "+num2+"px; -fx-font-size: "+fontSize+"px; -fx-font-family: Verdana;");
        button1.setMouseTransparent(false); // Prevent button from consuming mouse events
        applyHoverEffect(button1);
        button1.setMinHeight(height1);
        button1.setMinWidth(width1);
     

        Button button2 = new Button("Play");
        button2.setStyle("-fx-border-width: "+num+"px;-fx-border-color: #B89867;-fx-background-color: #ECD591; -fx-text-fill: #382E22;-fx-background-radius: "+num2+";-fx-border-radius: "+num2+"px; -fx-font-size: "+fontSize2+"px; -fx-font-family: Verdana;");
        button2.setMouseTransparent(false); // Prevent button from consuming mouse events
        applyHoverEffect(button2);
        button2.setMinHeight(height1);
        button2.setMinWidth(width1);
        
        Button button3 = new Button("Credits");
        button3.setStyle("-fx-border-width: "+num+"px;-fx-border-color: #ECD591;-fx-background-color: #382E22; -fx-text-fill: #ECD591;-fx-background-radius: "+num2+";-fx-border-radius: "+num2+"px; -fx-font-size: "+fontSize+"px; -fx-font-family: Verdana;");
        button3.setMouseTransparent(false); // Prevent button from consuming mouse events
        applyHoverEffect(button3);
        button3.setMinHeight(height2);
        button3.setMinWidth(width2);
       

        Button button4 = new Button("Quit");
        button4.setStyle("-fx-border-width: "+num+"px;-fx-border-color: #ECD591;-fx-background-color: #382E22; -fx-text-fill: #ECD591;-fx-background-radius: "+num2+";-fx-border-radius: "+num2+"px; -fx-font-size: "+fontSize+"px; -fx-font-family: Verdana;");
        button4.setMouseTransparent(false); // Prevent button from consuming mouse events
        applyHoverEffect(button4);
        button4.setMinHeight(height2);
        button4.setMinWidth(width2);
        
        button3.setTranslateX(width/-10.10526316);
        button4.setTranslateX(width/10.10526316);
        button3.setTranslateY(height/2.571428571);
        button4.setTranslateY(height/2.571428571);
        
        button3.setOnAction(event ->showCredits ());
        
        button1.setOnAction(event ->{
        	stopBackgroundMusic();
        	transitionToInstructions(primaryStage);
        });
        
        
        VBox buttonBox = new VBox(height/54); // 10 pixels spacing between buttons
        buttonBox.setTranslateY(height/2.769230769);// Move the buttons down by 50 pixels
        buttonBox.getChildren().addAll(button1, button2);
        buttonBox.setAlignment(Pos.CENTER); // Center the buttons horizontally and vertically
        
        Image icon = new Image(getClass().getResource("images/leaderboardIcon.png").toExternalForm());
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(height/13.5);
        iconView.setFitWidth(width/24);
        iconView.setPreserveRatio(true);
        iconView.setTranslateX(width/4.266666667);
        iconView.setTranslateY(height/2.571428571);
        Circle circle = new Circle();
        circle.setRadius(diagonal/57.97124132); // Set the radius of the circle
        circle.setFill(Color.TRANSPARENT); // Set the fill color of the circle
        circle.setStrokeWidth(0); // Set the stroke width of the circle
        circle.setTranslateX(width/4.266666667);
        circle.setTranslateY(height/2.583732057);
        circle.setOnMouseClicked(event ->{
        	Test test = new Test();
        	 Stage secondaryStage = new Stage();
             // Start the Test window in the new Stage
             try {
                 test.start(secondaryStage);  // Pass the new stage to open the Test window
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
        });
        root.getChildren().addAll(buttonBox,button3,button4,imageView,iconView,circle);
       
        
        button4.setOnAction(e -> showCustomConfirmation(primaryStage));
        
        primaryStage.setTitle("Game Background");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Update background size when window is resized
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            updateBackgroundSize(newVal.doubleValue(), scene.getHeight());
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateBackgroundSize(scene.getWidth(), newVal.doubleValue());
        });
        
        button2.setOnMouseClicked(event -> transitionToRules(primaryStage));
        
     
        
     // Load and play background music
        if (backgroundMediaPlayer == null) {
            Media sound = new Media(getClass().getResource("music/Ya rab yeshta8al.mp3").toExternalForm());
            backgroundMediaPlayer = new MediaPlayer(sound);
            backgroundMediaPlayer.setAutoPlay(true);
            backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMediaPlayer.setOnEndOfMedia(() -> backgroundMediaPlayer.seek(Duration.ZERO)); // Restart if ends unexpectedly
        }
    }

    private void updateBackgroundSize(double Width, double Height) {
        backgroundImage = new BackgroundImage(backgroundImage.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(Width, Height, false, false, true, true)); // Use COVER instead of AUTO
        StackPane root = (StackPane) primaryStage.getScene().getRoot();
        root.setBackground(new Background(backgroundImage));
    }
    
    public Scene getScene() {
        return scene;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public void stopBackgroundMusic() {
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.stop();
        }
    }
    
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.backgroundMediaPlayer = mediaPlayer;
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
    
    private void showCustomConfirmation(Stage parentStage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);  // Block input events to other windows
        dialog.initOwner(parentStage);
        dialog.setTitle("Confirm Quit");

        Text question = new Text("Are you sure you want to quit?");

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            parentStage.close();  // Close the main application window
            dialog.close();       // Close the dialog
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> dialog.close());  // Just close the dialog

        VBox dialogVBox = new VBox(height/108, question, yesButton, noButton);
        dialogVBox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogVBox, width/7.68, height/7.2);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    private void showCredits() {
        Stage creditsStage = new Stage();
        creditsStage.initModality(Modality.APPLICATION_MODAL); // Block interactions with other windows
        creditsStage.setTitle("Credits");

        // VBox layout for organized vertical spacing
        VBox layout = new VBox(height/108);
        layout.setStyle("-fx-background-color: WHITE; -fx-padding: 20;"); // Dark background, padding around edges

        // Uniform styling for all text `
        double x = height/51.42857143;
        String textStyle = "-fx-font-size: "+x+"px; -fx-text-fill: Black;"; // White color for all text

        // Creating headers and names
        Text title = new Text("Development Team:");
        double y = height/45;
        title.setStyle(textStyle + "-fx-font-size: "+y+"px; -fx-font-weight: bold;"); // Slightly larger and bold for headers

        Text directors = new Text("               Lead Developer: Daniel Ehab\nOther Developers: Mina Hossam, George Samy");
        directors.setStyle(textStyle);

        Text programming = new Text("Design Team:");
        programming.setStyle(textStyle + "-fx-font-size: "+y+"px; -fx-font-weight: bold;");

        Text programmers = new Text("Game Concept: The CS Department in the GUC\n            Game Mechanics: Daniel Ehab");
        programmers.setStyle(textStyle);

        Text executiveProducer = new Text("Art and Graphics:");
        executiveProducer.setStyle(textStyle + "-fx-font-size: "+y+"px; -fx-font-weight: bold;");

        Text execProducerName = new Text("Character Designs and Artwork: Daniel Ehab");
        execProducerName.setStyle(textStyle);

        Text producer = new Text("Music and Sound Selection:");
        producer.setStyle(textStyle + "-fx-font-size: "+y+"px; -fx-font-weight: bold;");

        Text producerName = new Text("Music: Daniel Ehab");
        producerName.setStyle(textStyle);

        Text art = new Text("Quality Assurance:");
        art.setStyle(textStyle + "-fx-font-size: "+y+"px; -fx-font-weight: bold;");

        Text artists = new Text("                         Front-End Testing: Daniel Ehab\nBack-End Testing: Mina Hossam, Daniel Ehab & George Samy");
        artists.setStyle(textStyle);

        Text soundDesigner = new Text("Writing and Story:");
        soundDesigner.setStyle(textStyle + "-fx-font-size: "+y+"px;;-fx-font-weight: bold;");

        Text soundName = new Text("Story and Dialogues: Daniel Ehab");
        soundName.setStyle(textStyle);

        Text gameDesign = new Text("Back-End Development and Coding:");
        gameDesign.setStyle(textStyle + "-fx-font-size: "+y+"px;-fx-font-weight: bold;");

        Text gameDesigner = new Text("Daniel Ehab\nMina Hossam\nGeorge Samy");
        gameDesigner.setStyle(textStyle);

        Text script = new Text("Special Thanks");
        script.setStyle(textStyle + "-fx-font-size: "+y+"px;-fx-font-weight: bold;");

        Text scripters = new Text("       Dr. Nourhan Ehab\n       Dr.Slim Abdennahder\n       Dr. Ahmed Abdelfattah");
        scripters.setStyle(textStyle);

        // Adding all elements to the layout
        layout.getChildren().addAll(title, directors, programming,
programmers, executiveProducer, execProducerName, producer,
producerName, art, artists, soundDesigner, soundName, gameDesign,
gameDesigner, script, scripters);
        layout.setAlignment(Pos.CENTER); // Center the text blocks

        Scene scene = new Scene(layout, width/3.2, height/1.2); // Set scene size
        creditsStage.setScene(scene);
        creditsStage.showAndWait();} // Display the window and wait until it is closed
    
    private void transitionToModee(Stage primaryStage) {
        Modee modee = new Modee();
        modee.setMediaPlayer(backgroundMediaPlayer); // Pass the existing media player
        modee.start(primaryStage);

        Scene newScene = modee.getScene();
        
        StackPane newRoot = (StackPane) newScene.getRoot();
        newRoot.setOpacity(0); // Set initial opacity to 0

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
        fadeTransition.setToValue(1); // Set final opacity to 1
        fadeTransition.play();
        

        primaryStage.setScene(newScene);
     // Center the stage on the screen
        primaryStage.centerOnScreen();
}
    
    private void transitionToInstructions(Stage primaryStage) {
        Instructions instructions = new Instructions();
        instructions.start(primaryStage);

        Scene newScene = instructions.getScene();
        
        StackPane newRoot = (StackPane) newScene.getRoot();
        newRoot.setOpacity(0); // Set initial opacity to 0

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
        fadeTransition.setToValue(1); // Set final opacity to 1
        fadeTransition.play();
        

        primaryStage.setScene(newScene);
     // Center the stage on the screen
        primaryStage.centerOnScreen();
}
    private void transitionToRules(Stage primaryStage) {
        Rules rules = new Rules();
        rules.setMediaPlayer(backgroundMediaPlayer); // Pass the existing media player
        rules.start(primaryStage);

        Scene newScene = rules.getScene();
        
        StackPane newRoot = (StackPane) newScene.getRoot();
        newRoot.setOpacity(0); // Set initial opacity to 0

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), newRoot);
        fadeTransition.setToValue(1); // Set final opacity to 1
        fadeTransition.play();

        primaryStage.setScene(newScene);
        // Center the stage on the screen
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args){
    	System.setProperty("prism.allowhidpi", "false"); // Disable high DPI scaling for JavaFX
    	System.setProperty("glass.gtk.uiScale", "1.0");
    	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println(screenSize.getWidth());
		launch(args);
    }
}