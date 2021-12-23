package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMapElement;
import agh.ics.oop.Animal;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


public class GuiElementBox extends Node {
    private final VBox vbox;

    public GuiElementBox(AbstractWorldMapElement object, int size, Stats stats){
        ImageView imageView = new ImageView(object.getImage());
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        vbox = new VBox(0);
        vbox.getChildren().addAll(imageView);
        vbox.setPrefWidth(100);
        vbox.setAlignment(Pos.CENTER);

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
