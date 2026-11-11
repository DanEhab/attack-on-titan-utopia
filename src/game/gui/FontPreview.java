package game.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FontPreview extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        // Get all available font families
        for (String fontFamily : Font.getFamilies()) {
        	System.out.println(fontFamily);
            HBox hBox = new HBox();
            Text fontName = new Text(fontFamily);
            fontName.setFont(Font.font("Arial", 20)); // Use a standard font for the font name
            fontName.setStyle("-fx-padding: 10;");

            Text previewText = new Text("The quick brown fox jumps over the lazy dog");
            previewText.setFont(Font.font(fontFamily, 20)); // Set the font family and size for the preview
            previewText.setStyle("-fx-padding: 10;");

            hBox.getChildren().addAll(fontName, previewText);
            root.getChildren().add(hBox);
        }

        Scene scene = new Scene(scrollPane, 800, 600);
        primaryStage.setTitle("Font Family Preview");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
