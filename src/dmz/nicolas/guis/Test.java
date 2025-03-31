package dmz.nicolas.guis;

import com.sun.glass.events.MouseEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();

        // Set up the scene
        Scene scene = new Scene(root, 400, 300);

        // Create a button
        Button button = new Button("Primary button");
        button.getStyleClass().add("button-primary");

        // Bind the button's X position to center it horizontally
        button.layoutXProperty().bind(
                root.widthProperty().subtract(button.widthProperty()).divide(2)
        );

        // Add action to the button if visible
        if (button.isVisible())
        {
            button.setOnAction(actionEvent ->
                    System.out.println("Visible"));
        }

        // Add the button to the Pane
        root.getChildren().add(button);


        // Ensure the CSS file path is correct
        URL cssResource = getClass().getResource("/resources/style.css");
        if (cssResource != null) {
            // Add style
            scene.getStylesheets().add(cssResource.toExternalForm());
        } else {
            System.out.println("CSS file not found!");
        }


        // Set up the stage
        primaryStage.setTitle("Just testing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
