package game.gui;

import java.util.List;

import game.leaderboard.FirebaseService;
import game.leaderboard.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {
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
    
    private VBox vbox4;

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
        
        Text numberText5 = new Text(String.valueOf(5));
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

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Image with Aspect Ratio and Rectangles Example");
        primaryStage.show();
        updateLeaderboard();
    }
    
    private void updateLeaderboard() {
        FirebaseService firebaseService = new FirebaseService();

        try {
            // Fetch the top 5 players and their scores from Firebase
            List<Player> top5Players = firebaseService.getTop5AndPlayerRank("", 0); // Replace "" with your player's name

            if (top5Players.size() > 0) {
                player1.setText(top5Players.get(0).getName());
                score1.setText(String.valueOf(top5Players.get(0).getScore()));
            }
            if (top5Players.size() > 1) {
                player2.setText(top5Players.get(1).getName());
                score2.setText(String.valueOf(top5Players.get(1).getScore()));
            }
            if (top5Players.size() > 2) {
                player3.setText(top5Players.get(2).getName());
                score3.setText(String.valueOf(top5Players.get(2).getScore()));
            }
            if (top5Players.size() > 3) {
                player4.setText(top5Players.get(3).getName());
                score4.setText(String.valueOf(top5Players.get(3).getScore()));
            }
            if (top5Players.size() > 4) {
                player5.setText(top5Players.get(4).getName());
                score5.setText(String.valueOf(top5Players.get(4).getScore()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
