package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

import static java.lang.System.out;

public class App extends Application {
    AbstractWorldMap map1;
    AbstractWorldMap map2;
    GridPane mainView = new GridPane();

    public void init() throws Exception {
        super.init();
        this.map1 = new NotExtendedMap(10, 10, 0.1, 10, 10, 1);
        this.map2 = new NotExtendedMap(10, 10, 0.1,2, 10, 1);
        Animal jim = new Animal(map1);
        Animal pam = new Animal(map1);
        

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
        VBox parametersBox = new ParametersBox().getParameters();
        Button start = new Button("Press to start");
        start.setDefaultButton(true);
        VBox firstBox = new VBox(20);
        firstBox.getChildren().addAll(parametersBox, start);
        firstBox.setAlignment(Pos.CENTER);
        mainView.add(firstBox, 0, 0, 1, 1);
        mainView.setAlignment(Pos.CENTER);

        HBox secondBox = new HBox(10);
        secondBox.getChildren().addAll(new SideBox(map1).getSideBox(), new SideBox(map2).getSideBox());

        start.setOnAction(value ->  {
            mainView.getChildren().clear();
            mainView.add(secondBox, 0, 0, 1, 1);
            mainView.setAlignment(Pos.CENTER);
        });
    }

}