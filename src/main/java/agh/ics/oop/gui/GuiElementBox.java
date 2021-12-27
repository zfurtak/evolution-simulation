package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.AbstractWorldMapElement;
import agh.ics.oop.Animal;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class GuiElementBox extends Node {
    private final VBox vbox;

    public GuiElementBox(AbstractWorldMap map, AbstractWorldMapElement object, int size, Stats stats, boolean flag){
        ImageView imageView = new ImageView(object.getImage());
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        vbox = new VBox(0);
        vbox.getChildren().addAll(imageView);
        vbox.setPrefWidth(100);
        vbox.setAlignment(Pos.CENTER);

        if(flag && object instanceof Animal && ((Animal) object).getGenome().equals(map.mostCommonGenome)){
            vbox.setBackground(new Background(new BackgroundFill(Color.rgb(244, 194, 194), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        EventHandler<MouseEvent> eventHandler = e -> {
            if(object instanceof Animal)
            stats.chosenGenome.setText("Genome of the chosen animal: "+((Animal) object).getGenome().toString());
        };
        vbox.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


    }

    public VBox getBox(){
        return vbox;
    }

}
