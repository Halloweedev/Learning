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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class App extends Application {

    private DecimalFormat decimalFormat = new DecimalFormat("#.###");
    private final StringBuilder currentInput = new StringBuilder();
    private double currentTotal = 0;
    private char lastOperator = ' ';
    private Label resultDisplayLabel = new Label("0");
    private boolean newNumber = true;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Halloweed's Calculator");
        GridPane gridPane = new GridPane();
        StackPane stackPane = new StackPane();

        resultDisplayLabel.getStyleClass().add("result-label");
        resultDisplayLabel.setAlignment(Pos.CENTER_RIGHT);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        decimalFormat = new DecimalFormat("#,###", symbols);

        // Column then row
        GridPane.setConstraints(resultDisplayLabel, 0, 0);
        GridPane.setRowSpan(resultDisplayLabel, 1);
        GridPane.setColumnSpan(resultDisplayLabel, 5);

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

        Button buttonClear = new Button("C");
        buttonClear.getStyleClass().add("button-zero");
        GridPane.setConstraints(buttonClear, 0, 1);
        GridPane.setColumnSpan(buttonClear, 2);
        buttonClear.setOnAction(actionEvent -> clearRegistry());

        Button buttonHistory = new Button("H");
        buttonHistory.getStyleClass().add("button-numbers");
        GridPane.setConstraints(buttonHistory, 2, 1);


        // for loop for operator buttons
        for(int i = 0; i <= 4; i++) {
            Button buttonOperators = new Button(String.valueOf(i));

            double num1;
            double num2;

            if(i == 0) {
                buttonOperators.setText("+");
            }

            if(i == 1) buttonOperators.setText("-");
            if(i == 2) buttonOperators.setText("/");
            if(i == 3) buttonOperators.setText("x");
            if(i == 4) {
                buttonOperators.setText("=");
            }

            buttonOperators.getStyleClass().add("button-operator");
            gridPane.add(buttonOperators, 4, 1 + i); // 4 columns and start 1 row lower for the right placement

        }


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

            //Gets text from buttons to resultDisplayLabel
            final String finalI = buttonNumber.getText();
            buttonNumber.setOnAction(event -> {
                if (resultDisplayLabel.getText().equals("0")) {
                    resultDisplayLabel.setText(finalI);
                } else {
                    resultDisplayLabel.setText(resultDisplayLabel.getText() + finalI);
                }
                // Format the number with thousands separators
                try {
                    Number number = decimalFormat.parse(resultDisplayLabel.getText());
                    resultDisplayLabel.setText(decimalFormat.format(number));
                } catch (ParseException e) {
                    // Handle the case where the text is not a valid number
                    resultDisplayLabel.setText(resultDisplayLabel.getText());
                }
            });


            buttonNumber.getStyleClass().add("button-numbers");
            gridPane.add(buttonNumber, col, row + 2); // +2 to start two rows under the resultDisplayLabel

        }

        gridPane.getChildren().addAll(resultDisplayLabel, buttonClear, buttonHistory);
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

    private void clearRegistry() {
        currentInput.setLength(0);
        currentTotal = 0.0;
        lastOperator = ' ';
        newNumber = true;
        updateDisplay();
    }

    private void updateDisplay() {
        if (currentInput.length() > 0) {
            resultDisplayLabel.setText(currentInput.toString());
        } else {
            // Format the total to remove unnecessary decimal places
            if (currentTotal == (long) currentTotal) {
                resultDisplayLabel.setText(String.valueOf((long) currentTotal));
            } else {
                resultDisplayLabel.setText(String.valueOf(currentTotal));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
