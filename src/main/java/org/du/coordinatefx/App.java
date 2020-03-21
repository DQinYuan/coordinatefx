package org.du.coordinatefx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


public class App extends Application {

    private static class Delta {
        double x, y;
    }

    @FXML
    private BorderPane ppp;
    @FXML
    private TextField tf;
    @FXML
    private Label layoutX;
    @FXML
    private Label layoutY;
    @FXML
    private Label translateX;
    @FXML
    private Label translateY;
    @FXML
    private Label subLayoutX;
    @FXML
    private Label subLayoutY;
    @FXML
    private Label subTranslateX;
    @FXML
    private Label subTranslateY;

    @FXML
    private void initialize() {
        drugable(ppp);
        listenLabel(ppp.layoutXProperty(), layoutX);
        listenLabel(ppp.layoutYProperty(), layoutY);
        listenLabel(ppp.translateXProperty(), translateX);
        listenLabel(ppp.translateYProperty(), translateY);
        listenLabel(tf.layoutXProperty(), subLayoutX);
        listenLabel(tf.layoutYProperty(), subLayoutY);
        listenLabel(tf.translateXProperty(), subTranslateX);
        listenLabel(tf.translateYProperty(), subTranslateY);
    }

    private void listenLabel(ObservableValue obv, Label display) {
        display.setText(obv.getValue().toString());

        obv.addListener((observable, oldValue, newValue) -> {
            display.setText(newValue.toString());
        });
    }

    private void drugable(Region region) {
        final Delta dragDelta = new Delta();
        region.setOnMousePressed(mouseEvent -> {
            dragDelta.x = region.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = region.getTranslateY() - mouseEvent.getSceneY();

            region.setCursor(Cursor.NONE);
            region.toFront();
        });
        region.setOnMouseReleased(mouseEvent -> region.setCursor(Cursor.HAND));
        region.setOnMouseDragged(mouseEvent -> {
            double translateX = mouseEvent.getSceneX() + dragDelta.x;
            double translateY = mouseEvent.getSceneY() + dragDelta.y;

            region.setTranslateX(translateX);

            region.setTranslateY(translateY);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane =
                FXMLLoader.load(getClass().getResource("App.fxml"));
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
