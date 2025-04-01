package dmz.nicolas;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Halloweed's Calculator");
        GridPane gridPane = new GridPane();
        StackPane stackPane = new StackPane();
        // Column then row
        Label resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        GridPane.setConstraints(resultLabel, 0, 0);
        GridPane.setRowSpan(resultLabel, 1);
        GridPane.setColumnSpan(resultLabel, 5);

        // Stackpane overlay buttons
        Button buttonOverlay = new Button("Overlay");
        StackPane.setAlignment(buttonOverlay, Pos.TOP_LEFT);
        StackPane.setMargin(buttonOverlay, new Insets(10, 0, 0, 10));
        Button buttonOverlay2 = new Button("Overlay2");
        StackPane.setAlignment(buttonOverlay2, Pos.TOP_LEFT);
        StackPane.setMargin(buttonOverlay2, new Insets(10, 0, 0, 10));
        buttonOverlay2.setVisible(false);
        // Set always on top
        buttonOverlay.setOnAction(event -> {
            primaryStage.setAlwaysOnTop(true);
            buttonOverlay.setVisible(false);
            buttonOverlay2.setVisible(true);
        });
        // Remove always on top
        buttonOverlay2.setOnAction(event -> {
            primaryStage.setAlwaysOnTop(false);
            buttonOverlay2.setVisible(false);
            buttonOverlay.setVisible(true);
        });

            // GridPane buttons
        Button buttonAdd = new Button("+");
        buttonAdd.getStyleClass().add("button-operator");
        int buttonOperatorColumn = 4;
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

        Button buttonClear = new Button("C");
        buttonClear.getStyleClass().add("button-zero");
        GridPane.setConstraints(buttonClear, 0, 1);
        GridPane.setColumnSpan(buttonClear, 2);

        Button buttonHistory = new Button("H");
        buttonHistory.getStyleClass().add("button-numbers");
        GridPane.setConstraints(buttonHistory, 2, 1);
        // GridPane buttons finish

        // Create the number buttons
        for(int i = 0; i <= 10; i++) { // 11 buttons, 9 for the numpads, 1 for zero, 1 for comma
            Button buttonNumber = new Button(String.valueOf(i));
            int index = i - 1; // To make sure we start at the right position on the grid, otherwise the grid is offset by 1
            int row = index / 3; // // Determine row (starting from bottom)
            int col = index % 3;

            // Adds a zero button
            if(i == 0) {
                col = 0;
                row = 3;
                buttonNumber.getStyleClass().add("button-zero");
                GridPane.setRowSpan(buttonNumber, 1);
                GridPane.setColumnSpan(buttonNumber, 2);
            }
            // Adds a comma button
            if(i == 10) {
                col = 2;
                row = 3;
                buttonNumber.setText(",");
                GridPane.setRowSpan(buttonNumber, 1);
            }

            buttonNumber.getStyleClass().add("button-numbers");
            gridPane.add(buttonNumber, col, row + 2); // +2 to start two rows under the resultLabel

        }

        gridPane.getChildren().addAll(resultLabel, buttonAdd, buttonMin, buttonDiv, buttonMul, buttonDone, buttonClear, buttonHistory);
        stackPane.getChildren().addAll(gridPane, buttonOverlay, buttonOverlay2);

        Scene scene = new Scene(stackPane ,416,624);

        // Ensure the CSS file path is correct
        URL cssResource = getClass().getResource("/resources/style.css");
        if (cssResource != null) {
            // Add style
            scene.getStylesheets().add(cssResource.toExternalForm());
        } else {
            System.out.println("CSS file not found!");
        }
        // Initialize scene
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
