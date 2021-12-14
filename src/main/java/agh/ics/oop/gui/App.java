package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import static java.lang.System.exit;

public class App extends Application implements IPositionChangeObserver {
    AbstractWorldMap map;
    AbstractWorldMap map1;
    int size = 50;


    public void init() throws Exception {
        super.init();

    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        VBox parametersBox = new ParametresBox().getParameters();
        Button start = new Button("Press to start");
        VBox firstBox = new VBox(20);
        firstBox.getChildren().addAll(parametersBox, start);
        firstBox.setAlignment(Pos.CENTER);


        HBox secondBox = new HBox(10);
        secondBox.getChildren().addAll(new SideBox(map).getSideBox(), new SideBox(map1).getSideBox());

        //Scene scene = new Scene(firstBox, 800, 700);
        Scene scene = new Scene(secondBox, 800, 700);
        stage.setScene(scene);
        stage.setTitle("Zuzinka did it");
        stage.show();

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}