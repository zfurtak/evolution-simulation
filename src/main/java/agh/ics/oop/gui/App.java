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
    GridPane mainView = new GridPane();

    public void init() throws Exception {
        super.init();
        this.map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);
        this.map1 = new NotExtendedMap(10, 10, 0.3,2, 10, 1);
        Animal jim = new Animal(map);
        Animal pam = new Animal(map);
        map.placeAnimal(jim);
        map.placeAnimal(pam);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        makeGrid();
        Scene scene = new Scene(mainView, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Zuzinka did it");
        stage.show();

    }

    public void makeGrid() throws FileNotFoundException {
        VBox parametersBox = new ParametresBox().getParameters();
        Button start = new Button("Press to start");
        start.setDefaultButton(true);
        VBox firstBox = new VBox(20);
        firstBox.getChildren().addAll(parametersBox, start);
        firstBox.setAlignment(Pos.CENTER);
        mainView.add(firstBox, 0, 0, 1, 1);
        mainView.setAlignment(Pos.CENTER);

        HBox secondBox = new HBox(10);
        secondBox.getChildren().addAll(new SideBox(map).getSideBox(), new SideBox(map1).getSideBox());

        start.setOnAction(value ->  {
            mainView.getChildren().clear();
            mainView.add(secondBox, 0, 0, 1, 1);
            mainView.setAlignment(Pos.CENTER);
        });

    }



    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}