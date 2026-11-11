package game.gui;

import java.util.List;

import game.leaderboard.FirebaseService;
import game.leaderboard.Player;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LeaderboardUI extends Application {
	private Text player1;
    private Text player2;
    private Text player3;
    private Text player4;
    private Text player5;

    private Text score1;
    private Text score2;
    private Text score3;
    private Text score4;
    private Text score5;
    
    private Text numberText5;
    
    private VBox vbox4;
    private String playerX;
    private int scoreY;

    public LeaderboardUI(String playerX, int scoreY) {
        this.playerX = playerX;
        this.scoreY = scoreY;
    }
    
    @Override
    public void start(Stage primaryStage) {
        // Load the image
        Image image = new Image(getClass().getResource("images/blackHexagons2.jpg").toExternalForm());

        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Set the desired width and height for the ImageView
        imageView.setFitWidth(1508); // Set the width you want
        imageView.setFitHeight(980); // Set the height you want

        // Preserve the aspect ratio of the image
        imageView.setPreserveRatio(true);

        // Optionally, set the smooth property to true for better quality
        imageView.setSmooth(true);

        // Create a VBox to hold the rectangles
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);  // Align rectangles to the center
        
        // Create 5 rectangles with same width and height
        int numberOfRectangles = 5;
        double rectangleWidth = 700;  // Width of each rectangle
        double rectangleHeight = 100; // Height of each rectangle
        double fontSize = 40;
        
        Text numberText1 = new Text(String.valueOf(1));
        numberText1.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        numberText1.setFill(Color.rgb(112, 178, 239)); // Set the color to rgb(112, 178, 239)
        numberText1.setTranslateY(-6);
        
        Text numberText2 = new Text(String.valueOf(2));
        numberText2.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        numberText2.setFill(Color.rgb(112, 178, 239)); // Set the color to rgb(112, 178, 239)
        numberText2.setTranslateY(-1);

        Text numberText3 = new Text(String.valueOf(3));
        numberText3.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        numberText3.setFill(Color.rgb(112, 178, 239)); // Set the color to rgb(112, 178, 239)
        numberText3.setTranslateY(2);
        
        Text numberText4 = new Text(String.valueOf(4));
        numberText4.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        numberText4.setFill(Color.rgb(112, 178, 239)); // Set the color to rgb(112, 178, 239)
        numberText4.setTranslateY(5);
        
        numberText5 = new Text(String.valueOf(5));
        numberText5.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        numberText5.setFill(Color.rgb(112, 178, 239)); // Set the color to rgb(112, 178, 239)
        numberText5.setTranslateY(12);
        
        VBox vbox2 = new VBox(50);
        vbox2.getChildren().addAll(numberText1,numberText2,numberText3,numberText4,numberText5);
        vbox2.setTranslateX(250);
        vbox2.setTranslateY(210);
        
        player1 = new Text("Player 1");
        player1.setFont(Font.font("Arial", FontWeight.BOLD, 33));
        player1.setFill(Color.WHITE);
        player2 = new Text("Player 2");
        player2.setFont(Font.font("Arial", FontWeight.BOLD, 33));
        player2.setFill(Color.WHITE);
        player2.setTranslateY(10);
        player3 = new Text("Player 3");
        player3.setFont(Font.font("Arial", FontWeight.BOLD, 33));
        player3.setFill(Color.WHITE);
        player3.setTranslateY(24);
        player4 = new Text("Player 4");
        player4.setFont(Font.font("Arial", FontWeight.BOLD, 33));
        player4.setFill(Color.WHITE);
        player4.setTranslateY(36);
        player5 = new Text("Player 5");
        player5.setFont(Font.font("Arial", FontWeight.BOLD, 33));
        player5.setFill(Color.WHITE);
        player5.setTranslateY(47);
        VBox vbox3 = new VBox(50);
        vbox3.getChildren().addAll(player1,player2,player3,player4,player5);
        vbox3.setTranslateX(395);
        vbox3.setTranslateY(210);
        
        score1 = new Text(String.valueOf(1000));
        score1.setFont(Font.font("Sans Serif Collection", FontWeight.BOLD, 33));
        score1.setFill(Color.WHITE);
        //score1.setTranslateX(25);
        score1.setTranslateY(-23);
        //score1.setTranslateX(45);
        score2 = new Text(String.valueOf(100));
        score2.setFont(Font.font("Sans Serif Collection", FontWeight.BOLD, 33));
        score2.setFill(Color.WHITE);
        score2.setTranslateY(-13);
        //score2.setTranslateX(34);
        score3 = new Text(String.valueOf(10));
        score3.setFont(Font.font("Sans Serif Collection", FontWeight.BOLD, 33));
        score3.setFill(Color.WHITE);
        score3.setTranslateY(-7);
        //score3.setTranslateX(23);
        score4 = new Text(String.valueOf(7));
        score4.setFont(Font.font("Sans Serif Collection", FontWeight.BOLD, 33));
        score4.setFill(Color.WHITE);
        //score4.setTranslateX(11);
        score4.setTranslateY(-3);
        score5 = new Text(String.valueOf(2));
        score5.setFont(Font.font("Sans Serif Collection", FontWeight.BOLD, 33));
        score5.setFill(Color.WHITE);
        
        vbox4 = new VBox(10);
        vbox4.getChildren().addAll(score1,score2,score3,score4,score5);
        vbox4.setTranslateX(795);
        vbox4.setTranslateY(210);
        for(int i=0;i<5;i++){
        	Text a = (Text)(vbox4.getChildren().get(i));
        	String b = (a.getText());
        	if(b.length()==1){
        		a.setTranslateX(45);
        	}
        	else{
        		if(b.length()==2){
        			a.setTranslateX(34);
        		}
        		else{
        			if(b.length()==3){
        				a.setTranslateX(23);
        			}
        			else{
        				if(b.length()==4){
        					a.setTranslateX(11);
        				}
        			}
        		}
        	}
        }
        
        
        Image avatar1 = new Image(getClass().getResource("images/lead1.png").toExternalForm());
        Image avatar2 = new Image(getClass().getResource("images/lead2.png").toExternalForm());
        Image avatar3 = new Image(getClass().getResource("images/lead3.png").toExternalForm());
        Image avatar4 = new Image(getClass().getResource("images/lead4.png").toExternalForm());
        Image avatar5 = new Image(getClass().getResource("images/lead5.png").toExternalForm());
        ImageView imageView1 = new ImageView(avatar1);
        ImageView imageView2 = new ImageView(avatar2);
        ImageView imageView3 = new ImageView(avatar3);
        ImageView imageView4 = new ImageView(avatar4);
        ImageView imageView5 = new ImageView(avatar5);
        imageView1.setFitHeight(86); // Set the height you want
        imageView2.setFitHeight(88); // Set the height you want
        imageView3.setFitHeight(85); // Set the height you want
        imageView4.setFitHeight(80); // Set the height you want
        imageView5.setFitHeight(70); // Set the height you want
        imageView1.setPreserveRatio(true);
        imageView2.setPreserveRatio(true);
        imageView3.setPreserveRatio(true);
        imageView4.setPreserveRatio(true);
        imageView5.setPreserveRatio(true);
        imageView1.setTranslateX(-235);
        imageView2.setTranslateX(-235);
        imageView3.setTranslateX(-235);
        imageView4.setTranslateX(-235);
        imageView5.setTranslateX(-235);
        imageView5.setTranslateY(198);
        imageView4.setTranslateY(100);
        imageView2.setTranslateY(-100);
        imageView1.setTranslateY(-200);
        Image leaderboard = new Image(getClass().getResource("images/leaderboard.png").toExternalForm());
        ImageView leaderboardView = new ImageView(leaderboard);
        leaderboardView.setTranslateY(-300);

        


        
        for (int i = 1; i <= numberOfRectangles; i++) {
            Rectangle rectangle = new Rectangle(rectangleWidth, rectangleHeight);
            if(i%2 == 0)
            	rectangle.setFill(Color.rgb(52, 89, 129));  // Set the color to rgb(52, 89, 129)
            else
            	rectangle.setFill(Color.rgb(43, 79, 122));  // Set the color to rgb(52, 89, 129)
            rectangle.setOpacity(0.9);
            // Create the number text
            
            vbox.getChildren().add(rectangle);
            
        }

        // Create a layout (StackPane) and add the image and rectangles
        StackPane root = new StackPane(imageView, vbox,vbox2,imageView1,imageView2,imageView3,imageView4,imageView5,vbox3,leaderboardView,vbox4);

        // Create a scene and add the layout to it
        Scene scene = new Scene(root, 1150, 850);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	transitionToNewScene(primaryStage);    }
        });

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Leaderboard");
        primaryStage.show();
        updateLeaderboard();
    }
    
    private void updateLeaderboard() {
        FirebaseService firebaseService = new FirebaseService();

        try {
            // Fetch the top 5 players and their scores from Firebase
            List<Player> top5Players = firebaseService.getTop5AndPlayerRank(playerX, scoreY);

            // Display top 5 players normally
            if (top5Players.size() > 0) {
                updatePlayerScore(player1, score1, top5Players.get(0));
            }
            if (top5Players.size() > 1) {
                updatePlayerScore(player2, score2, top5Players.get(1));
            }
            if (top5Players.size() > 2) {
                updatePlayerScore(player3, score3, top5Players.get(2));
            }
            if (top5Players.size() > 3) {
                updatePlayerScore(player4, score4, top5Players.get(3));
            }

            // Check if playerX is in the top 5
            boolean playerInTop5 = isPlayerInTop5(top5Players, playerX);
            System.out.println("Is playerInTop5? ----> "+playerInTop5);
            if (playerInTop5) {
                // Player X is already in the top 5
                if (top5Players.size() > 4) {
                    updatePlayerScore(player5, score5, top5Players.get(4));
                }
            } else {
                // Player X is not in the top 5; add them to the 5th slot and show their real rank
                Player player = new Player(playerX, scoreY);
                int playerRank = firebaseService.getPlayerRank(firebaseService.getAllPlayers(), playerX);

                player5.setText(player.getName());
                score5.setText(String.valueOf(player.getScore()));
                numberText5.setText(String.valueOf(playerRank)); // Display Player X's actual rank, not just "5"

                // Highlight player 5's name and score
                setGoldenHighlight(player5, score5);
            }

            // Highlight Player X if they are in the top 5
            for (int i = 0; i < top5Players.size(); i++) {
                if (top5Players.get(i).getName().equalsIgnoreCase(playerX)) {
                    highlightPlayer(i);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Align scores based on their length
        alignScoresBasedOnLength();
    }

    

    private void alignScoresBasedOnLength() {
        for (int i = 0; i < 5; i++) {
            Text a = (Text) (vbox4.getChildren().get(i));
            String b = (a.getText());
            if (b.length() == 1) {
                a.setTranslateX(45);
            } else if (b.length() == 2) {
                a.setTranslateX(34);
            } else if (b.length() == 3) {
                a.setTranslateX(23);
            } else if (b.length() == 4) {
                a.setTranslateX(11);
            }
        }
    }

    private boolean isPlayerInTop5(List<Player> top5Players, String playerX) {
    	int i=0;
        for (Player player : top5Players) {
            if (player.getName().equalsIgnoreCase(playerX) && i<5) {
                return true;  // Player is in the top 5
            }
            if(i>=5){
            	return false;
            }
            i++;
        }
        return false;  // Player is not in the top 5
    }

    private void updatePlayerScore(Text playerText, Text scoreText, Player player) {
        playerText.setText(player.getName());
        scoreText.setText(String.valueOf(player.getScore()));
    }

    private void highlightPlayer(int rank) {
        switch (rank) {
            case 0:
                setGoldenHighlight(player1, score1);
                break;
            case 1:
                setGoldenHighlight(player2, score2);
                break;
            case 2:
                setGoldenHighlight(player3, score3);
                break;
            case 3:
                setGoldenHighlight(player4, score4);
                break;
            case 4:
                setGoldenHighlight(player5, score5);
                break;
        }
    }

    private void setGoldenHighlight(Text playerText, Text scoreText) {
        playerText.setFill(Color.rgb(255, 215, 0)); // Golden color for name
        scoreText.setFill(Color.rgb(255, 215, 0));  // Golden color for score
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


    public static void main(String[] args) {
        launch(args);
    }
}