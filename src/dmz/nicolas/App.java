package dmz.nicolas;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    private Label resultLabel;
    private int buttonOperatorColumn = 4;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Halloweed's Calculator");
        GridPane gridPane = new GridPane();
        AnchorPane anchorPane = new AnchorPane();

        // Column then row
        resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        GridPane.setConstraints(resultLabel, 0, 0);
        GridPane.setRowSpan(resultLabel, 1);
        GridPane.setColumnSpan(resultLabel, 5);

        Button buttonAdd = new Button("+");
        buttonAdd.getStyleClass().add("button-operator");
        GridPane.setConstraints(buttonAdd, buttonOperatorColumn,1);

        Button buttonMin = new Button("-");
        buttonMin.getStyleClass().add("button-operator");
        GridPane.setConstraints(buttonMin, buttonOperatorColumn,2);

        Button buttonDiv = new Button("/");
        buttonDiv.getStyleClass().add("button-operator");
        GridPane.setConstraints(buttonDiv, buttonOperatorColumn,3);

        Button buttonMul = new Button("x");
        buttonMul.getStyleClass().add("button-operator");
        GridPane.setConstraints(buttonMul, buttonOperatorColumn,4);

        Button buttonDone = new Button("=");
        buttonDone.getStyleClass().add("button-operator");
        GridPane.setConstraints(buttonDone, buttonOperatorColumn,5);

        gridPane.getChildren().addAll(resultLabel, buttonAdd, buttonMin, buttonDiv, buttonMul, buttonDone);

        Scene scene = new Scene(gridPane ,416,624);

        // Ensure the CSS file path is correct
        URL cssResource = getClass().getResource("/resources/style.css");
        if (cssResource != null) {
            // Add style
            scene.getStylesheets().add(cssResource.toExternalForm());
        } else {
            System.out.println("CSS file not found!");
        }

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
