package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMapElement;
import agh.ics.oop.Animal;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;


public class GuiElementBox extends Node {
    private final VBox vbox;

    public GuiElementBox(AbstractWorldMapElement object, int size){
        ImageView imageView = new ImageView(object.getImage());
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        vbox = new VBox(0);
        vbox.getChildren().addAll(imageView);
        vbox.setPrefWidth(100);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getBox(){
        return vbox;
    }

}
