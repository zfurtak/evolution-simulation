package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMapElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox extends Node {
    private final VBox vbox;

    public GuiElementBox(AbstractWorldMapElement object) throws FileNotFoundException {
        Image image = null;
        try {
            image = new Image(new FileInputStream(object.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        Label label = new Label(object.getPosition().x+", "+object.getPosition().y);
        vbox = new VBox(0); // spacing = 8
        vbox.getChildren().addAll(imageView, label);
        vbox.setPrefWidth(100);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getBox(){
        return vbox;
    }

}


// kod app

/*
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

public class App extends Application implements IPositionChangeObserver{
    public static int moveDelay;
    AbstractWorldMap map;
    private SimulationEngine engine;
    GridPane grid = new GridPane();
    int size = 50;




    public void init() throws Exception {
        super.init();
        try {
            this.map = new ExtendedMap(10);
            Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 2)};
            engine = new SimulationEngine(map, 3, 900);

        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            exit(1);
        }
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        for (LinkedList<Animal> list : map.getAnimals().values()) {
            for(Animal animal : list){
                animal.addObserver(this);
            }
        }
        Button button = new Button("Start");
        TextField textField = new TextField("f f f f f f");


        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                //MoveDirection[] moves =
                //engine.getMoves(moves);
                Thread thread = new Thread(engine);
                thread.start();
            }
        });

        HBox movesInput = new HBox(button, textField);
        movesInput.setSpacing(30);
        VBox mainBox = new VBox(grid, movesInput);
        mainBox.setAlignment(Pos.CENTER);

        Vector2d leftDownCorner = map.findingLowerCorner();
        Vector2d rightUpCorner = map.findingUpperCorner();
        makeGrid();
        Scene scene = new Scene(mainBox, rightUpCorner.x * size + 200 - leftDownCorner.x * size,
                rightUpCorner.y * size + 200 - leftDownCorner.y * size);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Okno aplikacji");
        primaryStage.show();

    }



    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            try {
                makeGrid();
            } catch(FileNotFoundException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        });
    }


private void makeGrid() throws FileNotFoundException {
    grid.setGridLinesVisible(false);
    grid.getChildren().clear();
    grid.getColumnConstraints().clear();
    grid.getRowConstraints().clear();

    Label label1 = new Label("y/x");
    grid.add(label1, 0, 0, 1, 1);
    grid.getColumnConstraints().add(new ColumnConstraints(size));
    grid.getRowConstraints().add(new RowConstraints(size));
    GridPane.setHalignment(label1, HPos.CENTER);
    grid.setGridLinesVisible(true);

    Vector2d leftDownCorner = map.findingLowerCorner();
    Vector2d rightUpCorner = map.findingUpperCorner();

    for (int i = leftDownCorner.x; i <= rightUpCorner.x; i++) {
        Label label2 = new Label(" " + i + " ");
        grid.add(label2, i - leftDownCorner.x + 1, 0, 1, 1);
        grid.getColumnConstraints().add(new ColumnConstraints(size));
        GridPane.setHalignment(label2, HPos.CENTER);
    }

    for (int i = leftDownCorner.y; i <= rightUpCorner.y; i++) {
        Label label3 = new Label(" " + i + " ");
        grid.add(label3, 0, rightUpCorner.y - i + 1, 1, 1);
        grid.getRowConstraints().add(new RowConstraints(size));
        GridPane.setHalignment(label3, HPos.CENTER);
    }

    for (int i = leftDownCorner.x; i <= rightUpCorner.x; i++) {
        for (int j = leftDownCorner.y; j <= rightUpCorner.y; j++) {
            Vector2d pos = new Vector2d(i, j);
            if (map.objectAt(pos) != null) {
                GuiElementBox VBox = new GuiElementBox(map.getAnimals().get(pos).get(0));
                grid.add(VBox.getBox(), pos.x - leftDownCorner.x + 1,
                        rightUpCorner.y - pos.y + 1, 1, 1);
                GridPane.setHalignment(label1, HPos.CENTER);
            }
        }
    }
}
}
 */
