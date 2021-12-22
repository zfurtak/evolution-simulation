package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class TopBox {
    HBox mainBox;
    public CustomMap customMap;
    public TopBox(AbstractWorldMap map) throws FileNotFoundException {
        VBox stats = new Stats(map).getStats();
        customMap = new CustomMap(map);
        VBox mapBox = customMap.getCustomMap();
        mainBox = new HBox(30);
        mainBox.getChildren().addAll(stats, mapBox);
        mapBox.setAlignment(Pos.CENTER_RIGHT);
    }

    public HBox getTopBox(){
        return mainBox;
    }


}
